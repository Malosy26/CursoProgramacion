/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package academia.dao;

import academia.model.AcademiaUtil;
import academia.model.Alumno;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author dvr
 */
public class AlumnoDAO {

    public void guardar(Alumno alumno) {
        try (Session session = AcademiaUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(alumno);
            tx.commit();
        }
    }

    public List<Alumno> listarTodos() {
        try (Session session = AcademiaUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Alumno", Alumno.class).list();
        }
    }

    public Alumno buscarPorId(int id) {
        try (Session session = AcademiaUtil.getSessionFactory().openSession()) {
            return session.get(Alumno.class, id);
        }
    }

    public void eliminar(int id) {
        try (Session session = AcademiaUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Alumno alumno = session.get(Alumno.class, id);
            if (alumno != null) session.remove(alumno);
            tx.commit();
        }
    }
}
