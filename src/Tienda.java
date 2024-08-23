
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Tienda {
    private String nombre;
    private int maximoProductos;
    private double saldoCaja;
    private List<Producto> productos;

    public Tienda(String nombre, int maximoProductos, double saldoCaja) {
        this.nombre = nombre;
        this.maximoProductos = maximoProductos;
        this.saldoCaja = saldoCaja;
        this.productos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public double getSaldoCaja() {
        return saldoCaja;
    }

    public void comprarProducto(Producto producto, int cantidad) {
        if (cantidad <= 0) {
            System.out.println("La cantidad a comprar debe ser un número entero positivo.");
            return;
        }

        if (producto.getPrecioUnidad() <= 0) {
            System.out.println("El precio del producto debe ser positivo.");
            return;
        }

        double costoTotal = producto.getPrecioUnidad() * cantidad;
        int totalStockActual = productos.stream().mapToInt(Producto::getCantidadStock).sum();

        if (saldoCaja < costoTotal) {
            System.out.println("El producto no podrá ser agregado a la tienda por saldo insuficiente en la caja.");
            return;
        }

        if (totalStockActual + cantidad > maximoProductos) {
            System.out.println("No se pueden agregar nuevos productos a la tienda ya que se alcanzó el máximo de stock.");
            return;
        }

        // Verificar si el producto ya está en el stock
        boolean productoExistente = productos.stream()
                .anyMatch(p -> p.getIdentificador().equals(producto.getIdentificador()));

        if (productoExistente) {
            productos.stream()
                    .filter(p -> p.getIdentificador().equals(producto.getIdentificador()))
                    .findFirst()
                    .ifPresent(p -> p.setCantidadStock(p.getCantidadStock() + cantidad));
        } else {
            producto.setCantidadStock(cantidad);
            productos.add(producto);
        }

        saldoCaja -= costoTotal;
        producto.setDisponibleVenta(true);
    }

    public void venderProductos(List<Producto> productosAVender, List<Integer> cantidades) {
        double totalVenta = 0;
        StringBuilder detalleVenta = new StringBuilder();

        for (int i = 0; i < productosAVender.size(); i++) {
            Producto producto = productosAVender.get(i);
            int cantidad = cantidades.get(i);

            if (!producto.isDisponibleVenta()) {
                detalleVenta.append("El producto ").append(producto.getIdentificador()).append(" ").append(producto.getDescripcion()).append(" no se encuentra disponible.\n");
                continue;
            }

            int cantidadVendida = Math.min(cantidad, producto.getCantidadStock());
            totalVenta += cantidadVendida * producto.calcularPrecioFinal();

            detalleVenta.append(producto.getIdentificador()).append(" ").append(producto.getDescripcion()).append(" ").append(cantidadVendida).append(" x ").append(producto.calcularPrecioFinal()).append("\n");

            producto.setCantidadStock(producto.getCantidadStock() - cantidadVendida);

            if (producto.getCantidadStock() == 0) {
                producto.setDisponibleVenta(false);
                detalleVenta.append("Hay productos con stock disponible menor al solicitado.\n");
            }
        }

        detalleVenta.append("TOTAL VENTA: ").append(totalVenta);
        System.out.println(detalleVenta);
        saldoCaja += totalVenta;
    }

    public List<String> obtenerComestiblesConMenorDescuento(double porcentajeDescuento) {
        return productos.stream()
                .filter(p -> p instanceof Comestible)
                .map(p -> (Comestible) p)
                .filter(p -> !p.isImportado() && p.getPorcentajeDescuento() < porcentajeDescuento)
                .sorted((p1, p2) -> Double.compare(p1.calcularPrecioFinal(), p2.calcularPrecioFinal()))
                .map(p -> p.getDescripcion().toUpperCase())
                .collect(Collectors.toList());
    }
}
