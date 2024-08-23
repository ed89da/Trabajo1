public class ProductoEnvasado extends Comestible {
    private String tipoEnvase;

    public ProductoEnvasado(String id, String descripcion, int cantidadStock, double precioUnidad, double porcentajeGanancia, boolean disponibleVenta, String tipoEnvase, boolean importado, String fechaVencimiento, int calorias) {
        super(id, descripcion, cantidadStock, precioUnidad, porcentajeGanancia, disponibleVenta, fechaVencimiento, calorias, importado);
        if (!id.matches("AB\\d{3}")) {
            throw new IllegalArgumentException("ID inválido para un producto envasado.");
        }
        this.tipoEnvase = tipoEnvase;
    }
}
