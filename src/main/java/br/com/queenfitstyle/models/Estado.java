package br.com.queenfitstyle.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "estados")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long estadoId;

    private String sigla;

    private String nome;

    public Estado(DadoEstadoIbge dadoEstadoIbge) {
        this.sigla = dadoEstadoIbge.sigla();
        this.nome = dadoEstadoIbge.nome();
    }
}
