package dominio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Representa el tablero del Juego de la Vida de Conway.
 * <p>
 * Permite cargar el estado inicial desde un fichero o generarlo
 * aleatoriamente, aplicar las reglas del juego y transicionar
 * entre estados.
 */
public class Tablero {

    /**
     * Dimensión del tablero (número de filas y columnas).
     */
    private static final int DIMENSION = 30;

    /**
     * Matriz que representa el estado actual de cada célula:
     * 1 = viva, 0 = muerta.
     */
    private int[][] estadoActual = new int[DIMENSION][DIMENSION];

    /**
     * Matriz que representa el siguiente estado del tablero,
     * calculado a partir de {@code estadoActual}.
     */
    private int[][] estadoSiguiente = new int[DIMENSION][DIMENSION];

    /**
     * Lee el estado inicial de un fichero llamado 'matriz' o 'matriz.txt'.
     * <p>
     * Cada línea del archivo debe tener exactamente 30 caracteres
     * ('0' o '1'), y debe haber 30 líneas. Si el archivo no cumple
     * este formato, el tablero se llenará hasta donde sea posible.
     *
     * @throws IOException Si ocurre un error al leer el fichero.
     */
    public void leerEstadoActual() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("matriz"))) {
            for (int i = 0; i < DIMENSION; i++) {
                String linea = br.readLine();
                if (linea == null) {
                    break; // No hay más líneas
                }
                for (int j = 0; j < DIMENSION && j < linea.length(); j++) {
                    estadoActual[i][j] = (linea.charAt(j) == '1') ? 1 : 0;
                }
            }
        }
        // Calcula el estado siguiente tras la carga
        generarEstadoSiguiente();
    }

    /**
     * Genera un estado inicial aleatorio para el tablero.
     * <p>
     * Cada celda tiene una probabilidad del 50% de estar viva.
     */
    public void generarEstadoActualPorMontecarlo() {
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                estadoActual[i][j] = (Math.random() < 0.5) ? 1 : 0;
            }
        }
        generarEstadoSiguiente();
    }

    /**
     * Aplica las reglas del Juego de la Vida para generar la matriz
     * {@code estadoSiguiente} a partir de {@code estadoActual}.
     * <ul>
     *   <li>Una célula viva con menos de 2 vecinos vivos muere (soledad).</li>
     *   <li>Una célula viva con 2 o 3 vecinos vivos sigue viva.</li>
     *   <li>Una célula viva con más de 3 vecinos vivos muere (superpoblación).</li>
     *   <li>Una célula muerta con exactamente 3 vecinos vivos nace.</li>
     * </ul>
     */
    private void generarEstadoSiguiente() {
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                int vecinosVivos = contarVecinosVivos(i, j);

                // Célula viva
                if (estadoActual[i][j] == 1) {
                    if (vecinosVivos == 2 || vecinosVivos == 3) {
                        estadoSiguiente[i][j] = 1;
                    } else {
                        estadoSiguiente[i][j] = 0;
                    }
                }
                // Célula muerta
                else {
                    if (vecinosVivos == 3) {
                        estadoSiguiente[i][j] = 1;
                    } else {
                        estadoSiguiente[i][j] = 0;
                    }
                }
            }
        }
    }

    /**
     * Cuenta cuántos vecinos vivos tiene la célula en la posición (fila, col).
     * <p>
     * Se consideran vecinos las 8 celdas adyacentes (vertical, horizontal
     * y diagonal).
     *
     * @param fila Índice de la fila.
     * @param col  Índice de la columna.
     * @return Número de vecinos vivos alrededor de la célula.
     */
    private int contarVecinosVivos(int fila, int col) {
        int count = 0;
        for (int i = fila - 1; i <= fila + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i == fila && j == col) {
                    continue; // No contar la propia célula
                }
                if (i >= 0 && i < DIMENSION && j >= 0 && j < DIMENSION) {
                    count += estadoActual[i][j];
                }
            }
        }
        return count;
    }

    /**
     * Copia el contenido de {@code estadoSiguiente} a {@code estadoActual},
     * y luego recalcula un nuevo estado siguiente.
     */
    public void transitarAlEstadoSiguiente() {
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                estadoActual[i][j] = estadoSiguiente[i][j];
            }
        }
        generarEstadoSiguiente();
    }

    /**
     * Devuelve una representación en texto del tablero, donde cada
     * línea corresponde a una fila y cada carácter representa
     * el estado de una célula (0 = muerta, 1 = viva).
     *
     * @return Cadena de texto con el estado actual del tablero.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                sb.append(estadoActual[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
