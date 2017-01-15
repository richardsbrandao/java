DELETE FROM Indirizzo;
DELETE FROM Cliente;

INSERT INTO Cliente (IDCliente, StNome, StEmail, StSenha, DtNascimento) VALUES (1, 'Ketherin Felix', 'ketherin_hp@hotmail.com', '123456', '1991-04-02');
INSERT INTO Cliente (IDCliente, StNome, StEmail, StSenha, DtNascimento) VALUES (2, 'Carlos Brandão', 'caf_brandao@ig.com.br', '654321', '1949-08-18');
INSERT INTO Cliente (IDCliente, StNome, StEmail, StSenha, DtNascimento) VALUES (3, 'Cristina Brandão', 'caf_brandao@ig.com.br', '123456', '1965-04-24');
INSERT INTO Cliente (IDCliente, StNome, StEmail, StSenha, DtNascimento) VALUES (4, 'Rogerio Lacerda', 'rogerio@ideais.com.br', '321321', '1986-07-01');

INSERT INTO Indirizzo (IDEndereco, IDCliente, StNome, StNumero, StComplemento) VALUES (1, 1, 'Rua Ferraz', '55', '');
INSERT INTO Indirizzo (IDEndereco, IDCliente, StNome, StNumero, StComplemento) VALUES (2, 3, 'Estrada Marechal Mallet', '195', 'Casa 6');
INSERT INTO Indirizzo (IDEndereco, IDCliente, StNome, StNumero, StComplemento) VALUES (3, 2, 'Algum lugar de Muriqui', '200', 'Casa 26');

INSERT INTO Utente (IDUsuario, StNome, DtNascimento) VALUES (1, 'Peter Parker', '1988-09-04');