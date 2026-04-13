import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class T1Stage2 {
    private int step = 0;
    private Territory territory;
    private ETNube nube;

    // Constructor: crea el territorio y la nube
    public T1Stage2() {
        territory = new Territory();
        nube = new ETNube();
    }

    public static void main(String args[]) throws IOException {
        // Se esperan 2 argumentos:
        // 1) archivo de configuración
        // 2) archivo de movimientos
        if (args.length != 2) {
            System.out.println("Usage: java T1Stage2 <configFile> <moveFile>");
            System.exit(-1);
        }

        Scanner confFile = new Scanner(new File(args[0]));
        Scanner movFile = new Scanner(new File(args[1]));
        PrintStream outCsv = new PrintStream(new File("output.csv"));

        T1Stage2 stage = new T1Stage2();

        // Leer configuración y crear objetos
        stage.setupSimulator(confFile);

        // Luego de crear todo, los celulares intentan reportar los tags cercanos
        stage.territory.forEachTagTryToReportLocation();

        // Ejecutar movimientos y generar salida
        stage.runSimulation(movFile, outCsv);

        confFile.close();
        movFile.close();
        outCsv.close();
    }

    // Lee la configuración inicial del archivo
    public void setupSimulator(Scanner in) {
        if (!in.hasNextInt()) {
            return;
        }

        int personNumber = in.nextInt();

        for (int i = 0; i < personNumber; i++) {
            setupPersonEquipment(in);
        }
    }

    // Crea el celular y los tags de una persona
    private void setupPersonEquipment(Scanner in) {
        Cellular cellular;
        float x;
        float y;

        String personName = in.next();
        int tagNumber = in.nextInt();
        boolean isThereTablet = in.nextInt() == 1;

        // Leer posición inicial del celular
        x = in.nextFloat();
        y = in.nextFloat();

        // Crear celular, agregarlo al territorio y registrar su posición en la nube
        cellular = new Cellular(personName, x, y, nube);
        territory.addCellular(cellular);
        nube.updateLocation(personName, "celular", x, y);

        // Crear los tags de la persona
        for (int j = 0; j < tagNumber; j++) {
            setupEloTags(in, personName);
        }

        // En etapa 2 el tablet aún no se modela, solo se ignora su posición si existe
        if (isThereTablet) {
            in.nextFloat();
            in.nextFloat();
        }
    }

    // Crea un tag, lo agrega al territorio y lo deja registrado en la nube
    // para que el encabezado del CSV quede completo desde el paso 0
    private void setupEloTags(Scanner in, String personName) {
        float x;
        float y;

        String tagName = in.next();
        x = in.nextFloat();
        y = in.nextFloat();

        EloTelTag tag = new EloTelTag(personName, tagName, x, y);
        territory.addTag(tag);
        nube.updateLocation(personName, tagName, x, y);
    }

    // Ejecuta los comandos del archivo de movimientos
    public void runSimulation(Scanner in, PrintStream output) {
        // En esta etapa el CSV debe salir desde la nube
        nube.printHeader(output);
        nube.printState(output, step);

        while (in.hasNext()) {
            String equipment = in.next();

            // Debe venir en formato Dueño.Equipo
            if (!equipment.contains(".")) {
                continue;
            }

            String[] parts = equipment.split("\\.");

            if (parts.length != 2) {
                continue;
            }

            String personName = parts[0];
            String equipmentName = parts[1];

            // En etapa 2 solo se procesan desplazamientos
            if (!in.hasNextFloat()) {
                in.next();
                continue;
            }

            float deltaX = in.nextFloat();

            if (!in.hasNextFloat()) {
                continue;
            }

            float deltaY = in.nextFloat();

            // Mover celular o tag según corresponda
            if (equipmentName.equals("celular")) {
                Cellular cellular = territory.getCellular(personName);
                if (cellular != null) {
                    cellular.move(deltaX, deltaY);

                    // Actualizar la nueva posición del celular en la nube
                    nube.updateLocation(personName, "celular", cellular.getX(), cellular.getY());
                }
            } else {
                EloTelTag tag = territory.getTag(personName, equipmentName);
                if (tag != null) {
                    tag.move(deltaX, deltaY);
                }
            }

            // Después de cada movimiento, los celulares reportan los tags cercanos
            territory.forEachTagTryToReportLocation();

            // Avanzar de paso e imprimir estado actual de la nube
            step++;
            nube.printState(output, step);
        }
    }
}