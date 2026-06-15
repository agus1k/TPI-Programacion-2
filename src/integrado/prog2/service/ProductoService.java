package integrado.prog2.service;

import integrado.prog2.entities.Producto;

import java.util.ArrayList;
import java.util.List;

// Lógica de productos (HU-PROD-01 .. HU-PROD-04).
public class ProductoService {

    private final List<Producto> productos = new ArrayList<>();
    private long secuenciaId = 1L;

    private final CategoriaService categoriaService;

    public ProductoService(CategoriaService categoriaService) {
        // Recibe el CategoriaService para validar la categoría al crear/editar.
        this.categoriaService = categoriaService;
    }

    public List<Producto> listar() {
        // TODO: solo no eliminados (HU-PROD-01).
        return new ArrayList<>();
    }

    public Producto crear(String nombre, String descripcion, Double precio,
                          Integer stock, String imagen, boolean disponible, Long categoriaId) {
        // TODO:
        //  1) validar nombre no vacío.
        //  2) validar precio >= 0 (PrecioInvalidoException).
        //  3) validar stock >= 0 (StockInvalidoException).
        //  4) buscar categoría por id usando categoriaService; si no existe o está eliminada, cancelar.
        //  5) crear Producto, asignar id, agregar a colección.
        return null;
    }

    public Producto editar(Long id, String nombre, String descripcion, Double precio,
                           Integer stock, String imagen, Boolean disponible, Long categoriaId) {
        // TODO:
        //  - buscar producto por id (no eliminado); si no existe -> EntidadNoEncontradaException.
        //  - actualizar solo los campos que vienen no nulos / no vacíos.
        //  - revalidar precio/stock.
        return null;
    }

    public void eliminar(Long id) {
        // TODO: soft delete (HU-PROD-04). No remover físicamente para no romper detalles de pedidos.
    }

    public Producto buscarPorId(Long id) {
        // TODO: helper para PedidoService al armar detalles.
        return null;
    }
}
