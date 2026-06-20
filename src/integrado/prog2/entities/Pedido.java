package integrado.prog2.entities;

import integrado.prog2.enums.Estado;
import integrado.prog2.enums.FormaPago;
import integrado.prog2.exception.StockInvalidoException;

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
    private long secuenciaDetalle = 1L;

    public Pedido() {
        super();
        this.detalles = new ArrayList<>();
        this.fecha = LocalDate.now();
        this.estado = Estado.PENDIENTE;
        this.total = 0.0;
    }

    public Pedido(Usuario usuario, FormaPago formaPago) {
        this();
        if (usuario == null) {
            throw new IllegalArgumentException("El pedido debe estar asociado a un usuario.");
        }
        this.usuario = usuario;
        this.formaPago = formaPago;
    }

    // Métodos propios pedidos por el UML:

    public void addDetallePedido(int cantidad, Double precioUnitario, Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero.");
        }
        if (producto.getStock() < cantidad) {
            throw new StockInvalidoException("Stock insuficiente para " + producto.getNombre() +
                    ". Disponible: " + producto.getStock() + ".");
        }
        Double subtotal = precioUnitario * cantidad;
        DetallePedido detalle = new DetallePedido(cantidad, subtotal, producto);
        detalle.setId(secuenciaDetalle++);
        this.detalles.add(detalle);
        producto.setStock(producto.getStock() - cantidad);
    }

    public DetallePedido findeDetallePedidoByProducto(Producto producto) {
        for (DetallePedido d : detalles) {
            if (d.getProducto().equals(producto)) {
                return d;
            }
        }
        return null;
    }

    public void deleteDetallePedidoByProducto(Producto producto) {
        DetallePedido[] copia = detalles.toArray(new DetallePedido[0]);
        for (DetallePedido d : copia) {
            if (d.getProducto().equals(producto)) {
                d.getProducto().setStock(d.getProducto().getStock() + d.getCantidad());
                detalles.remove(d);
            }
        }
    }

    @Override
    public Double calcularTotal() {
        double suma = 0.0;
        for (DetallePedido d : detalles) {
            suma += d.getSubtotal();
        }
        this.total = suma;
        return this.total;
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
        return "Pedido{id=" + getId() +
                ", usuario=" + (usuario != null ? usuario.getMail() : "-") +
                ", estado=" + estado +
                ", formaPago=" + formaPago +
                ", total=" + total +
                ", fecha=" + fecha + "}";
    }
}
