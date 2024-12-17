CREATE TABLE clientes (
                          cliente_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          usuario_id BIGINT NOT NULL,
                          nome_completo VARCHAR(150),
                          telefone VARCHAR(15),
                          endereco_id BIGINT,
                          FOREIGN KEY (usuario_id) REFERENCES usuarios(usuario_id) ON DELETE CASCADE,
                          FOREIGN KEY (endereco_id) REFERENCES enderecos(endereco_id) ON DELETE SET NULL
);