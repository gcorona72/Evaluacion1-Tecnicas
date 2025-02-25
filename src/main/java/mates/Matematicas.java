package mates;

public class Matematicas {

    /**
     * Genera una aproximación al número pi mediante el método de Montecarlo.
     * El parámetro 'pasos' indica la cantidad de puntos a generar.
     *
     * @param pasos cantidad de puntos a generar
     * @return aproximación de π
     */
    public static double generarNumeroPi(long pasos) {
        long aciertos = 0;
        double areaCuadrado = 4.0;

        // Generamos 'pasos' puntos aleatorios en el cuadrado [-1, 1] x [-1, 1]
        for (long i = 0; i < pasos; i++) {
            // Genera un número aleatorio entre -1 y 1 para x e y
            double x = Math.random() * 2 - 1;
            double y = Math.random() * 2 - 1;

            // Si el punto (x, y) cae dentro del círculo de radio 1
            if (x * x + y * y <= 1) {
                aciertos++;
            }
        }
        // La relación entre puntos dentro del círculo y el total se multiplica por el área del cuadrado
        return areaCuadrado * ((double) aciertos / pasos);
    }
}
