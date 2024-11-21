package br.com.queenfitstyle.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cidades")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cidadeId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_id")
    private Estado estado;

    private String nome;



}
