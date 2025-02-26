# ExamenTecnicas

## Descripción General

ExamenTecnicas es un proyecto basado en Java que demuestra tres funcionalidades diferentes:
1. Aproximación de PI utilizando el método de Monte Carlo.
2. Implementación del Juego de la Vida de Conway.
3. Recorrido de un grafo para encontrar un camino entre dos nodos.

El proyecto utiliza Spring Boot para la configuración y ejecución de la aplicación, y Maven para la gestión de dependencias.

## Requisitos Previos

- Java 17 o superior
- Maven 3.6.3 o superior
- IntelliJ IDEA u otro IDE para Java

## Estructura del Proyecto

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

## Clases y Funcionalidades

### 1. ExamenTecnicasApplication

Esta es la clase principal del proyecto. Implementa `CommandLineRunner` para ejecutar la aplicación desde la línea de comandos. Proporciona un menú para seleccionar y ejecutar diferentes funcionalidades.

```java
@SpringBootApplication
public class ExamenTecnicasApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(ExamenTecnicasApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Lógica del menú y ejecución
    }
}
```

### 2. Matematicas

Esta clase proporciona un método para aproximar el valor de PI utilizando el método de Monte Carlo.

```java
public class Matematicas {
    public static double generarNumeroPi(long pasos) {
        // Implementación del método de Monte Carlo
    }
}
```

### 3. Tablero

Esta clase implementa el Juego de la Vida de Conway. Puede leer el estado inicial desde un archivo o generar un estado inicial aleatorio.

```java
public class Tablero {
    public void leerEstadoActual() {
        // Leer estado inicial desde archivo
    }

    public void generarEstadoActualPorMontecarlo() {
        // Generar estado inicial aleatorio
    }

    public void transitarAlEstadoSiguiente() {
        // Transitar al siguiente estado
    }

    @Override
    public String toString() {
        // Devolver el estado actual como cadena
    }
}
```

### 4. Graph

Esta clase implementa un grafo utilizando una lista de adyacencia. Proporciona métodos para añadir vértices y aristas, y para encontrar un camino entre dos nodos utilizando Búsqueda en Profundidad (DFS).

```java
public class Graph<V> {
    public boolean addVertex(V v) {
        // Añadir vértice
    }

    public boolean addEdge(V v1, V v2) {
        // Añadir arista
    }

    public List<V> onePath(V v1, V v2) {
        // Encontrar un camino utilizando DFS
    }

    @Override
    public String toString() {
        // Devolver la lista de adyacencia como cadena
    }
}
```

## Ejecutar la Aplicación

1. Clona el repositorio.
2. Navega al directorio del proyecto.
3. Construye el proyecto utilizando Maven:
   ```sh
   mvn clean install
   ```
4. Ejecuta la aplicación:
   ```sh
   mvn spring-boot:run
   ```

## Uso

Al ejecutar la aplicación, se presentará un menú para seleccionar una de las tres funcionalidades:

1. **Aproximación de PI**: Ingresa la cantidad de puntos a generar para el método de Monte Carlo.
2. **Juego de la Vida**: Elige leer el estado inicial desde un archivo o generar un estado inicial aleatorio. Luego, ingresa la cantidad de iteraciones a simular.
3. **Búsqueda de Camino en Grafo**: Se construye un grafo simple y se encuentra un camino entre dos nodos.

## Licencia

Este proyecto está licenciado bajo la Licencia apache 2.0. Consulta el archivo `LICENSE` para más detalles.

## Herramientas

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Maven](https://maven.apache.org/)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/)

Para más referencias, por favor considera las siguientes secciones en el archivo `HELP.md`:

- [Documentación oficial de Apache Maven](https://maven.apache.org/guides/index.html)
- [Guía de referencia del plugin de Maven para Spring Boot](https://docs.spring.io/spring-boot/3.4.3/maven-plugin)
- [Crear una imagen OCI](https://docs.spring.io/spring-boot/3.4.3/maven-plugin/build-image.html)
