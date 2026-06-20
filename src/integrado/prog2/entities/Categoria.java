package integrado.prog2.entities;

import java.util.ArrayList;
import java.util.List;

// Categoria: hereda de Base + nombre, descripcion.
// Relación 1:N con Producto.
public class Categoria extends Base {

    private String nombre;
    private String descripcion;
    private List<Producto> productos;

    public Categoria(Long id, String nombre, String descripcion) {
        super(id); // le pasa el id al constructor de Base
        this.productos = new ArrayList<>();
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Categoria() {
        super();
        this.productos = new ArrayList<>();
    }

    public Categoria(String nombre, String descripcion) {
        this();
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public List<Producto> getProductos() { return productos; }
    public void setProductos(List<Producto> productos) { this.productos = productos; }

    @Override
    public String toString() {
        return "Categoria{id=" + getId() + ", nombre='" + nombre + "', descripcion='" + descripcion + "'}";
    }
}
