package id.my.inienun.spring_mysql.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Departemen {
    @Id @GeneratedValue
    Long id;
    String name;
}