package id.my.inienun.spring_mysql.service;

import org.springframework.data.jpa.repository.JpaRepository;

import id.my.inienun.spring_mysql.model.Departemen;

public interface DepartemenRepository extends JpaRepository<Departemen,Long>{
    
}