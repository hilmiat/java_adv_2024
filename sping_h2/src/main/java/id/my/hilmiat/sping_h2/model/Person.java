package id.my.hilmiat.sping_h2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column @Getter @Setter
    String firstname, lastname;
}
