package id.my.hilmiat.spring_mysql.security;

import java.util.Base64;
import java.util.Collection;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;

public class JwtTokenUtil {
    
    public JwtTokenUtil() {
    }
    private SecretKey secretKey;
    private String rahasia = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOjEsInVzZXJuYW1lIjoicHVzdGFrYW11bGlhIiwicm9sZSI6InBlbnllZGlhIiwiZXhwIjoxNzExMTY5NDUxLCJpYXQiOjE3MDU5ODU0NTF9.yvaEK9xrWiJ9aLUuaNisGmLHwsQ0RWIia03fMg_J7jU";
    private static final Logger log = LoggerFactory.getLogger(JwtTokenUtil.class);
    @PostConstruct
    public void init(){
        String secret = Base64.getEncoder().encodeToString(rahasia.getBytes());
        this.secretKey = new SecretKeySpec(secret.getBytes(),"HmacSHA256");
    }
    //Create Token
    public String createToken(Authentication auth){
        this.init();
        log.info("CREATING TOKEN");
        //username
        String username = auth.getName();
        Date sekarang = new Date();
        //menambahkan authorities
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

        JwtBuilder builder = Jwts.builder()
            .issuedAt(sekarang)
            .subject(username)
            .signWith(secretKey);

        if(!authorities.isEmpty()){
            String roles = authorities.stream().map(GrantedAuthority::getAuthority).reduce((a,b)->a+","+b).get();
            builder.claim("roles", roles);
        }
        return builder.compact();
    }
    //Validate Token
    public boolean validateToken(String token){
        this.init();
        log.info("Validating "+token);
        try{
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
            return true;
        }catch(Exception e){
            log.error("TIDAK valid"+e.getMessage());
        }
        return false;    
    }
    //get Auth
    public Authentication getAuthFromToken(String token){
        Claims claims = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();

        Object authClaim = claims.get("roles");
        Collection<? extends GrantedAuthority> authorities = authClaim == null?
            AuthorityUtils.NO_AUTHORITIES :
            AuthorityUtils.commaSeparatedStringToAuthorityList(authClaim.toString());

        User user = new User(claims.getSubject(),"",authorities);
        return new UsernamePasswordAuthenticationToken(user, "",authorities);   
    }

}
