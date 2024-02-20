package id.my.hilmiat.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class Role {
    @Getter
    int id;
    @Setter @Getter
    String role_name;
}
