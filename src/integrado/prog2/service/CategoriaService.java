package integrado.prog2.service;

import integrado.prog2.entities.Categoria;

import java.util.ArrayList;
import java.util.List;

// Maneja la colección en memoria de Categorias y las reglas de negocio
// asociadas a las HUs HU-CAT-01 .. HU-CAT-04.
public class CategoriaService {

    private final List<Categoria> categorias = new ArrayList<>();
    private long secuenciaId = 1L;

    public List<Categoria> listar() {
        // TODO: devolver solo las categorías con eliminado == false (HU-CAT-01).
        return new ArrayList<>();
    }

    public Categoria crear(String nombre, String descripcion) {
        // TODO:
        //  1) validar nombre/descripcion no vacíos.
        //  2) validar que no exista otra categoría con el mismo nombre (HU-CAT-02).
        //  3) crear Categoria, asignar id (secuenciaId++), agregar a la colección y devolver.
        return null;
    }

    public Categoria editar(Long id, String nuevoNombre, String nuevaDescripcion) {
        // TODO:
        //  1) buscar por id (no eliminada); si no existe -> EntidadNoEncontradaException.
        //  2) actualizar nombre y/o descripcion.
        return null;
    }

    public void eliminar(Long id) {
        // TODO:
        //  1) buscar por id; si no existe -> EntidadNoEncontradaException.
        //  2) soft delete: setEliminado(true).
        //  3) (regla de cátedra) decidir qué pasa si tiene productos asociados.
    }

    public Categoria buscarPorId(Long id) {
        // TODO: helper para que ProductoService valide la categoría al crear/editar producto.
        return null;
    }
}
