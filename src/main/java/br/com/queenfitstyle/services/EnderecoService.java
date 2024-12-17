package br.com.queenfitstyle.services;

import br.com.queenfitstyle.dtos.CadastroEnderecoDto;
import br.com.queenfitstyle.dtos.DadoCepApi;
import br.com.queenfitstyle.dtos.EnderecoDto;
import br.com.queenfitstyle.infra.service.IntegracaoApiCepService;
import br.com.queenfitstyle.infra.util.EndpointBuilder;
import br.com.queenfitstyle.models.*;
import br.com.queenfitstyle.repositorys.ClienteRepository;
import br.com.queenfitstyle.repositorys.EnderecoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {


    private final EnderecoRepository enderecoRepository;
    private final IntegracaoApiCepService integracaoApiCepService;
    private final EstadoService estadoService;
    private final CidadeService cidadeService;
    private final ClienteService clienteService;


    public EnderecoService(EnderecoRepository enderecoRepository, IntegracaoApiCepService integracaoApiCepService, UsuarioService usuarioService, EstadoService estadoService, CidadeService cidadeService, ClienteRepository clienteRepository, ClienteService clienteService, UsuarioService usuarioService1) {
        this.enderecoRepository = enderecoRepository;
        this.integracaoApiCepService = integracaoApiCepService;
        this.estadoService = estadoService;
        this.cidadeService = cidadeService;
        this.clienteService = clienteService;
    }

    public DadoCepApi buscarEnderecoPorCep(String cep) {
        String endpoint = EndpointBuilder.buildCepSearchEndpoint(cep);
        DadoCepApi dadoCepApi = integracaoApiCepService.buscarCep(endpoint);

        if(dadoCepApi == null || dadoCepApi.localidade() == null) {
            throw new RuntimeException();
        }
        return dadoCepApi;
    }


    @Transactional
    public EnderecoDto cadastrarEndereco(CadastroEnderecoDto dto) {
        Cliente cliente = clienteService.findByUsuario(dto.usuarioId());
        Estado estado = estadoService.getEstado(dto);
        Cidade cidade = cidadeService.getCidade(dto.localidade(), estado);
        estado.getCidades().add(cidade);
        Endereco endereco = new Endereco(dto);
        endereco.setCidade(cidade);
        cliente.setEndereco(endereco);
        enderecoRepository.save(endereco);
        return new EnderecoDto(endereco);
    }
}
