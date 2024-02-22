package main.java.id.my.hilmiat.demo_spring.model;

@Entity
public class User {
    @Id 
    Long id;
    @Column
    String firstname, lastname;
}
