package id.my.hilmiat.sping_h2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer.AuthorizationManagerRequestMatcherRegistry;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig{
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http.authorizeRequests(c->c
        // .antMatchers("/","/error","/webjars/**").permitAll()
        // .antMatchers(HttpMethod.POST,"/users").hasRole("ADMIN")
        .anyRequest().authenticated()
        )
        .build();

    }

}
