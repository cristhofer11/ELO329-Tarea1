public class Cellular extends Equipo {
    private ETNube nube;

    // Constructor del celular con dueño, posición inicial y referencia a la nube
    public Cellular(String owner, float x, float y, ETNube nube) {
        super(owner, x, y);
        this.nube = nube;
    }

    // Reporta a la nube la ubicación de un tag usando la posición actual del celular
    public void reportTagLocation(EloTelTag tag) {
        nube.updateLocation(tag.getOwnerName(), tag.getName(), getX(), getY());
    }
}