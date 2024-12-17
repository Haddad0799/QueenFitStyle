ALTER TABLE usuarios
    ADD COLUMN cliente_id BIGINT UNIQUE,
    ADD CONSTRAINT fk_cliente_usuario FOREIGN KEY (cliente_id) REFERENCES clientes(cliente_id) ON DELETE CASCADE;
