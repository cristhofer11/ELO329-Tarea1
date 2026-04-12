// Clase base para todos los dispositivos del sistema
public abstract class Equipo {
    protected final String ownerName;
    protected final String name;
    protected float x, y;

    public Equipo(String owner, String name, float x, float y) {
        this.ownerName = owner;
        this.name = name;
        this.x = x;
        this.y = y;
    }

    // Métodos de acceso a atributos comunes
    public float getX() { return x; }
    public float getY() { return y; }
    public String getOwnerName() { return ownerName; }
    public String getName() { return name; }

    // Actualización de posición en el plano
    public void move(float dx, float dy) {
        this.x += dx;
        this.y += dy;
    }
}