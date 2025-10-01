/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.consecionario;

/**
 *
 * @author cursodesarrollo
 */
public class Vehiculo {
    String   matricula;
    int      deposito;
    
    
    

   
    protected void repostar() {
        setDeposito(100);
       
    }

  
    protected void consumir(int porcentaje) {
        if ((this.deposito - porcentaje) < 0) {
            
            this.deposito = 0;
        } else {
        
            this.deposito -= porcentaje;
        }
    }
    /**
     * Imrpime el estado del vehiculo
     * 
     */
    protected void imprimirEstado() {
        System.out.println("Vehiculo con matricula "+this.matricula+" le queda de deposito  "+this.deposito);
    }

    /**
     * @return the matricula
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * @param matricula the matricula to set
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     * @return the deposito
     */
    public int getDeposito() {
        return deposito;
    }

    /**
     * @param deposito the deposito to set
     */
    public void setDeposito(int deposito) {
        this.deposito = deposito;
    }
    
}
