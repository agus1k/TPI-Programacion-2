package integrado.prog2.service;

import integrado.prog2.entities.Usuario;
import integrado.prog2.enums.Rol;

import java.util.ArrayList;
import java.util.List;

// Lógica de usuarios (HU-USR-01 .. HU-USR-04).
public class UsuarioService {

    private final List<Usuario> usuarios = new ArrayList<>();
    private long secuenciaId = 1L;

    public List<Usuario> listar() {
        // TODO: solo no eliminados (HU-USR-01).
        return new ArrayList<>();
    }

    public Usuario crear(String nombre, String apellido, String mail,
                         String celular, String contrasena, Rol rol) {
        // TODO:
        //  1) validar campos no vacíos (mail al menos).
        //  2) validar unicidad de mail recorriendo la colección -> MailDuplicadoException.
        //  3) crear Usuario, asignar id, agregar a colección.
        return null;
    }

    public Usuario editar(Long id, String nombre, String apellido, String mail,
                          String celular, String contrasena, Rol rol) {
        // TODO:
        //  - buscar por id; si no existe o está eliminado -> EntidadNoEncontradaException.
        //  - si se cambia el mail, revalidar unicidad.
        return null;
    }

    public void eliminar(Long id) {
        // TODO: soft delete (HU-USR-04).
    }

    public Usuario buscarPorId(Long id) {
        // TODO: helper para PedidoService.
        return null;
    }
}
