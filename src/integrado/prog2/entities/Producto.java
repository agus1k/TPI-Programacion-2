package integrado.prog2.entities;

// Producto: hereda de Base + nombre, precio, descripcion, stock, imagen, disponible.
// Relación N:1 con Categoria.
public class Producto extends Base {

    private String nombre;
    private Double precio;
    private String descripcion;
    private Integer stock;
    private String imagen;
    private boolean disponible;
    private Categoria categoria;

    public Producto() {
        super();
    }

    public Producto(String nombre, Double precio, String descripcion,
                    Integer stock, String imagen, boolean disponible, Categoria categoria) {
        this();
        // TODO: validar precio >= 0 y stock >= 0 acá o en el Service (lanzar PrecioInvalidoException / StockInvalidoException).
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.stock = stock;
        this.imagen = imagen;
        this.disponible = disponible;
        this.categoria = categoria;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }

    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    @Override
    public String toString() {
        // TODO: ajustar formato para listado (HU-PROD-01) — incluir id, nombre, precio, stock y categoría.
        return "Producto{id=" + getId() + ", nombre='" + nombre + "', precio=" + precio +
                ", stock=" + stock + ", categoria=" + (categoria != null ? categoria.getNombre() : "-") + "}";
    }
}
