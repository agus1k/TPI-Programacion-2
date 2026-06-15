package integrado.prog2.entities;

// Interfaz que define el comportamiento para calcular totales.
// La implementa Pedido (calcularTotal() recorre los detalles y suma subtotales).
public interface Calculable {
    Double calcularTotal();
}
