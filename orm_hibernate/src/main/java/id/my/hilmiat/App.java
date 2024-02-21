package id.my.hilmiat;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import id.my.hilmiat.DAO.DepartementDAO;
import id.my.hilmiat.DAO.PersonDAO;
import id.my.hilmiat.entity.Departement;
import id.my.hilmiat.entity.Person;

public class App 
{
    public static void main( String[] args )
    {

        DepartementDAO departementDAO = new DepartementDAO();

        Departement d1 = new Departement();
        d1.setDept_name("HRD");
        d1.setId(1L);
        departementDAO.addData(d1);

        
        // List<Departement> dataDepartements = departementDAO.getAll();
        // for(Departement d:dataDepartements){
        //     System.out.println("Departemen: "+d.getDept_name());
        //     for(Person staff: d.getPersons()){
        //         System.out.println("- "+staff.getFirstname());
        //     }
        // }

        Person p = new Person(1L,"hilmi baru","tawakal",d1);
        Person p2 = new Person(2L,"hilmi baru","tawakal",d1);

        PersonDAO dao = new PersonDAO();
        dao.addData(p);
        dao.addData(p2);
        dao.addData(new Person(3L, "Test", "test ", d1));

        //test update
        p.setFirstname("Updated first name");
        dao.update(p);
        //test delete
        dao.delete(2L);

        //test get by id
        Optional opt = dao.get(1L);
        Person cari = (Person) opt.get();
        System.out.println(cari.getFirstname());

        // List<Person> dataPerson = dao.getAll();
        // for(Person x: dataPerson){
        //     System.out.println(x.getFirstname()+"=>"+x.getDepartement().getDept_name());
        // }



    }

   
}
