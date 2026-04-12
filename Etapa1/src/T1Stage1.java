import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Locale;

public class T1Stage1 {
    private Territory territory = new Territory();
    private int step = 0;

    public static void main(String args[]) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage: java T1Stage1 <configFile> <moveFile>");
            return;
        }

        T1Stage1 stage = new T1Stage1();
        Scanner confFile = new Scanner(new File(args[0])).useLocale(Locale.US);
        stage.setupSimulator(confFile);
        confFile.close();

        Scanner movFile = new Scanner(new File(args[1])).useLocale(Locale.US);
        PrintStream csvOutput = new PrintStream(new File("output.csv"));
        
        stage.runSimulation(movFile, csvOutput);
        
        movFile.close();
        csvOutput.close();
        System.out.println("Simulación finalizada. Resultados en output.csv");
    }

    public void setupSimulator(Scanner in) {
        if (!in.hasNextInt()) return;
        int personNumber = in.nextInt();
        for (int i = 0; i < personNumber; i++) {
            String personName = in.next();
            int tagNumber = in.nextInt();
            boolean hasTablet = (in.nextInt() == 1);

            // AGREGAR CELULAR (Antes lo saltábamos, ahora lo guardamos)
            double celX = in.nextDouble();
            double celY = in.nextDouble();
            territory.addTag(new EloTelTag(personName, "celular", celX, celY));

            // AGREGAR TAGS
            for (int j = 0; j < tagNumber; j++) {
                String tagName = in.next();
                double x = in.nextDouble();
                double y = in.nextDouble();
                territory.addTag(new EloTelTag(personName, tagName, x, y));
            }

            // AGREGAR TABLET (Si existe)
            if (hasTablet) {
                double tabX = in.nextDouble();
                double tabY = in.nextDouble();
                territory.addTag(new EloTelTag(personName, "tablet", tabX, tabY));
            }
        }
    }

    public void runSimulation(Scanner in, PrintStream output) {
        territory.printHeader(output);
        territory.printState(output, step);

        while (in.hasNext()) {
            String equipment = in.next();
            
            if (!in.hasNextDouble()) {
                if (in.hasNext()) in.next(); // Consume "FindMy"
            } else {
                double deltaX = in.nextDouble();
                double deltaY = in.nextDouble();

                String[] parts = equipment.split("\\.");
                if (parts.length >= 2) {
                    // Ahora esto encontrará tanto tags como celulares/tablets
                    EloTelTag tag = territory.getTag(parts[0], parts[1]);
                    if (tag != null) {
                        tag.move(deltaX, deltaY);
                    }
                }
            }
            step++;
            territory.printState(output, step);
        }
    }
}