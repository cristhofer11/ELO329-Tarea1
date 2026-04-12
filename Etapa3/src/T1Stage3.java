import java.io.*;
import java.util.*;

// Driver principal para la simulación de red distribuida
public class T1Stage3 {
    private Territory territory = new Territory();
    private ETNube nube = new ETNube();
    private int step = 0;

    public static void main(String[] args) throws IOException {
        if (args.length != 2) return;
        Scanner conf = new Scanner(new File(args[0])).useLocale(Locale.US);
        Scanner mov = new Scanner(new File(args[1])).useLocale(Locale.US);
        
        T1Stage3 sim = new T1Stage3();
        sim.setup(conf);
        sim.run(mov);
    }

    // Inicialización del territorio y registro inicial en nube
    private void setup(Scanner sc) {
        if (!sc.hasNextInt()) return;
        int n = sc.nextInt();
        while (n-- > 0) {
            String owner = sc.next();
            int tagNum = sc.nextInt();
            boolean hasTab = sc.nextInt() == 1;
            float cx = sc.nextFloat(), cy = sc.nextFloat();
            
            Cellular c = new Cellular(owner, cx, cy, nube);
            territory.addCellular(c);
            nube.updateLocation(owner, "celular", cx, cy);

            for (int i=0; i<tagNum; i++) {
                String tName = sc.next();
                float tx = sc.nextFloat(), ty = sc.nextFloat();
                territory.addTag(new EloTelTag(owner, tName, tx, ty));
                nube.updateLocation(owner, tName, tx, ty);
            }
            if (hasTab) {
                float tx = sc.nextFloat(), ty = sc.nextFloat();
                territory.addTablet(new Tablet(owner, "Tablet", tx, ty, nube));
                nube.updateLocation(owner, "Tablet", tx, ty);
            }
        }
    }

    // Ciclo de vida de la simulación y activación de App FindMy
    private void run(Scanner mov) throws IOException {
        PrintStream csv = new PrintStream(new File("output.csv"));
        nube.printHeader(csv);
        nube.printState(csv, step);

        while (mov.hasNext()) {
            step++;
            String[] parts = mov.next().split("\\.");
            float dx = mov.nextFloat(), dy = mov.nextFloat();

            Equipo eq = territory.getAny(parts[0], parts[1]);
            if (eq != null) {
                eq.move(dx, dy);
                if (eq instanceof Cellular) nube.updateLocation(eq.getOwnerName(), "celular", eq.getX(), eq.getY());
            }

            territory.checkAndReport();
            nube.printState(csv, step);
            
            // Simulación de interacción del usuario con su dispositivo
            if (eq instanceof Cellular) ((Cellular) eq).findMy(System.out);
            else if (eq instanceof Tablet) ((Tablet) eq).findMy(System.out);
        }
        csv.close();
    }
}