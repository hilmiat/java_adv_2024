package id.my.hilmiat.spring_mysql.service;

import org.springframework.data.jpa.repository.JpaRepository;

import id.my.hilmiat.spring_mysql.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{
    
}
