package practica3;

import java.util.*;

/**
 * Representa un grafo genérico utilizando lista de adyacencia.
 * <p>
 * Por defecto, se asume un grafo NO dirigido, ya que al agregar
 * una arista (v1, v2) también se agrega (v2, v1). En caso de
 * querer un grafo dirigido, basta con comentar la parte que
 * agrega la arista inversa.
 *
 * @param <V> Tipo de los vértices (e.g., Integer, String).
 */
public class Graph<V> {

    /**
     * Estructura de datos para la lista de adyacencia.
     * Cada vértice se asocia a un conjunto de vértices adyacentes.
     */
    private Map<V, Set<V>> adjacencyList = new HashMap<>();

    /**
     * Añade el vértice {@code v} al grafo.
     *
     * @param v Vértice a añadir.
     * @return {@code true} si el vértice se ha añadido (no existía),
     *         {@code false} si ya existía.
     */
    public boolean addVertex(V v) {
        if (adjacencyList.containsKey(v)) {
            return false;
        }
        adjacencyList.put(v, new HashSet<>());
        return true;
    }

    /**
     * Añade una arista entre {@code v1} y {@code v2}. En caso de que
     * no exista alguno de los vértices, también se añade.
     * <p>
     * Si el grafo se considera no dirigido, también se añade la
     * arista inversa (v2 -> v1).
     *
     * @param v1 Vértice origen.
     * @param v2 Vértice destino.
     * @return {@code true} si la arista no existía y se ha añadido,
     *         {@code false} si ya existía.
     */
    public boolean addEdge(V v1, V v2) {
        // Aseguramos que ambos vértices existen
        addVertex(v1);
        addVertex(v2);

        // Si la arista ya existe, devolvemos false
        if (adjacencyList.get(v1).contains(v2)) {
            return false;
        }

        // Añadir arista v1 -> v2
        adjacencyList.get(v1).add(v2);
        // Añadir arista v2 -> v1 (no dirigido)
        adjacencyList.get(v2).add(v1);

        return true;
    }

    /**
     * Obtiene el conjunto de vértices adyacentes a {@code v}.
     *
     * @param v Vértice del que se obtienen los adyacentes.
     * @return Conjunto de vértices adyacentes a {@code v}.
     * @throws Exception Si el vértice {@code v} no existe en el grafo.
     */
    public Set<V> obtainAdjacents(V v) throws Exception {
        if (!adjacencyList.containsKey(v)) {
            throw new Exception("El vértice " + v + " no existe en el grafo.");
        }
        return adjacencyList.get(v);
    }

    /**
     * Comprueba si el grafo contiene el vértice dado.
     *
     * @param v Vértice para la comprobación.
     * @return {@code true} si el vértice existe en el grafo,
     *         {@code false} en caso contrario.
     */
    public boolean containsVertex(V v) {
        return adjacencyList.containsKey(v);
    }

    /**
     * Devuelve la representación del grafo en formato de lista de adyacencia,
     * donde cada línea muestra un vértice y el conjunto de sus adyacentes.
     *
     * @return Cadena con la representación del grafo.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<V, Set<V>> entry : adjacencyList.entrySet()) {
            sb.append(entry.getKey()).append(" -> ")
                    .append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Obtiene, en caso de existir, un camino entre {@code v1} y {@code v2}.
     * <p>
     * Utiliza una búsqueda en profundidad (DFS) con una pila para
     * encontrar el camino. Si se encuentra, se reconstruye el camino
     * en orden desde {@code v1} hasta {@code v2}; si no, se devuelve null.
     *
     * @param v1 Vértice origen.
     * @param v2 Vértice destino.
     * @return Lista de vértices que representan un camino desde {@code v1}
     *         hasta {@code v2}, o {@code null} si no existe camino.
     */
    public List<V> onePath(V v1, V v2) {
        // Si alguno de los vértices no existe, no hay camino
        if (!adjacencyList.containsKey(v1) || !adjacencyList.containsKey(v2)) {
            return null;
        }

        // 'parent' rastrea el vértice desde el que llegamos a la clave
        Map<V, V> parent = new HashMap<>();
        Stack<V> stack = new Stack<>();

        // Iniciamos la búsqueda con v1
        stack.push(v1);
        parent.put(v1, null);

        boolean found = false;
        while (!stack.isEmpty() && !found) {
            V current = stack.pop();
            if (current.equals(v2)) {
                found = true;
            } else {
                for (V adj : adjacencyList.get(current)) {
                    if (!parent.containsKey(adj)) {
                        parent.put(adj, current);
                        stack.push(adj);
                    }
                }
            }
        }

        if (!found) {
            return null;
        }

        // Reconstruimos el camino desde v2 hacia v1
        LinkedList<V> path = new LinkedList<>();
        V step = v2;
        while (step != null) {
            path.addFirst(step);
            step = parent.get(step);
        }
        return path;
    }
}
