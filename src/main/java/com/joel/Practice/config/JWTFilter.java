package com.joel.Practice.config;


import com.joel.Practice.exceptions.customExceptions.UnAuthorizedException;
import com.joel.Practice.service.JWTService;
import com.joel.Practice.service.MyUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NullMarked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JWTFilter extends OncePerRequestFilter {

   @Autowired
   private JWTService jwtService;
   @Autowired
   ApplicationContext context;

    public JWTFilter(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @NullMarked
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {


        String authHeader = request.getHeader("Authorization");
        String token=null;
        String username=null;
      try {
          if (authHeader != null && authHeader.startsWith("Bearer ")) {
              token = authHeader.substring(7);
              username = jwtService.getUsername(token);
          }
      }

      catch (SignatureException e) {
          logger.info("Invalid Token, Please Authenticate Again");
          request.setAttribute("error","Invalid Token, Please Authenticate Again");
          throw new UnAuthorizedException("Invalid Token, Please Authenticate Again"); // Custom message for EntryPoint
      } catch (ExpiredJwtException e) {
          logger.info("Token Expired, Please Authenticate Again");
          request.setAttribute("error","Session Expired !!, Please Authenticate Again");
          throw new UnAuthorizedException("Token Expired, Please Authenticate Again !!");
      }
      catch (IllegalArgumentException e) {
          throw new UnAuthorizedException("JWT claims string is empty or invalid.");
      }

        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = context.getBean(MyUserDetailsService.class).loadUserByUsername(username);
            Set<String> roles = jwtService.extractRoles(token);
            // Convert Set<String> roles to Set<GrantedAuthority>
            Set<GrantedAuthority> authorities = roles.stream()
                    .map(SimpleGrantedAuthority::new)  // Convert each role string to a SimpleGrantedAuthority
                    .collect(Collectors.toSet());

            if(jwtService.validateToke(token, userDetails)){
                UsernamePasswordAuthenticationToken utoken=
                        new UsernamePasswordAuthenticationToken(userDetails,null,authorities);
                utoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(utoken);
            }
        }
        filterChain.doFilter(request,response);

    }
}
