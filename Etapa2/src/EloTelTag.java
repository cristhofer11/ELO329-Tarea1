// Dispositivo de rastreo pasivo
public class EloTelTag extends Equipo {
    private static final float RANGE = 10.0f;

    public EloTelTag(String owner, String name, float x, float y) {
        super(owner, name, x, y);
    }

    // Verificación de proximidad con un celular (rango 10m)
    public boolean isWithinRange(Cellular cell) {
        double dist = Math.sqrt(Math.pow(x - cell.getX(), 2) + Math.pow(y - cell.getY(), 2));
        return dist <= RANGE;
    }
}