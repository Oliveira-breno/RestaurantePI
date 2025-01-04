create database restaurante_db;
use restaurante_db;
CREATE TABLE entrega_produtos (
    entrega_id BIGINT NOT NULL,
    produtos_id BIGINT NOT NULL,
    PRIMARY KEY (entrega_id, produtos_id),
    FOREIGN KEY (entrega_id) REFERENCES entrega(id),
    FOREIGN KEY (produtos_id) REFERENCES produto(id)
);
CREATE TABLE mesa_produtos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    mesa_id BIGINT NOT NULL,
    produto_id BIGINT NOT NULL,
    quantidade INT NOT NULL,
    FOREIGN KEY (mesa_id) REFERENCES mesas(id),
    FOREIGN KEY (produto_id) REFERENCES produtos(id)
);


