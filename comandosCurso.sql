create table autor(
                      id uuid not null primary key,
                      nome varchar(100) not null,
                      data_nascimento date not null,
                      nacionalidade varchar(50) not null
);

create table livro(
                      id uuid not null primary key,
                      isbn varchar(20) not null,
                      titulo varchar(150) not null,
                      data_publicacao date not null,
                      preco numeric(18,2),
                      id_autor uuid not null references autor(id),
                      genero varchar(30) not null,
                      constraint chk_genero check (genero in ('FICCAO', 'FANTASIA', 'MISTERIO', 'ROMANCE', 'BIOGRAFIA', 'CIENCIA'))
)

drop table livro
drop table autor


select * from autor
select * from livro

select l.titulo , a.nome from livro l join autor a on a.id = l.id_autor --livro UFO pertence ao autor maria--



--BUSCA O NOME DO LIVRO E AUTOR --
select l1_0.id,a1_0.id,a1_0.data_nascimento,a1_0.nacionalidade,a1_0.nome,l1_0.data_publicacao,l1_0.genero,l1_0.isbn,l1_0.preco,l1_0.titulo
from livro l1_0
    left join public.autor a1_0 on a1_0.id=l1_0.id_autor
where l1_0.id=?