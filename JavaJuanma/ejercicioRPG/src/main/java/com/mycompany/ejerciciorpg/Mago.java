/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejerciciorpg;

/**
 *
 * @author cursodesarrollo
 */


public class Mago extends Personaje {
    private final String clase = "Mago";

    public Mago(String nombre) {
        super(nombre, 100, 200, 50); // vida, ataque, defensa
    }

    public String getClase() {
        return clase;
    }
}

