import java.io.PrintStream;
import java.util.ArrayList;

public class Territory {
    private ArrayList<EloTelTag> tags = new ArrayList<EloTelTag>();

    public void addTag(EloTelTag tag) {
        tags.add(tag);
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

    // Imprime la primera línea del CSV con los nombres de las columnas
    public void printHeader(PrintStream output) {
        output.print("Step");
        for (EloTelTag tag : tags) {
            output.print("\t" + tag.getHeader());
        }
        output.println();
    }

    // Imprime una fila con el paso actual y las posiciones de todos los tags
    public void printState(PrintStream output, int step) {
        output.print(step);
        for (EloTelTag tag : tags) {
            output.print("\t" + tag.getState());
        }
        output.println();
    }
}