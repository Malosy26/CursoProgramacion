package pseudocod;

import java.util.Scanner;

public class pseudocod1 {
    public static boolean pseudocodigo1(int pin) {
        // Lógica para validar el PIN
        return pin == 1235;
    }

    // Otros métodos
    public static int lecturaNumeros(int a, int b, int c) {
        int mayor = a;
        if (b > mayor)
            mayor = b;
        if (c > mayor)
            mayor = c;
        return mayor;
    }

    public static int[] contarNumerosPositivosYTotal(java.util.Scanner scanner) {
        int positivos = 0;
        int totalNumeros = 0;

        System.out.println("Introduce números enteros (escribe 'FIN' para terminar):");

        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                int numero = scanner.nextInt();
                totalNumeros++;
                if (numero > 0) {
                    positivos++;
                }
            } else {
                String entrada = scanner.next();
                if (entrada.equalsIgnoreCase("FIN")) {
                    break;
                }
            }
        }
        return new int[] { positivos, totalNumeros };
    }

    public static void tablaMultiplicar(Integer n) {
        for (Integer i = 1; i <= 10; i++) {
            System.out.println(i + " * " + n + " = " + (i * n));
        }
    }

    public static void calculaFactorial(Integer n) {
        long resultado = 1;

        if (n < 0) {
            System.out.println("No se puede calcular el factorial de un número negativo.");
            return;
        }

        if (n == 0) {
            System.out.println("El factorial de 0 es: 1");
            return;
        }

        for (Integer i = 1; i <= n; i++) {
            resultado *= i;
        }

        System.out.println("El factorial de " + n + " es: " + resultado);
    }

    /**
     * Método para contar letras, espacios, caracteres especiales y palabras en una
     * frase.
     * 
     * @param frase La cadena de texto a analizar.
     */
    public static void contarCaracteres(String frase) {
        // Contadores para cada tipo de carácter y palabras
        int contadorLetras = 0; // Contador de letras (a-z, A-Z)
        int contadorEspacios = 0; // Contador de espacios en blanco
        int contadorEspecial = 0; // Contador de caracteres especiales (tabuladores, saltos de línea, puntuación,
                                  // etc.)
        int contadorPalabras = 0; // Contador de palabras

        // Recorremos cada carácter de la frase
        for (int i = 0; i < frase.length(); i++) {
            char c = frase.charAt(i); // Obtenemos el carácter actual

            // Si el carácter es un espacio, incrementamos el contador de espacios
            if (c == ' ') {
                contadorEspacios++;
            }
            // Si el carácter es una letra, incrementamos el contador de letras
            else if (Character.isLetter(c)) {
                contadorLetras++;
                // Lógica para contar palabras:
                // Si es el primer carácter de la frase o el carácter anterior es un espacio,
                // significa que estamos al inicio de una nueva palabra
                if (i == 0 || frase.charAt(i - 1) == ' ') {
                    contadorPalabras++;
                }
            }
            // Si no es ni espacio ni letra, es un carácter especial
            else {
                contadorEspecial++;
            }
        }

        // Imprimimos los resultados
        System.out.println("Letras: " + contadorLetras);
        System.out.println("Espacios: " + contadorEspacios);
        System.out.println("Caracteres especiales: " + contadorEspecial);
        System.out.println("Palabras: " + contadorPalabras);
    }

}// fin de la clase de las funciones