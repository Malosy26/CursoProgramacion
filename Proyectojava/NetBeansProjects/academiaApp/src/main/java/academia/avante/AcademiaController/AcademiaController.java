/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package academia.avante.AcademiaController;

import academia.avante.DataBaseConfig.Config;
import academia.avante.model.Curso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dvr
 */
public class AcademiaController {

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

}
