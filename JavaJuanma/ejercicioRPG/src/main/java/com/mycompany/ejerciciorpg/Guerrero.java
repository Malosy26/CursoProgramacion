/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejerciciorpg;

/**
 *
 * @author cursodesarrollo
 */

public class Guerrero extends Personaje {
    private final String clase = "Guerrero";

    public Guerrero(String nombre) {
        super(nombre, 100, 50, 200); // vida, ataque, defensa
    }

    public String getClase() {
        return clase;
    }
}

    
    
    
    
    

