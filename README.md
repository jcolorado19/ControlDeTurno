
# Control de Turnos

Una aplicación de escritorio desarrollada en Java para gestionar turnos entre usuarios y asesores.

## Requisitos

- Java JDK 21 o superior
- Sistema operativo compatible con Java Swing

## Descripción

Esta aplicación permite gestionar turnos en un sistema de dos ventanas:

- **Ventana de Usuarios**: Permite a los usuarios tomar un turno para ser atendidos
- **Ventana de Asesores**: Permite a los asesores ver y gestionar los turnos pendientes

## Cómo usar

1. Ejecute la aplicación desde la clase `Main`
2. Se abrirán dos ventanas:
    - Ventana de Usuarios: Para que los clientes soliciten turnos
    - Ventana de Asesores: Para que los asesores gestionen los turnos

La aplicación utiliza Swing para la interfaz gráfica y maneja la sincronización de turnos entre ambas ventanas en tiempo real.

## Estructura

- `Main.java`: Punto de entrada de la aplicación
- `ListaTurnos.java`: Maneja la lógica de los turnos
- `VentanaUsuarios.java`: Interfaz para los usuarios
- `VentanaAsesores.java`: Interfaz para los asesores