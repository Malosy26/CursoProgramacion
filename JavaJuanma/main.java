import java.util.Scanner;
import pseudocod.pseudocod1;
public class main {
    public static void main(String[] args) {
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

        System.out.println("--------------------------------");
        System.out.println("Resultado del pseudocodigo2");
        int resultado = pseudocod1.lecturaNumeros(5, 9, 2);
        System.out.println("El número mayor es: " + resultado);

        System.out.println("--------------------------------");
        int[] resultados = pseudocod1.contarNumerosPositivosYTotal(scanner);
        System.out.println("Total de números positivos: " + resultados[0]);
        System.out.println("Total de números ingresados: " + resultados[1]);

        Integer n = 5;
        pseudocod1.tablaMultiplicar(n);

        pseudocod1.calculaFactorial(5);

        String frase= "contar la frase";
        System.out.println("EL tamaño de la frase sin contar espacios es  = "+frase.length());
        System.out.println("La frase es : "+frase);
        pseudocod1.contarCaracteres(frase);
        scanner.close();
    }
}