package integrado.prog2;

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

        // TODO (opcional): cargar datos de prueba (categorías/productos/usuarios) para probar más rápido el flujo.

        Menu menu = new Menu(categoriaService, productoService, usuarioService, pedidoService);
        menu.iniciar();
    }
}
