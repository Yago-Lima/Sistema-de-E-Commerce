-- -- Inserindo dados na tabela ROLE

INSERT INTO Role (id, nome) VALUES (1, 'ROLE_ADMIN');
INSERT INTO Role (id, nome) VALUES (2, 'ROLE_USER');

-- Inserindo dados na tabela USUARIO

INSERT INTO Usuario (id, login, password) VALUES (1, 'admin', '$2a$10$wHxfPS8JLEyVh7OllW.2OefP1x.aF.zcQzN2oJ9Dcb2qFgwCq1l6K');
INSERT INTO Usuario (id, login, password) VALUES (2, 'user', '$2a$10$0WJrvX3qZzm3dqPZcyGlPeqYqaY2lwXPftg2hd8Y8wh.i/6uEsuRW');


-- Inserindo dados na tabela usuario_roles
 INSERT INTO Usuario_Roles (usuarios_id, roles_id) VALUES (1, 1);
 INSERT INTO Usuario_Roles (usuarios_id, roles_id) VALUES (2, 2);



-- Inserindo dados na tabela TB_PESSOA
INSERT INTO TB_PESSOA (id, cnpj, cpf, endereco, nome, telefone) VALUES (1, ' ', '11122233344', 'Rua A, 123', 'Carlos Aparecido dos Santos', '1234567');

INSERT INTO TB_PESSOA (id, cnpj, cpf, endereco, nome, telefone) VALUES (2, '12345678901234', ' ', 'Rua B, 456', 'RB Turismo', '123456789');


INSERT INTO TB_PRODUTO (id, nome, valor) VALUES (1, 'Ventilador Eletrolux', 40.00);
INSERT INTO TB_PRODUTO (id, nome, valor) VALUES (2, 'Lava Louças', 1000.00);
INSERT INTO TB_PRODUTO (id, nome, valor) VALUES (3, 'Notebook Dell', 2500.00);
INSERT INTO TB_PRODUTO (id, nome, valor) VALUES (4, 'Mala de viagem', 100.00);

INSERT INTO TB_VENDA (id, cliente_id, data) VALUES (1, 1, '2021-01-01T09:22:50');
INSERT INTO TB_VENDA (id, cliente_id, data) VALUES (2, 2, '2021-01-02T15:17:02');
INSERT INTO TB_VENDA (id, cliente_id, data) VALUES (3, 2, '2021-01-03T12:05:22');

INSERT INTO TB_ITEM_VENDA (id, quantidade, produto_id, venda_id) VALUES (1, 2, 1, 1);
INSERT INTO TB_ITEM_VENDA (id, quantidade, produto_id, venda_id) VALUES (2, 1, 2, 2);
INSERT INTO TB_ITEM_VENDA (id, quantidade, produto_id, venda_id) VALUES (3, 4, 4, 2);
INSERT INTO TB_ITEM_VENDA (id, quantidade, produto_id, venda_id) VALUES (4, 1, 3, 1);
INSERT INTO TB_ITEM_VENDA (id, quantidade, produto_id, venda_id) VALUES (5, 1, 4, 3);


