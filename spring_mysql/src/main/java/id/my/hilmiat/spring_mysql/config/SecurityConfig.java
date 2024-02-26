package id.my.hilmiat.spring_mysql.config;

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
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * JwtTokenFilter
 */
class JwtTokenFilter extends OncePerRequestFilter {

    // private final JwtTokenUtil tokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //baca dari header, apakah ada token
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(!header.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        //validasi token
        final String token = header.split(" ")[1].trim();
        // if(!tokenUtil.validate(token)){
        //     filterChain.doFilter(request, response);
        //     return;
        // }

        //get username
        //cari di database


    }

    
}


@Configuration
public class SecurityConfig {

    private final JwtTokenFilter jwtTokenFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http.authorizeHttpRequests(
            (r)->r
            .requestMatchers(HttpMethod.PUT,"person").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE,"person").authenticated()
            .requestMatchers("/person","/swagger-ui/**","/v3/**").permitAll()
            .anyRequest().authenticated()
        ).httpBasic(Customizer.withDefaults()).build();
        // tambahkan JWT filter
        http.addFilterBefore(jwtTokenFilter, null)

    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){

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

    @Autowired
    private DataSource dataSource;

    // @Autowired
    // public void configUser(AuthenticationManagerBuilder auth) throws Exception{
    //     auth.jdbcAuthentication().dataSource(dataSource);
    // }

    @Autowired
    public void configUser(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication().dataSource(dataSource);
        // .usersByUsernameQuery("select * from person where firstname =?");
    }

    // @Bean
    // AuthenticationManager manager(UserDetailsService userDetailsService){
    //     return auth -> {
    //         String username = auth.getPrincipal().toString();
    //         String pass = auth.getCredentials().toString();
            
    //         // UserDetails user = userDetailsService.loadUserByUsername(username);
    //         // if(!user.isEnabled()){
    //         //     //user belum di enable
    //         //     throw new DisabledException("User belum aktif");
    //         // }
    //         return new UsernamePasswordAuthenticationToken(username,null);
    //     };
    // }
}
