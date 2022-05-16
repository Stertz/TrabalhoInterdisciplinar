create database cryptoquestion;
use cryptoquestion;

CREATE TABLE usuario (
codUser int unsigned not null auto_increment,
idUser varchar(20) not null,
email varchar(45) not null,
senha varchar(70) not null,
nameUser varchar(45) not null,
dateNasc date NOT NULL DEFAULT '1900-01-01',
typeUser tinyint(2) unsigned not null,
photoUser longblob,
primary key (codUser)
); 

CREATE TABLE post (
codPost int unsigned not null auto_increment,
codUser int unsigned not null,
textPost varchar(300) not null,
datePost datetime not null,
primary key (codPost),
foreign key (codUser) references usuario (codUser) on delete cascade
);

select current_timestamp();
CREATE TABLE coments (
codComents int unsigned not null auto_increment,
codPost int unsigned not null,
codUser int unsigned not null,
textComents varchar(500) not null,
dateComents datetime not null,
primary key (codComents),
foreign key (codPost) references post (codPost),
foreign key (codUser) references usuario (codUser)
);

CREATE TABLE likes (
codLikes int unsigned not null auto_increment,
codPost int unsigned not null,
codUser int unsigned not null,
dateLikes datetime not null,
primary key (codLikes),
foreign key (codPost) references post (codPost),
foreign key (codUser) references usuario (codUser)
);

insert into usuario (idUser, email, senha, nameUser, dateNasc, typeUser, photoUser) 
values ('CryptoAdmin', 'CryptoQuestion@outlook.com', 'ff97956aa7ad148a1af325d663c03d2992a1e72cbb95d8b34506889291892401', 'Adminitrador Crypto Question', '2022-01-01', 1, null);
insert into usuario (idUser, email, senha, nameUser, dateNasc, typeUser, photoUser) 
values ('Pastilha', 'lucaspadilhastertz@gmail.com', 'ff97956aa7ad148a1af325d663c03d2992a1e72cbb95d8b34506889291892401', 'Lucas Fernando Padilha Stertz', '2002-05-07', 2, null);
insert into usuario (idUser, email, senha, nameUser, dateNasc, typeUser, photoUser) 
values ('monas44', 'jpmonassa@gmail.com', 'ff97956aa7ad148a1af325d663c03d2992a1e72cbb95d8b34506889291892401', 'João Pedro Monassa dos Santos', '2002-11-14', 1, null);
insert into usuario (idUser, email, senha, nameUser, dateNasc, typeUser, photoUser) 
values ('UsuarioTeste', 'lucas.nissin@gmail.com', 'ff97956aa7ad148a1af325d663c03d2992a1e72cbb95d8b34506889291892401', 'Lucas Teste', '2002-05-07', 2, null);
select * from usuario;
select * from post left outer join usuario on usuario.codUser = post.codUser;
select * from post;
select * from post left outer join usuario on usuario.codUser = post.codUser ORDER BY post.datePost ASC;