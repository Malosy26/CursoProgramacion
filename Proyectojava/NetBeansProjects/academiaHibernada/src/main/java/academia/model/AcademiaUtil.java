/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package academia.model;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author dvr
 */


/**
 * Clase de utilidad para gestionar la SessionFactory de Hibernate.
 * 
 * Esta clase se encarga de:
 * - Crear una única instancia de SessionFactory (patrón Singleton).
 * - Proveer acceso global a esa SessionFactory para abrir sesiones (Session).
 */
public class AcademiaUtil {

    // 🔹 Declaramos una variable estática y final:
    // "static" -> pertenece a la clase, no a los objetos.
    // "final" -> solo se asignará una vez (no puede cambiar después).
    private static final SessionFactory sessionFactory;

    // 🔹 Bloque estático:
    // Este bloque se ejecuta automáticamente UNA SOLA VEZ cuando se carga la clase en memoria.
    // No hace falta crear un objeto de AcademiaUtil para que esto ocurra.
    static {
        try {
            // 🏗️ 1. Crea una instancia de Configuration.
            // Hibernate buscará el archivo "hibernate.cfg.xml" en el classpath (normalmente en src/main/resources).
            // 🧩 2. Llama a .configure() para leer y aplicar las propiedades del XML.
            // 🧰 3. Llama a .buildSessionFactory() para construir el objeto SessionFactory.
            sessionFactory = new Configuration().configure().buildSessionFactory();
            
            // ✅ Si todo va bien, Hibernate queda inicializado y listo para abrir sesiones.
        } catch (Throwable ex) {
            // ⚠️ Si algo falla (por ejemplo, el XML tiene un error o falta una clase mapeada),
            // mostramos un mensaje de error en consola.
            System.err.println("❌ Error inicializando Hibernate: " + ex);

            // Lanzamos un error crítico (ExceptionInInitializerError)
            // Esto hace que el programa falle inmediatamente, porque sin SessionFactory
            // Hibernate no puede funcionar.
            throw new ExceptionInInitializerError(ex);
        }
    }

    // 🔹 Método público y estático para obtener la única SessionFactory del sistema.
    // Como es "static", no hace falta crear un objeto de AcademiaUtil:
    // se puede llamar directamente: AcademiaUtil.getSessionFactory().
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
