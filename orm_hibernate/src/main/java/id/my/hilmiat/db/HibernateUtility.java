package id.my.hilmiat.db;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import id.my.hilmiat.entity.Person;

public class HibernateUtility {
    private static SessionFactory session;
    public static SessionFactory buatSession(){
        //cek apakah sebelumnya sudah ada session, jika belum ada maka buat session
        if(session==null){
            String url = "jdbc:mysql://localhost:3306/service_demo";
            String user = "service_user";
            String pass = "service_password";
            //buat session
            Configuration config = new Configuration();
            Properties setting = new Properties();
            setting.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            setting.put(Environment.URL, url);
            setting.put(Environment.USER,user);
            setting.put(Environment.PASS, pass);

            setting.put(Environment.SHOW_SQL,"true");
            setting.put(Environment.HBM2DDL_AUTO, "create-drop");

            config.setProperties(setting);

            config.addAnnotatedClass(Person.class);

            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(config.getProperties()).build();
            session = config.buildSessionFactory(serviceRegistry);
        }
        return session;
    }
}
