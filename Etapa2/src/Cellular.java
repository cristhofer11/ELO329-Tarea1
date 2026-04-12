// Dispositivo móvil con capacidad de reporte a la nube
public class Cellular extends Equipo {
    private ETNube nube;

    public Cellular(String owner, float x, float y, ETNube nube) {
        super(owner, "celular", x, y);
        this.nube = nube;
    }

    // Reporte de posición detectada hacia la nube
    public void reportTagLocation(EloTelTag tag) {
        nube.updateLocation(tag.getOwnerName(), tag.getName(), this.x, this.y);
    }
}