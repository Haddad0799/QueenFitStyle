CREATE TABLE enderecos (
                           endereco_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           cep VARCHAR(10) NOT NULL,
                           logradouro VARCHAR(200),
                           bairro VARCHAR(100),
                           numero INT,
                           complemento VARCHAR(200),
                           cidade_id BIGINT,
                           FOREIGN KEY (cidade_id) REFERENCES cidades(cidade_id) ON DELETE SET NULL
);