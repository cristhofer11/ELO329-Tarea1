import java.io.PrintStream; // Import necesario para corregir el error

public class Tablet extends Equipo {
    private Viewer viewer; // Instancia de Viewer según etapa 3.3

    public Tablet(String owner, String name, float x, float y, ETNube nube) {
        super(owner, name, x, y);
        this.viewer = new Viewer(nube);
    }

    // Delega la visualización al objeto Viewer
    public void findMy(PrintStream out) {
        out.println("FindMy App en " + this.name + " (" + this.ownerName + "):");
        viewer.show(out, this.ownerName);
    }

    // Lógica de proximidad para reporte indirecto
    public boolean isWithinRange(Cellular cell) {
        double dist = Math.sqrt(Math.pow(x - cell.getX(), 2) + Math.pow(y - cell.getY(), 2));
        return dist <= 10.0;
    }
}