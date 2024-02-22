package demo.db;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import demo.entity.Departement;
import demo.entity.Person;

public class HibernateUtility {
    private static SessionFactory session;
    
    // public static SessionFactory buatSession(){
    //     //cek apakah sebelumnya sudah ada session, jika belum ada maka buat session
    //     if(session==null){
    //         String url = "jdbc:mysql://localhost:3306/service_demo";
    //         String user = "service_user";
    //         String pass = "service_password";
    //         //buat session
    //         //buat properties meniru file konfigurasi 
    //         Properties setting = new Properties();
    //         setting.put(Environment.JAKARTA_JDBC_DRIVER, "com.mysql.cj.jdbc.Driver");
    //         setting.put(Environment.JAKARTA_JDBC_URL, url);
    //         setting.put(Environment.JAKARTA_JDBC_USER,user);
    //         setting.put(Environment.JAKARTA_JDBC_PASSWORD, pass);
    //         setting.put(Environment.SHOW_SQL,"true");
    //         setting.put(Environment.HBM2DDL_AUTO,"create-drop");

    //         Configuration config = new Configuration();
    //         config.setProperties(setting);
    //         config.addAnnotatedClass(Person.class);
    //         config.addAnnotatedClass(Departement.class);

    //         StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
    //             .applySettings(config.getProperties()).build();
    //         session = config.buildSessionFactory(serviceRegistry);
    //     }
    //     return session;
    // }
    
    public static SessionFactory buatSessionFactory(){
        if (session==null) {
            session = new Configuration().configure().buildSessionFactory();   
        }
        return session;   
    }
}