public class ProductoLimpieza extends Producto {
    public enum TipoAplicacion {
        COCINA, BAÑO, ROPA, MULTIUSO
    }

    private TipoAplicacion tipoAplicacion;

    public ProductoLimpieza(String id, String descripcion, int cantidadStock, double precioUnidad, double porcentajeGanancia, boolean disponibleVenta, TipoAplicacion tipoAplicacion) {
        super(id, descripcion, cantidadStock, precioUnidad, porcentajeGanancia, disponibleVenta);
        if (!id.matches("AZ\\d{3}")) {
            throw new IllegalArgumentException("ID inválido para un producto de limpieza.");
        }
        this.tipoAplicacion = tipoAplicacion;
    }

    @Override
    public void aplicarDescuento(double porcentaje) {
        if (tipoAplicacion == TipoAplicacion.COCINA || tipoAplicacion == TipoAplicacion.MULTIUSO) {
            this.porcentajeDescuento = porcentaje;
        } else if (porcentaje >= 10 && porcentaje <= 25) {
            this.porcentajeDescuento = porcentaje;
        } else {
            throw new IllegalArgumentException("Porcentaje de descuento inválido para productos de limpieza.");
        }
    }
}
