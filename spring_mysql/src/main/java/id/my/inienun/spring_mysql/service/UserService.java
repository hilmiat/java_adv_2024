package id.my.inienun.spring_mysql.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

public class UserService {
     public UserDetailsService getDetailUser(){
        UserDetails user = User.withUsername("USER 1").roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user);
    }
}