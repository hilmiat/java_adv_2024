package id.my.hilmiat.sping_h2.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@NamedQuery(name = "Person.getName", query="select CONCAT(p.firstname,p.lastname) from Person p")
public class Person {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) 
    Long id;
    @Column 
    String firstname, lastname;

    List<String> getName(){
        List<String> test = new ArrayList<>();
        test.add("firstname"+firstname);
        return test;
    }


}
