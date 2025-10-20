/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package academia.avante.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author dvr
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Curso {
    private int id;
    private String nombre;
    private LocalDate fechaIni;
    private LocalDate fechaFin;
    
}
