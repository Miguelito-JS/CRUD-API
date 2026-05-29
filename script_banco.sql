CREATE DATABASE IF NOT EXISTS estoque_veiculos
    DEFAULT CHARACTER SET utf8mb4
    DEFAULT COLLATE utf8mb4_unicode_ci;

USE estoque_veiculos;

CREATE TABLE IF NOT EXISTS marcas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(80) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS modelos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(80) NOT NULL,
    marca_id BIGINT NOT NULL,
    CONSTRAINT fk_modelos_marcas
        FOREIGN KEY (marca_id) REFERENCES marcas(id),
    CONSTRAINT uk_modelo_marca_nome
        UNIQUE (marca_id, nome)
);

CREATE TABLE IF NOT EXISTS veiculos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    modelo_id BIGINT NOT NULL,
    ano INT NOT NULL,
    cor VARCHAR(40) NOT NULL,
    preco DECIMAL(12, 2) NOT NULL,
    quilometragem INT NOT NULL,
    status VARCHAR(20) NOT NULL,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_veiculos_modelos
        FOREIGN KEY (modelo_id) REFERENCES modelos(id),
    CONSTRAINT ck_veiculos_status
        CHECK (status IN ('DISPONIVEL', 'VENDIDO', 'RESERVADO', 'DESCONTINUADO')),
    CONSTRAINT ck_veiculos_preco
        CHECK (preco >= 0),
    CONSTRAINT ck_veiculos_quilometragem
        CHECK (quilometragem >= 0)
);

INSERT IGNORE INTO marcas (id, nome) VALUES
(1, 'Toyota'),
(2, 'Honda'),
(3, 'Volkswagen');

INSERT IGNORE INTO modelos (id, nome, marca_id) VALUES
(1, 'Corolla', 1),
(2, 'Hilux', 1),
(3, 'Civic', 2),
(4, 'HR-V', 2),
(5, 'Golf', 3),
(6, 'T-Cross', 3);

INSERT IGNORE INTO veiculos (id, modelo_id, ano, cor, preco, quilometragem, status) VALUES
(1, 1, 2022, 'Prata', 128900.00, 32000, 'DISPONIVEL'),
(2, 2, 2021, 'Branco', 219900.00, 54000, 'RESERVADO'),
(3, 3, 2020, 'Preto', 119500.00, 61000, 'DISPONIVEL'),
(4, 6, 2023, 'Azul', 143000.00, 18000, 'VENDIDO');
