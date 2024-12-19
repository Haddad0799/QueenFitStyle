package br.com.queenfitstyle.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clientes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clienteId;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @Setter
    private Usuario usuario;

    private String nomeCompleto;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    private String telefone;

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
        endereco.setCliente(this);
    }

}

