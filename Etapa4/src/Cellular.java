public class Cellular extends Equipo {
    private ETNube nube;
    private Viewer viewer;

    // Constructor del celular con dueño, posición, nube y visor
    public Cellular(String owner, float x, float y, ETNube nube, Viewer viewer) {
        super(owner, x, y);
        this.nube = nube;
        this.viewer = viewer;
    }

    // Reporta a la nube la ubicación de un tag usando la posición actual del celular
    public void reportTagLocation(EloTelTag tag) {
        nube.updateLocation(tag.getOwnerName(), tag.getName(), getX(), getY());
    }

    // Reporta a la nube la ubicación de un tablet usando la posición actual del celular
    public void reportTabletLocation(Tablet tablet) {
        nube.updateLocation(tablet.getOwnerName(), "tablet", getX(), getY());
    }

    // Muestra en pantalla la información de FindMy del dueño del celular
    public void findMy() {
        viewer.show("celular");
    }

    @Override
    public String getHeader() {
        return ownerName + ".celular.x\t" + ownerName + ".celular.y";
    }
}