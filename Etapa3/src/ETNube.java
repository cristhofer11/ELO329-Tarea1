import java.awt.geom.Point2D;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Locale;

// Servidor central que gestiona la persistencia y consultas de red
public class ETNube {
    private ArrayList<Data> cloudData = new ArrayList<>();

    // Registro o actualización de coordenadas reportadas
    public void updateLocation(String owner, String device, float x, float y) {
        for (Data d : cloudData) {
            if (d.owner.equals(owner) && d.device.equals(device)) {
                d.pos.setLocation(x, y);
                return;
            }
        }
        cloudData.add(new Data(owner, device, new Point2D.Float(x, y)));
    }

    // Servicio FindMy: despliegue jerárquico de bienes por dueño
    public void printUserItems(PrintStream out, String owner) {
        out.println(" Bienes de " + owner + ":");
        
        out.println("  Items:");
        for (Data d : cloudData) {
            if (d.owner.equals(owner) && isTag(d.device))
                out.format(Locale.US, "    %s: %.1f, %.1f%n", d.device, d.pos.getX(), d.pos.getY());
        }

        out.println("  Dispositivos:");
        for (Data d : cloudData) {
            if (d.owner.equals(owner) && !isTag(d.device))
                out.format(Locale.US, "    %s: %.1f, %.1f%n", d.device, d.pos.getX(), d.pos.getY());
        }
    }

    // Lógica para diferenciar tipos de equipo en el reporte
    private boolean isTag(String device) {
        return !device.equals("celular") && !device.toLowerCase().contains("tablet");
    }

    // Formateo de cabecera CSV para graficar trayectorias
    public void printHeader(PrintStream out) {
        out.print("Step");
        for (Data d : cloudData) out.print("\t" + d.owner + "." + d.device + ".x\t" + d.owner + "." + d.device + ".y");
        out.println();
    }

    // Vuelco de estado de la nube en cada paso de simulación
    public void printState(PrintStream out, int step) {
        out.print(step);
        for (Data d : cloudData) out.format(Locale.US, "\t%.1f\t%.1f", d.pos.getX(), d.pos.getY());
        out.println();
    }

    private static class Data {
        String owner, device; Point2D pos;
        Data(String o, String d, Point2D p) { owner = o; device = d; pos = p; }
    }
}