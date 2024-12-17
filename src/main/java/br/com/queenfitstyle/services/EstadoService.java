package br.com.queenfitstyle.services;

import br.com.queenfitstyle.dtos.CadastroEnderecoDto;
import br.com.queenfitstyle.models.Estado;
import br.com.queenfitstyle.repositorys.EstadoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {

    private final EstadoRepository estadoRepository;

    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    @Transactional
    protected Estado getEstado(CadastroEnderecoDto dto) {

        if(estadoRepository.existsByUf(dto.uf())) {
           return estadoRepository.findEstadoByUf(dto.uf());
        }

        return estadoRepository.save(new Estado(dto));
    }
}
