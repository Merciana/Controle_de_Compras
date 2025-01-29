USE database_planilha;

SET SQL_SAFE_UPDATES = 0;

DROP TABLE IF EXISTS compra;

CREATE TABLE compra (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome_comprador VARCHAR(50),
    nome_solicitante VARCHAR(50),
    almoxerifado VARCHAR(50),
    nome_empresa VARCHAR(100),
    nota_fiscal BIGINT,
    palavra_chave VARCHAR(50),
    nome_produto VARCHAR(100),
    data_chegada DATE,
    nome_porteiro VARCHAR(100),
    data_recebido DATE,
    tarefa_concluida TINYINT(1) DEFAULT 0,
    is_exemplo BOOLEAN DEFAULT FALSE
);

INSERT INTO compra (nome_comprador, nome_solicitante, almoxerifado, nome_empresa, nota_fiscal, palavra_chave, nome_produto, data_chegada, nome_porteiro, data_recebido) VALUES
('João Silva', 'Rafael silva', 'Marketing', 'Empresa X', 216516512, 'EH554FL', 'Teclado', '2025-01-10', 'Carlos Pereira', '2025-01-21'),
('Maria Oliveira', 'Rafael silva', 'TI', 'Fornecedor Y', 5416816516, 'EH554FL', 'Impressora', '2025-01-12', 'Ana Souza', '2025-01-21'),
('Carlos Souza', 'Rafael silva', 'Logística', 'Fornecedor Z', 65816168156, 'EH554FL', 'Maquina', '2025-01-13', 'Roberto Costa', '2025-01-21');

UPDATE compra SET is_exemplo = TRUE WHERE id IN (1, 2, 3);
ALTER TABLE compra AUTO_INCREMENT = 1;
SELECT * FROM compra WHERE is_exemplo = FALSE;

USE database_planilha;

SHOW COLUMNS FROM compra;

ALTER TABLE usuario_role DROP FOREIGN KEY usuario_role_ibfk_2;

DROP TABLE IF EXISTS usuario_role;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS usuario;

CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL
);
CREATE TABLE role (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

SELECT * FROM role WHERE name = '';

UPDATE role SET name = 'default_role' WHERE name = '';

INSERT INTO role (name) VALUES ('ROLE_USER'), ('ROLE_ADMIN');

CREATE TABLE usuario_role (
    usuario_id BIGINT,
    role_id BIGINT,
    PRIMARY KEY (usuario_id, role_id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (role_id) REFERENCES role(id)
);


INSERT INTO usuario (username, password) VALUES 
('karla.lidiane', 'Tster021'),
('diego.rogeiro', 'Mster021'),
('yure.franca', 'Cster021'),
('carlos.sigmund', 'TIster021'),
('portaria', 'portaria'),
('admin', 'admin!@#');


INSERT INTO usuario_role (usuario_id, role_id)
SELECT u.id, r.id
FROM usuario u
JOIN role r ON r.name = 'ROLE_USER'
WHERE u.username = 'portaria';

-- "admin" com a role ROLE_ADMIN
INSERT INTO usuario_role (usuario_id, role_id)
SELECT u.id, r.id
FROM usuario u
JOIN role r ON r.name = 'ROLE_ADMIN'
WHERE u.username = 'karla.lidiane';

INSERT INTO usuario_role (usuario_id, role_id)
SELECT u.id, r.id
FROM usuario u
JOIN role r ON r.name = 'ROLE_ADMIN'
WHERE u.username = 'diego.rogeiro';

INSERT INTO usuario_role (usuario_id, role_id)
SELECT u.id, r.id
FROM usuario u
JOIN role r ON r.name = 'ROLE_ADMIN'
WHERE u.username = 'yure.franca';

INSERT INTO usuario_role (usuario_id, role_id)
SELECT u.id, r.id
FROM usuario u
JOIN role r ON r.name= 'ROLE_ADMIN'
WHERE u.username = 'carlos.sigmund';

INSERT INTO usuario_role (usuario_id, role_id)
SELECT u.id, r.id
FROM usuario u
JOIN role r ON r.name = 'ROLE_ADMIN'
WHERE u.username = 'admin';

SELECT u.username, r.name AS role
FROM usuario u
JOIN usuario_role ur ON ur.usuario_id = u.id
JOIN role r ON ur.role_id = r.id;

SHOW CREATE TABLE usuario_role;

SELECT username, password FROM usuario;

SHOW COLUMNS FROM role;
