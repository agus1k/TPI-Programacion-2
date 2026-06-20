package integrado.prog2.service;

import integrado.prog2.entities.Categoria;
import integrado.prog2.entities.Producto;
import java.util.ArrayList;
import java.util.List;

// Maneja la colección en memoria de Categorias y las reglas de negocio
// asociadas a las HUs HU-CAT-01 .. HU-CAT-04.
public class CategoriaService {

    private final List<Categoria> categorias = new ArrayList<>();
    private long secuenciaId = 1L;

    public List<Categoria> listar() {
        List<Categoria> resultado = new ArrayList<>();
        for (Categoria c : categorias) {
            if (!c.isEliminado()) {
                resultado.add(c);
            }
        }
        if (resultado.isEmpty()) {
            System.out.println("No hay categorías para mostrar.");
        }
        return resultado;
    }

    public Categoria crear(String nombre, String descripcion) {
        //  1) validar nombre/descripcion no vacíos.
        //  2) validar que no exista otra categoría con el mismo nombre (HU-CAT-02).
        //  3) crear Categoria, asignar id (secuenciaId++), agregar a la colección y devolver.
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía.");
        }
        //  2) validar que no exista otra categoría con el mismo nombre (HU-CAT-02).
        for (Categoria c : categorias) {
            if (!c.isEliminado() && c.getNombre().equals(nombre)) {
                throw new IllegalArgumentException("Ya existe una categoría con ese nombre.");
            }
        }
        Categoria categoria = new Categoria(secuenciaId++, nombre, descripcion);
        categorias.add(categoria);
        
        return categoria;
    }

    public Categoria editar(Long id, String nuevoNombre, String nuevaDescripcion) {
        // TODO:
        //  1) buscar por id (no eliminada); si no existe -> EntidadNoEncontradaException.
        //  2) actualizar nombre y/o descripcion.
        Categoria categoria = buscarPorId(id);
        if (categoria == null || categoria.isEliminado()) {
            throw new IllegalArgumentException("Categoría no encontrada.");
        }
            if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
                // Validar que no exista otra categoría con el mismo nombre.
                for (Categoria c : categorias) {
                    if (!c.isEliminado() && c.getNombre().equals(nuevoNombre) && !c.getId().equals(id)) {
                        throw new IllegalArgumentException("Ya existe una categoría con ese nombre.");
                    }
                }
                categoria.setNombre(nuevoNombre);
            }
            if (nuevaDescripcion != null && !nuevaDescripcion.trim().isEmpty()) {
                categoria.setDescripcion(nuevaDescripcion);
            }
        return categoria;
    }

    public void eliminar(Long id) { 
        //  1) buscar por id; si no existe -> EntidadNoEncontradaException.
        //  2) soft delete: setEliminado(true).
        //  3) (regla de cátedra) decidir qué pasa si tiene productos asociados.
        Categoria categoria = buscarPorId(id);
        if (categoria == null || categoria.isEliminado()) {
            throw new IllegalArgumentException("Categoría no encontrada.");
        }
         if (!categoria.getProductos().isEmpty()) {
            System.out.println("La categoría tenía productos asociados. Se han eliminado junto con la categoría.");
            for (Producto p : categoria.getProductos()) {
                p.setEliminado(true);
            }
        }
        else {
            categoria.setEliminado(true);
        }
            
    }

    public Categoria buscarPorId(Long id) {
            for (Categoria c : categorias) {
                if (c.getId().equals(id)) {
                    return c;
                }
            }
        return null;
    }
}
