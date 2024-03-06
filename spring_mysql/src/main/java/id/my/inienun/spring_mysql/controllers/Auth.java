package id.my.inienun.spring_mysql.controllers;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import id.my.inienun.spring_mysql.security.JwtTokenUtil;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class Auth {
    @Autowired
    AuthenticationManagerBuilder builder;
    // AuthenticationManager authenticationManager;
    private static final Logger log = LoggerFactory.getLogger(Auth.class);

    @RateLimiter(name = "auth-service")
    @PostMapping("/auth/token")
    public String getToken(@RequestBody String username,@RequestBody String password){
        //cek di database, ada user pass yang dikirima atau tidak
        log.info("AUTH>>"+username);
        AuthenticationManager authenticationManager;
        try {
            authenticationManager = builder.getOrBuild();
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );
            log.info("AUTH>>"+auth.getPrincipal());
            log.info(auth.toString());
            SecurityContextHolder.getContext().setAuthentication(auth);
            //Jika ada
            return new JwtTokenUtil().createToken(auth); 
        } catch (Exception e) {
            return e.getMessage();
        }
        // return "To be changed";
    }
}
