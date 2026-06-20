# Food Store

TPI de Programación 2 (Tecnicatura Universitaria en Programación, UTN). Es un sistema de gestión de pedidos para un local de comidas: maneja categorías, productos, usuarios y pedidos, todo por consola.

No hay base de datos. Todo vive en memoria mientras el programa está corriendo, usando listas de los Services. Apenas cerrás la app se pierde todo, así que al arrancar se cargan un par de categorías, productos y un usuario de prueba para no tener que tipear todo a mano cada vez (ver `cargarDatosDePrueba` en `Main.java`).

## Requisitos

JDK 21 (usa `var`, records de los exception messages, switch expressions, etc).

## Compilar y correr

Desde la raíz del proyecto:

```bash
javac -d out $(find src -name "*.java")
java -cp out integrado.prog2.Main
```

Si usás IntelliJ, el `.iml` ya está armado, así que abrís la carpeta y le das directamente a Run sobre `Main.java`.

## Qué hace

Es un menú con cuatro módulos (Categorías, Productos, Usuarios, Pedidos), y cada uno con su CRUD de toda la vida: listar, crear, editar, eliminar.

Lo único que tiene algo más de lógica es Pedidos:

- Un pedido se arma eligiendo un usuario, una forma de pago y después agregando productos uno por uno (ID + cantidad) hasta escribir `fin`.
- Cada producto agregado descuenta stock al momento. Si pedís más de lo que hay, te tira `StockInvalidoException` y no se agrega.
- El total se recalcula sumando los subtotales de cada detalle (`Pedido.calcularTotal()`).
- Se puede editar el estado del pedido (PENDIENTE → CONFIRMADO → TERMINADO, o CANCELADO) y la forma de pago después de creado.
- Si eliminás un detalle de un pedido, el stock del producto se devuelve.

Las eliminaciones son soft delete (un flag `eliminado` en `Base`, la clase de la que heredan todas las entidades junto con `id` y `createdAt`), no se borra nada de las listas en memoria.

## Estructura

```
src/integrado/prog2/
├── Main.java          # arma los services y levanta el menú
├── entities/           # Base, Categoria, Producto, Usuario, Pedido, DetallePedido
├── enums/              # Rol, Estado, FormaPago
├── exception/          # excepciones de negocio (stock, precio, mail duplicado, no encontrado)
├── service/             # toda la lógica y validaciones
└── ui/                 # Menu.java, el único lugar que usa Scanner
```

La idea fue separar bien las capas: las entidades no validan nada de negocio (eso vive en los Services), y el Menu no tiene ninguna regla, solo pide datos y muestra resultados.

## Pendiente para la entrega

- [ ] Grabar el video demostrativo
- [ ] Armar el PDF de documentación
- [ ] Pegar acá los links al repo y al video
