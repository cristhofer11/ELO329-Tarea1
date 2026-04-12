// Clase base abstracta para la jerarquía de dispositivos
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

    public float getX() { return x; }
    public float getY() { return y; }
    public String getOwnerName() { return ownerName; }
    public String getName() { return name; }

    // Actualización de coordenadas físicas
    public void move(float dx, float dy) {
        this.x += dx;
        this.y += dy;
    }
}