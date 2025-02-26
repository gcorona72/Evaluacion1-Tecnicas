package com.example.examentecnicas;

import mates.Matematicas;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MatematicasTests {

    @Test
    void testGenerarNumeroPi() {
        double piAprox = Matematicas.generarNumeroPi(1000000);
        assertEquals(3.14, piAprox, 0.01);
    }

}