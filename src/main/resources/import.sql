-- Inserindo dados na tabela role
insert into tb_role ( nome) values ( 'ROLE_ADMIN');
insert into tb_role ( nome) values ( 'ROLE_USER');

-- Inserindo dados na tabela usuario
insert into tb_usuario ( login, password) values ( 'admin', '$2a$10$dCkepJ/rflJeSyoVRr8BxOFOxsWwYtBBMnNP6EMmeWJpPFCtUc9CW');
insert into tb_usuario ( login, password) values ( 'user1', '$2a$10$19JzRW5iY.R3KjnNABCKeOLOGd.abay5HatPsHdGe.q/Dq6OVsSyi');
insert into tb_usuario ( login, password) values ( 'user2', '$2a$10$19JzRW5iY.R3KjnNABCKeOLOGd.abay5HatPsHdGe.q/Dq6OVsSyi');

-- Inserindo dados na tabela usuario_roles
insert into tb_usuario_roles (usuarios_id, roles_id) values (1, 1);
insert into tb_usuario_roles (usuarios_id, roles_id) values (2, 2);
insert into tb_usuario_roles (usuarios_id, roles_id) values (3, 2);

-- Inserindo dados na tabela tb_pessoa
insert into tb_pessoa (usuario_id, cnpj, cpf, endereco, nome, telefone) values (2, null, '11122233344', 'rua a, 123', 'carlos aparecido dos santos', '1234567');
insert into tb_pessoa (usuario_id, cnpj, cpf, endereco, nome, telefone) values (3, '12345678901234', null, 'rua b, 456', 'rb turismo', '123456789');

-- Inserindo dados na tabela tb_produto
insert into tb_produto ( nome, valor) values ( 'ventilador eletrolux', 40.00);
insert into tb_produto ( nome, valor) values ( 'lava louças', 1000.00);
insert into tb_produto ( nome, valor) values ( 'notebook dell', 2500.00);
insert into tb_produto ( nome, valor) values ( 'mala de viagem', 100.00);

-- Inserindo dados na tabela tb_venda
insert into tb_venda (cliente_id, data) values ( 1, '2021-01-01 09:22:50');
insert into tb_venda (cliente_id, data) values ( 2, '2021-01-02 15:17:02');
insert into tb_venda (cliente_id, data) values ( 2, '2021-01-03 12:05:22');

-- Inserindo dados na tabela tb_item_venda
insert into tb_item_venda (quantidade, produto_id, venda_id) values ( 2, 1, 1);
insert into tb_item_venda ( quantidade, produto_id, venda_id) values ( 1, 2, 2);
insert into tb_item_venda ( quantidade, produto_id, venda_id) values ( 4, 4, 2);
insert into tb_item_venda ( quantidade, produto_id, venda_id) values ( 1, 3, 1);
insert into tb_item_venda ( quantidade, produto_id, venda_id) values ( 1, 4, 3);
