import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    static class Resultado {
        String nombre;
        long nodos;
        long tiempo;
        int movimientos;

        Resultado(String n, long nod, long t, int m) {
            this.nombre = n;
            this.nodos = nod;
            this.tiempo = t;
            this.movimientos = m;
        }
    }

    public static void main(String[] args) {
        String inicial = obtenerEstadoInicial();
        String objetivo = "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,0";

        List<Resultado> comparativa = new ArrayList<>();

        System.out.println("Iniciando búsqueda...");

        Object[][] heuristicas = new Object[][]{
                {true, "Manhattan"},
                {false, "Fuera de Lugar"}
        };

        for (Object[] h : heuristicas) {
            boolean usaManhattan = (Boolean) h[0];
            String etiqueta = (String) h[1];
            System.out.println("\n--- " + etiqueta + " ---");
            Resultado res = ejecutarYObtener(inicial, objetivo, usaManhattan, etiqueta);
            if (res != null) {
                comparativa.add(res);
                System.out.println("Solución encontrada.");
            }
        }

        imprimirEncabezadoAnalisis();

        for (Resultado r : comparativa) {
            System.out.printf("%-20s | %,12d | %,12d | %5d%n",
                    r.nombre, r.nodos, r.tiempo, r.movimientos);
        }
    }

    private static String obtenerEstadoInicial() {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.println("Ingrese estado inicial (25 nmeros separados por comas) :");
        String line = sc.nextLine().trim();
        if (line.isEmpty()) {
            return "9,8,3,4,5,6,7,1,10,15,11,12,2,22,20,16,17,13,19,24,21,14,18,23,0";
        }
        return line;
    }

    private static Resultado ejecutarYObtener(String ini, String obj, boolean m, String nombre) {
        Nodo raiz = new Nodo(ini);
        ArbolBusqueda buscador = new ArbolBusqueda(raiz);

        long tInicio = System.currentTimeMillis();
        Nodo solucion = buscador.buscarIDAStar(obj, m);
        long tFin = System.currentTimeMillis();

        if (solucion != null) {
            if (m) {
                imprimirCamino(reconstruirCamino(solucion));
            }
            System.out.printf("Resultado %s - nodos: %,d, tiempo: %,d ms, movs: %d%n",
                    nombre, buscador.getNodosExpandidos(), (tFin - tInicio), solucion.getNivel());
            return new Resultado(nombre, buscador.getNodosExpandidos(), (tFin - tInicio), solucion.getNivel());
        }
        return null;
    }

    private static List<String> reconstruirCamino(Nodo nodo) {
        LinkedList<String> camino = new LinkedList<>();
        while (nodo != null) {
            camino.addFirst(nodo.getEstado());
            nodo = nodo.getPadre();
        }
        return camino;
    }

    private static void imprimirCamino(List<String> camino) {
        int paso = 0;
        for (String estado : camino) {
            System.out.printf("Paso %02d:%n", paso++);
            imprimirTablero(estado);
            System.out.println("-----------");
        }
    }

    private static void imprimirTablero(String e) {
        String[] p = e.split(",");
        for (int i = 0; i < 25; i++) {
            String val = p[i].equals("0") ? " " : p[i];
            System.out.printf("%3s", val);
            if ((i + 1) % 5 == 0)
                System.out.println();
            else
                System.out.print(" ");
        }
    }

    private static void imprimirEncabezadoAnalisis() {
        System.out.println("\n\n=== ANALISIS ===");
        System.out.printf("%-20s | %-12s | %-12s | %-5s%n", "Heuristica", "Nodos Exp.", "Tiempo (ms)", "Movs");
        System.out.println("-----------------------------------------------------------------------");
    }
}