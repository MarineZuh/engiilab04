CREATE TABLE IF NOT EXISTS cliente (
codigo_cliente int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  endereco varchar(30) DEFAULT NULL,
  nome varchar(30) DEFAULT NULL,
  telefone varchar(30) DEFAULT NULL
);