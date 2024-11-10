CREATE TABLE usuario_roles (
                               usuario_id BIGINT NOT NULL,
                               role VARCHAR(255) NOT NULL,
                               FOREIGN KEY (usuario_id) REFERENCES usuarios(usuario_id)
);
