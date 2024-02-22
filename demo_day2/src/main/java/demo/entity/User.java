package demo.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
public class User {  
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    @Getter
    int id;
    @Getter @Setter
    String username,email,password;
}
