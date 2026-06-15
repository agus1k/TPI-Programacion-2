package integrado.prog2.service;

import integrado.prog2.entities.Pedido;
import integrado.prog2.enums.Estado;
import integrado.prog2.enums.FormaPago;

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
        // TODO: solo no eliminados (HU-PED-01).
        return new ArrayList<>();
    }

    public Pedido crear(Long usuarioId, FormaPago formaPago, List<DetalleInput> items) {
        // TODO (HU-PED-02):
        //  1) buscar usuario por id; si no existe o está eliminado -> EntidadNoEncontradaException.
        //  2) crear Pedido(usuario, formaPago).
        //  3) por cada item: buscar producto y usar pedido.addDetallePedido(cantidad, producto.getPrecio(), producto).
        //     - si alguna operación falla (ej. sin stock), capturar la excepción y NO agregar el pedido a la colección
        //       para no dejar datos inconsistentes.
        //  4) usar pedido.calcularTotal() (interfaz Calculable) para fijar el total.
        //  5) asignar id, agregar a colección y devolver.
        return null;
    }

    public Pedido actualizarEstadoYFormaPago(Long pedidoId, Estado nuevoEstado, FormaPago nuevaFormaPago) {
        // TODO (HU-PED-03): buscar pedido, validar y actualizar.
        return null;
    }

    public void eliminar(Long id) {
        // TODO (HU-PED-04): soft delete del pedido (y opcionalmente de sus detalles).
    }

    // Tipo auxiliar para que la UI pase los items sin acoplar entidades.
    public record DetalleInput(Long productoId, int cantidad) {}
}
