import java.util.ArrayList;

// Territorio donde están ubicados y se mueven celulares, tags y tablets
public class Territory {
    private ArrayList<Cellular> cellulars = new ArrayList<Cellular>();
    private ArrayList<EloTelTag> tags = new ArrayList<EloTelTag>();
    private ArrayList<Tablet> tablets = new ArrayList<Tablet>();

    // Agrega un celular al territorio
    public void addCellular(Cellular cel) {
        cellulars.add(cel);
    }

    // Agrega un tag al territorio
    public void addTag(EloTelTag tag) {
        tags.add(tag);
    }

    // Agrega un tablet al territorio
    public void addTablet(Tablet tablet) {
        tablets.add(tablet);
    }

    // Para cada tag, busca un celular cercano y reporta su ubicación a la nube
    public void forEachTagTryToReportLocation() {
        for (int i = 0; i < tags.size(); i++) {
            EloTelTag tag = tags.get(i);
            Cellular celularCercano = findNearByCellular(tag);

            if (celularCercano != null) {
                celularCercano.reportTagLocation(tag);
            }
        }
    }

    // Para cada tablet, busca un celular cercano y reporta su ubicación a la nube
    public void forEachTabletTryToReportLocation() {
        for (int i = 0; i < tablets.size(); i++) {
            Tablet tablet = tablets.get(i);
            Cellular celularCercano = findNearByCellular(tablet);

            if (celularCercano != null) {
                celularCercano.reportTabletLocation(tablet);
            }
        }
    }

    // Busca un celular cercano a un tag
    private Cellular findNearByCellular(EloTelTag tag) {
        for (int i = 0; i < cellulars.size(); i++) {
            Cellular cell = cellulars.get(i);
            if (tag.isWithinRange(cell)) {
                return cell;
            }
        }
        return null;
    }

    // Busca un celular cercano a un tablet
    private Cellular findNearByCellular(Tablet tablet) {
        for (int i = 0; i < cellulars.size(); i++) {
            Cellular cell = cellulars.get(i);
            if (tablet.isWithinRange(cell)) {
                return cell;
            }
        }
        return null;
    }

    // Retorna el celular de una persona
    public Cellular getCellular(String ownerName) {
        for (int i = 0; i < cellulars.size(); i++) {
            Cellular cell = cellulars.get(i);
            if (cell.getOwnerName().equals(ownerName)) {
                return cell;
            }
        }
        return null;
    }

    // Retorna el tablet de una persona
    public Tablet getTablet(String ownerName) {
        for (int i = 0; i < tablets.size(); i++) {
            Tablet tablet = tablets.get(i);
            if (tablet.getOwnerName().equals(ownerName)) {
                return tablet;
            }
        }
        return null;
    }

    // Busca un tag específico por dueño y nombre
    public EloTelTag getTag(String ownerName, String equipmentName) {
        for (int i = 0; i < tags.size(); i++) {
            EloTelTag tag = tags.get(i);
            if (tag.getOwnerName().equals(ownerName) && tag.getName().equals(equipmentName)) {
                return tag;
            }
        }
        return null;
    }
}