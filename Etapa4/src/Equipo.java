public class Equipo {
    protected final String ownerName;
    protected float x;
    protected float y;

    // Constructor del equipo con dueño y posición inicial
    public Equipo(String owner, float x, float y) {
        ownerName = owner;
        this.x = x;
        this.y = y;
    }

    // Retorna coordenada x
    public float getX() {
        return x;
    }

    // Retorna coordenada y
    public float getY() {
        return y;
    }

    // Mueve el equipo según los desplazamientos recibidos
    public void move(float deltaX, float deltaY) {
        x += deltaX;
        y += deltaY;
    }

    // Retorna el nombre del dueño
    public String getOwnerName() {
        return ownerName;
    }

    // Retorna el encabezado para el CSV
    public String getHeader() {
        return ownerName + ".x\t" + ownerName + ".y";
    }

    // Retorna el estado actual del equipo para el CSV
    public String getState() {
        return x + "\t" + y;
    }
}