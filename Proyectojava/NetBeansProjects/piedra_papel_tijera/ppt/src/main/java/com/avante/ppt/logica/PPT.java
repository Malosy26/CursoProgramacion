/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avante.ppt.logica;

import java.util.Scanner;

/**
 *
 * @author Juan Manuel
 */
public class PPT {
    /************ METODOS **************/
    
    private Scanner scan = null;
    
    private int puntosJ1 = 0;
    private int puntosJ2 = 0;
    private int numRonda  = 0;
    private int numero_rondas = 0;
    private String j1 = "",j2 = "";
    
    
    
    public PPT(String jugador1, String jugador2,int numero_rondas){
        this.j1=jugador1;
        this.j2=jugador2;
        this.numero_rondas = numero_rondas;
        
    }
    
    
    /*
    private static final int max_rondas = 3;
    private static final int gana_ronda = 2;
    */
    
    /************ METODOS **************/
    
    private void infoRonda () {
        System.out.printf("\nAl final de la ronda %d estos son los resultados:\n", this.numRonda);
        System.out.printf("\tJUGADOR 1: %d puntos\n",this.puntosJ1);
        System.out.printf("\tJUGADOR 2: %d puntos\n",this.puntosJ2);
    }
    
    private String pedirJugada(int jugador) {
        // VARIABLES
        String jugada = "";
        boolean ok = false;
        
        // LOGICA
        
        while (!ok) {
            // pedir datos de jugada al jugador
            System.out.printf("\n\nJugador %d, escribe tu jugada:\npiedra | papel | tijera > ", jugador);
            jugada = this.scan.nextLine();
        
            // limpiamos el texto en jugado
            jugada = jugada.trim().toLowerCase();
        
            // Comprobar que la entrada es correcta
            if (
                   jugada.equals("piedra")
                || jugada.equals("papel")
                || jugada.equals("tijera")
            ) {
                ok = true;
            } 
            else {
                System.out.println("No has escrito una jugada correcta.");
            }
        }
        
        // RETURN la jugada que ha introducido el jugador
        return jugada;
    }
    
    private void calculaGanadorRonda (String jugadaJ1, String jugadaJ2) {
        // VARS
        int ganador = 0;
        
        // Logica
        
        // Ver quien gana
        if (jugadaJ1.equals("piedra")) {
            if (jugadaJ2.equals("papel")) {
                ganador = 2;
            }
            else if (jugadaJ2.equals("tijera")) {
                ganador = 1;
            }
        }
        else if (jugadaJ1.equals("papel")) {
            if (jugadaJ2.equals("piedra")) {
                ganador = 1;
            }
            else if (jugadaJ2.equals("tijera")) {
                ganador = 2;
            }
        }
        else if (jugadaJ1.equals("tijera")) {
            if (jugadaJ2.equals("piedra")) {
                ganador = 2;
            }
            else if (jugadaJ2.equals("papel")) {
                ganador = 1;
            }        
        }
        
        // Sumar los puntos al marcador
        switch (ganador) {
            case 1:
                this.puntosJ1++;
                break;
            case 2:
                this.puntosJ2++;
                break;
        }
        
        // Escribir por pantalla los datos  
        System.out.println("");
        
        if (ganador == 0) {
            System.out.println("Ha habido un empate.");
        }
        else {
            System.out.printf("Ha ganado el jugador %d.\n\n", ganador);
        }
    }
    
    private void ronda () {
        // VARIABLES
        String jugadaJ1 = "";
        String jugadaJ2 = "";
        
        // LOGICA
        System.out.printf("\n\n-----> RONDA %d.\n", this.numRonda-1);
        jugadaJ1 = this.pedirJugada(1);
        jugadaJ2 = this.pedirJugada(2);
        
        this.calculaGanadorRonda(jugadaJ1, jugadaJ2);        
    }
    
    
    public void jugar () {
        // VARS
        int ganador = 0;
        int max_rondas = this.numero_rondas;
        int gana_ronda = (int) Math.floor((max_rondas / 2.0) + 1);

        
        // Inicio el scanner
        this.scan = new Scanner(System.in);
        
        // Imprimo detalles del juego
        System.out.println("Vamos a jugra a Piedra Papel o Tijera\n");
        System.out.printf("Ganará el mejor de %s rondas.\n", max_rondas);
        System.out.printf("O lo que es lo mismo, el primero que llegue a %d puntos.\n\n", gana_ronda);
        System.out.printf("Si hay un empate despues de las %d rondas, se seguirá jugando hasta que alguien gane.\n\n", max_rondas);
        
        // inicio mi numero de ronda
        this.numRonda = 1;
   
        
        // Jugamos rondas hasta que haya un ganador
        while ( ganador == 0 ) {
            // jugar la ronda
            this.ronda();
            this.infoRonda();
        
            // hay ganador ????
            if ( this.numRonda < max_rondas ) {
                // Si estamos en la parte de "El mejor de X rondas"
                if (this.puntosJ1 >= gana_ronda) {
                    ganador = 1;
                }
                else if (this.puntosJ2 >= gana_ronda) {
                    ganador = 2;
                }
            }
            else {
                // Si estamos en la parte de la muerte súbita
                if ( this.puntosJ1 > this.puntosJ2 ){
                    ganador = 1;
                }
                else if ( this.puntosJ2 > this.puntosJ1 ) {
                    ganador = 2;
                }
            }
            
            
            // incremento la ronda
            this.numRonda++;
        }
        
        
        // Mostramos el ganador
        System.out.println("\n\n=============> RESULTADO");
        System.out.printf("\tDespues de %d rondas... ",this.numRonda);
        
        
        switch (ganador) {
            case 0:
                System.out.println("ha habido un empate.");    
                break;
            case 1:
                System.out.println("ha ganado "+this.j1);    
                break;
            case 2:
                System.out.println("ha ganado "+this.j2);    
                break;
        }
     
        // Cierro el scanner
        //this.scan.close();
    }
}
