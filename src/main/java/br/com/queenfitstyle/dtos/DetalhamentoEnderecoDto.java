package br.com.queenfitstyle.dtos;

import br.com.queenfitstyle.models.Endereco;

public record DetalhamentoEnderecoDto(

        Long clienteId,
        String cep,
        String logradouro,
        String complemento,
        String bairro,
        Integer numero,
        String localidade,
        String uf,
        String estado
) {

    public DetalhamentoEnderecoDto(Endereco endereco) {
        this(endereco.getCliente().getClienteId(),
                endereco.getCep(),
                endereco.getLogradouro(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getNumero(),
                endereco.getCidade().getNome(),
                endereco.getCidade().getEstado().getUf(),
                endereco.getCidade().getEstado().getNome());
    }
}
