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
            sf = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(user.class).buildSessionFactory();

            // Crear un usuario
          //createUser( "daniel mejia");

            // Leer usuarios
           //readUsers();

            // Actualizar un usuario
           //updateUser(6, "daniel castro");

            // Leer usuarios actualizados
           //readUsers();

            // Eliminar un usuario
            
           //deleteUser(6);

            // Leer usuarios después de eliminar
          //readUsers();
        } finally {
            if (sf != null) {
                sf.close();
            }
        }
    }

    public static void createUser(String name) {
        Session session = sf.openSession(); //  Apertura de Sesión
        Transaction tx = null;
        try {
            tx = session.beginTransaction(); //  Inicio de la Transacción
            user u = new user(); //  Creación de un Nuevo Usuario
            u.setName(name);
            session.persist(u); //  Persistencia del Usuario
            tx.commit(); //  Confirmación de la Transacción
            System.out.println("Usuario creado.");
        } catch (Exception e) { //  Manejo de Excepciones
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close(); //  Cierre de la Sesión
        }
    }


    public static void readUsers() {
        try (Session session = sf.openSession()) { //  Apertura de Sesión
            session.beginTransaction(); //  Inicio de la Transacción
            List<user> users = session.createQuery("FROM user", user.class).getResultList(); //  Consulta de Usuarios
            for (user user : users) { //  Recorrido y Visualización de Usuarios
                System.out.println("User ID: " + user.getId() + ", Name: " + user.getName());
            }
            session.getTransaction().commit(); //  Confirmación de la Transacción
        } catch (Exception e) { // Manejo de Excepciones
            e.printStackTrace();
        }
    }


    public static void updateUser(int id, String newName) {
        EntityManagerFactory emf = null; // Creación de EntityManagerFactory y EntityManager
        EntityManager em = null;
        try {
            emf = Persistence.createEntityManagerFactory("persistenceUnit");
            em = emf.createEntityManager();
            
            em.getTransaction().begin(); // Inicio de la Transacción
            
            user u = em.find(user.class, id); //  Búsqueda y Actualización del Usuario
            if (u != null) {
                u.setName(newName);
                em.merge(u);
            }
            
            em.getTransaction().commit(); //  Confirmación de la Transacción
            System.out.println("Usuario actualizado.");
        } catch (Exception e) { //  Manejo de Excepciones
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close(); //  Cierre de EntityManager
            }
            if (emf != null) {
                emf.close(); //  Cierre de EntityManagerFactory
            }
        }
    }


    public static void deleteUser(int userId) {
        try (Session session = sf.openSession()) { //  Apertura de Sesión
            session.beginTransaction(); //  Inicio de la Transacción
            user user = session.get(user.class, userId); //  Búsqueda y Eliminación del Usuario
            if (user != null) {
                session.delete(user);
                System.out.println("Usuario con ID " + userId + " fue eliminado correctamente.");
            } else {
                System.out.println("El usuario " + userId + " no existe.");
            }
            session.getTransaction().commit(); // Confirmación de la Transacción
        } catch (Exception e) { //  Manejo de Excepciones
            e.printStackTrace();
        }
    }

}