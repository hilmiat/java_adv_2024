package id.my.hilmiat.spring_mysql.service;

import org.springframework.data.jpa.repository.JpaRepository;

import id.my.hilmiat.spring_mysql.model.Departemen;

public interface DepartemenRepository extends JpaRepository<Departemen,Long>{
    
}
