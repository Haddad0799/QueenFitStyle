package br.com.queenfitstyle.dtos;


import br.com.queenfitstyle.models.Endereco;

public record EnderecoDto(

        String cep,
        String logradouro,
        String complemento,
        String bairro,
        int numero
) {

    public EnderecoDto (Endereco endereco) {
        this(endereco.getCep(),
                endereco.getLogradouro(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getNumero());
    }
}
