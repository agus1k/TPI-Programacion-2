package integrado.prog2.entities;

import java.time.LocalDateTime;
import java.util.Objects;

// Clase abstracta base de la que heredan todas las entidades del UML.
// Atributos comunes: id, eliminado (soft delete) y createdAt.
public abstract class Base {

    private Long id;
    private boolean eliminado;
    private LocalDateTime createdAt;

    protected Base() {
        //inicializar eliminado=false y createdAt=LocalDateTime.now()
        this.eliminado = false;
        this.createdAt = LocalDateTime.now();
    }

    protected Base(Long id) {
        this();
        this.id = id;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public boolean isEliminado() { return eliminado; }
    public void setEliminado(boolean eliminado) { this.eliminado = eliminado; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    @Override
    public boolean equals(Object o) {
        // Igualdad por id (typical para entidades).
        if (this == o) return true;
        if (!(o instanceof Base base)) return false;
        return Objects.equals(id, base.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        //sobrescribir en cada clase hija con la info útil para listar en consola.
        return "id=" + id + ", eliminado=" + eliminado;
    }
}
