package demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Setter @Getter
    Long id;
    @Setter @Getter
    String firstname,lastname;
}