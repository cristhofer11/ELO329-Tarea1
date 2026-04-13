// Definición de la clase para representar un EloTelTag
public class EloTelTag {
    private final String name;
    private final String ownerName;
    private float x;
    private float y;

    // Constructor del tag con nombre, dueño y posición inicial
    public EloTelTag(String ownerName, String name, float x, float y) {
        this.ownerName = ownerName;
        this.name = name;
        this.x = x;
        this.y = y;
    }

    // Retorna el nombre del tag
    public String getName() {
        return name;
    }

    // Retorna el nombre del dueño del tag
    public String getOwnerName() {
        return ownerName;
    }

    // Mueve el tag según los desplazamientos recibidos
    public void move(float deltaX, float deltaY) {
        x += deltaX;
        y += deltaY;
    }

    // Retorna los nombres de las columnas que usa este tag en el CSV
    public String getHeader() {
        return ownerName + "." + name + ".x\t" + ownerName + "." + name + ".y";
    }

    // Retorna la posición actual del tag para escribirla en el CSV
    public String getState() {
        return x + "\t" + y;
    }
}