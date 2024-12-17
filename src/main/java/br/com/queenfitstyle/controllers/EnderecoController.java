package br.com.queenfitstyle.controllers;

import br.com.queenfitstyle.dtos.CadastroEnderecoDto;
import br.com.queenfitstyle.dtos.DadoCepApi;
import br.com.queenfitstyle.dtos.EnderecoDto;
import br.com.queenfitstyle.services.EnderecoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping("/{cep}")
    public ResponseEntity<DadoCepApi> buscarEndereco(@PathVariable String cep) {
        return ResponseEntity.ok(enderecoService.buscarEnderecoPorCep(cep));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<EnderecoDto> salvarEndereco(CadastroEnderecoDto dto) {
        return ResponseEntity.ok( enderecoService.cadastrarEndereco(dto));
    }
}

