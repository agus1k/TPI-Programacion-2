package integrado.prog2.service;

import integrado.prog2.entities.Usuario;
import integrado.prog2.enums.Rol;
import integrado.prog2.exception.EntidadNoEncontradaException;
import integrado.prog2.exception.MailDuplicadoException;

import java.util.ArrayList;
import java.util.List;

// Lógica de usuarios (HU-USR-01 .. HU-USR-04).
public class UsuarioService {

    private final List<Usuario> usuarios = new ArrayList<>();
    private long secuenciaId = 1L;

    public List<Usuario> listar() {
        List<Usuario> resultado = new ArrayList<>();
        for (Usuario u : usuarios) {
            if (!u.isEliminado()) {
                resultado.add(u);
            }
        }
        if (resultado.isEmpty()) {
            System.out.println("No hay usuarios para mostrar.");
        }
        return resultado;
    }

    public Usuario crear(String nombre, String apellido, String mail,
                         String celular, String contrasena, Rol rol) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (apellido == null || apellido.isBlank()) {
            throw new IllegalArgumentException("El apellido no puede estar vacío.");
        }
        if (mail == null || mail.isBlank()) {
            throw new IllegalArgumentException("El mail no puede estar vacío.");
        }
        for (Usuario u : usuarios) {
            if (!u.isEliminado() && u.getMail().equalsIgnoreCase(mail)) {
                throw new MailDuplicadoException("Ya existe un usuario registrado con el mail " + mail + ".");
            }
        }
        Usuario usuario = new Usuario(nombre, apellido, mail, celular, contrasena, rol);
        usuario.setId(secuenciaId++);
        usuarios.add(usuario);
        return usuario;
    }

    public Usuario editar(Long id, String nombre, String apellido, String mail,
                          String celular, String contrasena, Rol rol) {
        Usuario usuario = buscarPorId(id);
        if (usuario == null || usuario.isEliminado()) {
            throw new EntidadNoEncontradaException("No existe un usuario con ID " + id + ".");
        }
        if (mail != null && !mail.isBlank() && !mail.equalsIgnoreCase(usuario.getMail())) {
            for (Usuario u : usuarios) {
                if (!u.isEliminado() && !u.getId().equals(id) && u.getMail().equalsIgnoreCase(mail)) {
                    throw new MailDuplicadoException("Ya existe un usuario registrado con el mail " + mail + ".");
                }
            }
            usuario.setMail(mail);
        }
        if (nombre != null && !nombre.isBlank()) {
            usuario.setNombre(nombre);
        }
        if (apellido != null && !apellido.isBlank()) {
            usuario.setApellido(apellido);
        }
        if (celular != null && !celular.isBlank()) {
            usuario.setCelular(celular);
        }
        if (contrasena != null && !contrasena.isBlank()) {
            usuario.setContrasena(contrasena);
        }
        if (rol != null) {
            usuario.setRol(rol);
        }
        return usuario;
    }

    public void eliminar(Long id) {
        Usuario usuario = buscarPorId(id);
        if (usuario == null || usuario.isEliminado()) {
            throw new EntidadNoEncontradaException("No existe un usuario con ID " + id + ".");
        }
        usuario.setEliminado(true);
    }

    public Usuario buscarPorId(Long id) {
        for (Usuario u : usuarios) {
            if (u.getId().equals(id)) {
                return u;
            }
        }
        return null;
    }
}
