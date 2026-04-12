import java.util.ArrayList;

// Administrador de dispositivos y lógica de cercanía
public class Territory {
    private ArrayList<Cellular> cells = new ArrayList<>();
    private ArrayList<EloTelTag> tags = new ArrayList<>();

    public void addCellular(Cellular c) { cells.add(c); }
    public void addTag(EloTelTag t) { tags.add(t); }

    // Búsqueda de celular por nombre de dueño
    public Cellular getCellular(String owner) {
        for (Cellular c : cells) if (c.getOwnerName().equals(owner)) return c;
        return null;
    }

    // Búsqueda de tag por dueño y nombre del dispositivo
    public EloTelTag getTag(String owner, String name) {
        for (EloTelTag t : tags) {
            if (t.getOwnerName().equals(owner) && t.getName().equals(name)) return t;
        }
        return null;
    }

    // Verificación de rangos y activación de reportes
    public void checkAndReport() {
        for (EloTelTag t : tags) {
            for (Cellular c : cells) {
                if (t.isWithinRange(c)) {
                    c.reportTagLocation(t);
                    break;
                }
            }
        }
    }
}