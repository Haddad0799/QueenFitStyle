package br.com.queenfitstyle.services;

import br.com.queenfitstyle.dtos.CadastroUsuarioDto;
import br.com.queenfitstyle.exceptions.UsuarioException;
import br.com.queenfitstyle.models.Cliente;
import br.com.queenfitstyle.models.Role;
import br.com.queenfitstyle.models.Usuario;
import br.com.queenfitstyle.repositorys.ClienteRepository;
import br.com.queenfitstyle.repositorys.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, ClienteRepository clienteRepository, ClienteRepository clienteRepository1) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void cadastrarUsuarioCliente(CadastroUsuarioDto dto) {
        if (usuarioRepository.existsByLogin(dto.email())) {
            throw new UsuarioException("Email já cadastrado.");
        }

        Usuario usuario = new Usuario(dto.email(), passwordEncoder.encode(dto.senha()));
        usuario.getRoles().add(Role.ROLE_CLIENTE);

        Cliente cliente = new Cliente();
        cliente.setUsuario(usuario);
        usuario.setCliente(cliente);

        usuarioRepository.save(usuario);
    }


    @Transactional
    public void cadastrarUsuarioAdmin(CadastroUsuarioDto dto) {

        if (usuarioRepository.existsByLogin(dto.email())) {
            throw new UsuarioException("Email já cadastrado.");
        }
        Usuario usuario = new Usuario(dto.email(),passwordEncoder.encode(dto.senha()));
        usuario.getRoles().add(Role.ROLE_ADMIN);
        usuarioRepository.save(usuario);
    }

}
