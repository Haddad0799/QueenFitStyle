package br.com.queenfitstyle.controllers;

import br.com.queenfitstyle.dtos.CadastroUsuarioDto;
import br.com.queenfitstyle.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    @PostMapping("/cliente")
    public ResponseEntity<String> cadastrarCliente(@RequestBody @Valid CadastroUsuarioDto dto) {
        usuarioService.cadastrarUsuarioCliente(dto);
        return ResponseEntity.ok("Usuário cadastrado com sucesso!");
    }

    @PostMapping("/admin")
    public ResponseEntity<String> cadastrarAdmin(@RequestBody @Valid CadastroUsuarioDto dto) {
        usuarioService.cadastrarUsuarioAdmin(dto);
        return ResponseEntity.ok("Usuário cadastrado com sucesso!");
    }
}
