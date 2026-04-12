import java.io.PrintStream;
import java.util.ArrayList;

public class Territory {
    private ArrayList<EloTelTag> tags = new ArrayList<>();

    public void addTag(EloTelTag tag) {
        tags.add(tag);
    }

    public EloTelTag getTag(String ownerName, String equipmentName) {
        for (EloTelTag tag : tags) {
            if (tag.getName().equals(equipmentName) && tag.getOwnerName().equals(ownerName)) {
                return tag;
            }
        }
        return null;
    }

    public void printHeader(PrintStream output) {
        output.print("Step\t");
        for (EloTelTag tag : tags) {
            output.print(tag.getOwnerName() + "." + tag.getName() + ".x\t");
            output.print(tag.getOwnerName() + "." + tag.getName() + ".y\t");
        }
        output.println();
    }

    public void printState(PrintStream output, int step) {
        output.print(step + "\t");
        for (EloTelTag tag : tags) {
            output.print(tag.getState() + "\t");
        }
        output.println();
    }
}