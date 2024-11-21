package br.com.queenfitstyle.services;

import org.springframework.stereotype.Service;

@Service
public class PopularDatabase {

    private final EstadoService estadoService;

    public PopularDatabase(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    public void popularTabelaEStados() {
        if (estadoService.dataBaseJaPopulado()){
            return;
        }
        String endpointEstados = "https://servicodados.ibge.gov.br/api/v1/localidades/estados";
        estadoService.buscarESalvarEstados(endpointEstados);
    }

}
