import java.io.*;
import java.util.*;

// Ejecución principal de la simulación de la Etapa 2
public class T1Stage2 {
    private Territory territory = new Territory();
    private ETNube nube = new ETNube();
    private int step = 0;

    public static void main(String[] args) throws IOException {
        if (args.length != 2) return;
        Scanner conf = new Scanner(new File(args[0])).useLocale(Locale.US);
        Scanner mov = new Scanner(new File(args[1])).useLocale(Locale.US);
        
        T1Stage2 sim = new T1Stage2();
        sim.setup(conf);
        sim.run(mov);
    }

    // Lectura de archivos y creación de instancias iniciales
    private void setup(Scanner sc) {
        int n = sc.nextInt();
        while (n-- > 0) {
            String owner = sc.next();
            int tags = sc.nextInt();
            boolean tab = sc.nextInt() == 1;
            float cx = sc.nextFloat(), cy = sc.nextFloat();
            
            Cellular c = new Cellular(owner, cx, cy, nube);
            territory.addCellular(c);
            nube.updateLocation(owner, "celular", cx, cy);

            while (tags-- > 0) {
                String tName = sc.next();
                float tx = sc.nextFloat(), ty = sc.nextFloat();
                territory.addTag(new EloTelTag(owner, tName, tx, ty));
                nube.updateLocation(owner, tName, tx, ty);
            }
            if (tab) { sc.nextFloat(); sc.nextFloat(); }
        }
    }

    // Procesamiento de movimientos y generación de salida CSV
    private void run(Scanner mov) throws IOException {
        PrintStream out = new PrintStream(new File("output.csv"));
        nube.printHeader(out);
        nube.printState(out, step);

        while (mov.hasNext()) {
            step++;
            String[] target = mov.next().split("\\.");
            float dx = mov.nextFloat(), dy = mov.nextFloat();

            if (target[1].equals("celular")) {
                Cellular c = territory.getCellular(target[0]);
                if (c != null) {
                    c.move(dx, dy);
                    nube.updateLocation(c.getOwnerName(), "celular", c.getX(), c.getY());
                }
            } else {
                EloTelTag t = territory.getTag(target[0], target[1]);
                if (t != null) t.move(dx, dy);
            }
            territory.checkAndReport();
            nube.printState(out, step);
        }
        out.close();
    }
}