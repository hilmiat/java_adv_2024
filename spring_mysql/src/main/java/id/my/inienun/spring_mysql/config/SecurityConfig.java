package id.my.inienun.spring_mysql.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import id.my.inienun.spring_mysql.security.JwtTokenFilter;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf->csrf.disable());
        http.authorizeHttpRequests(
            (r)->r
            .requestMatchers(HttpMethod.PUT,"person").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE,"/person").authenticated()
            .requestMatchers("/person","/swagger-ui/**","/v3/**","/auth/**").permitAll()
            .anyRequest().authenticated()
        ).httpBasic(Customizer.withDefaults());
        //tambahkan filter
        http.addFilterBefore(new JwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){

        UserDetails user = User.builder()
        .username("bob")
        .password(passwordEncoder().encode("rahasia"))
        .roles("USER").build();

        UserDetails admin = User.builder()
        .username("admin")
        .password(passwordEncoder().encode("password"))
        .roles("ADMIN").build();

        return new InMemoryUserDetailsManager(user,admin);
    }

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configUser(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication().dataSource(dataSource);
    }

    // @Autowired
    // public void configUser(AuthenticationManagerBuilder auth) throws Exception{
    //     auth.jdbcAuthentication().dataSource(dataSource);
    //     // .usersByUsernameQuery("select * from person where firstname =?");
    // }

    @Bean
    AuthenticationManager manager(UserDetailsService userDetailsService){
        return auth -> {
            String username = auth.getPrincipal().toString();
            String pass = auth.getCredentials().toString();
            
            UserDetails user = userDetailsService.loadUserByUsername(username);
            if(!user.isEnabled()){
            //     //user belum di enable
                throw new DisabledException("User belum aktif");
            }
            return new UsernamePasswordAuthenticationToken(username,pass);
        };
    }
}