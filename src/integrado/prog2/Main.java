package integrado.prog2;

import integrado.prog2.enums.Rol;
import integrado.prog2.service.CategoriaService;
import integrado.prog2.service.PedidoService;
import integrado.prog2.service.ProductoService;
import integrado.prog2.service.UsuarioService;
import integrado.prog2.ui.Menu;

// Punto de entrada de la aplicación Food Store.
// Solo arma las dependencias y arranca el menú.
public class Main {

    public static void main(String[] args) {
        // Instanciar services (orden importa por las dependencias entre ellos).
        CategoriaService categoriaService = new CategoriaService();
        ProductoService productoService = new ProductoService(categoriaService);
        UsuarioService usuarioService = new UsuarioService();
        PedidoService pedidoService = new PedidoService(usuarioService, productoService);

        cargarDatosDePrueba(categoriaService, productoService, usuarioService);

        Menu menu = new Menu(categoriaService, productoService, usuarioService, pedidoService);
        menu.iniciar();
    }

    // Datos mínimos para no tener que tipear todo a mano al probar el flujo de pedidos.
    private static void cargarDatosDePrueba(CategoriaService categoriaService,
                                              ProductoService productoService,
                                              UsuarioService usuarioService) {
        try {
            var bebidas = categoriaService.crear("Bebidas", "Gaseosas, jugos y aguas");
            var comidas = categoriaService.crear("Comidas", "Platos principales");
            productoService.crear("Coca-Cola 500ml", "Botella de gaseosa", 1500.0, 50, "coca.png", true, bebidas.getId());
            productoService.crear("Hamburguesa Completa", "Con papas y bebida", 4500.0, 20, "hamburguesa.png", true, comidas.getId());
            usuarioService.crear("Juan", "Perez", "juan.perez@mail.com", "11-2233-4455", "1234", Rol.USUARIO);
        } catch (RuntimeException e) {
            System.out.println("No se pudieron cargar los datos de prueba: " + e.getMessage());
        }
    }
}
