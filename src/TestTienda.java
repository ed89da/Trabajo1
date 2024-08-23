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

        // Imprimir estado inicial de la tienda
        System.out.println("Saldo inicial en caja: " + tienda.getSaldoCaja());

        // Comprar productos para la tienda
        tienda.comprarProducto(envasado, 5);
        tienda.comprarProducto(bebida, 3);
        tienda.comprarProducto(limpieza, 7);

        // Imprimir estado de los productos después de la compra
        imprimirEstadoProducto(envasado);
        imprimirEstadoProducto(bebida);
        imprimirEstadoProducto(limpieza);
        System.out.println("Saldo en caja después de compras: " + tienda.getSaldoCaja());

        // Vender productos
        List<Producto> productosAVender = Arrays.asList(envasado, bebida, limpieza);
        List<Integer> cantidades = Arrays.asList(3, 2, 5);

        tienda.venderProductos(productosAVender, cantidades);

        // Imprimir estado de los productos después de la venta
        imprimirEstadoProducto(envasado);
        imprimirEstadoProducto(bebida);
        imprimirEstadoProducto(limpieza);
        System.out.println("Saldo en caja después de ventas: " + tienda.getSaldoCaja());

        // Aplicar un descuento a productos de limpieza
        limpieza.aplicarDescuento(20);

        // Imprimir estado de los productos después de aplicar descuento
        imprimirEstadoProducto(limpieza);

        // Obtener productos comestibles con menor descuento que un porcentaje dado
        List<String> comestiblesConMenorDescuento = tienda.obtenerComestiblesConMenorDescuento(10);
        System.out.println("Comestibles con menor descuento (menos de 10%): " + comestiblesConMenorDescuento);
    }

    private static void imprimirEstadoProducto(Producto producto) {
        System.out.println("Producto: " + producto.getDescripcion() +
                ", Stock: " + producto.getCantidadStock() +
                ", Precio Unidad: " + producto.getPrecioUnidad() +
                ", Precio Final: " + producto.calcularPrecioFinal() +
                ", Disponible para venta: " + producto.isDisponibleVenta());
    }
}
