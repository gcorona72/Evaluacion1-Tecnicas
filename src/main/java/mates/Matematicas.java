package mates;

/**
 * Clase que proporciona métodos matemáticos de utilidad.
 * <p>
 * En este proyecto, se incluye el método para aproximar
 * el valor de PI mediante el método de Montecarlo.
 */
public class Matematicas {

    /**
     * Genera una aproximación al número PI mediante el método de Montecarlo.
     * <p>
     * Dibuja un cuadrado de lado 2 (de -1 a 1 en ambos ejes) y genera un número de
     * puntos aleatorios en su interior. Se cuenta cuántos puntos caen dentro del
     * círculo unitario. La aproximación se obtiene con la fórmula:
     * <br>
     * {@code aproximacionPI = 4 * (puntosDentro / totalPuntos)}.
     *
     * @param pasos Cantidad de puntos a generar.
     * @return Aproximación de PI calculada.
     */
    public static double generarNumeroPi(long pasos) {
        long aciertos = 0;
        double areaCuadrado = 4.0;

        // Generamos 'pasos' puntos aleatorios en el cuadrado [-1, 1] x [-1, 1]
        for (long i = 0; i < pasos; i++) {
            double x = Math.random() * 2 - 1;  // Genera entre -1 y 1
            double y = Math.random() * 2 - 1;  // Genera entre -1 y 1

            // Si el punto (x, y) cae dentro del círculo de radio 1
            if (x * x + y * y <= 1) {
                aciertos++;
            }
        }

        // Relación de aciertos respecto al total, multiplicada por el área del cuadrado (4)
        return areaCuadrado * ((double) aciertos / pasos);
    }
}
