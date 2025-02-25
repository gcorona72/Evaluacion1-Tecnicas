package dominio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Tablero {
    private static final int DIMENSION = 30;
    private int[][] estadoActual = new int[DIMENSION][DIMENSION];
    private int[][] estadoSiguiente = new int[DIMENSION][DIMENSION];

    /**
     * Lee el estado inicial desde el archivo 'matriz.txt' ubicado en resources.
     * Si el archivo no existe, muestra un error y no cambia el estado actual.
     */
    public void leerEstadoActual() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("matriz.txt")) {
            if (inputStream == null) {
                System.out.println("Error: No se encontró el archivo 'matriz.txt' en resources.");
                return;
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                for (int i = 0; i < DIMENSION; i++) {
                    String linea = br.readLine();
                    if (linea == null || linea.length() < DIMENSION) {
                        System.out.println("Error: Archivo 'matriz.txt' tiene un formato incorrecto.");
                        return;
                    }
                    for (int j = 0; j < DIMENSION; j++) {
                        estadoActual[i][j] = (linea.charAt(j) == '1') ? 1 : 0;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        // Calcula el estado siguiente después de cargar la matriz
        generarEstadoSiguiente();
    }

    /**
     * Genera un estado inicial aleatorio (Montecarlo).
     * Aproximadamente la mitad de las celdas estarán vivas (1).
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
     * Calcula el próximo estado del tablero aplicando las reglas del Juego de la Vida.
     */
    private void generarEstadoSiguiente() {
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                int vecinosVivos = contarVecinosVivos(i, j);
                estadoSiguiente[i][j] = calcularNuevoEstado(estadoActual[i][j], vecinosVivos);
            }
        }
    }

    /**
     * Aplica las reglas del Juego de la Vida a una célula individual.
     */
    private int calcularNuevoEstado(int estadoActual, int vecinosVivos) {
        if (estadoActual == 1) {
            return (vecinosVivos == 2 || vecinosVivos == 3) ? 1 : 0;  // Sigue viva o muere
        } else {
            return (vecinosVivos == 3) ? 1 : 0;  // Renace si tiene 3 vecinos
        }
    }

    /**
     * Cuenta cuántos vecinos vivos tiene una célula dada en el tablero.
     */
    private int contarVecinosVivos(int fila, int col) {
        int count = 0;
        for (int i = fila - 1; i <= fila + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i == fila && j == col) continue;  // Ignorar la propia célula
                if (i >= 0 && i < DIMENSION && j >= 0 && j < DIMENSION) {
                    count += estadoActual[i][j];
                }
            }
        }
        return count;
    }

    /**
     * Avanza el estado del tablero a la siguiente generación.
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
     * Devuelve el estado actual del tablero en formato texto.
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
