import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class TestTienda {
    public static void main(String[] args) {
        // Crear una tienda con capacidad para 100 productos y un saldo inicial de 1000.0
        Tienda tienda = new Tienda("Mi Tienda", 100, 1000.0);

        // Crear productos de diferentes tipos
        ProductoEnvasado envasado = new ProductoEnvasado("AB123", "Aceite", 10, 10.0, 15.0, true, "Plástico", false, "2025-12-31", 500);
        Bebida bebida = new Bebida("AC456", "Vino", 5, 20.0, 10.0, true, 5.0, false, "2024-06-30", 250);
        ProductoLimpieza limpieza = new ProductoLimpieza("AZ789", "Detergente", 20, 8.0, 12.0, true, ProductoLimpieza.TipoAplicacion.COCINA);

        // Imprimir saldo inicial
        System.out.println("Saldo inicial en caja: $" + tienda.getSaldoCaja());

        // Comprar productos
        System.out.println("\n--- Compra de Productos ---");
        comprarProductoConValidaciones(tienda, envasado, 5);
        comprarProductoConValidaciones(tienda, bebida, 3);
        comprarProductoConValidaciones(tienda, limpieza, 7);

        // Imprimir estado de los productos después de la compra
        System.out.println("\n--- Estado de los productos después de la compra ---");
        imprimirEstadoProducto(envasado);
        imprimirEstadoProducto(bebida);
        imprimirEstadoProducto(limpieza);
        System.out.println("Saldo en caja después de compras: $" + tienda.getSaldoCaja());

        // Vender productos
        System.out.println("\n--- Venta de Productos ---");
        List<Producto> productosAVender = Arrays.asList(envasado, bebida, limpieza);
        List<Integer> cantidades = Arrays.asList(3, 2, 5);
        venderProductosConValidaciones(tienda, productosAVender, cantidades);

        // Imprimir estado de los productos después de la venta
        System.out.println("\n--- Estado de los productos después de la venta ---");
        imprimirEstadoProducto(envasado);
        imprimirEstadoProducto(bebida);
        imprimirEstadoProducto(limpieza);
        System.out.println("Saldo en caja después de ventas: $" + tienda.getSaldoCaja());

        // Aplicar un descuento a productos de limpieza
        System.out.println("\n--- Aplicación de Descuento ---");
        limpieza.aplicarDescuento(20);
        System.out.println("\nEstado de los productos después de aplicar descuento:");
        imprimirEstadoProducto(limpieza);

        // Obtener productos comestibles con menor descuento que un porcentaje dado
        System.out.println("\n--- Productos Comestibles con Menor Descuento (menos de 10%) ---");
        List<String> comestiblesConMenorDescuento = tienda.obtenerComestiblesConMenorDescuento(10);
        for (String producto : comestiblesConMenorDescuento) {
            System.out.println("- " + producto);
        }
    }

    private static void comprarProductoConValidaciones(Tienda tienda, Producto producto, int cantidad) {
        if (cantidad <= 0) {
            System.out.println("La cantidad a comprar debe ser un número entero positivo.");
            return;
        }

        if (producto.getPrecioUnidad() <= 0) {
            System.out.println("El precio del producto debe ser positivo.");
            return;
        }

        tienda.comprarProducto(producto, cantidad);
    }

    private static void venderProductosConValidaciones(Tienda tienda, List<Producto> productos, List<Integer> cantidades) {
        if (productos.size() != cantidades.size()) {
            System.out.println("El número de productos y cantidades no coincide.");
            return;
        }

        for (int i = 0; i < productos.size(); i++) {
            Producto producto = productos.get(i);
            int cantidad = cantidades.get(i);

            if (cantidad <= 0) {
                System.out.println("La cantidad a vender debe ser un número entero positivo.");
                continue;
            }

            if (cantidad > producto.getCantidadStock()) {
                System.out.println("No hay suficiente stock para el producto: " + producto.getDescripcion());
                continue;
            }

            if (producto.getPrecioUnidad() <= 0) {
                System.out.println("El precio del producto debe ser positivo: " + producto.getDescripcion());
                continue;
            }

            tienda.venderProductos(Arrays.asList(producto), Arrays.asList(cantidad));
        }
    }

    private static void imprimirEstadoProducto(Producto producto) {
        DecimalFormat formatoPorcentaje = new DecimalFormat("#0.00%"); // Formato para porcentaje
        DecimalFormat formatoDinero = new DecimalFormat("$#0.00"); // Formato para dinero

        System.out.println("Producto: " + producto.getDescripcion() +
                "\n  Stock: " + producto.getCantidadStock() +
                "\n  Precio Unidad: " + formatoDinero.format(producto.getPrecioUnidad()) +
                "\n  Precio Final: " + formatoDinero.format(producto.calcularPrecioFinal()) +
                "\n  Disponible para venta: " + producto.isDisponibleVenta() +
                "\n  Porcentaje de ganancia: " + formatoPorcentaje.format(producto.getPorcentajeGanancia() / 100) +
                "\n");
    }
}
