package br.com.queenfitstyle.services;

import br.com.queenfitstyle.infra.service.IntegracaoIbgeApiService;
import br.com.queenfitstyle.models.DadoEstadoIbge;
import br.com.queenfitstyle.models.Estado;
import br.com.queenfitstyle.repositorys.EstadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    private final IntegracaoIbgeApiService integracaoIbgeApiService;
    private final EstadoRepository estadoRepository;

    public EstadoService(IntegracaoIbgeApiService integracaoIbgeApiService, EstadoRepository estadoRepository) {
        this.integracaoIbgeApiService = integracaoIbgeApiService;
        this.estadoRepository = estadoRepository;
    }

    public void buscarESalvarEstados(String endpoint) {

        List<DadoEstadoIbge> estadosIbgeApiResponse = integracaoIbgeApiService.buscarDados(DadoEstadoIbge[].class, endpoint);

        List<Estado> estados = estadosIbgeApiResponse.stream()
                .map(Estado::new)
                .toList();

        // Salva todos os estados no banco de dados
        estadoRepository.saveAll(estados);
    }

    public boolean dataBaseJaPopulado(){
        return estadoRepository.count() > 0;
    }

}
