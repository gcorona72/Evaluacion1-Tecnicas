package practica3;

import java.util.*;

/**
 * Implementación de un grafo usando lista de adyacencia.
 * Se asume por defecto que el grafo es NO dirigido.
 * Para hacerlo dirigido, comenta la línea en addEdge
 * donde se añade la arista inversa.
 */
public class Graph<V> {

    // Lista de adyacencia: cada vértice se asocia a un conjunto de vértices adyacentes.
    private Map<V, Set<V>> adjacencyList = new HashMap<>();

    /**
     * Añade el vértice 'v' al grafo.
     * @param v vértice a añadir.
     * @return 'true' si se ha añadido (no existía antes) y 'false' en caso contrario.
     */
    public boolean addVertex(V v) {
        if (adjacencyList.containsKey(v)) {
            return false;
        }
        adjacencyList.put(v, new HashSet<>());
        return true;
    }

    /**
     * Añade una arista (v1, v2) al grafo. En caso de que no exista alguno de los
     * vértices, lo añade también.
     * @param v1 el origen de la arista.
     * @param v2 el destino de la arista.
     * @return 'true' si la arista no existía y se ha añadido correctamente,
     *         'false' en caso contrario.
     */
    public boolean addEdge(V v1, V v2) {
        // Si no existen, se añaden
        addVertex(v1);
        addVertex(v2);

        // Si ya existe la arista, devolvemos false
        if (adjacencyList.get(v1).contains(v2)) {
            return false;
        }

        // Añadir arista (v1 -> v2)
        adjacencyList.get(v1).add(v2);
        // Añadir también la arista inversa (v2 -> v1) si es no dirigido
        adjacencyList.get(v2).add(v1);

        return true;
    }

    /**
     * Obtiene el conjunto de vértices adyacentes a 'v'.
     * @param v vértice del que se obtienen los adyacentes.
     * @return conjunto de vértices adyacentes.
     * @throws Exception si el vértice 'v' no existe en el grafo.
     */
    public Set<V> obtainAdjacents(V v) throws Exception {
        if (!adjacencyList.containsKey(v)) {
            throw new Exception("El vértice " + v + " no existe en el grafo.");
        }
        return adjacencyList.get(v);
    }

    /**
     * Comprueba si el grafo contiene el vértice dado.
     * @param v vértice para el que se realiza la comprobación.
     * @return 'true' si 'v' es un vértice del grafo, 'false' en caso contrario.
     */
    public boolean containsVertex(V v) {
        return adjacencyList.containsKey(v);
    }

    /**
     * Devuelve una cadena con la lista de adyacencia de cada vértice.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<V, Set<V>> entry : adjacencyList.entrySet()) {
            sb.append(entry.getKey()).append(" -> ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Obtiene, en caso de que exista, un camino entre 'v1' y 'v2'.
     * En caso contrario, devuelve 'null'.
     *
     * Se implementa con un enfoque de DFS usando una pila:
     * - "traza" es un Map para recordar el padre de cada vértice visitado.
     * - "abierta" es la pila con los vértices por explorar.
     *
     * @param v1 el vértice origen.
     * @param v2 el vértice destino.
     * @return lista con la secuencia de vértices desde 'v1' hasta 'v2'
     *         (o 'null' si no existe un camino).
     */
    public List<V> onePath(V v1, V v2) {
        // Si alguno de los vértices no existe, no hay camino
        if (!adjacencyList.containsKey(v1) || !adjacencyList.containsKey(v2)) {
            return null;
        }

        // Map para recordar de dónde venimos: "traza"
        Map<V, V> parent = new HashMap<>();
        // Pila para explorar el grafo (DFS)
        Stack<V> stack = new Stack<>();

        // Iniciamos la búsqueda con v1
        stack.push(v1);
        parent.put(v1, null); // v1 no tiene padre (es el origen)

        boolean found = false;
        while (!stack.isEmpty() && !found) {
            V current = stack.pop();
            if (current.equals(v2)) {
                found = true; // hemos encontrado el destino
            } else {
                // Para cada adyacente, si no lo hemos visitado, lo metemos en la pila
                for (V adj : adjacencyList.get(current)) {
                    if (!parent.containsKey(adj)) {
                        stack.push(adj);
                        parent.put(adj, current);
                    }
                }
            }
        }

        // Si se encontró un camino, reconstruimos la ruta desde v2 hacia v1
        if (found) {
            LinkedList<V> path = new LinkedList<>();
            V step = v2;
            while (step != null) {
                path.addFirst(step);
                step = parent.get(step);
            }
            return path;
        }

        return null; // no se encontró camino
    }
}
