INSERT INTO cidade(nome, taxa, uf) VALUES ('São Luis', 1.3, 'MA');
INSERT INTO cidade(nome, taxa, uf) VALUES ('São Paulo', 1.5, 'SP');
INSERT INTO cidade(nome, taxa, uf) VALUES ('Teresina', 0.3, 'PI');


INSERT INTO cliente(endereco, nome, telefone) VALUES ('Rua 03','João','1234-1234');
INSERT INTO cliente(endereco, nome, telefone) VALUES ('Rua 07','Maria','1234-2312');


INSERT INTO frete(descricao, peso, valor, codigo_cidade, codigo_cliente) VALUES (
	'frete 01', 20, 15, 1,1
);