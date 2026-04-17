import java.awt.geom.Point2D;
import java.io.PrintStream;
import java.util.ArrayList;

public class ETNube {
    private ArrayList<Data> cloudData;

    // Constructor: crea la lista donde se guardan las ubicaciones reportadas
    public ETNube() {
        cloudData = new ArrayList<Data>();
    }

    // Actualiza la ubicación de un equipo en la nube
    public void updateLocation(String owner, String equipment, float x, float y) {
        Point2D location = getLocation(owner, equipment);

        if (location == null) {
            location = new Point2D.Float(x, y);
            Data data = new Data(owner, equipment, location);
            cloudData.add(data);
        } else {
            location.setLocation(x, y);
        }
    }

    // Retorna la ubicación guardada de un equipo
    public Point2D getLocation(String owner, String equipment) {
        for (int i = 0; i < cloudData.size(); i++) {
            Data data = cloudData.get(i);
            if (data.ownerName.equals(owner) && data.equipmentName.equals(equipment)) {
                return data.location;
            }
        }
        return null;
    }

    // Imprime el encabezado del archivo CSV
    public void printHeader(PrintStream output) {
        output.print("Step");
        for (int i = 0; i < cloudData.size(); i++) {
            Data data = cloudData.get(i);
            output.print("\t" + data.ownerName + "." + data.equipmentName + ".x");
            output.print("\t" + data.ownerName + "." + data.equipmentName + ".y");
        }
        output.println();
    }

    // Imprime el estado actual de la nube en un paso dado
    public void printState(PrintStream output, int step) {
        output.print(step);
        for (int i = 0; i < cloudData.size(); i++) {
            Data data = cloudData.get(i);
            output.print("\t" + data.location.getX());
            output.print("\t" + data.location.getY());
        }
        output.println();
    }

    // Muestra por pantalla todos los equipos registrados de una persona
    public void printOwnerLocations(String ownerName) {
        System.out.println("Equipos de " + ownerName + ":");
        for (int i = 0; i < cloudData.size(); i++) {
            Data data = cloudData.get(i);
            if (data.ownerName.equals(ownerName)) {
                System.out.println(
                    data.equipmentName + ": " +
                    data.location.getX() + ", " +
                    data.location.getY()
                );
            }
        }
    }

    // Clase interna para guardar dueño, nombre de equipo y ubicación
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