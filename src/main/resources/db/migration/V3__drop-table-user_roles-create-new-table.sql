-- Excluir a tabela 'usuario_roles' existente
DROP TABLE IF EXISTS usuario_roles;

-- Criar a tabela 'usuario_roles' com a estrutura correta
CREATE TABLE usuario_roles (
                               usuario_id BIGINT NOT NULL,
                               role VARCHAR(255) NOT NULL,
                               FOREIGN KEY (usuario_id) REFERENCES usuarios(usuario_id),
                               PRIMARY KEY (usuario_id, role)
);
