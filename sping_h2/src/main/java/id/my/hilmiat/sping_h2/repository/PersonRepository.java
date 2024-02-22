package id.my.hilmiat.sping_h2.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import id.my.hilmiat.sping_h2.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{
    List<Person> searchPersonByFirstnameOrLastname(String query,String query2);

    @Query("select p from Person p where p.firstname = %?1%")
    List<Person> searchPersonSQL(String query);
}
