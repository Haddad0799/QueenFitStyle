package br.com.queenfitstyle.repositorys;

import br.com.queenfitstyle.models.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
