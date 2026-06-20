package integrado.prog2.service;

import integrado.prog2.entities.Categoria;
import integrado.prog2.entities.Producto;
import integrado.prog2.exception.EntidadNoEncontradaException;
import integrado.prog2.exception.PrecioInvalidoException;
import integrado.prog2.exception.StockInvalidoException;

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
        
        List<Producto> productosActivos = new ArrayList<>();
        for (Producto p : productos ) {
            if (!p.isEliminado()) {
                productosActivos.add(p);
            }
        }
        if (productosActivos.isEmpty()) {
            System.out.println("No hay productos disponibles.");
        }
        return productosActivos;
    }

    public Producto crear(String nombre, String descripcion, Double precio,
                          Integer stock, String imagen, boolean disponible, Long categoriaId) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (precio == null || precio < 0) {
            throw new PrecioInvalidoException("El precio no puede ser negativo.");
        }
        if (stock == null || stock < 0) {
            throw new StockInvalidoException("El stock no puede ser negativo.");
        }
        Categoria categoria = categoriaService.buscarPorId(categoriaId);
        if (categoria == null || categoria.isEliminado()) {
            throw new EntidadNoEncontradaException("No existe una categoría con ID " + categoriaId + ".");
        }
        Producto producto = new Producto(nombre, precio, descripcion, stock, imagen, disponible, categoria);
        producto.setId(secuenciaId++);
        productos.add(producto);
        categoria.getProductos().add(producto);
        return producto;
    }

    public Producto editar(Long id, String nombre, String descripcion, Double precio,
                           Integer stock, String imagen, Boolean disponible, Long categoriaId) {
        Producto producto = buscarPorId(id);
        if (producto == null || producto.isEliminado()) {
            throw new EntidadNoEncontradaException("No existe un producto con ID " + id + ".");
        }
        if (nombre != null && !nombre.isBlank()) {
            producto.setNombre(nombre);
        }
        if (descripcion != null && !descripcion.isBlank()) {
            producto.setDescripcion(descripcion);
        }
        if (precio != null) {
            if (precio < 0) {
                throw new PrecioInvalidoException("El precio no puede ser negativo.");
            }
            producto.setPrecio(precio);
        }
        if (stock != null) {
            if (stock < 0) {
                throw new StockInvalidoException("El stock no puede ser negativo.");
            }
            producto.setStock(stock);
        }
        if (imagen != null && !imagen.isBlank()) {
            producto.setImagen(imagen);
        }
        if (disponible != null) {
            producto.setDisponible(disponible);
        }
        if (categoriaId != null) {
            Categoria nuevaCategoria = categoriaService.buscarPorId(categoriaId);
            if (nuevaCategoria == null || nuevaCategoria.isEliminado()) {
                throw new EntidadNoEncontradaException("No existe una categoría con ID " + categoriaId + ".");
            }
            producto.getCategoria().getProductos().remove(producto);
            producto.setCategoria(nuevaCategoria);
            nuevaCategoria.getProductos().add(producto);
        }
        return producto;
    }

    public void eliminar(Long id) {
        Producto producto = buscarPorId(id);
        if (producto == null || producto.isEliminado()) {
            throw new EntidadNoEncontradaException("No existe un producto con ID " + id + ".");
        }
        producto.setEliminado(true);
    }

    public Producto buscarPorId(Long id) {
        for (Producto p : productos) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }
}
