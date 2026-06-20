package integrado.prog2.ui;

import integrado.prog2.entities.Categoria;
import integrado.prog2.entities.DetallePedido;
import integrado.prog2.entities.Pedido;
import integrado.prog2.entities.Producto;
import integrado.prog2.entities.Usuario;
import integrado.prog2.enums.Estado;
import integrado.prog2.enums.FormaPago;
import integrado.prog2.enums.Rol;
import integrado.prog2.service.CategoriaService;
import integrado.prog2.service.PedidoService;
import integrado.prog2.service.ProductoService;
import integrado.prog2.service.UsuarioService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Menú principal de consola. Solo se encarga de la interacción con el usuario
// (Scanner, prints) y delega toda la lógica a los Services.
public class Menu {

    private final Scanner scanner = new Scanner(System.in);

    private final CategoriaService categoriaService;
    private final ProductoService productoService;
    private final UsuarioService usuarioService;
    private final PedidoService pedidoService;

    public Menu(CategoriaService categoriaService,
                ProductoService productoService,
                UsuarioService usuarioService,
                PedidoService pedidoService) {
        this.categoriaService = categoriaService;
        this.productoService = productoService;
        this.usuarioService = usuarioService;
        this.pedidoService = pedidoService;
    }

    public void iniciar() {
        boolean salir = false;
        while (!salir) {
            System.out.println();
            System.out.println("=== SISTEMA DE PEDIDOS (FOOD STORE) ===");
            System.out.println("1. Categorías");
            System.out.println("2. Productos");
            System.out.println("3. Usuarios");
            System.out.println("4. Pedidos");
            System.out.println("0. Salir");
            System.out.print("Seleccione: ");

            String input = scanner.nextLine();
            switch (input) {
                case "1": menuCategorias(); break;
                case "2": menuProductos(); break;
                case "3": menuUsuarios(); break;
                case "4": menuPedidos(); break;
                case "0": salir = true; break;
                default: System.out.println("Opción inválida.");
            }
        }
        System.out.println("¡Hasta luego!");
    }

