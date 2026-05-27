public class ExamenDiagnosticoIA {

    public static void main(String[] args) {
        // Cambiamos el nombre de la variable para que sea más descriptivo
        Arbol MiArbol = new Arbol();

        // Bloque de inserción de registros
        MiArbol.insertar("Armando");    
        MiArbol.insertar("Angel");  
        MiArbol.insertar("Fernando");    
        MiArbol.insertar("Carlos");   
        MiArbol.insertar("Alisson");   

        // Separadores visuales para la consola
        System.out.println("=====================================");
        System.out.println("     VISUALIZACIÓN DEL ÁRBOL         ");
        System.out.println("=====================================");
        MiArbol.imprimirArbol();
        System.out.println("=====================================\n");

        // Sección de búsqueda
        String objetivoBusqueda = "Angel";
        System.out.println("Procesando búsqueda para: [" + objetivoBusqueda + "]...");
        
        Nodo nodoHallado = MiArbol.buscarNodo(objetivoBusqueda);
        
        // Resultados con indicadores visuales
        if (nodoHallado != null) {
            System.out.println("Éxito: El registro '" + nodoHallado.nombre + "' sí existe en el sistema.");
        } else {
            System.out.println("Fallo: No se encontró ningún registro para '" + objetivoBusqueda + "'.");
        }
    }
}