package br.com.queenfitstyle.controllers;

import br.com.queenfitstyle.dtos.CadastroEnderecoDto;
import br.com.queenfitstyle.dtos.DadoCepApi;
import br.com.queenfitstyle.dtos.DetalhamentoEnderecoDto;
import br.com.queenfitstyle.services.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping("/search/{cep}")
    public ResponseEntity<DadoCepApi> buscarEndereco(@PathVariable String cep) {
        return ResponseEntity.ok(enderecoService.buscarEnderecoPorCep(cep));
    }
    @PostMapping("/{userId}/cadastrar")
    public ResponseEntity<DetalhamentoEnderecoDto> cadastrarEndereco(@PathVariable Long userId,@RequestBody @Valid CadastroEnderecoDto dto) {
      return ResponseEntity.ok(enderecoService.cadastrarEndereco(dto, userId));
    }

}

