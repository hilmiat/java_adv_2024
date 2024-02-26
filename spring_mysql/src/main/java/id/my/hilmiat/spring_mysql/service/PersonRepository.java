package id.my.hilmiat.spring_mysql.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import id.my.hilmiat.spring_mysql.model.Departemen;
import id.my.hilmiat.spring_mysql.model.Person;

public interface PersonRepository 
extends JpaRepository<Person, Long>{
    //Cara 1: dengan menggunakan method name
    //Cari seluruh person berdasarkan firstname
    List<Person> findAllByFirstname(String firstname);
    List<Person> findAllByFirstnameAndLastname(String firstname,String lastname);
    List<Person> findAllByFirstnameOrLastname(String firstname,String lastname);

    //searching ignore case
    List<Person> findByFirstnameOrLastnameAllIgnoreCase(String firstname, String lastname);
    List<Person> findAllOrderByfirstname(String order);

    List<Person> findAllByDepartemen(Departemen d);
    List<Person> findAllByDepartemen_name(String dept_name);

    //Cara 2: dengan annotation query
    @Query(value="select * from person where firstname like %?1%",nativeQuery = true)
    List<Person> findAllByFirstnameLike(String firstname);
    @Query("select p from Person p ORDER BY firstname")
    List<Person> findPersonOrderByFirstname();

    List<Person> getActivePerson();

}
