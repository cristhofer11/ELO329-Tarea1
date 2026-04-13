import java.util.ArrayList;

// Territorio donde están ubicados y se mueven celulares y tags
public class Territory {
    private ArrayList<Cellular> cellulars = new ArrayList<Cellular>();
    private ArrayList<EloTelTag> tags = new ArrayList<EloTelTag>();

    // Agrega un celular al territorio
    public void addCellular(Cellular cel) {
        cellulars.add(cel);
    }

    // Agrega un tag al territorio
    public void addTag(EloTelTag tag) {
        tags.add(tag);
    }

    // Para cada tag, busca un celular cercano y, si existe, reporta su ubicación a la nube
    public void forEachTagTryToReportLocation() {
        for (EloTelTag tag : tags) {
            Cellular cell = findNearByCellular(tag);
            if (cell != null) {
                cell.reportTagLocation(tag);
            }
        }
    }

    // Busca un celular que esté a menos de 10 m del tag
    private Cellular findNearByCellular(EloTelTag tag) {
        for (Cellular cell : cellulars) {
            if (tag.isWithinRange(cell)) {
                return cell;
            }
        }
        return null;
    }

    // Retorna el celular de una persona según el nombre de su dueño
    public Cellular getCellular(String ownerName) {
        for (Cellular cell : cellulars) {
            if (cell.getOwnerName().equals(ownerName)) {
                return cell;
            }
        }
        return null;
    }

    // Busca un tag específico por dueño y nombre
    public EloTelTag getTag(String ownerName, String equipmentName) {
        for (EloTelTag tag : tags) {
            if (tag.getOwnerName().equals(ownerName) && tag.getName().equals(equipmentName)) {
                return tag;
            }
        }
        return null;
    }
}