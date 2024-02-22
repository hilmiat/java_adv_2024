package demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "departement")
public class Departement {
    @Id @Setter @Getter
    Long id;

    @Column @Setter @Getter
    String dept_name;

    @OneToMany() @Getter
    private List<Person> persons = new ArrayList<Person>();
}