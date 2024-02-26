package id.my.hilmiat.sping_h2.repository;


import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import id.my.hilmiat.sping_h2.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{


    List<Person> searchPersonByFirstnameOrLastname(String query,String query2);

    @Query(
        value="select p from Person p where p.firstname like %?1%", 
        countQuery= "select count(p) from Person p where p.firstname like %?1%"
        )
    Page<Person> searchPersonSQL(String query, Pageable page);
    
    List<String> getName();
}
