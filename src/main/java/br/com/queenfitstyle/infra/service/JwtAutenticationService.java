package br.com.queenfitstyle.infra.service;

import br.com.queenfitstyle.dtos.AuthDto;
import br.com.queenfitstyle.dtos.JwtDto;
import br.com.queenfitstyle.infra.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class JwtAutenticationService {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public JwtAutenticationService(JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    public JwtDto autenticar(AuthDto dto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.email(), dto.senha())
        );
        String token = jwtUtil.gerarToken(authentication.getName());
        return new JwtDto(token);
    }
}
