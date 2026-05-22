

public class Arbol {
    private Nodo raiz;

    public Arbol() {
        this.raiz = null;
    }

    public boolean vacio() {
        return raiz == null;
    }

    public Nodo buscarNodo(String nombre) {
        return buscarRecursiva(raiz, nombre);
    }

    // Implementación del orden de búsqueda (Recursivo)
    private Nodo buscarRecursiva(Nodo actual, String nombre) {
        // 1. Si el nodo es nulo o encontramos el nombre, lo retornamos
        if (actual == null || actual.nombre.equals(nombre)) {
            return actual;
        }

        // 2. Lógica de búsqueda binaria:
        // Si el nombre buscado es "menor" alfabéticamente, vamos a la izquierda
        if (nombre.compareTo(actual.nombre) < 0) {
            return buscarRecursiva(actual.izquierdo, nombre);
        }

        // 3. Si es "mayor", vamos a la derecha
        return buscarRecursiva(actual.derecho, nombre);
    }

    // Método auxiliar para insertar (para que puedas probarlo)
    public void insertar(String nombre) {
        raiz = insertarRecursivo(raiz, nombre);
    }

    private Nodo insertarRecursivo(Nodo actual, String nombre) {
        if (actual == null) {
            return new Nodo(nombre);
        }
        if (nombre.compareTo(actual.nombre) < 0) {
            actual.izquierdo = insertarRecursivo(actual.izquierdo, nombre);
        } else if (nombre.compareTo(actual.nombre) > 0) {
            actual.derecho = insertarRecursivo(actual.derecho, nombre);
        }
        return actual;
    }
    
    public void imprimirArbol() {
        if (vacio()) {
            System.out.println("El arbol está vacio :((");
        } else {
            System.out.println("Estructura del arbol PreOrder:");
            OrdenarEnPreOrder(raiz, "");
        }
    }

    private void OrdenarEnPreOrder(Nodo actual, String prefijo) {
        if (actual != null) {
            System.out.println(prefijo + actual.nombre);
            OrdenarEnPreOrder(actual.izquierdo, prefijo + "  ");
            OrdenarEnPreOrder(actual.derecho, prefijo + "  ");
        }
    }
}