    private void menuCategorias() {
        boolean salir = false;
        while (!salir) {
            System.out.println();
            System.out.println("=== CATEGORIAS ===");
            System.out.println("1. Listar");
            System.out.println("2. Crear");
            System.out.println("3. Editar");
            System.out.println("4. Eliminar");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");

            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    for (Categoria c : categoriaService.listar()) {
                        System.out.println(c);
                    }
                    break;
                case "2":
                    try {
                        String nombre = leerTexto("Ingrese el nombre de la categoría: ");
                        String descripcion = leerTexto("Ingrese la descripción de la categoría: ");
                        Categoria creada = categoriaService.crear(nombre, descripcion);
                        System.out.println("Categoría creada con ID " + creada.getId() + ".");
                    } catch (RuntimeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case "3":
                    try {
                        Long id = leerLong("Ingrese el ID de la categoría a editar: ");
                        if (id == null) { System.out.println("Operación cancelada."); break; }
                        String nuevoNombre = leerTexto("Ingrese el nuevo nombre (vacío para no cambiar): ");
                        String nuevaDescripcion = leerTexto("Ingrese la nueva descripción (vacío para no cambiar): ");
                        Categoria categoriaEditada = categoriaService.editar(id, nuevoNombre, nuevaDescripcion);
                        System.out.println("Categoría editada: " + categoriaEditada);
                    } catch (RuntimeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case "4":
                    try {
                        Long idEliminar = leerLong("Ingrese el ID de la categoría a eliminar: ");
                        if (idEliminar == null) { System.out.println("Operación cancelada."); break; }
                        String confirmacion = leerTexto("¿Está seguro que desea eliminar la categoría con ID " + idEliminar + "? (s/n): ");
                        if (confirmacion.equalsIgnoreCase("s")) {
                            categoriaService.eliminar(idEliminar);
                            System.out.println("Categoría eliminada.");
                        } else {
                            System.out.println("Eliminación cancelada.");
                        }
                    } catch (RuntimeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case "0": salir = true; break;
                default: System.out.println("Opción inválida.");
            }
        }
    }

    private void menuProductos() {
        boolean salir = false;
        while (!salir) {
            System.out.println();
            System.out.println("=== PRODUCTOS ===");
            System.out.println("1. Listar");
            System.out.println("2. Crear");
            System.out.println("3. Editar");
            System.out.println("4. Eliminar");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");

            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    for (Producto p : productoService.listar()) {
                        System.out.println(p);
                    }
                    break;
                case "2":
                    try {
                        System.out.println("--- Categorías disponibles ---");
                        for (Categoria c : categoriaService.listar()) {
                            System.out.println(c);
                        }
                        String nombre = leerTexto("Ingrese el nombre del producto: ");
                        String descripcion = leerTexto("Ingrese la descripción: ");
                        Double precio = leerDouble("Ingrese el precio: ");
                        if (precio == null) { System.out.println("Operación cancelada."); break; }
                        Integer stock = leerEntero("Ingrese el stock: ");
                        if (stock == null) { System.out.println("Operación cancelada."); break; }
                        String imagen = leerTexto("Ingrese la imagen (nombre o URL): ");
                        boolean disponible = leerTexto("¿Está disponible? (s/n): ").equalsIgnoreCase("s");
                        Long categoriaId = leerLong("Ingrese el ID de la categoría: ");
                        if (categoriaId == null) { System.out.println("Operación cancelada."); break; }
                        Producto creado = productoService.crear(nombre, descripcion, precio, stock, imagen, disponible, categoriaId);
                        System.out.println("Producto creado con ID " + creado.getId() + ".");
                    } catch (RuntimeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case "3":
                    try {
                        Long id = leerLong("Ingrese el ID del producto a editar: ");
                        if (id == null) { System.out.println("Operación cancelada."); break; }
                        String nombre = leerTexto("Nuevo nombre (vacío para no cambiar): ");
                        String descripcion = leerTexto("Nueva descripción (vacío para no cambiar): ");
                        Double precio = leerDoubleOpcional("Nuevo precio (vacío para no cambiar): ");
                        Integer stock = leerEnteroOpcional("Nuevo stock (vacío para no cambiar): ");
                        String imagen = leerTexto("Nueva imagen (vacío para no cambiar): ");
                        String dispInput = leerTexto("¿Disponible? (s/n, vacío para no cambiar): ");
                        Boolean disponible = dispInput.isBlank() ? null : dispInput.equalsIgnoreCase("s");
                        Long categoriaId = leerLongOpcional("Nuevo ID de categoría (vacío para no cambiar): ");
                        Producto editado = productoService.editar(id, nombre, descripcion, precio, stock, imagen, disponible, categoriaId);
                        System.out.println("Producto actualizado: " + editado);
                    } catch (RuntimeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case "4":
                    try {
                        Long id = leerLong("Ingrese el ID del producto a eliminar: ");
                        if (id == null) { System.out.println("Operación cancelada."); break; }
                        String confirmacion = leerTexto("¿Confirma la eliminación del producto " + id + "? (s/n): ");
                        if (confirmacion.equalsIgnoreCase("s")) {
                            productoService.eliminar(id);
                            System.out.println("Producto eliminado.");
                        } else {
                            System.out.println("Eliminación cancelada.");
                        }
                    } catch (RuntimeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case "0": salir = true; break;
                default: System.out.println("Opción inválida.");
            }
        }
    }

    private void menuUsuarios() {
        boolean salir = false;
        while (!salir) {
            System.out.println();
            System.out.println("=== USUARIOS ===");
            System.out.println("1. Listar");
            System.out.println("2. Crear");
            System.out.println("3. Editar");
            System.out.println("4. Eliminar");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");

            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    for (Usuario u : usuarioService.listar()) {
                        System.out.println(u);
                    }
                    break;
                case "2":
                    try {
                        String nombre = leerTexto("Ingrese el nombre: ");
                        String apellido = leerTexto("Ingrese el apellido: ");
                        String mail = leerTexto("Ingrese el mail: ");
                        String celular = leerTexto("Ingrese el celular: ");
                        String contrasena = leerTexto("Ingrese la contraseña: ");
                        Rol rol = leerRol();
                        if (rol == null) { System.out.println("Operación cancelada."); break; }
                        Usuario creado = usuarioService.crear(nombre, apellido, mail, celular, contrasena, rol);
                        System.out.println("Usuario creado con ID " + creado.getId() + ".");
                    } catch (RuntimeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case "3":
                    try {
                        Long id = leerLong("Ingrese el ID del usuario a editar: ");
                        if (id == null) { System.out.println("Operación cancelada."); break; }
                        String nombre = leerTexto("Nuevo nombre (vacío para no cambiar): ");
                        String apellido = leerTexto("Nuevo apellido (vacío para no cambiar): ");
                        String mail = leerTexto("Nuevo mail (vacío para no cambiar): ");
                        String celular = leerTexto("Nuevo celular (vacío para no cambiar): ");
                        String contrasena = leerTexto("Nueva contraseña (vacío para no cambiar): ");
                        Usuario editado = usuarioService.editar(id, nombre, apellido, mail, celular, contrasena, null);
                        System.out.println("Usuario actualizado: " + editado);
                    } catch (RuntimeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case "4":
                    try {
                        Long id = leerLong("Ingrese el ID del usuario a eliminar: ");
                        if (id == null) { System.out.println("Operación cancelada."); break; }
                        String confirmacion = leerTexto("¿Confirma la eliminación del usuario " + id + "? (s/n): ");
                        if (confirmacion.equalsIgnoreCase("s")) {
                            usuarioService.eliminar(id);
                            System.out.println("Usuario eliminado.");
                        } else {
                            System.out.println("Eliminación cancelada.");
                        }
                    } catch (RuntimeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case "0": salir = true; break;
                default: System.out.println("Opción inválida.");
            }
        }
    }

    private void menuPedidos() {
        boolean salir = false;
        while (!salir) {
            System.out.println();
            System.out.println("=== PEDIDOS ===");
            System.out.println("1. Listar");
            System.out.println("2. Crear");
            System.out.println("3. Actualizar estado/forma de pago");
            System.out.println("4. Eliminar");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");

            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    for (Pedido p : pedidoService.listar()) {
                        System.out.println(p);
                        for (DetallePedido d : p.getDetalles()) {
                            System.out.println("    " + d);
                        }
                    }
                    break;
                case "2":
                    try {
                        System.out.println("--- Usuarios disponibles ---");
                        for (Usuario u : usuarioService.listar()) {
                            System.out.println(u);
                        }
                        Long usuarioId = leerLong("Ingrese el ID del usuario: ");
                        if (usuarioId == null) { System.out.println("Operación cancelada."); break; }
                        FormaPago formaPago = leerFormaPago();
                        if (formaPago == null) { System.out.println("Operación cancelada."); break; }

                        System.out.println("--- Productos disponibles ---");
                        for (Producto p : productoService.listar()) {
                            System.out.println(p);
                        }
                        List<PedidoService.DetalleInput> items = new ArrayList<>();
                        boolean agregando = true;
                        while (agregando) {
                            String productoInput = leerTexto("ID de producto a agregar (o 'fin' para terminar): ");
                            if (productoInput.equalsIgnoreCase("fin")) {
                                agregando = false;
                                break;
                            }
                            Long productoId;
                            try {
                                productoId = Long.parseLong(productoInput);
                            } catch (NumberFormatException e) {
                                System.out.println("ID inválido, intente de nuevo.");
                                continue;
                            }
                            Integer cantidad = leerEntero("Cantidad: ");
                            if (cantidad == null) {
                                System.out.println("Cantidad inválida, ese ítem no se agregó.");
                                continue;
                            }
                            items.add(new PedidoService.DetalleInput(productoId, cantidad));
                        }

                        if (items.isEmpty()) {
                            System.out.println("No se agregó ningún producto. Pedido cancelado.");
                            break;
                        }

                        Pedido creado = pedidoService.crear(usuarioId, formaPago, items);
                        System.out.println("Pedido creado con ID " + creado.getId() + ". Total: $" + creado.getTotal());
                    } catch (RuntimeException e) {
                        System.out.println("Error: " + e.getMessage());
                        System.out.println("No se creó el pedido.");
                    }
                    break;
                case "3":
                    try {
                        Long id = leerLong("Ingrese el ID del pedido: ");
                        if (id == null) { System.out.println("Operación cancelada."); break; }
                        Estado nuevoEstado = leerEstado();
                        FormaPago nuevaFormaPago = leerFormaPago();
                        Pedido actualizado = pedidoService.actualizarEstadoYFormaPago(id, nuevoEstado, nuevaFormaPago);
                        System.out.println("Pedido actualizado: " + actualizado);
                    } catch (RuntimeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case "4":
                    try {
                        Long id = leerLong("Ingrese el ID del pedido a eliminar: ");
                        if (id == null) { System.out.println("Operación cancelada."); break; }
                        String confirmacion = leerTexto("¿Confirma la eliminación del pedido " + id + "? (s/n): ");
                        if (confirmacion.equalsIgnoreCase("s")) {
                            pedidoService.eliminar(id);
                            System.out.println("Pedido eliminado.");
                        } else {
                            System.out.println("Eliminación cancelada.");
                        }
                    } catch (RuntimeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case "0": salir = true; break;
                default: System.out.println("Opción inválida.");
            }
        }
    }

    // --- Wrappers de lectura: centralizan el parseo de Scanner para no repetir try/catch en cada prompt ---

    private Integer leerEntero(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            System.out.println("Debe ingresar un número entero válido.");
            return null;
        }
    }

    private Long leerLong(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        try {
            return Long.parseLong(input.trim());
        } catch (NumberFormatException e) {
            System.out.println("Debe ingresar un ID numérico válido.");
            return null;
        }
    }

    private Double leerDouble(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        try {
            return Double.parseDouble(input.trim());
        } catch (NumberFormatException e) {
            System.out.println("Debe ingresar un número válido.");
            return null;
        }
    }

    private String leerTexto(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    // Variantes "opcionales" para los formularios de edición: vacío significa "no cambiar este campo",
    // y se distingue de un valor mal escrito (que sí avisa y tampoco cambia el campo).
    private Double leerDoubleOpcional(String prompt) {
        String input = leerTexto(prompt);
        if (input.isBlank()) return null;
        try {
            return Double.parseDouble(input.trim());
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido, no se actualizará ese campo.");
            return null;
        }
    }

    private Integer leerEnteroOpcional(String prompt) {
        String input = leerTexto(prompt);
        if (input.isBlank()) return null;
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido, no se actualizará ese campo.");
            return null;
        }
    }

    private Long leerLongOpcional(String prompt) {
        String input = leerTexto(prompt);
        if (input.isBlank()) return null;
        try {
            return Long.parseLong(input.trim());
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido, no se actualizará ese campo.");
            return null;
        }
    }

    private Rol leerRol() {
        System.out.println("1. ADMIN");
        System.out.println("2. USUARIO");
        Integer opcion = leerEntero("Seleccione el rol: ");
        if (opcion == null) return null;
        return switch (opcion) {
            case 1 -> Rol.ADMIN;
            case 2 -> Rol.USUARIO;
            default -> {
                System.out.println("Rol inválido.");
                yield null;
            }
        };
    }

    private FormaPago leerFormaPago() {
        System.out.println("0. No cambiar");
        System.out.println("1. TARJETA");
        System.out.println("2. TRANSFERENCIA");
        System.out.println("3. EFECTIVO");
        Integer opcion = leerEntero("Seleccione la forma de pago: ");
        if (opcion == null) return null;
        return switch (opcion) {
            case 0 -> null;
            case 1 -> FormaPago.TARJETA;
            case 2 -> FormaPago.TRANSFERENCIA;
            case 3 -> FormaPago.EFECTIVO;
            default -> {
                System.out.println("Opción inválida, no se cambiará la forma de pago.");
                yield null;
            }
        };
    }

    private Estado leerEstado() {
        System.out.println("0. No cambiar");
        System.out.println("1. PENDIENTE");
        System.out.println("2. CONFIRMADO");
        System.out.println("3. TERMINADO");
        System.out.println("4. CANCELADO");
        Integer opcion = leerEntero("Seleccione el estado: ");
        if (opcion == null) return null;
        return switch (opcion) {
            case 0 -> null;
            case 1 -> Estado.PENDIENTE;
            case 2 -> Estado.CONFIRMADO;
            case 3 -> Estado.TERMINADO;
            case 4 -> Estado.CANCELADO;
            default -> {
                System.out.println("Opción inválida, no se cambiará el estado.");
                yield null;
            }
        };
    }
}
