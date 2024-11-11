package br.com.queenfitstyle.infra.filter;

import br.com.queenfitstyle.exceptions.TokenNotProvidedException;
import br.com.queenfitstyle.infra.util.JwtUtil;
import br.com.queenfitstyle.services.UsuarioService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
public class JwtAutenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UsuarioService usuarioService;

    public JwtAutenticationFilter(JwtUtil jwtUtil, UsuarioService usuarioService) {
        this.jwtUtil = jwtUtil;
        this.usuarioService = usuarioService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Se o endpoint não requer autenticação, continue a cadeia de filtros
        if (!endpointBloqueado(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Recupera o token JWT do cabeçalho Authorization
        String tokenJwt = recuperarToken(request);

        // Extrai o subject do token e carrega o usuário
        String subject = jwtUtil.getSubject(tokenJwt);
        UserDetails userDetails = usuarioService.loadUserByUsername(subject);

        // Cria a autenticação e a define no contexto de segurança
        var usuarioAutenticado = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usuarioAutenticado);

        // Prossegue com o restante da cadeia de filtros
        filterChain.doFilter(request, response);
    }

    private Boolean endpointBloqueado(HttpServletRequest request) {
        String requestUri = request.getRequestURI();

        return !requestUri.equalsIgnoreCase("/login") &&
                !requestUri.equalsIgnoreCase("/usuarios/cadastro-cliente") &&
                !requestUri.startsWith("/swagger-ui") &&
                !requestUri.startsWith("/v3/api-docs");
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.replace("Bearer ", "");
        }
        throw new TokenNotProvidedException();
    }
}

