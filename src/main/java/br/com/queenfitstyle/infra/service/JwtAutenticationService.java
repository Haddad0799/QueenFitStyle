package br.com.queenfitstyle.infra.service;

import br.com.queenfitstyle.dtos.AuthDto;
import br.com.queenfitstyle.dtos.JwtDto;
import br.com.queenfitstyle.infra.util.JwtUtil;
import br.com.queenfitstyle.models.Usuario;
import br.com.queenfitstyle.repositorys.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtAutenticationService implements UserDetailsService {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;

    public JwtAutenticationService(JwtUtil jwtUtil, @Lazy AuthenticationManager authenticationManager, UsuarioRepository usuarioRepository) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
    }

    public JwtDto autenticar(AuthDto dto) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());

        var usuarioAutenticado = authenticationManager.authenticate(token);

        return new JwtDto(jwtUtil.gerarToken((Usuario) usuarioAutenticado.getPrincipal()));
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findUsuarioBylogin(username);
    }
}
