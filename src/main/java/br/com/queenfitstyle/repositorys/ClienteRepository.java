package br.com.queenfitstyle.repositorys;

import br.com.queenfitstyle.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

        @Query("select c from Cliente c where c.usuario.id = :id")
        Cliente findByUsuarioId(Long id);

}
