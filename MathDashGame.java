import java.util.Scanner;

/**
 * Clase principal del juego Math Dash.
 * Gestiona la ejecución en modo consola, incluyendo pruebas automáticas
 * del motor matemático y un modo interactivo de resolución de operaciones.
 * 
 * Integrantes (Team B04):
 * - Juan Camilo Martelo Ochoa
 * - Cristian Andres Malagon Rincon
 * - Dayan Samuel Martinez Jaramillo
 * - Ricardo Andres Martinez Camacho
 * - David Santiago Maldonado Gomez
 * 
 * @author Ricardo Martínez (Rickcm22) & Team B04
 */
public class MathDashGame {

    /**
     * Método principal que ejecuta el flujo de consola de Math Dash.
     * 
     * @param args Argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            OperationGenerator og = new OperationGenerator();

            System.out.println(" MATH DASH - SUMA, RESTA Y DIVISION ");
            System.out.println("=========================================");

            // Prueba automática de 5 operaciones
            runAutomaticTests(og);

            // Modo interactivo en consola
            runInteractiveMode(og, scanner);
        }
    }

    /**
     * Ejecuta una serie de pruebas automáticas para verificar la generación de
     * operaciones.
     */
    private static void runAutomaticTests(OperationGenerator og) {
        System.out.println("\n  PRUEBA AUTOMATICA (5 operaciones):");
        System.out.println("=====================================");

        for (int i = 1; i <= 5; i++) {
            String operacion = og.generarOperacion();
            int[] opciones = og.generarRespuestas();
            int respuestaCorrecta = og.getResultado();

            System.out.println("\nOperacion " + i + ": " + operacion);
            System.out.println("Opciones: A) " + opciones[0] + " | B) " + opciones[1] + " | C) " + opciones[2]);
            System.out.println("Respuesta correcta: " + respuestaCorrecta);

            boolean correctaEnOpciones = false;
            for (int opcion : opciones) {
                if (opcion == respuestaCorrecta) {
                    correctaEnOpciones = true;
                    break;
                }
            }
            System.out.println("Respuesta correcta en opciones? " + (correctaEnOpciones ? " Si" : " NO"));

            if (operacion.contains("/")) {
                System.out.println(" Division exacta verificada: ");
            }
        }
    }

    /**
     * Inicia el modo de juego interactivo por consola.
     */
    private static void runInteractiveMode(OperationGenerator og, Scanner scanner) {
        System.out.println("\n MODO JUEGO - ELIGE TU RESPUESTA:");
        System.out.println("===================================");

        String operacion = og.generarOperacion();
        int[] opciones = og.generarRespuestas();

        System.out.println("Resuelve la operacion:");
        System.out.println(operacion);
        System.out.println();
        System.out.println("A) " + opciones[0]);
        System.out.println("B) " + opciones[1]);
        System.out.println("C) " + opciones[2]);
        System.out.print("\nSelecciona A, B o C: ");

        String eleccion = scanner.next().toUpperCase();
        int indice = switch (eleccion) {
            case "A" -> 0;
            case "B" -> 1;
            case "C" -> 2;
            default -> -1;
        };

        System.out.println();

        if (indice != -1 && opciones[indice] == og.getResultado()) {
            System.out.println("Correcto! ");
        } else {
            System.out.println("Incorrecto. ");
            System.out.println("La respuesta correcta era: " + og.getResultado());
        }
    }
}