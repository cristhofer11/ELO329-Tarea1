import java.awt.geom.Point2D;
import java.io.PrintStream;
import java.util.ArrayList;

public class ETNube {
    private ArrayList<Data> cloudData;

    // Constructor: crea la estructura donde se guardan los datos reportados
    public ETNube() {
        cloudData = new ArrayList<Data>();
    }

    // Actualiza la última ubicación reportada de un equipo
    public void updateLocation(String owner, String equipment, float x, float y) {
        Point2D location;

        if ((location = getLocation(owner, equipment)) == null) {
            location = new Point2D.Float(x, y);
            Data data = new Data(owner, equipment, location);
            cloudData.add(data);
        }

        location.setLocation(x, y);
    }

    // Busca la ubicación registrada de un equipo en la nube
    public Point2D getLocation(String owner, String equipment) {
        for (Data data : cloudData) {
            if (data.ownerName.equals(owner) && data.equipmentName.equals(equipment)) {
                return data.location;
            }
        }
        return null;
    }

    // Imprime la primera línea del CSV con los nombres de las columnas
    public void printHeader(PrintStream output) {
        output.print("Step");
        for (Data data : cloudData) {
            output.print("\t" + data.ownerName + "." + data.equipmentName + ".x");
            output.print("\t" + data.ownerName + "." + data.equipmentName + ".y");
        }
        output.println();
    }

    // Imprime una fila con el paso actual y las posiciones registradas en la nube
    public void printState(PrintStream output, int step) {
        output.print(step);
        for (Data data : cloudData) {
            output.print("\t" + data.location.getX());
            output.print("\t" + data.location.getY());
        }
        output.println();
    }

    // Clase interna para guardar la información de cada equipo reportado
    private static class Data {
        public String ownerName;
        public String equipmentName;
        public Point2D location;

        public Data(String owner, String equipment, Point2D loc) {
            ownerName = owner;
            equipmentName = equipment;
            location = loc;
        }
    }
}