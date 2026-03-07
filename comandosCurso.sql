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

select l.id, l.titulo , a.nome from livro l join autor a on a.id = l.id_autor --livro UFO pertence ao autor maria--

select distinct titulo from livro --SOmente titulos diferentes--

--Like : que tem que ter no nome o Avengers --
select * from livro where titulo like '%Avengers%';

--Like : que comece com UF
select * from livro where titulo like 'UF%';

--Like : que termine com UF
select * from livro where titulo like '%UF';

select l.* from livro as l order by l.titulo , l.preco

select a.* from livro l join autor as a on a.id = l.id_autor


select l.genero from livro as l join autor as a on l.id_autor  = a.id where nacionalidade = 'Brasileira' order by l.genero
