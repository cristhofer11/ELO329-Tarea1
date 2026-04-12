import java.io.PrintStream; // Import necesario para corregir el error

public class Cellular extends Equipo {
    private ETNube nube;
    private Viewer viewer; // Instancia de Viewer según etapa 3.3

    public Cellular(String owner, float x, float y, ETNube nube) {
        super(owner, "celular", x, y);
        this.nube = nube;
        this.viewer = new Viewer(nube);
    }

    public void reportLocation(Equipo eq) {
        nube.updateLocation(eq.getOwnerName(), eq.getName(), this.x, this.y);
    }

    // Delega la visualización al objeto Viewer
    public void findMy(PrintStream out) {
        out.println("FindMy App en celular de " + this.ownerName + ":");
        viewer.show(out, this.ownerName);
    }
}