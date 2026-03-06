package io.github.cursodanzin.libraryapi.repository;

import io.github.cursodanzin.libraryapi.model.Autor;
import io.github.cursodanzin.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
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
}
