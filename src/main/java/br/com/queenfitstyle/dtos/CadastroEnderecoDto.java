package br.com.queenfitstyle.dtos;

public record CadastroEnderecoDto(

        Long usuarioId,
        String cep,
        String logradouro,
        String complemento,
        String bairro,
        int numero,
        String localidade,
        String uf,
        String estado

) {
}
