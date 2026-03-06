package io.github.cursodanzin.libraryapi.repository;

import io.github.cursodanzin.libraryapi.model.Autor;
import io.github.cursodanzin.libraryapi.model.Livro;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {
    //Fazer a pesquisa que quiser

    //Metodo de consulta QUery :
    // select * from livro where id_autor = id
    List<Livro> findByAutor(Autor autor); //Propriedade do tipo autor , BUSCAR LIVRO POR AUTOR

    //Pesquisar por titulo
    // select * from livro where titulo = titulo;
    List<Livro> findByTitulo(String titulo);

    List<Livro> findByIsbn(String isbn);

    // select * from livro where titulo = ? and isbn = ?
    List<Livro> findByTituloAndPreco(String titulo, BigDecimal preco);

    // select * from livro where titulo = ? or isbn = ?
    List<Livro> findByTituloOrIsbn(String titulo, String isbn);

    // select * from livro where data_publicacao between ? and ?
    List<Livro> findByDataPublicacaoBetween(LocalDate inicio, LocalDate fim);//Between = Entre

    //JPQL -> referencia as entidades e as propriedades d
    // select l.* from livro as l order by l.titulo, l.preco
    @Query(" select l from Livro as l order by l.titulo, l.preco") //colocar o nome da classe Livro em vez do nome da tabela
    List<Livro> listarTodosOrdernadoPOrTituloAndPreco();
    //select a.* from livro l join autor as a on a.id = l.id_autor
    @Query("select a from Livro l join l.autor a") //colocando as variaveis, autor ja e id_autor na classe do Java
    List<Autor> listarAutoresDosLivros();
    //select distinct l.* from livro l
    @Query("select distinct l.titulo from Livro l")
    List<String> listarNomesDiferentesLivros(); //titulo e uma String

    //select l.genero from livro as l join autor as a on l.id_autor  = a.id where nacionalidade = 'Brasileira'order by l.genero
    @Query("""
        select l.genero 
        from Livro l 
        join l.autor a 
        where a.nacionalidade = 'Brasileira'
        order by l.genero
""")
    List<String> listarGenerosAutoresBrasileiros();
}
