public abstract class Comestible extends Producto {
    private String fechaVencimiento;
    public int calorias;
    private boolean importado;

    public Comestible(String id, String descripcion, int cantidadStock, double precioUnidad, double porcentajeGanancia, boolean disponibleVenta, String fechaVencimiento, int calorias, boolean importado) {
        super(id, descripcion, cantidadStock, precioUnidad, porcentajeGanancia, disponibleVenta);
        this.fechaVencimiento = fechaVencimiento;
        this.calorias = calorias;
        this.importado = importado;
    }

    public boolean isImportado() {
        return importado;
    }
}
