package id.my.hilmiat.spring_mysql.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Person {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String firstname,lastname;
}

