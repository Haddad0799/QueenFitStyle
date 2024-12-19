package br.com.queenfitstyle.models;

import br.com.queenfitstyle.dtos.CadastroEnderecoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "enderecos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enderecoId;

    @OneToOne
    @JoinColumn(name = "cliente_id")
    @Setter
    private Cliente cliente;

    private String cep;

    @ManyToOne
    @JoinColumn(name = "cidade_id")
    @Setter
    private Cidade cidade;

    private String logradouro;

    private String bairro;

    private Integer numero;

    private String complemento;

    public Endereco(CadastroEnderecoDto dto) {
        this.cep = dto.cep();
        this.logradouro = dto.logradouro();
        this.bairro = dto.bairro();
        this.numero = dto.numero();
        this.complemento = dto.complemento();
    }
}
