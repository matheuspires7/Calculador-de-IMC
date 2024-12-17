create database sistema_alunos;
use sistema_alunos;

create table alunos(
                       cpf varchar(14) primary key,
                       nome varchar (100),
                       data_nascimento varchar (30),
                       peso float,
                       altura float
);

INSERT INTO alunos (cpf, nome, data_nascimento, peso, altura) VALUES
                                                                  ('123.456.789-00', 'Ana Maria Silva', '15/04/1990', 65.4, 1.68),
                                                                  ('987.654.321-11', 'João Carlos Souza', '22/11/1985', 78.9, 1.75),
                                                                  ('456.123.789-22', 'Mariana Oliveira', '03/07/1995', 54.2, 1.60),
                                                                  ('789.456.123-33', 'Pedro Henrique Lima', '12/02/2000', 82.5, 1.80),
                                                                  ('321.654.987-44', 'Luciana Alves', '09/09/1988', 70.1, 1.65),
                                                                  ('654.987.321-55', 'Ricardo Santos', '27/03/1992', 90.0, 1.85),
                                                                  ('147.258.369-66', 'Camila Rocha', '18/12/1998', 58.3, 1.70);

select * from alunos;