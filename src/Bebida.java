public class Bebida extends Comestible {
    private double graduacionAlcoholica;

    public Bebida(String id, String descripcion, int cantidadStock, double precioUnidad, double porcentajeGanancia, boolean disponibleVenta, double graduacionAlcoholica, boolean importado, String fechaVencimiento, int calorias) {
        super(id, descripcion, cantidadStock, precioUnidad, porcentajeGanancia, disponibleVenta, fechaVencimiento, calorias, importado);
        if (!id.matches("AC\\d{3}")) {
            throw new IllegalArgumentException("ID inválido para una bebida.");
        }
        this.graduacionAlcoholica = graduacionAlcoholica;
        calcularCalorias();
    }

    private void calcularCalorias() {
        if (graduacionAlcoholica >= 0 && graduacionAlcoholica <= 2) {

        } else if (graduacionAlcoholica > 2 && graduacionAlcoholica <= 4.5) {
            calorias *= 1.25;
        } else if (graduacionAlcoholica > 4.5) {
            calorias *= 1.5;
        }
    }
}
