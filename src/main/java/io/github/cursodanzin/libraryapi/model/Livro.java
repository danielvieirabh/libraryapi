package io.github.cursodanzin.libraryapi.model;

import io.github.cursodanzin.libraryapi.model.enums.GeneroLivro;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

//@NoArgsConstructor //construtor sem argumentos
@Entity
@Table(name = "livro")
public class Livro {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "isbn", length = 20, nullable = false)
    private String isbn;

    @Column(name = "titulo", length = 150, nullable = false)
    private String titulo;

    @Column(name = "data_publicacao", nullable = false)
    private LocalDate dataPublicacao;

    @Column(name = "preco", precision = 18, scale = 2)
    private BigDecimal preco; //ou BigDecimal no lugar do Double

    @ManyToOne //Muitos livros para um autor
    @JoinColumn(name = "id_autor")
    private Autor autor;

    @Enumerated(EnumType.STRING) //String de Genero
    @Column(name = "genero", length = 30, nullable = false)
    private GeneroLivro genero;

    public Livro() {

    }

    public Livro(UUID id, String isbn, String titulo, LocalDate dataPublicacao, BigDecimal preco, Autor autor, GeneroLivro genero) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.dataPublicacao = dataPublicacao;
        this.preco = preco;
        this.autor = autor;
        this.genero = genero;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public GeneroLivro getGenero() {
        return genero;
    }

    public void setGenero(GeneroLivro genero) {
        this.genero = genero;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return Objects.equals(id, livro.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
