CREATE TABLE cidades (
                         cidade_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         nome VARCHAR(100) NOT NULL,
                         estado_id BIGINT,
                         FOREIGN KEY (estado_id) REFERENCES estados(estado_id) ON DELETE SET NULL
);