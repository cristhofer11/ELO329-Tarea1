public class EloTelTag extends Equipo {
    private final String name;
    private static final float TRACKING_RANGE = 10;

    // Constructor del tag con dueño, nombre y posición inicial
    public EloTelTag(String owner, String n, float x, float y) {
        super(owner, x, y);
        name = n;
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

    // Retorna true si el tag está a menos de 10 m del celular
    public boolean isWithinRange(Cellular cell) {
        float deltaX = x - cell.getX();
        float deltaY = y - cell.getY();
        float distanciaCuadrada = deltaX * deltaX + deltaY * deltaY;
        return distanciaCuadrada < TRACKING_RANGE * TRACKING_RANGE;
    }
}