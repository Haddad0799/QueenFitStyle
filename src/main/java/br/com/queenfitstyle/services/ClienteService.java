package br.com.queenfitstyle.services;

import br.com.queenfitstyle.models.Cliente;
import br.com.queenfitstyle.repositorys.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public Cliente findByUsuario(Long id) {
        return clienteRepository.findByUsuarioId(id);
    }


}
