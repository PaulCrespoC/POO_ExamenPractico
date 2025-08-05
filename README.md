# Buscaminas - Examen Práctico POO

## Descripción del Proyecto

Implementación del clásico juego Buscaminas en Java utilizando conceptos de Programación Orientada a Objetos (POO). El proyecto aplica el patrón MVC, manejo de excepciones, persistencia de datos y principios de desarrollo TDD.

## Características Principales

- **Interfaz de Consola**: Juego completamente funcional en terminal
- **Patrón MVC**: Separación clara entre Modelo, Vista y Controlador
- **Manejo de Excepciones**: Excepciones personalizadas y manejo de errores
- **Persistencia de Datos**: Guardado y carga de partidas usando serialización
- **Pruebas Unitarias**: Implementación de TDD con pruebas completas
- **Principios POO**: Encapsulamiento, herencia y polimorfismo

## Objetivos del Proyecto

- **Aplicar conceptos de POO**: Clases, objetos, encapsulamiento y relaciones
- **Implementar patrón MVC**: Separar lógica de negocio, presentación y control
- **Manejo de archivos**: Persistencia del estado del juego
- **Desarrollo TDD**: Pruebas unitarias para validar funcionalidad
- **Buenas prácticas**: Código limpio y documentado

## Estructura del Proyecto

```
/
├── src/
│   ├── modelo/                         # Modelo (M en MVC)
│   │   ├── Tablero.java               # Lógica del tablero de juego
│   │   ├── Casilla.java               # Representación de cada casilla
│   │   └── Jugador.java               # Información del jugador
│   ├── vista/                         # Vista (V en MVC)
│   │   └── VistaConsola.java          # Interfaz de consola
│   ├── controlador/                   # Controlador (C en MVC)
│   │   └── ControladorJuego.java      # Lógica de control del juego
│   ├── excepciones/                   # Excepciones personalizadas
│   │   └── CasillaYaDescubiertaException.java
│   ├── persistencia/                  # Manejo de archivos
│   │   └── GestorArchivos.java        # Serialización y guardado
│   ├── test/                          # Pruebas unitarias (TDD)
│   │   ├── TestTablero.java           # Tests del tablero
│   │   ├── TestCasilla.java           # Tests de casillas
│   │   ├── TestJugador.java           # Tests del jugador
│   │   └── TestRunner.java            # Ejecutor de pruebas
│   └── Main.java                      # Punto de entrada
└── README.md                          # Este archivo
```

## Instrucciones de Instalación y Uso

### Requisitos Previos
- Java Development Kit (JDK) 8 o superior
- Un IDE de Java (Eclipse, IntelliJ IDEA, VS Code) o terminal con javac

### Instalación

1. **Compilar el proyecto**:
   ```bash
   javac -d bin src/**/*.java src/*.java
   ```

2. **Ejecutar el juego**:
   ```bash
   java -cp bin Main
   ```

3. **Ejecutar las pruebas unitarias**:
   ```bash
   java -cp bin test.TestRunner
   ```

### Cómo Jugar

1. **Objetivo**: Descubrir todas las casillas sin minas (90 casillas de 100 total)
2. **Tablero**: 10x10 casillas con 10 minas distribuidas aleatoriamente
3. **Coordenadas**: Use formato letra-número (ej: A5, B10, J1)
4. **Acciones disponibles**:
   - Descubrir casilla: Ingrese coordenada directamente (ej: `A5`)
   - Marcar casilla: Ingrese `M` + coordenada (ej: `MA5`)
   - Guardar juego: Ingrese `GUARDAR`
   - Volver al menú: Ingrese `MENU`

### Ejemplo de Tablero

```
     1  2  3  4  5  6  7  8  9 10
  --------------------------------
A |   |   |   |   |   |   |   |   |   |   |
  --------------------------------
B |   |   | 1 | 2 | X |   |   |   |   |   |
  --------------------------------
C |   |   | V | 3 | * |   |   |   |   |   |
  --------------------------------
...

Caracteres:
• X = Ubicación de una mina (marcada)
• V = Espacio vacío seleccionado  
• * = Mina descubierta
• Número = Cantidad de minas adyacentes
• Espacio = Casilla no descubierta
```

## Funcionalidades Implementadas

### Lógica del Juego
- **Inicialización**: Tablero 10x10 con 10 minas colocadas aleatoriamente
- **Descubrimiento automático**: Revela casillas vacías adyacentes automáticamente
- **Conteo de minas**: Calcula número de minas adyacentes para cada casilla
- **Condiciones de victoria/derrota**: Detecta automáticamente el fin del juego

### Características Técnicas
- **Encapsulamiento**: Atributos privados con métodos getter/setter
- **Manejo de excepciones**: `CasillaYaDescubiertaException`, `InputMismatchException`
- **Persistencia**: Guardado/carga del estado usando serialización binaria
- **Validaciones**: Entrada de coordenadas con expresiones regulares

## Conceptos de POO Aplicados

### Encapsulamiento
- Atributos privados en todas las clases
- Acceso controlado mediante métodos públicos
- Validación de datos en setters

### Relaciones entre Clases
- **Composición**: Tablero contiene Casillas
- **Asociación**: Controlador usa Tablero y Jugador
- **Dependencia**: Vista depende del Modelo para mostrar datos

### Patrón MVC
- **Modelo**: Clases de negocio (Tablero, Casilla, Jugador)
- **Vista**: Presentación en consola (VistaConsola)
- **Controlador**: Coordinación y lógica de control (ControladorJuego)

## Pruebas Unitarias (TDD)

Las pruebas cubren:
- **Creación de objetos**: Verificación de estado inicial
- **Lógica del juego**: Colocación de minas, descubrimiento de casillas
- **Excepciones**: Manejo correcto de errores
- **Persistencia**: Guardado y carga de datos

Ejecutar pruebas:
```bash
java -cp bin test.TestRunner
```

## Tecnologías Utilizadas

- **Lenguaje**: Java 8+
- **Paradigma**: Programación Orientada a Objetos
- **Arquitectura**: Patrón MVC
- **Testing**: Pruebas unitarias con assertions
- **Persistencia**: Serialización de objetos Java
- **Interfaz**: Consola de comandos

---

**Desarrollado por**: Paul Crespo  
**Curso**: Programación Orientada a Objetos  
**Proyecto**: Examen Práctico Final