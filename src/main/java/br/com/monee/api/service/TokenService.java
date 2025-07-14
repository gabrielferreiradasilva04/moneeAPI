package br.com.monee.api.service;

import br.com.monee.api.entity.UserEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
        try {
            Algorithm algorithm = Algorithm.HMAC256(key);
            String token = JWT.create()
                    .withIssuer(ISSUE)
                    .withSubject(userEntity.getEmail())
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

}
