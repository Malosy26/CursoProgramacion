/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Cod_java.eje03;



/**
 *
 * @author cursodesarrollo
 * @version 1.0
 * @date 2025-0-17
 *
 */
public class ejemplo_03_referencia {
    

    public static void main(String args[]) {
        // String   ->  cadena de caracteres
        String nombre = " Daniel";

        //Impresiones
        System.out.println("Mi nombre es :" + nombre);
        System.out.println("Mi dni dentro del pc es :" + nombre.hashCode());

        Persona alumno1 = new Persona();
        alumno1.imprimirDatos();

    }
}
//Voy a crear una plantilla para definir alumnos
class Persona {

    //definir las propiedades
    String nombre;
    int edad;
    boolean genero;

    //Constructor
    Persona() {
        this.nombre = "Dani";
        this.edad = 18;
        this.genero = true;
    }

    //metodo imprimir
    public void imprimirDatos() {
        System.out.println("nombre : " + nombre);
        System.out.println("edad : " + edad);
        System.out.println("genero : " + genero);

    }
}