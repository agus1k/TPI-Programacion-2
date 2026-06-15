package integrado.prog2.ui;

import integrado.prog2.service.CategoriaService;
import integrado.prog2.service.PedidoService;
import integrado.prog2.service.ProductoService;
import integrado.prog2.service.UsuarioService;

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
        // TODO: bucle del menú principal hasta que el usuario elija 0 (Salir).
        //  Imprimir opciones, leer entero con validación (try/catch NumberFormatException),
        //  y derivar a los submenús (menuCategorias, menuProductos, menuUsuarios, menuPedidos).
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
            // TODO: parsear input y manejar opciones inválidas.
            switch (input) {
                case "1" -> menuCategorias();
                case "2" -> menuProductos();
                case "3" -> menuUsuarios();
                case "4" -> menuPedidos();
                case "0" -> salir = true;
                default -> System.out.println("Opción inválida.");
            }
        }
        System.out.println("¡Hasta luego!");
    }

    private void menuCategorias() {
        // TODO: submenú 1.Listar 2.Crear 3.Editar 4.Eliminar 0.Volver
        //  Usar categoriaService y mostrar mensajes claros (éxito / error).
    }

    private void menuProductos() {
        // TODO: ídem para Productos (productoService).
    }

    private void menuUsuarios() {
        // TODO: ídem para Usuarios (usuarioService).
    }

    private void menuPedidos() {
        // TODO: ídem para Pedidos (pedidoService).
        //  En "Crear pedido": pedir usuario, luego loop pidiendo productoId + cantidad
        //  hasta que el usuario diga "fin", y pasarle la lista a pedidoService.crear(...).
    }
}
