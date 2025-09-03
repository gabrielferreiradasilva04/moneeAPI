package br.com.monee.api.service;

import br.com.monee.api.config.SecurityFilterConfigurarion;
import br.com.monee.api.entity.UserEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class TokenService {

    @Value("$app.config.api-key")
    private String key;
    private final String ISSUE = "monee";

    /**
     * Criar o token do usuário na autenticação
     * @param userEntity
     * @return
     */
    public String generateToken(UserEntity userEntity){
        Date expirationDate = Date.from(
                Instant.now().plus(1, ChronoUnit.HOURS)
        );
        try {
            Algorithm algorithm = Algorithm.HMAC256(key);
            String token = JWT.create()
                    .withIssuer(ISSUE)
                    .withSubject(userEntity.getEmail())
                    .withExpiresAt(expirationDate)
                    .sign(algorithm);
            return token;
        }catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar o token: " +exception.getMessage());
        }
    }

    /**
     * Validar o token a cada requisição enviada pelo usuário.
     * @param token
     * @return
     */
    public String validateToken(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(key);
            return JWT.require(algorithm)
                    .withIssuer(ISSUE)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Erro ao verificar token: "+exception.getMessage());
        }
    }

    /**
     * Armazena o token JWT gerado pela aplicação no cookie do navegador
     * @param token token string gerado pela aplicação
     * @param response response passada no metodo de autenticacao
     */
    public void registerTokenInCookie(String token, HttpServletResponse response){
        ResponseCookie jwtCookie =  ResponseCookie.from(SecurityFilterConfigurarion.ACCESS_TOKEN, token)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(Duration.ofHours(1))
                .sameSite("Lax")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, jwtCookie.toString());
    }

    /**
     * Busca o token em diferentes lugares a cada request
     * @param request
     * @return
     */
    public String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        //busca o token no bearer
        if(authHeader != null && authHeader.startsWith("Bearer")){
            return authHeader.replace("Bearer", "");
        } else {
            //busca o token no cookie se não encontrar no bearer
            return this.getTokenFromCookie(request);
        }
    }

    /**
     * recupera o token do cookie a cada requisição
     * @param request
     * @return
     */
    public String getTokenFromCookie(HttpServletRequest request){
        if(request.getCookies() != null){
            for(Cookie cookie : request.getCookies()){
                if(SecurityFilterConfigurarion.ACCESS_TOKEN.equals(cookie.getName())){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

}
