USE database_planilha;

SET SQL_SAFE_UPDATES = 0;

DROP TABLE IF EXISTS compra;

CREATE TABLE compra (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome_comprador VARCHAR(50),
    ramal INT,
    setor VARCHAR(50),
    nome_produto VARCHAR(100),
    nome_empresa VARCHAR(100),
    nota_fiscal INT,
    data_chegada DATE,
    codigo_pedido INT,
    nome_porteiro VARCHAR(100),
    data_recebido DATE,
    tarefa_concluida TINYINT(1) DEFAULT 0,
    is_exemplo BOOLEAN DEFAULT FALSE
);

INSERT INTO compra (nome_comprador, ramal, setor, nome_produto, nome_empresa, nota_fiscal, data_chegada, codigo_pedido, nome_porteiro, data_recebido) VALUES
('João Silva', 1234, 'Compras', 'Teclado', 'Empresa X', 123456, '2025-01-10', 101, 'Carlos Pereira', '2025-01-21'),
('Maria Oliveira', 5678, 'TI', 'Monitor', 'Fornecedor Y', 789012, '2025-01-12', 102, 'Ana Souza', '2025-01-21'),
('Carlos Souza', 2345, 'Logística', 'Mouse', 'Fornecedor Z', 345678, '2025-01-13', 103, 'Roberto Costa', '2025-01-21');

UPDATE compra SET is_exemplo = TRUE WHERE id IN (1, 2, 3);

SELECT * FROM compra WHERE is_exemplo = FALSE;

SHOW DATABASES;

SHOW TABLES IN database_planilha;

USE database_planilha;

