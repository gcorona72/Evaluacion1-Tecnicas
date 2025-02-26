package com.example.examentecnicas;

import dominio.Tablero;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TableroTests {

    @Test
    void testLeerEstadoActual() {
        Tablero tablero = new Tablero();
        tablero.leerEstadoActual();
        assertNotNull(tablero.toString());
    }

    @Test
    void testGenerarEstadoActualPorMontecarlo() {
        Tablero tablero = new Tablero();
        tablero.generarEstadoActualPorMontecarlo();
        assertNotNull(tablero.toString());
    }

}