# Food Store – Sistema de Gestión de Pedidos de Comida

TPI – Programación 2 (Tecnicatura Universitaria en Programación a Distancia).

Aplicación de consola en **Java 21** que permite gestionar Categorías, Productos, Usuarios y Pedidos
mediante un menú CRUD. Toda la información se guarda en memoria usando Colecciones (no hay BD).

## Cómo ejecutar

Desde la raíz del proyecto:

```bash
# Compilar
javac -d out $(find src -name "*.java")

# Ejecutar
java -cp out integrado.prog2.Main
```

Requiere JDK 21 o superior.

## Estructura

```
src/integrado/prog2/
├── Main.java                # Punto de entrada
├── config/                  # (Reservado para configuración)
├── entities/                # Clases del UML (Base, Categoria, Producto, Usuario, Pedido, DetallePedido)
├── enums/                   # Rol, Estado, FormaPago
├── exception/               # Excepciones propias
├── service/                 # Lógica/casos de uso
└── ui/                      # Menús de consola
```

## Entregables pendientes

- [ ] Video demostrativo (10–15 min)
- [ ] Documentación PDF (carátula, índice, marco teórico, decisiones, dificultades, biblio)
- [ ] Link al repo y al video en este README
