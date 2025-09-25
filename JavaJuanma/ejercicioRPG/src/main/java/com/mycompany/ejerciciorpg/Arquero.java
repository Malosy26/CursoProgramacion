/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejerciciorpg;

/**
 *
 * @author cursodesarrollo
 */
public class Arquero extends Personaje {
    private final String clase = "Arquero";

    public Arquero(String nombre) {
        super(nombre, 100, 100, 80); // vida, ataque, defensa
    }

    public String getClase() {
        return clase;
    }
}