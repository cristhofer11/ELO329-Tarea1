import java.awt.geom.Point2D;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Locale;

// Repositorio central de datos reportados
public class ETNube {
    private ArrayList<Data> cloudData = new ArrayList<>();

    // Registro de última posición conocida de un dispositivo
    public void updateLocation(String owner, String equipment, float x, float y) {
        for (Data d : cloudData) {
            if (d.owner.equals(owner) && d.device.equals(equipment)) {
                d.pos.setLocation(x, y);
                return;
            }
        }
        cloudData.add(new Data(owner, equipment, new Point2D.Float(x, y)));
    }

    // Generación de cabecera para el reporte CSV
    public void printHeader(PrintStream out) {
        out.print("Step");
        for (Data d : cloudData) {
            out.print("\t" + d.owner + "." + d.device + ".x\t" + d.owner + "." + d.device + ".y");
        }
        out.println();
    }

    // Escritura del estado de la nube en el archivo de salida
    public void printState(PrintStream out, int step) {
        out.print(step);
        for (Data d : cloudData) {
            out.format(Locale.US, "\t%.1f\t%.1f", d.pos.getX(), d.pos.getY());
        }
        out.println();
    }

    // Clase interna para almacenamiento de datos en la nube
    private static class Data {
        String owner, device;
        Point2D pos;
        Data(String o, String d, Point2D p) { owner = o; device = d; pos = p; }
    }
}