package integrado.prog2.service;

import integrado.prog2.entities.Pedido;
import integrado.prog2.entities.Producto;
import integrado.prog2.entities.Usuario;
import integrado.prog2.enums.Estado;
import integrado.prog2.enums.FormaPago;
import integrado.prog2.exception.EntidadNoEncontradaException;
import integrado.prog2.exception.StockInvalidoException;

import java.util.ArrayList;
import java.util.List;

// Lógica de pedidos y detalles (HU-PED-01 .. HU-PED-04).
public class PedidoService {

    private final List<Pedido> pedidos = new ArrayList<>();
    private long secuenciaId = 1L;

    private final UsuarioService usuarioService;
    private final ProductoService productoService;

    public PedidoService(UsuarioService usuarioService, ProductoService productoService) {
        this.usuarioService = usuarioService;
        this.productoService = productoService;
    }

    public List<Pedido> listar() {
        List<Pedido> resultado = new ArrayList<>();
        for (Pedido p : pedidos) {
            if (!p.isEliminado()) {
                resultado.add(p);
            }
        }
        if (resultado.isEmpty()) {
            System.out.println("No hay pedidos para mostrar.");
        }
        return resultado;
    }

    public Pedido crear(Long usuarioId, FormaPago formaPago, List<DetalleInput> items) {
        Usuario usuario = usuarioService.buscarPorId(usuarioId);
        if (usuario == null || usuario.isEliminado()) {
            throw new EntidadNoEncontradaException("No existe un usuario con ID " + usuarioId + ".");
        }
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("El pedido debe tener al menos un producto.");
        }

        // primera pasada: validar todo antes de tocar stock, para no dejar el pedido a medio crear
        for (DetalleInput item : items) {
            Producto producto = productoService.buscarPorId(item.productoId());
            if (producto == null || producto.isEliminado()) {
                throw new EntidadNoEncontradaException("No existe un producto con ID " + item.productoId() + ".");
            }
            if (item.cantidad() <= 0) {
                throw new IllegalArgumentException("La cantidad debe ser mayor a cero.");
            }
            if (producto.getStock() < item.cantidad()) {
                throw new StockInvalidoException("Stock insuficiente para " + producto.getNombre() + ".");
            }
        }

        Pedido pedido = new Pedido(usuario, formaPago);
        for (DetalleInput item : items) {
            Producto producto = productoService.buscarPorId(item.productoId());
            pedido.addDetallePedido(item.cantidad(), producto.getPrecio(), producto);
        }
        pedido.calcularTotal();
        pedido.setId(secuenciaId++);
        pedidos.add(pedido);
        usuario.getPedidos().add(pedido);
        return pedido;
    }

    public Pedido actualizarEstadoYFormaPago(Long pedidoId, Estado nuevoEstado, FormaPago nuevaFormaPago) {
        Pedido pedido = buscarPorId(pedidoId);
        if (pedido == null || pedido.isEliminado()) {
            throw new EntidadNoEncontradaException("No existe un pedido con ID " + pedidoId + ".");
        }
        if (nuevoEstado != null) {
            pedido.setEstado(nuevoEstado);
        }
        if (nuevaFormaPago != null) {
            pedido.setFormaPago(nuevaFormaPago);
        }
        return pedido;
    }

    public void eliminar(Long id) {
        Pedido pedido = buscarPorId(id);
        if (pedido == null || pedido.isEliminado()) {
            throw new EntidadNoEncontradaException("No existe un pedido con ID " + id + ".");
        }
        pedido.setEliminado(true);
    }

    public Pedido buscarPorId(Long id) {
        for (Pedido p : pedidos) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    // Tipo auxiliar para que la UI pase los items sin acoplar entidades.
    public record DetalleInput(Long productoId, int cantidad) {}
}
