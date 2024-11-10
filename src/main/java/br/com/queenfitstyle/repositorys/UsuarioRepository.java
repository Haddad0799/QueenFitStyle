package br.com.queenfitstyle.repositorys;

import br.com.queenfitstyle.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByLogin(String login);

    Optional<UserDetails> findByLogin(String username);
}
