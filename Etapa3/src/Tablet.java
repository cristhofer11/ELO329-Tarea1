public class Tablet extends Equipo {
    private Viewer viewer;
    private static final float TRACKING_RANGE = 10;

    // Constructor del tablet con dueño, posición inicial y visor
    public Tablet(String owner, float x, float y, Viewer viewer) {
        super(owner, x, y);
        this.viewer = viewer;
    }

    // Muestra en pantalla la información de FindMy del dueño del tablet
    public void findMy() {
        viewer.show("tablet");
    }

    // Indica si el tablet está a menos de 10 m de un celular
    public boolean isWithinRange(Cellular cell) {
        float deltaX = x - cell.getX();
        float deltaY = y - cell.getY();
        float distanciaCuadrada = deltaX * deltaX + deltaY * deltaY;
        return distanciaCuadrada < TRACKING_RANGE * TRACKING_RANGE;
    }

    // Encabezado del tablet para el CSV
    @Override
    public String getHeader() {
        return ownerName + ".tablet.x\t" + ownerName + ".tablet.y";
    }
}