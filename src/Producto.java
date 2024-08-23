
public abstract class Producto {
    private String id;
    private String descripcion;
    private int cantidadStock;
    private double precioUnidad;
    private double porcentajeGanancia;
    private boolean disponibleVenta;
    protected double porcentajeDescuento;

    public Producto(String id, String descripcion, int cantidadStock, double precioUnidad, double porcentajeGanancia, boolean disponibleVenta) {
        this.id = id;
        this.descripcion = descripcion;
        this.cantidadStock = cantidadStock;
        this.precioUnidad = precioUnidad;
        this.porcentajeGanancia = porcentajeGanancia;
        this.disponibleVenta = disponibleVenta;
        this.porcentajeDescuento = 0;
    }

    public String getIdentificador() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCantidadStock() {
        return cantidadStock;
    }

    public double getPrecioUnidad() {
        return precioUnidad;
    }

    public double getPorcentajeGanancia() {
        return porcentajeGanancia;
    }

    public boolean isDisponibleVenta() {
        return disponibleVenta;
    }

    public void setCantidadStock(int cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public void setDisponibleVenta(boolean disponibleVenta) {
        this.disponibleVenta = disponibleVenta;
    }

    public double calcularPrecioVenta() {
        return precioUnidad + (precioUnidad * porcentajeGanancia / 100);
    }

    public void aplicarDescuento(double porcentaje) {
        if (porcentaje > 0) {
            this.porcentajeDescuento = porcentaje;
        }
    }

    public double calcularPrecioFinal() {
        double precioVenta = calcularPrecioVenta();
        double precioConDescuento = precioVenta - (precioVenta * porcentajeDescuento / 100);
        return precioConDescuento;
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }
}
