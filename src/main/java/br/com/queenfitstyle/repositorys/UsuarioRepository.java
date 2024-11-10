package br.com.queenfitstyle.repositorys;

import br.com.queenfitstyle.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByLogin(String login);
}
