import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class T1Stage1 {
    private Territory territory;
    private int step = 0;

    // Constructor: crea el territorio donde se registran los tags
    public T1Stage1() {
        territory = new Territory();
    }

    public static void main(String args[]) throws IOException {
        // Se esperan exactamente 2 argumentos:
        // 1) archivo de configuración
        // 2) archivo de movimientos
        if (args.length != 2) {
            System.out.println("Usage: java T1Stage1 <configFile> <moveFile>");
            System.exit(-1);
        }

        // Abrir archivos de entrada y archivo de salida
        Scanner confFile = new Scanner(new File(args[0]));
        Scanner movFile = new Scanner(new File(args[1]));
        PrintStream outCsv = new PrintStream(new File("output.csv"));

        // Crear simulador, cargar configuración y ejecutar movimientos
        T1Stage1 stage = new T1Stage1();
        stage.setupSimulator(confFile);
        stage.runSimulation(movFile, outCsv);

        // Cerrar archivos abiertos
        confFile.close();
        movFile.close();
        outCsv.close();
    }

    // Lee el archivo de configuración y crea los tags iniciales
    public void setupSimulator(Scanner in) {
        // Si no hay número de personas, termina
        if (!in.hasNextInt()) {
            return;
        }

        int personNumber = in.nextInt();

        // Leer los datos de cada persona
        for (int i = 0; i < personNumber; i++) {
            String personName = in.next();
            int tagNumber = in.nextInt();
            boolean isThereTablet = in.nextInt() == 1;

            // En etapa 1 se ignora la posición del celular
            in.nextFloat();
            in.nextFloat();

            // Leer y crear los tags de la persona
            for (int j = 0; j < tagNumber; j++) {
                String tagName = in.next();
                float x = in.nextFloat();
                float y = in.nextFloat();
                territory.addTag(new EloTelTag(personName, tagName, x, y));
            }

            // En etapa 1 se ignora la posición del tablet si existe
            if (isThereTablet) {
                in.nextFloat();
                in.nextFloat();
            }
        }
    }

    // Procesa el archivo de movimientos y actualiza el CSV
    public void runSimulation(Scanner in, PrintStream output) {
        // Escribir encabezado del archivo y estado inicial
        territory.printHeader(output);
        territory.printState(output, step);

        // Leer cada comando del archivo de movimientos
        while (in.hasNext()) {
            String equipment = in.next();

            // El nombre del objeto debe tener formato Dueño.Tag
            if (!equipment.contains(".")) {
                continue;
            }

            String[] parts = equipment.split("\\.");

            // Debe dividirse en exactamente 2 partes
            if (parts.length != 2) {
                continue;
            }

            // Si no hay más datos, se termina la lectura
            if (!in.hasNext()) {
                break;
            }

            // Si el siguiente token no es número,
            // entonces no es un desplazamiento y se ignora
            if (!in.hasNextFloat()) {
                in.next();
                continue;
            }

            float deltaX = in.nextFloat();

            // El segundo valor del desplazamiento también debe ser número
            if (!in.hasNextFloat()) {
                continue;
            }

            float deltaY = in.nextFloat();

            // Buscar el tag correspondiente
            EloTelTag tag = territory.getTag(parts[0], parts[1]);

            // Si el tag existe, moverlo y registrar nuevo estado
            if (tag != null) {
                tag.move(deltaX, deltaY);
                step++;
                territory.printState(output, step);
            }
        }
    }
}