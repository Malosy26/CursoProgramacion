/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package academia.avante.AcademiaController;

import academia.avante.DataBaseConfig.Config;
import academia.avante.model.Alumno;
import academia.avante.model.Curso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author dvr
 */
public class AcademiaController {
    //===========================================================================================
    //===========================================================================================
    //======================================PARTE CURSO==========================================
    /**
     * IMPRIME LOS CURSOS DE LA BASE DE DATOS
     */
    public void imprimeCursos() {

        List<Curso> cursos = new ArrayList<>();

        try (Connection con = Config.getInstance().getConection(); 
             PreparedStatement stmt = con.prepareStatement("SELECT id,nombre,fecha_inicio,fecha_fin FROM cursos;"); 
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                cursos.add(new Curso(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDate("fecha_inicio").toLocalDate(),
                        rs.getObject("fecha_fin", LocalDate.class)
                ));
            }

        } catch (SQLException e) {

            e.printStackTrace();;

        }

        if(!cursos.isEmpty()){
            
            for(Curso c : cursos){
                System.out.println(c);
            }
            
            
        }else{
               System.out.println("No se han obtenido cursos");     
               }

    }
    
  
    
    
    //===========================================================================================
    //===========================================================================================
    //==================================FIN PARTE CURSO==========================================
    
    
    
    
    
    //===========================================================================================
    //===========================================================================================
    //======================================PARTE ALUMNO==========================================
    
    /**
     * IMPRIME LOS ALUMNOS DE LA BASE DE DATOS
     */
    public void imprimeAlumnos(){
        List<Alumno> lista = new ArrayList<>();
        
        try(Connection con = Config.getInstance().getConection();
            PreparedStatement stmt = con.prepareStatement("SELECT id,nombre,apellidos,email,curso_id FROM alumnos;");
            ResultSet rs = stmt.executeQuery()
                
                ){
            
            while(rs.next()){
                
                lista.add(new Alumno (
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("apellidos"),
                rs.getString("email"),
                rs.getInt("curso_id")));
                
            }
            
            
        }catch(SQLException e ){
            e.printStackTrace();
            
        }
        
      
        if(!lista.isEmpty()){
            for(Alumno a : lista){
                
                System.out.println(a);
            }
        }else{
            System.out.println("No hay alumnos registrados en la base de datos");
        }
        
    }

}
    //===========================================================================================
    //===========================================================================================
    //==================================FIN PARTE ALUMNO==========================================