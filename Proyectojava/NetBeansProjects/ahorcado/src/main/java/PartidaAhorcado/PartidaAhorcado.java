/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PartidaAhorcado;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class PartidaAhorcado {
    private String palabra;
    private String construccion;
    private String[] letrasAdivinadas;
    private int numeroRondas;

    public PartidaAhorcado() {
        this.numeroRondas = 8;
        this.palabraElegida();
        letrasAdivinadas = new String[palabra.length()];
        for (int i = 0; i < letrasAdivinadas.length; i++) {
            letrasAdivinadas[i] = "-";
        }
    }

    private void actualizarYMostrarPalabra(String letraNueva) {
        for (int i = 0; i < palabra.length(); i++) {
            if (palabra.charAt(i) == letraNueva.charAt(0)) {
                letrasAdivinadas[i] = letraNueva;
            }
        }

        String resultado = "";
        for (int i = 0; i < letrasAdivinadas.length; i++) {
            if (letrasAdivinadas[i] == null) {
                resultado += "-";
            } else {
                resultado += letrasAdivinadas[i];
            }
        }

        System.out.println(resultado);
        this.construccion = resultado;
    }

    private boolean palabraCompleta() {
        for (String letra : letrasAdivinadas) {
            if (letra.equals("-")) {
                return false;
            }
        }
        return true;
    }

    public void jugar(String nombreJugador) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < numeroRondas; i++) {
            System.out.println("Introduzca una letra para continuar: ");
            String letraNueva = sc.nextLine();

            if (letraNueva.length() != 1) {
                System.out.println("Por favor, introduce solo una letra.");
                i--;
                continue;
            }

            actualizarYMostrarPalabra(letraNueva.toLowerCase());

            if (palabraCompleta()) {
                System.out.println("¡Felicidades " + nombreJugador + "! Has adivinado la palabra.");
                break;
            }
        }
        sc.close();
    }
    
    private void palabraElegida() {
     try {
         File archivo = new File("src/palabras"); 
         Scanner lector = new Scanner(archivo);

         String linea = lector.nextLine();
         lector.close();

         String[] palabras = linea.split(",");
         Random eleccion = new Random();
         int numero = eleccion.nextInt(palabras.length);
         this.palabra = palabras[numero].trim();  // Quitamos espacios

     } catch (FileNotFoundException e) {
         System.out.println("Archivo no encontrado.");
         e.printStackTrace();
         
     }
     
   }
}




