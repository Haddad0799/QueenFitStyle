package br.com.queenfitstyle.infra.filter;

import br.com.queenfitstyle.infra.service.JwtAutenticationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
public class JwtAutenticationFilter extends OncePerRequestFilter {

    private JwtAutenticationService jwtAutenticationService;

    public JwtAutenticationFilter(JwtAutenticationService jwtAutenticationService) {
        this.jwtAutenticationService = jwtAutenticationService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    }
}
