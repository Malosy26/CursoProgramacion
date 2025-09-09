package codJava.pseudocod;

import java.util.Scanner;

public class pseudocod1 {
    private static int intentos = 0;
    private static final int MAX_INTENTOS = 3;

    public static boolean pseudocodigo1(int pin) {
        if (intentos >= MAX_INTENTOS) {
            System.out.println("Acceso bloqueado: se han agotado los intentos.");
            return false;
        }

        boolean accesoConcedido = pin == 1235;
        if (!accesoConcedido) {
            intentos++;
            System.out.println("PIN incorrecto. Intentos restantes: " + (MAX_INTENTOS - intentos));
        } else {
            intentos = 0; // Reiniciar los intentos si el PIN es correcto
        }

        return accesoConcedido;
    }

    public static int lecturaNumeros(int a, int b, int c) {
        int mayor = a;
        if (b > mayor)
            mayor = b;
        if (c > mayor)
            mayor = c;
        return mayor;
    }

    public static int[] contarNumerosPositivosYTotal() {
        Scanner scanner = new Scanner(System.in);
        int positivos = 0; // Contador de números positivos
        int totalNumeros = 0; // Contador de total de números ingresados

        System.out.println("Introduce números enteros (escribe 'FIN' para terminar):");

        // Mientras haya más entrada disponible en el Scanner
        while (scanner.hasNext()) {
            // Verifica si la siguiente entrada es un número entero
            if (scanner.hasNextInt()) {
                // Lee el siguiente número entero de la entrada
                int numero = scanner.nextInt();
                totalNumeros++; // Incrementa el contador de total de números

                // Verifica si el número es positivo
                if (numero > 0) {
                    // Incrementa el contador de números positivos
                    positivos++;
                }
            } else {
                // Si la siguiente entrada no es un número entero, léela como una cadena de
                // texto
                String entrada = scanner.next();
                // Verifica si la entrada es igual a "FIN" (ignora mayúsculas/minúsculas)
                if (entrada.equalsIgnoreCase("FIN")) {
                    // Si se ingresa "FIN", termina el bucle
                    break;
                }
            }
        }

        scanner.close();
        // Devuelve un arreglo con el total de números positivos y el total de números
        // ingresados
        return new int[] { positivos, totalNumeros };
    }

    public static void calculaFactorial(Integer n) {
    long resultado = 1; // Usamos long para manejar números grandes

    // Validación para números negativos
    if (n < 0) {
        System.out.println("No se puede calcular el factorial de un número negativo.");
        return;
    }

    // Caso especial para 0! = 1
    if (n == 0) {
        System.out.println("El factorial de 0 es: 1");
        return;
    }

    // Bucle para calcular el factorial
    for (Integer i = 1; i <= n; i++) {
        resultado *= i;
    }

    // Imprimir el resultado
    System.out.println("El factorial de " + n + " es: " + resultado);  
    }

}

