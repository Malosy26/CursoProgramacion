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
        if (b > mayor) mayor = b;
        if (c > mayor) mayor = c;
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
        return new int[]{positivos, totalNumeros};
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
}