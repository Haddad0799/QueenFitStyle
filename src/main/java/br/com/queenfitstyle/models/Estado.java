package br.com.queenfitstyle.models;

import br.com.queenfitstyle.dtos.CadastroEnderecoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "estados")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long estadoId;

    private String uf;

    private String nome;

    @OneToMany(mappedBy = "estado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cidade> cidades;


    public Estado(CadastroEnderecoDto dto) {
        this.uf = dto.uf();
        this.nome = dto.estado();
    }
}
