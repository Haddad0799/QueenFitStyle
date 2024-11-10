package br.com.queenfitstyle.controllers;

import br.com.queenfitstyle.dtos.AuthDto;
import br.com.queenfitstyle.dtos.JwtDto;
import br.com.queenfitstyle.infra.service.JwtAutenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    private final JwtAutenticationService jwtAutenticationService;

    public AuthController(JwtAutenticationService jwtAutenticationService) {
        this.jwtAutenticationService = jwtAutenticationService;
    }

    @PostMapping
    public ResponseEntity<JwtDto> efetuarLogin(@RequestBody @Valid AuthDto dto) {

        return ResponseEntity.ok().body(jwtAutenticationService.autenticar(dto));
    }
}
