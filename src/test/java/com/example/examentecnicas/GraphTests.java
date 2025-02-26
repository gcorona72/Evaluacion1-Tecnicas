package com.example.examentecnicas;

import practica3.Graph;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GraphTests {

    @Test
    void testAddVertex() {
        Graph<Integer> graph = new Graph<>();
        assertTrue(graph.addVertex(1));
    }

    @Test
    void testAddEdge() {
        Graph<Integer> graph = new Graph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        assertTrue(graph.addEdge(1, 2));
    }

    @Test
    void testOnePath() {
        Graph<Integer> graph = new Graph<>();
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        assertNotNull(graph.onePath(1, 3));
    }

}