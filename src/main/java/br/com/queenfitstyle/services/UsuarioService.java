package br.com.queenfitstyle.services;

import br.com.queenfitstyle.dtos.CadastroUsuarioDto;
import br.com.queenfitstyle.exceptions.UsuarioException;
import br.com.queenfitstyle.models.Role;
import br.com.queenfitstyle.models.Usuario;
import br.com.queenfitstyle.repositorys.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;


    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void cadastrarUsuarioCliente (CadastroUsuarioDto dto) {
            if(usuarioRepository.existsByLogin(dto.email())) {
                throw new UsuarioException("Usuário já cadastrado.");
            }

           Usuario usuario = new Usuario(dto.email(), passwordEncoder.encode(dto.senha()));
           usuario.getRoles().add(Role.ROLE_CLIENTE);
           usuarioRepository.save(usuario);
    }

    public void cadastrarUsuarioAdmin(CadastroUsuarioDto dto) {

        if (usuarioRepository.existsByLogin(dto.email())){
            throw new UsuarioException("Usuário já cadastrado.");
        }

        Usuario usuario = new Usuario(dto.email(), passwordEncoder.encode(dto.senha()));
        usuario.getRoles().add(Role.ROLE_ADMIN);
        usuarioRepository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(username).orElseThrow(()-> new UsernameNotFoundException("Usuário não encontrado"));
    }
}
