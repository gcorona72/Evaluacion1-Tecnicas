# ExamenTecnicas

## Overview

ExamenTecnicas is a Java-based project that demonstrates three different functionalities:
1. Approximation of PI using the Monte Carlo method.
2. Implementation of Conway's Game of Life.
3. Graph traversal to find a path between two nodes.

The project uses Spring Boot for application configuration and execution, and Maven for dependency management.

## Prerequisites

- Java 17 or higher
- Maven 3.6.3 or higher
- IntelliJ IDEA or any other Java IDE

## Project Structure

```
ExamenTecnicas
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── com
│   │   │   │   └── example
│   │   │   │       └── examentecnicas
│   │   │   │           └── ExamenTecnicasApplication.java
│   │   │   ├── dominio
│   │   │   │   └── Tablero.java
│   │   │   ├── mates
│   │   │   │   └── Matematicas.java
│   │   │   └── practica3
│   │   │       └── Graph.java
│   │   └── resources
│   │       └── matriz.txt
│   └── test
│       └── java
│           └── com
│               └── example
│                   └── examentecnicas
│                       └── ExamenTecnicasApplicationTests.java
├── HELP.md
└── pom.xml
```

## Classes and Functionalities

### 1. ExamenTecnicasApplication

This is the main class of the project. It implements `CommandLineRunner` to execute the application from the command line. It provides a menu to select and run different functionalities.

```java
@SpringBootApplication
public class ExamenTecnicasApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(ExamenTecnicasApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Menu and execution logic
    }
}
```

### 2. Matematicas

This class provides a method to approximate the value of PI using the Monte Carlo method.

```java
public class Matematicas {
    public static double generarNumeroPi(long pasos) {
        // Monte Carlo method implementation
    }
}
```

### 3. Tablero

This class implements Conway's Game of Life. It can read the initial state from a file or generate a random initial state.

```java
public class Tablero {
    public void leerEstadoActual() {
        // Read initial state from file
    }

    public void generarEstadoActualPorMontecarlo() {
        // Generate random initial state
    }

    public void transitarAlEstadoSiguiente() {
        // Transition to the next state
    }

    @Override
    public String toString() {
        // Return the current state as a string
    }
}
```

### 4. Graph

This class implements a graph using an adjacency list. It provides methods to add vertices and edges, and to find a path between two nodes using Depth-First Search (DFS).

```java
public class Graph<V> {
    public boolean addVertex(V v) {
        // Add vertex
    }

    public boolean addEdge(V v1, V v2) {
        // Add edge
    }

    public List<V> onePath(V v1, V v2) {
        // Find a path using DFS
    }

    @Override
    public String toString() {
        // Return the adjacency list as a string
    }
}
```

## Running the Application

1. Clone the repository.
2. Navigate to the project directory.
3. Build the project using Maven:
   ```sh
   mvn clean install
   ```
4. Run the application:
   ```sh
   mvn spring-boot:run
   ```

## Usage

Upon running the application, you will be presented with a menu to select one of the three functionalities:

1. **Approximation of PI**: Enter the number of points to generate for the Monte Carlo method.
2. **Game of Life**: Choose to read the initial state from a file or generate a random initial state. Then, enter the number of iterations to simulate.
3. **Graph Path Finding**: A simple graph is constructed, and a path is found between two nodes.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

## Acknowledgements

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Maven](https://maven.apache.org/)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/)

For further reference, please consider the following sections in the `HELP.md` file:

- [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
- [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.4.3/maven-plugin)
- [Create an OCI image](https://docs.spring.io/spring-boot/3.4.3/maven-plugin/build-image.html)
