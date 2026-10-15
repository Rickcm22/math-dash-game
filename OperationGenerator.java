import java.util.Random;

/**
 * Clase que genera operaciones matemáticas y sus respuestas.
 * Incluye lógica para generar operaciones de suma, resta y división exacta.
 * 
 * @author Ricardo Martínez (Rickcm22) & Team B04
 */
public class OperationGenerator {
    private final Random random;
    private int num1;
    private int num2;
    private char operador;
    private int resultado;

    /**
     * Constructor de la clase OperationGenerator.
     * Inicializa el generador de números aleatorios.
     */
    public OperationGenerator() {
        random = new Random();
    }

    /**
     * Genera una operación matemática aleatoria (suma, resta o división exacta).
     * 
     * @return La operación generada en formato de cadena.
     */
    public String generarOperacion() {
        num1 = random.nextInt(20) + 1;
        num2 = random.nextInt(20) + 1;

        // Operadores disponibles
        char[] ops = { '+', '-', '/' };
        operador = ops[random.nextInt(ops.length)];

        // Asegurar que la resta no dé negativo
        if (operador == '-') {
            if (num1 < num2) {
                int temp = num1;
                num1 = num2;
                num2 = temp;
            }
        }

        // Asegurar división exacta
        if (operador == '/') {
            num2 = generarDivisorExacto(num1);
        }

        switch (operador) {
            case '+' -> resultado = num1 + num2;
            case '-' -> resultado = num1 - num2;
            case '/' -> resultado = num1 / num2;
        }
        return num1 + " " + operador + " " + num2 + " = ?";
    }

    /**
     * Genera un divisor exacto para garantizar que la división sea exacta.
     * 
     * @param numero El número para el cual se generará un divisor exacto.
     * @return Un divisor exacto del número dado.
     */
    private int generarDivisorExacto(int numero) {
        int divisor;
        do {
            divisor = random.nextInt(numero) + 1;
        } while (numero % divisor != 0);
        return divisor;
    }

    /**
     * Obtiene el resultado de la operación generada.
     * 
     * @return El resultado de la operación.
     */
    public int getResultado() {
        return resultado;
    }

    /**
     * Genera tres posibles respuestas para la operación, incluyendo la correcta.
     * 
     * @return Un arreglo con las tres respuestas posibles.
     */
    public int[] generarRespuestas() {
        int[] respuestas = new int[3];
        int posicionCorrecta = random.nextInt(3);
        respuestas[posicionCorrecta] = resultado;

        for (int i = 0; i < 3; i++) {
            if (i == posicionCorrecta)
                continue;
            int incorrecta;
            do {
                incorrecta = resultado + (random.nextInt(11) - 5);
            } while (incorrecta == resultado || incorrecta < 0);
            respuestas[i] = incorrecta;
        }
        return respuestas;
    }
}