package br.com.queenfitstyle.services;

import br.com.queenfitstyle.models.Cidade;
import br.com.queenfitstyle.models.Estado;
import br.com.queenfitstyle.repositorys.CidadeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CidadeService {

    private final CidadeRepository cidadeRepository;

    public CidadeService(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

    @Transactional
    protected Cidade getCidade(String nome, Estado estado) {

        if(cidadeRepository.existsByNomeAndEstado(nome,estado)) {
            return cidadeRepository.findByNomeAndEstado(nome,estado);
        }

        return cidadeRepository.save(new Cidade(nome,estado));
    }
}
