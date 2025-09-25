/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejerciciorpg;

/**
 *
 * @author cursodesarrollo
 */
public abstract class Personaje {
    private String nombre;
    private int vida;
    private int defensa;
    private int ataque;
    private boolean vivo = true;

    public Personaje(String nombre, int vida, int ataque, int defensa) {
        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
    }

    // Método común para recibir ataque
    public void recibeAtaque(int ataqueEntrante) {
        int daño = ataqueEntrante - this.getDefensa();

        if (daño <= 0) {
            System.out.println("El ataque no traspasa la defensa de " + this.getNombre());
        } else {
            System.out.println(this.getNombre() + " ha recibido " + daño + " de daño.");
            this.quitarVida(daño);
        }
    }

    // Método común para restar vida
    public void quitarVida(int cantidad) {
        int nuevaVida = this.getVida() - cantidad;

        if (nuevaVida <= 0) {
            this.vida = 0;
            this.vivo = false;
            System.out.println("☠️ " + this.getNombre() + " ha muerto.");
        } else {
            this.vida = nuevaVida;
            System.out.println("💥 Vida restante de " + this.getNombre() + ": " + this.getVida());
        }
    }

    // Método para atacar a otro personaje
    public void atacar(Personaje enemigo) {
        System.out.println(this.getNombre() + " ataca a " + enemigo.getNombre() + " con " + this.getAtaque() + " de ataque.");
        enemigo.recibeAtaque(this.getAtaque());
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public boolean estaVivo() {
        return vivo;
    }
}
