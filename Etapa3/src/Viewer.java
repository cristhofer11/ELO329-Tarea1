import java.io.PrintStream;

// Clase Viewer: Centraliza la visualización de datos (Requisito Etapa 3.3)
public class Viewer {
    private ETNube nube;

    public Viewer(ETNube nube) {
        this.nube = nube;
    }

    // Consulta la nube y despliega los bienes del propietario
    public void show(PrintStream out, String owner) {
        nube.printUserItems(out, owner);
    }
}