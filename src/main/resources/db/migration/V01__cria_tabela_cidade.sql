CREATE TABLE IF NOT EXISTS cidade (
codigo_cidade int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  nome varchar(30) DEFAULT NULL,
  taxa double DEFAULT NULL,
  uf varchar(30) DEFAULT NULL
);