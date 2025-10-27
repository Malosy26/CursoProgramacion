/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package academia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author dvr
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
    
@Entity
@Table(name="alumnos")




public class Alumno implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name="nombre",nullable=false,length=500)
    private String nombre;
    
    @Column(name="apellidos",nullable=false,length=500)
    private String apellidos;
    
    @Column(name="email",nullable=true,length=500)
    private String email;
    
    //Esto es lo que habia pensado yo en un principio pero con chat gpt me recomienda lo siguiente para que
    // hibernate se ocupe de la relacion
    /*
    @Column(name="curso_id",nullable=true)
    private int id_curso;
    */
    @ManyToOne
    @JoinColumn(name="curso_id")
    private Curso curso;
}
