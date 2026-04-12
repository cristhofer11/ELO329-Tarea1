// Rastreador pasivo sin GPS propio
public class EloTelTag extends Equipo {
    private static final float RANGE = 10.0f;

    public EloTelTag(String owner, String name, float x, float y) {
        super(owner, name, x, y);
    }

    // Cálculo de proximidad euclidiana: $\sqrt{\Delta x^2 + \Delta y^2}$
    public boolean isWithinRange(Cellular cell) {
        double dist = Math.sqrt(Math.pow(x - cell.getX(), 2) + Math.pow(y - cell.getY(), 2));
        return dist <= RANGE;
    }
}