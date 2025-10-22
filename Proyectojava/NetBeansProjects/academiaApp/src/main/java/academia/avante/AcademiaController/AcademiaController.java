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
        //ESTO ERA CON LISTAS
        /*
        List<Alumno> lista = new ArrayList<>();
        List<Curso>  curso = new ArrayList<>();
        */
        String sql = """
                     SELECT
                     a.id,
                     a.nombre,
                     a.apellidos,
                     a.email,
                     a.curso_id,
                     c.nombre AS nombre_curso,
                     c.fecha_inicio,
                     c.fecha_fin
                     
                     
                     FROM
                     alumnos a
                     INNER JOIN
                     cursos c
                     
                     ON
                     a.curso_id = c.id;
                     
                     
                     """;
        
        try(Connection con = Config.getInstance().getConection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
                
                ){
            
         
                
                            while (rs.next()) {
                                Alumno a = new Alumno(
                                rs.getInt("id"),
                                rs.getString("nombre"),
                                rs.getString("apellidos"),
                                rs.getString("email"),
                                rs.getInt("curso_id")
                                
                                );
                                
                                Curso c = new Curso(
                                rs.getInt("curso_id"),
                                rs.getString("nombre_curso"),
                                rs.getDate("fecha_inicio").toLocalDate(),
                                rs.getDate("fecha_fin").toLocalDate()
                                );
                                System.out.println("================================");
                                System.out.println(a);
                                System.out.println(c);
                                System.out.println("================================");
                                
                            }
                                /*
                                        System.out.printf("""
                                                          Id_alumno       = %d
                                                          Alumno          = %s
                                                          Apellidos       = %s
                                                          Email           = %s
                                                          Curso_id        = %d
                                                          Curso           = %s
                                                          Fecha_inicio    = %7$td/%7$tm/%7$tY
                                                          Fecha_fin       = %8$td/%8$tm/%8$tY

                                                          """,
                                            rs.getInt("id"),               // 1$
                                            rs.getString("nombre"),        // 2$
                                            rs.getString("apellidos"),     // 3$
                                            rs.getString("email"),         // 4$
                                            rs.getInt("curso_id"),         // 5$
                                            rs.getString("nombre_curso"),  // 6$
                                            rs.getDate("fecha_inicio"),    // 7$
                                            rs.getDate("fecha_fin")        // 8$
                 );
             }
                                */


                
                
                
                
                //ESTO ERA CON LISTAS
                /*
                lista.add(new Alumno (
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("apellidos"),
                rs.getString("email"),
                rs.getInt("curso_id")));
                
                try{
                 curso.add(new Curso(
                 rs.getInt("curso_id"),
                 rs.getString("nombre_curso"),
                 rs.getDate("fecha_inicio").toLocalDate(),
                 rs.getDate("fecha_fin").toLocalDate()
                         
                 ));
                }finally{
                    
                }
                */
            
            
            
        }catch(SQLException e ){
            e.printStackTrace();
            
        }
       // Esto era con listas
      /*
        if(!lista.isEmpty()){
            for(Alumno a : lista){
                
                System.out.println(a);
            }
        }else{
            System.out.println("No hay alumnos registrados en la base de datos");
        }
      */  
    }

}
    //===========================================================================================
    //===========================================================================================
    //==================================FIN PARTE ALUMNO==========================================