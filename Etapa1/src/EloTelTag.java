public class EloTelTag {
    private final String name;
    private final String ownerName;
    private double x, y;

    public EloTelTag(String owner, String n, double _x, double _y) {
        this.ownerName = owner;
        this.name = n;
        this.x = _x;
        this.y = _y;
    }

    public String getName() { return name; }
    public String getOwnerName() { return ownerName; }

    public void move(double delta_x, double delta_y) {
        this.x += delta_x;
        this.y += delta_y;
    }

    public String getState() {
        return x + "\t" + y;
    }
}