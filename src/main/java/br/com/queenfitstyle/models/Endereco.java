package br.com.queenfitstyle.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private Cliente cliente;

    private String cep;

    @OneToOne
    @JoinColumn(name = "estado_id")
    private Estado estado;

    @OneToOne
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;

    private String logradouro;

    private String bairro;

    private int numero;

    private String complemento;
}
