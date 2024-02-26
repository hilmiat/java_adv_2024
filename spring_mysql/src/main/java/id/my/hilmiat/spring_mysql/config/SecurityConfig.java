package id.my.hilmiat.spring_mysql.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http.authorizeHttpRequests(
            (r)->r
            .requestMatchers(HttpMethod.PUT,"person").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE,"person").authenticated()
            .requestMatchers("/person","/swagger-ui/**","/v3/**").permitAll()
            .anyRequest().authenticated()
        ).httpBasic(Customizer.withDefaults()).build();
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        // UserDetails user = User.withUsername("USER 1").roles("ADMIN").build();
        // return new InMemoryUserDetailsManager(user);
        

        UserDetails user = User.builder()
        .username("user")
        .password(passwordEncoder().encode("password"))
        .roles("USER").build();

        UserDetails admin = User.builder()
        .username("admin")
        .password(passwordEncoder().encode("password"))
        .roles("ADMIN").build();

        return new InMemoryUserDetailsManager(user,admin);
    }

    @Bean
    AuthenticationManager manager(UserDetailsService userDetailsService){
        return auth -> {
            String username = auth.getPrincipal().toString();
            String pass = auth.getCredentials().toString();
            
            // UserDetails user = userDetailsService.loadUserByUsername(username);
            // if(!user.isEnabled()){
            //     //user belum di enable
            //     throw new DisabledException("User belum aktif");
            // }
            return new UsernamePasswordAuthenticationToken(username,null);
        };
    }
}
