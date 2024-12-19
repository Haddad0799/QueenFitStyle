package br.com.queenfitstyle.repositorys;

import br.com.queenfitstyle.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByLogin(String login);

    @Query("SELECT u FROM Usuario u WHERE u.login = :login")
    UserDetails findBylogin(@Param("login") String login);
}
