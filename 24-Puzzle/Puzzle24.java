import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Puzzle24 {
    private static final int SIZE = 5;

    public static String[] generarSucesores(String estadoActual) {
        if (estadoActual == null) return new String[0];

        String[] tokens = estadoActual.split(",");
        if (tokens.length != SIZE * SIZE) return new String[0];

        int zeroIndex = -1;
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("0")) {
                zeroIndex = i;
                break;
            }
        }
        if (zeroIndex == -1) return new String[0];

        int row = zeroIndex / SIZE;

        int[] moves = {-SIZE, SIZE, -1, 1};

        List<String> list = new ArrayList<>();
        for (int m : moves) {
            int dest = zeroIndex + m;
            if (dest < 0 || dest >= SIZE * SIZE) continue;
           
            if (Math.abs(m) == 1 && (dest / SIZE != row)) continue;

            String[] copy = tokens.clone();
            copy[zeroIndex] = copy[dest];
            copy[dest] = "0";
            list.add(String.join(",", copy));
        }

        return list.toArray(new String[0]);
    }


    public static int calcularManhattan(String estado, String objetivo) {
        if (estado == null || objetivo == null) return Integer.MAX_VALUE;
        String[] actual = estado.split(",");
        String[] meta = objetivo.split(",");
        if (actual.length != SIZE * SIZE || meta.length != SIZE * SIZE) return Integer.MAX_VALUE;

 
        Map<String, Integer> posMeta = new HashMap<>();
        for (int i = 0; i < meta.length; i++) posMeta.put(meta[i], i);

        int dist = 0;
        for (int i = 0; i < actual.length; i++) {
            String val = actual[i];
            if (val.equals("0")) continue;
            Integer pos = posMeta.get(val);
            if (pos == null) continue;
            dist += Math.abs(i / SIZE - pos / SIZE) + Math.abs(i % SIZE - pos % SIZE);
        }
        return dist;
    }


    public static int calcularFueraDeLugar(String estado, String objetivo) {
        if (estado == null || objetivo == null) return Integer.MAX_VALUE;
        String[] actual = estado.split(",");
        String[] meta = objetivo.split(",");
        if (actual.length != SIZE * SIZE || meta.length != SIZE * SIZE) return Integer.MAX_VALUE;

        int count = 0;
        for (int i = 0; i < actual.length; i++) {
            if (!actual[i].equals("0") && !actual[i].equals(meta[i])) count++;
        }
        return count;
    }
}