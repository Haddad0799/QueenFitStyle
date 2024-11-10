package br.com.queenfitstyle.services;

import br.com.queenfitstyle.dtos.CadastroUsuarioDto;
import br.com.queenfitstyle.exceptions.UsuarioException;
import br.com.queenfitstyle.models.Role;
import br.com.queenfitstyle.models.Usuario;
import br.com.queenfitstyle.repositorys.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;


    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void cadastrarUsuarioCliente (CadastroUsuarioDto dto) {
            if(usuarioRepository.existsByLogin(dto.login())) {
                throw new UsuarioException("Usuário já cadastrado.");
            }

           Usuario usuario = new Usuario(dto.login(), passwordEncoder.encode(dto.senha()));
           setRole(usuario,Role.ROLE_CLIENTE);
           usuarioRepository.save(usuario);
    }

    public void cadastrarUsuarioAdmin(CadastroUsuarioDto dto) {

        if (usuarioRepository.existsByLogin(dto.login())){
            throw new UsuarioException("Usuário já cadastrado.");
        }

        Usuario usuario = new Usuario(dto.login(), passwordEncoder.encode(dto.senha()));
        setRole(usuario,Role.ROLE_ADMIN);
        usuarioRepository.save(usuario);
    }

    private void setRole(Usuario usuario, Role role) {
        usuario.getRoles().add(role);
    }
}
