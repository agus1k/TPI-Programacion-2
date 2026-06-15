package integrado.prog2.entities;

// DetallePedido: hereda de Base + cantidad, subtotal.
// Relación N:1 con Producto.
public class DetallePedido extends Base {

    private Integer cantidad;
    private Double subtotal;
    private Producto producto;

    public DetallePedido() {
        super();
    }

    public DetallePedido(Integer cantidad, Double subtotal, Producto producto) {
        this();
        // TODO: validar cantidad > 0 (lanzar excepción si no).
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.producto = producto;
    }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }

    @Override
    public String toString() {
        return "DetallePedido{id=" + getId() + ", cantidad=" + cantidad +
                ", subtotal=" + subtotal +
                ", producto=" + (producto != null ? producto.getNombre() : "-") + "}";
    }
}
