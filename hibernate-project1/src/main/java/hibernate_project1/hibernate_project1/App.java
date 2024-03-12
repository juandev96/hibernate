package hibernate_project1.hibernate_project1;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.hibernate.Transaction;

public class App {

    private static SessionFactory sf;

    public static void main(String[] args) {
    	try {
            // Configurar la sesión de Hibernate
            sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

            // Verificar si la conexión se estableció correctamente
            if (sf != null) {
                System.out.println("Conexión a la base de datos establecida correctamente.");
            } else {
                System.out.println("No se pudo establecer la conexión a la base de datos.");
            }
        } finally {
            // Cerrar la sesión de Hibernate al finalizar
            if (sf != null) {
                sf.close();
            }
        }
    }
}