package integrado.prog2.entities;

import integrado.prog2.enums.Estado;
import integrado.prog2.enums.FormaPago;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Pedido: hereda de Base + fecha, estado, total, formaPago.
// Relación de composición 1:N con DetallePedido.
// Implementa Calculable (calcularTotal()).
public class Pedido extends Base implements Calculable {

    private LocalDate fecha;
    private Estado estado;
    private Double total;
    private FormaPago formaPago;
    private Usuario usuario;
    private List<DetallePedido> detalles;

    public Pedido() {
        super();
        this.detalles = new ArrayList<>();
        this.fecha = LocalDate.now();
        this.estado = Estado.PENDIENTE;
        this.total = 0.0;
    }

    public Pedido(Usuario usuario, FormaPago formaPago) {
        this();
        // TODO: validar que usuario != null (regla: no se puede crear Pedido sin usuario).
        this.usuario = usuario;
        this.formaPago = formaPago;
    }

    // Métodos propios pedidos por el UML:

    public void addDetallePedido(int cantidad, Double precioUnitario, Producto producto) {
        // TODO:
        //  1) validar cantidad > 0 (lanzar StockInvalidoException o similar).
        //  2) validar stock disponible del producto.
        //  3) calcular subtotal = cantidad * precioUnitario.
        //  4) crear DetallePedido y agregarlo a la lista.
        //  5) (opcional) descontar stock del producto.
    }

    public DetallePedido findeDetallePedidoByProducto(Producto producto) {
        // TODO: recorrer detalles y devolver el que tenga ese producto, o null / lanzar excepción si no existe.
        return null;
    }

    public void deleteDetallePedidoByProducto(Producto producto) {
        // TODO: buscar detalle por producto y removerlo de la colección.
    }

    @Override
    public Double calcularTotal() {
        // TODO: recorrer detalles y sumar subtotales. Asignar a this.total y devolverlo.
        return 0.0;
    }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }

    public FormaPago getFormaPago() { return formaPago; }
    public void setFormaPago(FormaPago formaPago) { this.formaPago = formaPago; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public List<DetallePedido> getDetalles() { return detalles; }
    public void setDetalles(List<DetallePedido> detalles) { this.detalles = detalles; }

    @Override
    public String toString() {
        // TODO: ajustar para HU-PED-01 (id, usuario, estado, formaPago, total, fecha).
        return "Pedido{id=" + getId() +
                ", usuario=" + (usuario != null ? usuario.getMail() : "-") +
                ", estado=" + estado +
                ", formaPago=" + formaPago +
                ", total=" + total +
                ", fecha=" + fecha + "}";
    }
}
