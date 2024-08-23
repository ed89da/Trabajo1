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

    public void comprarProducto(Producto producto, int cantidad) {
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

        producto.setCantidadStock(producto.getCantidadStock() + cantidad);
        saldoCaja -= costoTotal;
        productos.add(producto);
        producto.setDisponibleVenta(true);
    }

    public void venderProductos(List<Producto> productosAVender, List<Integer> cantidades) {
        double totalVenta = 0;

        for (int i = 0; i < productosAVender.size(); i++) {
            Producto producto = productosAVender.get(i);
            int cantidad = cantidades.get(i);

            if (!producto.isDisponibleVenta()) {
                System.out.println("El producto " + producto.getId() + " " + producto.getDescripcion() + " no se encuentra disponible.");
                continue;
            }

            int cantidadVendida = Math.min(cantidad, producto.getCantidadStock());
            totalVenta += cantidadVendida * producto.calcularPrecioFinal();

            System.out.println(producto.getId() + " " + producto.getDescripcion() + " " + cantidadVendida + " x " + producto.calcularPrecioFinal());

            producto.setCantidadStock(producto.getCantidadStock() - cantidadVendida);

            if (producto.getCantidadStock() == 0) {
                producto.setDisponibleVenta(false);
                System.out.println("Hay productos con stock disponible menor al solicitado.");
            }
        }

        System.out.println("TOTAL VENTA: " + totalVenta);
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
