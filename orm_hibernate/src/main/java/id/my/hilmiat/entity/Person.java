package id.my.hilmiat.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "person")
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id @Setter @Getter
    Long id;

    @Setter @Getter
    @Column(name = "firstname")
    String firstname;
    
    @Setter @Getter
    @Column
    String lastname;

    @ManyToOne
    @JoinColumn(name = "id_departement")
    @Getter @Setter
    private Departement departement;
}
