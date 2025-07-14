package br.com.monee.api.config;

import br.com.monee.api.repository.UserRepository;
import br.com.monee.api.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilterConfigurarion extends OncePerRequestFilter {

    private TokenService tokenService;
    private UserRepository userRepository;

    public SecurityFilterConfigurarion(TokenService tokenService, UserRepository userRepository){
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = recoverToken(request);
        if(token != null){
            var email = tokenService.validateToken(token);
            UserDetails userDetails = userRepository.findByEmail(email);
            if(userDetails != null){
                var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader != null && authHeader.startsWith("Bearer")){
            return authHeader.replace("Bearer", "");
        }
        return null;
    }
}
