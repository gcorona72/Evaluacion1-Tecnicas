package com.example.examentecnicas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import mates.Matematicas;      // Práctica 1
import dominio.Tablero;       // Práctica 2
import practica3.Graph;       // Práctica 3

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Clase principal de la aplicación Spring Boot que integra tres prácticas:
 * <ul>
 *   <li>Aproximación de PI (Práctica 1)</li>
 *   <li>Juego de la Vida (Práctica 2)</li>
 *   <li>Grafo - Búsqueda de camino (Práctica 3)</li>
 * </ul>
 *
 * Ofrece un menú interactivo en la consola para que el usuario seleccione la
 * práctica a ejecutar. Al terminar cada práctica, el programa regresa al menú
 * principal, permitiendo ejecutar otra práctica o salir.
 */
@SpringBootApplication
public class ExamenTecnicasApplication implements CommandLineRunner {

    /**
     * Método principal que inicia la aplicación Spring Boot.
     *
     * @param args Argumentos de línea de comando (no se utilizan en este proyecto).
     */
    public static void main(String[] args) {
        SpringApplication.run(ExamenTecnicasApplication.class, args);
    }

    /**
     * Sobrescribe el método run de CommandLineRunner para mostrar el menú interactivo
     * en consola y ejecutar la práctica seleccionada por el usuario.
     *
     * @param args Argumentos de línea de comando pasados a la aplicación.
     * @throws Exception Si ocurre alguna excepción durante la ejecución de las prácticas.
     */
    @Override
    public void run(String... args) throws Exception {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n======================================");
            System.out.println("    ¡Bienvenido al ExamenTecnicas!    ");
            System.out.println("======================================");
            System.out.println("Seleccione la práctica que desea ejecutar:");
            System.out.println("1) Aproximación de PI (Práctica 1)");
            System.out.println("2) Juego de la Vida (Práctica 2)");
            System.out.println("3) Grafo - Búsqueda de camino (Práctica 3)");
            System.out.println("0) Salir");
            System.out.println("--------------------------------------");
            System.out.print("Ingrese su opción: ");

            int opcion;
            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida. Intente de nuevo.");
                continue;
            }

            if (opcion == 0) {
                System.out.println("Saliendo de la aplicación...");
                break; // Sale del bucle y termina la aplicación
            }

            switch (opcion) {
                case 1:
                    ejecutarPractica1(sc);
                    break;
                case 2:
                    ejecutarPractica2(sc);
                    break;
                case 3:
                    ejecutarPractica3();
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }

    /**
     * Ejecuta la Práctica 1: Aproximación de PI mediante el método de Montecarlo.
     * <p>
     * Pide al usuario la cantidad de puntos a generar, invoca el método
     * {@code Matematicas.generarNumeroPi} y muestra el resultado en consola.
     *
     * @param sc Objeto Scanner para leer la entrada del usuario.
     */
    private void ejecutarPractica1(Scanner sc) {
        System.out.println("\n=== Práctica 1: Aproximación de PI ===");
        System.out.print("Ingrese la cantidad de puntos a generar: ");

        long pasos;
        try {
            pasos = Long.parseLong(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Debe ser un número entero.");
            return;
        }

        double piAprox = Matematicas.generarNumeroPi(pasos);
        System.out.println("Resultado: El número PI aproximado es " + piAprox);
        System.out.println("=========================================");
    }

    /**
     * Ejecuta la Práctica 2: Juego de la Vida.
     * <p>
     * Permite elegir entre leer el estado inicial desde un fichero o generarlo
     * aleatoriamente, y luego simula un número determinado de iteraciones,
     * mostrando la evolución del tablero en consola.
     *
     * @param sc Objeto Scanner para leer la entrada del usuario.
     * @throws InterruptedException Si ocurre un error durante las pausas entre generaciones.
     */
    private void ejecutarPractica2(Scanner sc) throws InterruptedException {
        System.out.println("\n=== Práctica 2: Juego de la Vida ===");
        Tablero tablero = new Tablero();

        System.out.println("1) Leer estado inicial desde fichero 'matriz'");
        System.out.println("2) Generar estado inicial aleatorio (Montecarlo)");
        System.out.print("Elija una opción (1 o 2): ");

        int opcion = 0;
        try {
            opcion = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Opción inválida. Usando opción 1 por defecto.");
            opcion = 1;
        }

        if (opcion == 2) {
            System.out.println("Generando estado inicial aleatorio...");
            tablero.generarEstadoActualPorMontecarlo();
        } else {
            System.out.println("Leyendo estado inicial desde fichero 'matriz'...");
            tablero.leerEstadoActual();
        }

        System.out.println("Estado inicial:\n" + tablero);

        // Pedir cuántas iteraciones
        System.out.print("¿Cuántas iteraciones desea simular? (ej: 5): ");
        int iteraciones = 5;
        try {
            iteraciones = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Usando 5 por defecto.");
        }

        for (int i = 1; i <= iteraciones; i++) {
            TimeUnit.SECONDS.sleep(1); // Pausa de 1 segundo
            tablero.transitarAlEstadoSiguiente();
            System.out.println("Generación " + i + ":\n" + tablero);
        }
        System.out.println("=========================================");
    }

    /**
     * Ejecuta la Práctica 3: Grafo y Búsqueda de un camino.
     * <p>
     * Construye un grafo de ejemplo y busca un camino entre dos nodos, mostrando
     * el resultado en consola.
     */
    private void ejecutarPractica3() {
        System.out.println("\n=== Práctica 3: Búsqueda de un camino en Grafo ===");
        Graph<Integer> g = new Graph<>();
        // Ejemplo simple
        g.addEdge(1, 2);
        g.addEdge(3, 4);
        g.addEdge(1, 5);
        g.addEdge(5, 6);
        g.addEdge(6, 4);

        System.out.println("Grafo construido (no dirigido). Lista de adyacencia:");
        System.out.println(g);

        System.out.println("Buscando un camino desde 1 hasta 4...");
        System.out.println("Camino encontrado: " + g.onePath(1, 4));
        System.out.println("=========================================");
    }
}
