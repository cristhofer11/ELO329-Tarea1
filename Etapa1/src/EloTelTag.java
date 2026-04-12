// Definición de la clase para etiquetas de telemetría
public class EloTelTag {
    private final String name;
    private final String ownerName;
    private float x, y;

    // Inicialización de los atributos del tag
    public EloTelTag(String owner, String n, float _x, float _y) {
        this.ownerName = owner;
        this.name = n;
        this.x = _x;
        this.y = _y;
    }

    // Obtención del nombre del tag
    public String getName() { return name; }
    
    // Obtención del nombre del propietario
    public String getOwnerName() { return ownerName; }

    // Actualización de la posición según los deltas recibidos
    public void move(float delta_x, float delta_y) {
        this.x += delta_x;
        this.y += delta_y;
    }

    // Generación de la cabecera para las columnas del CSV
    public String getHeader() {
        return ownerName + "." + name + ".x\t" + ownerName + "." + name + ".y";
    }

    // Generación del archivo CSV de salida
    public String getState() {
        return x + "\t" + y;
    }
}