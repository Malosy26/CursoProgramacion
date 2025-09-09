package codJava;

import java.util.Scanner;

import codJava.pseudocod.pseudocod1;

public class main {

    public static void main(String[] args) {
        /*
         * 
         * 
         * ----------------------- PSEUDOCODIGO1------------------------------
         */

        System.out.println("Resultado del pseudocodigo1");
        Scanner scanner = new Scanner(System.in);
        boolean accesoConcedido = false;

        System.out.println("Introduce el PIN (tienes 3 intentos):");

        for (int i = 0; i < 3; i++) {
            System.out.print("Intento " + (i + 1) + ": ");
            int pin = scanner.nextInt();
            accesoConcedido = pseudocod1.pseudocodigo1(pin);

            if (accesoConcedido) {
                System.out.println("Acceso concedido.");
                break;
            }
        }

        if (!accesoConcedido) {
            System.out.println("Acceso denegado: se han agotado los intentos.");
        }
        /*
         * 
         * 
         * -----------------------FIN DE PSEUDOCODIGO1------------------------------
         */

        /*
         * 
         * 
         * ----------------------- PSEUDOCODIGO2------------------------------
         */
        System.out.println("--------------------------------");
        System.out.println("Resultado del pseudocodigo2");
        int resultado = pseudocod1.lecturaNumeros(5, 9, 2);
        System.out.println("El número mayor es: " + resultado);
        /*
         * 
         * 
         * -----------------------FIN PSEUDOCODIGO2------------------------------
         */
        /*
         * 
         * 
         * ----------------------- PSEUDOCODIGO3------------------------------
         */

        int[] resultados = pseudocod1.contarNumerosPositivosYTotal();
        System.out.println("Total de números positivos: " + resultados[0]);
        System.out.println("Total de números ingresados: " + resultados[1]);

        /*
         * 
         * 
         * -----------------------FIN DE PSEUDOCODIGO3------------------------------
         */
    }
}
