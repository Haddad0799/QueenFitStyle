package br.com.queenfitstyle.repositorys;

import br.com.queenfitstyle.models.Cidade;
import br.com.queenfitstyle.models.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    boolean existsByNomeAndEstado(String nome, Estado estado);

    Cidade findByNomeAndEstado(String nome, Estado estado);
}
