public class EloTelTag extends Equipo {
    private final String name;
    private static final float TRACKING_RANGE = 10;

    // Constructor del tag con dueño, nombre y posición inicial
    public EloTelTag(String owner, String name, float x, float y) {
        super(owner, x, y);
        this.name = name;
    }

    // Retorna el nombre del tag
    public String getName() {
        return name;
    }

    // Retorna el encabezado de este tag para el CSV
    @Override
    public String getHeader() {
        return ownerName + "." + name + ".x\t" + ownerName + "." + name + ".y";
    }

    // Indica si el tag está a menos de 10 m de un celular
    public boolean isWithinRange(Cellular cell) {
        float deltaX = x - cell.getX();
        float deltaY = y - cell.getY();
        float distanciaCuadrada = deltaX * deltaX + deltaY * deltaY;
        return distanciaCuadrada < TRACKING_RANGE * TRACKING_RANGE;
    }
}