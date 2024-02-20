package id.my.hilmiat.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
public class User {

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Getter
    private int id;

    @Getter @Setter
    private String username;

    @Getter @Setter
    private String email;

    @Setter
    private String password;

}
