public class Viewer {
    private String ownerName;
    private ETNube nube;

    // Constructor del visor
    public Viewer(String ownerName, ETNube nube) {
        this.ownerName = ownerName;
        this.nube = nube;
    }

    // Muestra por pantalla las ubicaciones registradas de la persona
    public void show(String desde) {
        System.out.println();
        System.out.println("=== FindMy de " + ownerName + " desde " + desde + " ===");
        nube.printOwnerLocations(ownerName);
        System.out.println("==============================");
        System.out.println();
    }
}