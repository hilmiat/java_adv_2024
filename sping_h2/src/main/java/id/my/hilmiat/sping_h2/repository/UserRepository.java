package id.my.hilmiat.sping_h2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import id.my.hilmiat.sping_h2.model.Person;

public interface UserRepository extends JpaRepository<Person, Long>{
    
}
