/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package academia.main;

import academia.model.Alumno;
import academia.model.Curso;
import academia.model.AcademiaUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author dvr
 */
public class AcademiaHibernada {

     public static void main(String[] args) {
        
        // Abrir sesión
        try (Session session = AcademiaUtil.getSessionFactory().openSession()) {

            // Iniciar transacción (aunque solo lectura, es buena práctica)
            Transaction tx = session.beginTransaction();

            // Obtener todos los cursos
            List<Curso> cursos = session.createQuery("from Curso", Curso.class).list();
            System.out.println("===== CURSOS =====");
            for (Curso c : cursos) {
                System.out.println(c.getId() + " - " + c.getNombre() + " (" + c.getFechaInicio() + " - " + c.getFechaFin() + ")");
            }

            // Obtener todos los alumnos
            List<Alumno> alumnos = session.createQuery("from Alumno", Alumno.class).list();
            System.out.println("===== ALUMNOS =====");
            for (Alumno a : alumnos) {
                //condición ? valorSiEsVerdadero : valorSiEsFalso;

                String cursoNombre = (a.getCurso() != null) ? a.getCurso().getNombre() : "Sin curso";
                System.out.println(a.getId() + " - " + a.getNombre() + " " + a.getApellidos() + " -> " + cursoNombre);
            }

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            AcademiaUtil.getSessionFactory().close();
         }
        /*
        try(Session session = AcademiaUtil.getSessionFactory().openSession()){
            Transaction tx = session.beginTransaction();
            
           Curso curso = session.get(Curso.class,1);
           session.persist(new Alumno(0,"Adel","Marquez","adelmarquez@correo.com",curso));
           tx.commit();
            
        }catch(Exception e){
            e.printStackTrace();
            
        }finally {
            AcademiaUtil.getSessionFactory().close();
         }
            
        */    
    }

        
        
        
}
