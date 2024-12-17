package br.com.queenfitstyle.infra.filter;

import br.com.queenfitstyle.exceptions.TokenNotProvidedException;
import br.com.queenfitstyle.infra.service.JwtAutenticationService;
import br.com.queenfitstyle.infra.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
public class JwtAutenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final JwtAutenticationService jwtAutenticationService;
    private final AuthenticationEntryPoint customAuthenticationEntryPoint;

    public JwtAutenticationFilter(JwtUtil jwtUtil, @Lazy JwtAutenticationService jwtAutenticationService, AuthenticationEntryPoint customAuthenticationEntryPoint) {
        this.jwtUtil = jwtUtil;
        this.jwtAutenticationService = jwtAutenticationService;
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try{
            if (isEndpointLiberado(request)) {
                filterChain.doFilter(request, response);
                return;
            }

            String tokenJwt = recuperarToken(request);

            String subject = jwtUtil.getSubject(tokenJwt);

            UserDetails userDetails = jwtAutenticationService.loadUserByUsername(subject);

            var usuarioAutenticado = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(usuarioAutenticado);

            filterChain.doFilter(request, response);
        } catch (AuthenticationException authException) {
            SecurityContextHolder.clearContext();
            customAuthenticationEntryPoint.commence(request, response, authException);
        }

    }

    private Boolean isEndpointLiberado(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        return requestUri.equalsIgnoreCase("/login") ||
                requestUri.equalsIgnoreCase("/register") ||
                requestUri.startsWith("/enderecos/") ||
                requestUri.startsWith("/swagger-ui") ||
                requestUri.startsWith("/v3/api-docs");
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        throw new TokenNotProvidedException();
    }
}

