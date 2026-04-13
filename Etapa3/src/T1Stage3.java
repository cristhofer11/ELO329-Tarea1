import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class T1Stage3 {
    private Territory territory;
    private ETNube nube;
    private int step = 0;

    // Constructor: crea territorio y nube
    public T1Stage3() {
        territory = new Territory();
        nube = new ETNube();
    }

    public static void main(String[] args) throws IOException {
        // Se esperan 2 argumentos:
        // 1) archivo de configuración
        // 2) archivo de movimientos
        if (args.length != 2) {
            System.out.println("Usage: java T1Stage3 <configFile> <moveFile>");
            System.exit(-1);
        }

        Scanner confFile = new Scanner(new File(args[0]));
        Scanner movFile = new Scanner(new File(args[1]));
        PrintStream outCsv = new PrintStream(new File("output.csv"));

        T1Stage3 stage = new T1Stage3();
        stage.setupSimulator(confFile);

        // Después de crear todo, los celulares reportan los tags cercanos
        stage.territory.forEachTagTryToReportLocation();

        // Ejecutar la simulación
        stage.runSimulation(movFile, outCsv);

        confFile.close();
        movFile.close();
        outCsv.close();
    }

    // Lee la configuración y crea los objetos iniciales
    public void setupSimulator(Scanner in) {
        if (!in.hasNextInt()) {
            return;
        }

        int personNumber = in.nextInt();

        for (int i = 0; i < personNumber; i++) {
            setupPersonEquipment(in);
        }
    }

    // Crea los equipos de una persona
    private void setupPersonEquipment(Scanner in) {
        String personName = in.next();
        int tagNumber = in.nextInt();
        boolean isThereTablet = in.nextInt() == 1;

        float celularX = in.nextFloat();
        float celularY = in.nextFloat();

        Viewer viewerCelular = new Viewer(personName, nube);
        Cellular celular = new Cellular(personName, celularX, celularY, nube, viewerCelular);
        territory.addCellular(celular);
        nube.updateLocation(personName, "celular", celularX, celularY);

        for (int j = 0; j < tagNumber; j++) {
            setupEloTag(in, personName);
        }

        if (isThereTablet) {
            float tabletX = in.nextFloat();
            float tabletY = in.nextFloat();

            Viewer viewerTablet = new Viewer(personName, nube);
            Tablet tablet = new Tablet(personName, tabletX, tabletY, viewerTablet);
            territory.addTablet(tablet);

            // Se deja registrado para mantener completo el header del CSV
            nube.updateLocation(personName, "tablet", tabletX, tabletY);
        }
    }

    // Crea un tag y lo agrega al territorio
    private void setupEloTag(Scanner in, String personName) {
        String tagName = in.next();
        float x = in.nextFloat();
        float y = in.nextFloat();

        EloTelTag tag = new EloTelTag(personName, tagName, x, y);
        territory.addTag(tag);

        // Se deja registrado para mantener completo el header del CSV
        nube.updateLocation(personName, tagName, x, y);
    }

    // Ejecuta los comandos del archivo de movimientos
    public void runSimulation(Scanner in, PrintStream output) {
        nube.printHeader(output);
        nube.printState(output, step);

        while (in.hasNext()) {
            String equipment = in.next();

            if (!equipment.contains(".")) {
                continue;
            }

            String[] parts = equipment.split("\\.");
            if (parts.length != 2) {
                continue;
            }

            String ownerName = parts[0];
            String equipmentName = parts[1];

            if (!in.hasNext()) {
                break;
            }

            // Puede venir FindMy o un desplazamiento
            if (!in.hasNextFloat()) {
                String command = in.next();

                if (command.equals("FindMy")) {
                    processFindMy(ownerName, equipmentName);
                }

                continue;
            }

            float deltaX = in.nextFloat();

            if (!in.hasNextFloat()) {
                continue;
            }

            float deltaY = in.nextFloat();

            moveEquipment(ownerName, equipmentName, deltaX, deltaY);

            // Después de cada movimiento, los celulares reportan tags y tablets cercanos
            territory.forEachTagTryToReportLocation();
            territory.forEachTabletTryToReportLocation();

            step++;
            nube.printState(output, step);
        }
    }

    // Procesa el comando FindMy
    private void processFindMy(String ownerName, String equipmentName) {
        if (equipmentName.equals("celular")) {
            Cellular celular = territory.getCellular(ownerName);
            if (celular != null) {
                celular.findMy();
            }
        } else if (equipmentName.equals("tablet")) {
            Tablet tablet = territory.getTablet(ownerName);
            if (tablet != null) {
                tablet.findMy();
            }
        }
    }

    // Mueve el equipo indicado
    private void moveEquipment(String ownerName, String equipmentName, float deltaX, float deltaY) {
        if (equipmentName.equals("celular")) {
            Cellular celular = territory.getCellular(ownerName);
            if (celular != null) {
                celular.move(deltaX, deltaY);
                nube.updateLocation(ownerName, "celular", celular.getX(), celular.getY());
            }
        } else if (equipmentName.equals("tablet")) {
            Tablet tablet = territory.getTablet(ownerName);
            if (tablet != null) {
                tablet.move(deltaX, deltaY);
            }
        } else {
            EloTelTag tag = territory.getTag(ownerName, equipmentName);
            if (tag != null) {
                tag.move(deltaX, deltaY);
            }
        }
    }
}