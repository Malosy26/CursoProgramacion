/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.avante.ppt;

import com.avante.ppt.logica.PPT;


/**
 *
 * @author Juan Manuel
 */
public class PartidaPiedraPapelTijera {

    public static void main(String[] args) {
    String nombreJ1 = "J1",nombreJ2 = "J1";
    int max_rondas = 3;
    
    if (args.length > 3 || args.length < 3) {
    throw new IllegalArgumentException("Se deben pasar exactamente 3 argumentos");
    }
    
    if (!args[0].equals(" ")){
        nombreJ1 = args[0];
    }
    if (!args[1].equals(" ")){
        nombreJ2 = args[1];
    }
    if(!args[2].equals(" ")){
        max_rondas = Integer.parseInt(args[2]);
    }
    
    System.out.printf("""
                      El nombre del jugador 1 es %s
                      El nombre del jugador 2 es %s
                      Y el n\u00famero de rondas es %d
                      """, nombreJ1, nombreJ2, max_rondas);

        
        
        
        
        
       
        
        
        PPT partida = new PPT(nombreJ1,nombreJ2,max_rondas);
        
        partida.jugar();
           
    }
    
   
   
 
}
