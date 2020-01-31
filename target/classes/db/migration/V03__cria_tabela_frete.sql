CREATE TABLE IF NOT EXISTS frete (
codigo_frete int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  descricao varchar(30) DEFAULT NULL,
  peso double DEFAULT NULL,
  valor double DEFAULT NULL,
  codigo_cidade int(11) DEFAULT NULL,
  codigo_cliente int(11) DEFAULT NULL,
  
   FOREIGN KEY (codigo_cidade) REFERENCES cidade(codigo_cidade),
   FOREIGN KEY (codigo_cliente) REFERENCES cliente(codigo_cliente)
);
