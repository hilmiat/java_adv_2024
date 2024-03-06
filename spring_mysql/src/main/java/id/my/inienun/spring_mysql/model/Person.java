package id.my.inienun.spring_mysql.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Data
// cara 3: named query
@NamedQuery(
    name="Person.getActivePerson",
    query="select p from Person p where p.isActive = true"
)
@Entity
public class Person {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String firstname,lastname;
    boolean isActive;
    Date created_at;
    Date updated_at;

    @PrePersist
    protected void onCreate(){
        this.created_at = new Date();
        this.updated_at = new Date();
        isActive = true;
    }
    @PreUpdate
    protected void onUpdate(){
        this.updated_at = new Date();
    }

    @ManyToOne(optional = false)
    @JoinColumn(name="id_departemen", nullable = false)
    Departemen departemen;
}
