import java.util.ArrayList;

// Gestor del espacio físico y comunicaciones locales
public class Territory {
    private ArrayList<Cellular> cells = new ArrayList<>();
    private ArrayList<EloTelTag> tags = new ArrayList<>();
    private ArrayList<Tablet> tablets = new ArrayList<>();

    public void addCellular(Cellular c) { cells.add(c); }
    public void addTag(EloTelTag t) { tags.add(t); }
    public void addTablet(Tablet tab) { tablets.add(tab); }

    public Cellular getCellular(String owner) {
        for (Cellular c : cells) if (c.getOwnerName().equals(owner)) return c;
        return null;
    }

    public Equipo getAny(String owner, String name) {
        if (name.equals("celular")) return getCellular(owner);
        for (EloTelTag t : tags) if (t.getOwnerName().equals(owner) && t.getName().equals(name)) return t;
        for (Tablet tab : tablets) if (tab.getOwnerName().equals(owner) && tab.getName().equals(name)) return tab;
        return null;
    }

    // Escaneo de proximidad para reportes indirectos (Tags y Tablets)
    public void checkAndReport() {
        for (Cellular c : cells) {
            for (EloTelTag t : tags) if (t.isWithinRange(c)) c.reportLocation(t);
            for (Tablet tab : tablets) if (tab.isWithinRange(c)) c.reportLocation(tab);
        }
    }
}