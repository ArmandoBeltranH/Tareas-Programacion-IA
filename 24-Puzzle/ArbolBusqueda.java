
public class ArbolBusqueda {
    private final Nodo raiz;
    private int nodosExpandidos;

    public ArbolBusqueda(Nodo raiz) {
        this.raiz = raiz;
    }


    public Nodo buscarIDAStar(String objetivo, boolean usarManhattan) {
        nodosExpandidos = 0;
        int limite = obtenerHeuristica(raiz.getEstado(), objetivo, usarManhattan);

        while (true) {
            Object resultado = buscarLimite(raiz, objetivo, limite, usarManhattan);
            if (resultado instanceof Nodo) return (Nodo) resultado;
            if ((int) resultado == Integer.MAX_VALUE) return null;
            limite = (int) resultado;
        }
    }


    private Object buscarLimite(Nodo nodo, String objetivo, int limite, boolean usarManhattan) {
        int h = obtenerHeuristica(nodo.getEstado(), objetivo, usarManhattan);
        nodo.setCosto(h);
        int f = nodo.getF();

        if (f > limite) return f;
        if (nodo.getEstado().equals(objetivo)) return nodo;

        int siguienteMin = Integer.MAX_VALUE;
        nodosExpandidos++;

        for (Nodo hijo : nodo.generarSucesores()) {
            if (nodo.getPadre() != null && hijo.getEstado().equals(nodo.getPadre().getEstado())) continue;

            Object res = buscarLimite(hijo, objetivo, limite, usarManhattan);
            if (res instanceof Nodo) return res;
            int valor = (int) res;
            if (valor < siguienteMin) siguienteMin = valor;
        }
        return siguienteMin;
    }

    private int obtenerHeuristica(String estado, String objetivo, boolean usarManhattan) {
        return usarManhattan ? Puzzle24.calcularManhattan(estado, objetivo)
                : Puzzle24.calcularFueraDeLugar(estado, objetivo);
    }

    public int getNodosExpandidos() { return nodosExpandidos; }
}