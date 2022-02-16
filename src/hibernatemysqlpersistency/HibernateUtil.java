package hibernatemysqlpersistency;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 * <br>
 * <b>Customized:</b><br>
 * Made as a Singleton Pattern class;<br>
 * Added a method <b>close()</b> which Releases this <code>SessionFactory</code>
 * object's database and JDBC resources immediately<br>instead of waiting for
 * them to be automatically released;<br>Destroys a service registry;
 *
 * @author: Dejan Smiljić; e-mail: dej4n.s@gmail.com
 */
public class HibernateUtil {// Singleton Pattern Class

    private static volatile SessionFactory sessionFactory = null;
    private static ServiceRegistry serviceRegistry;

    //Disallows creating instances of this class
    private HibernateUtil() {
    }

    protected static synchronized SessionFactory createSessionFactory() {
        //Applying Singleton Pattern
        if (sessionFactory == null) {
            try {
                //Creating SessionFactory object
                Configuration configuration = new Configuration();
                configuration.configure();
                serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                System.out.println("sessionFactory created | hash code: " + sessionFactory.hashCode());
            } catch (HibernateException hExc) {
                System.out.println("Cannot create SessionFactory object...\n" + hExc);
            }
        } else {
            System.out.println("Using existing sessionFactory | hash code: " + sessionFactory.hashCode());
        }
        //Returns the same SessionFactory object everytime the method is called
        return sessionFactory;
    }//createSessionFactory() END

    protected static synchronized void close() {

        if (sessionFactory != null) {
            try {
                sessionFactory.close();
                System.out.println("sessionFactory closed: " + sessionFactory.isClosed());

            } catch (HibernateException hExc) {
                System.out.println("Cannot close sessionFactory...\n" + hExc);
            }

        }
        if (serviceRegistry != null) {
            StandardServiceRegistryBuilder.destroy(serviceRegistry);
            System.out.println("Service Registry destroyed");
        }

    }//close() END

}
