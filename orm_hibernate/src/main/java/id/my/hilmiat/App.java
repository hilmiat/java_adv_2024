package id.my.hilmiat;

import java.util.List;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import id.my.hilmiat.DAO.PersonDAO;
import id.my.hilmiat.entity.Person;

public class App 
{
    public static void main( String[] args )
    {
        Person p = new Person(1L,"hilmi baru","tawakal");
        
        PersonDAO dao = new PersonDAO();
        dao.addData(p);

        List<Person> dataPerson = dao.getAll();
        for(Person x: dataPerson){
            System.out.println(x.getFirstname());
        }
    }

   
}
