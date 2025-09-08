package codJava.pseudocod;

public class pseudocod1 {

    // Declaración de la función para validar el PIN
    public static boolean pseudocodigo1(Integer pin) {
        Integer PIN = 1234;
        boolean t = pin.equals(PIN); // Usar equals() para comparar objetos Integer
        return t;
    }
    public static Integer lecturaNumeros(Integer n1, Integer n2, Integer n3) {
    Integer mayor = n1; // Asumimos inicialmente que n1 es el mayor

    if (n2 > mayor) {
        mayor = n2; // Si n2 es mayor, actualizamos el valor
    }

    if (n3 > mayor) {
        mayor = n3; // Si n3 es mayor, actualizamos el valor
    }

    return mayor; // Devolvemos el número mayor
    }


    public static void main(String[] args) {
        // Ejemplo de cómo llamar a la función
        System.out.println("Resultado del pseudocodigo1");
        boolean accesoConcedido = pseudocodigo1(1235);
        System.out.println(accesoConcedido); // Imprimirá true si el PIN es correcto
        System.out.println("-------------------------------");
        System.out.println("Resultado del pseudocodigo2");
        Integer resultado = lecturaNumeros(5, 9, 2);
        System.out.println("El número mayor es: " + resultado);
        
    
    
    
    
    }
}
