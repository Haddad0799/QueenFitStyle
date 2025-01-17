package br.com.queenfitstyle.services;

import br.com.queenfitstyle.models.Cliente;
import br.com.queenfitstyle.repositorys.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente findByUsuario(Long id) {
        return clienteRepository.findByUsuarioId(id);
    }
}
