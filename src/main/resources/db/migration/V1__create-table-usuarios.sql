CREATE TABLE usuarios (
                          usuario_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          login VARCHAR(255) NOT NULL UNIQUE,
                          senha VARCHAR(255) NOT NULL
);
