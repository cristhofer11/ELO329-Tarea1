import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class T1Stage1 {
    private Territory territory;
    private int step = 0;

    T1Stage1() {
        territory = new Territory();
    }

    public static void main(String args[]) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage: java T1Stage1 <configFile> <moveFile>");
            System.exit(-1);
        }

        Scanner confFile = new Scanner(new File(args[0]));
        Scanner movFile = new Scanner(new File(args[1]));
        
        // Generación del archivo CSV de salida 
        PrintStream outCsv = new PrintStream(new File("output.csv"));

        T1Stage1 stage = new T1Stage1();
        stage.setupSimulator(confFile);
        stage.runSimulation(movFile, outCsv);
        
        outCsv.close();
    }

    public void setupSimulator(Scanner in) {
        if (!in.hasNextInt()) return;
        int personNumber = in.nextInt();
        for (int i = 0; i < personNumber; i++) {
            String personName = in.next();
            int tagNumber = in.nextInt();
            boolean isThereTablet = in.nextInt() == 1;
            
            // Ignorar posición del celular en esta etapa [cite: 71, 125]
            in.nextFloat(); in.nextFloat(); 
            
            for (int j = 0; j < tagNumber; j++) {
                String tagName = in.next();
                float x = in.nextFloat();
                float y = in.nextFloat();
                territory.addTag(new EloTelTag(personName, tagName, x, y));
            }
            
            // Ignorar posición de tablet si existe [cite: 73, 125]
            if (isThereTablet) {
                in.nextFloat(); in.nextFloat();
            }
        }
    }

    public void runSimulation(Scanner in, PrintStream output) {
        territory.printHeader(output);
        territory.printState(output, step); // Paso 0: Estado inicial 
        
        while (in.hasNext()) {
            String equipment = in.next();
            // Solo procesamos tags con formato Dueño.Tag en esta etapa [cite: 125]
            if (equipment.contains(".")) {
                String[] parts = equipment.split("\\.");
                float deltaX = in.nextFloat();
                float deltaY = in.nextFloat();
                
                EloTelTag tag = territory.getTag(parts[0], parts[1]);
                if (tag != null) {
                    tag.move(deltaX, deltaY);
                    step++;
                    territory.printState(output, step);
                }
            }
        }
    }
}