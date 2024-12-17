package br.com.queenfitstyle.infra.util;

import br.com.queenfitstyle.exceptions.InvalidTokenException;
import br.com.queenfitstyle.exceptions.TokenNotProvidedException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", System.currentTimeMillis());
        body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        body.put("path", request.getRequestURI());

        if (authException instanceof InvalidTokenException) {
            body.put("error", "Token inválido");
            body.put("message", "O token fornecido é inválido ou expirou.");
        } else if (authException instanceof TokenNotProvidedException) {
            body.put("error", "Falta de autenticação");
            body.put("message", "O token de autenticação não foi enviado.");}
        else {
            body.put("error", "Erro de autenticação");
            body.put("message", authException.getMessage());
        }


        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(body));
    }
}


