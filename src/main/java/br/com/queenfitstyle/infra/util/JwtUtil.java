package br.com.queenfitstyle.infra.util;

import br.com.queenfitstyle.exceptions.FalhaAoGerarTokenException;
import br.com.queenfitstyle.exceptions.InvalidTokenException;
import br.com.queenfitstyle.models.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public String gerarToken(Usuario usuario) {
        var algoritmo = Algorithm.HMAC256(jwtSecret);
        Instant tempoDeExpiracao = LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant();

        try {
            return JWT.create()
                    .withIssuer("queenfitstyle")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(tempoDeExpiracao)
                    .sign(algoritmo);
        } catch (JWTCreationException ex) {
            throw new FalhaAoGerarTokenException();
        }
    }

    public String getSubject(String token)  {
        try {
            var algoritmo = Algorithm.HMAC256(jwtSecret);

            return JWT.require(algoritmo)
                    .withIssuer("queenfitstyle")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTDecodeException | TokenExpiredException ex) {
            throw new InvalidTokenException();
        }
    }

}
