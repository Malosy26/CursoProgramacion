/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.piedrapapeltijera;
import java.util.Scanner;
/**
 *
 * @author cursodesarrollo
 */
public class Piedrapapeltijera {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int marcador1 = 0, marcador2 = 0;

        // Jugar 5 rondas normales
        for (int ronda = 1; ronda <= 5; ronda++) {
            System.out.println("=== Ronda " + ronda + " ===");

            String jugadaJ1 = leerJugada(1);
            String jugadaJ2 = leerJugada(2);

            System.out.println("------------------------------------");
            System.out.println("Jugador 1: " + jugadaJ1);
            System.out.println("Jugador 2: " + jugadaJ2);

            int ganador = quienGana(jugadaJ1, jugadaJ2);
            if (ganador == 1) {
                System.out.println("Gana Jugador 1");
                marcador1++;
            } else if (ganador == 2) {
                System.out.println("Gana Jugador 2");
                marcador2++;
            } else {
                System.out.println("Ronda queda en empate");
            }

            System.out.println("Marcador: Jugador 1 = " + marcador1 + " | Jugador 2 = " + marcador2);
            System.out.println("------------------------------------\n");
        }

        // Muerte súbita si hay empate
        if (marcador1 == marcador2) {
            System.out.println("=== EMPATE TRAS 5 RONDAS. ENTRA EN MUERTE SÚBITA ===\n");

            while (marcador1 == marcador2) {
                String jugadaJ1 = leerJugada(1);
                String jugadaJ2 = leerJugada(2);

                int ganador = quienGana(jugadaJ1, jugadaJ2);
                if (ganador == 1) {
                    marcador1++;
                    System.out.println("Jugador 1 gana en muerte súbita");
                } else if (ganador == 2) {
                    marcador2++;
                    System.out.println("Jugador 2 gana en muerte súbita");
                } else {
                    System.out.println("Empate... otra ronda de muerte súbita");
                }

                System.out.println("Marcador: Jugador 1 = " + marcador1 + " | Jugador 2 = " + marcador2);
                System.out.println("------------------------------------\n");
            }
        }

        // Resultado final
        System.out.println("=== RESULTADO FINAL ===");
        if (marcador1 > marcador2) {
            System.out.println("🏆 Jugador 1 gana el juego");
        } else {
            System.out.println("🏆 Jugador 2 gana el juego");
        }

        scanner.close();
    }

    public static String leerJugada(int jugador) {
        String jugada;

        do {
            System.out.print("Jugador " + jugador + " debe introducir piedra, papel o tijera: ");
            jugada = scanner.nextLine().toLowerCase();

            if (!jugada.equals("piedra") && !jugada.equals("papel") && !jugada.equals("tijera")) {
                System.err.println("❌ Entrada inválida. Escribe exactamente 'piedra', 'papel' o 'tijera'.");
            }

        } while (!jugada.equals("piedra") && !jugada.equals("papel") && !jugada.equals("tijera"));

        return jugada;
    }

    private static int quienGana(String jugadaJ1, String jugadaJ2) {
        if (jugadaJ1.equals(jugadaJ2)) {
            return 0; // Empate
        }

        if ((jugadaJ1.equals("piedra") && jugadaJ2.equals("tijera")) ||
            (jugadaJ1.equals("tijera") && jugadaJ2.equals("papel")) ||
            (jugadaJ1.equals("papel") && jugadaJ2.equals("piedra"))) {
            return 1; // Gana jugador 1
        } else {
            return 2; // Gana jugador 2
        }
    }
}
