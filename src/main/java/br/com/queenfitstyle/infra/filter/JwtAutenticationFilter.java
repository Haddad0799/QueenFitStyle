package br.com.queenfitstyle.infra.filter;

import br.com.queenfitstyle.exceptions.TokenNotProvidedException;
import br.com.queenfitstyle.infra.util.JwtUtil;
import br.com.queenfitstyle.services.UsuarioService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
public class JwtAutenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final ApplicationContext applicationContext;

    public JwtAutenticationFilter(JwtUtil jwtUtil, ApplicationContext applicationContext) {
        this.jwtUtil = jwtUtil;
        this.applicationContext = applicationContext;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Se o endpoint não requer autenticação, continue a cadeia de filtros
        if (isEndpointLiberado(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Recupera o token JWT do cabeçalho Authorization
        String tokenJwt = recuperarToken(request);

        // Obtém o usuário através do serviço, usando o contexto da aplicação
        UsuarioService usuarioService = applicationContext.getBean(UsuarioService.class);
        String subject = jwtUtil.getSubject(tokenJwt);
        UserDetails userDetails = usuarioService.loadUserByUsername(subject);

        var usuarioAutenticado = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usuarioAutenticado);

        filterChain.doFilter(request, response);
    }

    private Boolean isEndpointLiberado(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        return requestUri.equalsIgnoreCase("/login") ||
                requestUri.equalsIgnoreCase("/usuarios/cadastrar-cliente") ||
                requestUri.equalsIgnoreCase("/usuarios/cadastrar-admin") ||
                requestUri.startsWith("/swagger-ui") ||
                requestUri.startsWith("/v3/api-docs");
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.replace("Bearer ", "");
        }
        throw new TokenNotProvidedException();
    }
}

