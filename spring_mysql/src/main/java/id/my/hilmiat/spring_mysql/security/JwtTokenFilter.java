package id.my.hilmiat.spring_mysql.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(JwtTokenFilter.class);
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //baca request header
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        // log.info("HEADER>>"+header);
        //jika tidak ada header
        if(header==null || !header.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        //get token dari string
        String token = header.split(" ")[1].trim();
        if(token == null){
            filterChain.doFilter(request, response);
            return;
        }
        //validasi token
        JwtTokenUtil tokenUtil = new JwtTokenUtil();
        if(!tokenUtil.validateToken(token)){
            filterChain.doFilter(request, response);
            return;
        }

        Authentication auth = tokenUtil.getAuthFromToken(token);
        log.info(auth.toString());
        //set auth
        if(auth!=null && !(auth instanceof AnonymousAuthenticationToken)){
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        log.info("TOKEN>>"+token);
        filterChain.doFilter(request, response);
    }
    
}
