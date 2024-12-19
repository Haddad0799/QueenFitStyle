ALTER TABLE enderecos
    ADD COLUMN cliente_id BIGINT;

ALTER TABLE enderecos
    ADD CONSTRAINT fk_cliente
        FOREIGN KEY (cliente_id) REFERENCES clientes(cliente_id);
