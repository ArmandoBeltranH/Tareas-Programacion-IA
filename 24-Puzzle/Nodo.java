import java.util.ArrayList;
import java.util.List;

public class Nodo {
    private final String estado;
    private Nodo padre;
    private int nivel;
    private int costo;

    public Nodo(String estado) {
        this.estado = estado;
        this.padre = null;
        this.nivel = 0;
        this.costo = 0;
    }

    public Nodo(String estado, Nodo padre) {
        this.estado = estado;
        this.padre = padre;
        this.nivel = padre.nivel + 1;
        this.costo = 0;
    }

   
    public String getEstado() { return estado; }
    public Nodo getPadre() { return padre; }
    public int getNivel() { return nivel; }
    public int getCosto() { return costo; }

    // f(n) = g(n) + h(n)
    public int getF() { return nivel + costo; }

    
    public void setPadre(Nodo padre) { this.padre = padre; }
    public void setNivel(int nivel) { this.nivel = nivel; }
    public void setCosto(int costo) { this.costo = costo; }

    public List<Nodo> generarSucesores() {
        String[] estadosHijos = Puzzle24.generarSucesores(estado);
        List<Nodo> sucesores = new ArrayList<>();

        if (estadosHijos == null) return sucesores;

        for (String e : estadosHijos) {
            if (e != null) {
                sucesores.add(new Nodo(e, this));
            }
        }
        return sucesores;
    }
}