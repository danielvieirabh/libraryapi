package io.github.cursodanzin.libraryapi.repository;

import io.github.cursodanzin.libraryapi.model.Autor;
import io.github.cursodanzin.libraryapi.model.Livro;
import io.github.cursodanzin.libraryapi.model.enums.GeneroLivro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class LivroRepositoryTest {
    @Autowired
    LivroRepository livroRepository;
    @Autowired
    AutorRepository autorRepository;

    @Test
    public void salvarTest() { //Inserir no banco
        UUID id = UUID.fromString("2812ea9e-9b22-464b-9135-6eff4c5f18a8");
        Autor autor = autorRepository.findById(id).orElse(null);
        Livro livro = new Livro("45633-44", "CIENCIA DE VOCE", LocalDate.of(2000, 1, 2), BigDecimal.valueOf(100), autor, GeneroLivro.CIENCIA);
        livroRepository.save(livro);
    }

    @Test //Sem Cascade :
    public void salvarAutorELivroTest() { //Inserir no banco com o Cascade , puxar mais de um com o Cascade
        Autor autor = new Autor("Luana", LocalDate.of(1999, 3, 13), "Alemã");
        Livro livro = new Livro("98886-44", "NOVO LIVRO", LocalDate.of(1980, 1, 2), BigDecimal.valueOf(100), autor, GeneroLivro.FICCAO);
        autorRepository.save(autor);
        livroRepository.save(livro);
    }

    @Test
    public void salvarCascadeTest() { //Inserir no banco com o Cascade , puxar mais de um com o Cascade
        Autor autor = new Autor("Joao", LocalDate.of(1999, 3, 13), "Brasileira");
        Livro livro = new Livro("98886-44", "UFO", LocalDate.of(1980, 1, 2), BigDecimal.valueOf(100), autor, GeneroLivro.FICCAO);
        livroRepository.save(livro);
    }

    @Test
    public void atualizarAutorDoLivro() { //Atualiza para o terceiro livro pertencer a Joana
        UUID idLivro = UUID.fromString("11c25d26-9ce9-4212-bd8e-3e6fa2fff634");
        Livro livroParaAtualizar = livroRepository.findById(idLivro).orElse(null);

        UUID idAutor = UUID.fromString("85a76b36-83fe-4afd-a202-eb5a399aac13");
        Autor autor = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(autor);
        livroRepository.save(livroParaAtualizar);
    }

    @Test
    public void deletar() {
        UUID idLivro = UUID.fromString("c2123f1b-1da1-438e-8135-d55f307b3f84");
        livroRepository.deleteById(idLivro);
    }

    @Test
    public void deletarCascade() { //Deleta registro do autor e livro
        UUID idLivro = UUID.fromString("c2123f1b-1da1-438e-8135-d55f307b3f84");
        livroRepository.deleteById(idLivro);
    }

    @Test
    @Transactional
    public void buscarLivroTest() {
        UUID id = UUID.fromString("11c25d26-9ce9-4212-bd8e-3e6fa2fff634");
        Livro livro = livroRepository.findById(id).orElse(null);
        System.out.println("LIVRO: ");
        System.out.println(livro.getTitulo());
        System.out.println("Nome do Autor: ");
        System.out.println(livro.getAutor().getNome());
    }

    @Test
    public void pesquisaPorTituloTest() {
      List<Livro> livroList =  livroRepository.findByTitulo("The Avengers");
      for (Livro livro : livroList) {
          System.out.println("Dados do livro " + livro);
      }
    }

    @Test
    public void pesquisaPorIsbnoTest() { //Retorna 2 livros
        List<Livro> livroList =  livroRepository.findByIsbn("98886-44");
        for (Livro livro : livroList) {
            System.out.println("Dados do livro " + livro);
        }
    }

    @Test
    public void pesquisaPorTituloAndPreco() { //Retorna 2 livros
        List<Livro> livroList =  livroRepository.findByTituloAndPreco("The Avengers", BigDecimal.valueOf(204.00)); //Sempre valueOf por causa da precisao
        for (Livro livro : livroList) {
            System.out.println("Dados do livro " + livro);
        }
    }

    @Test
    public void pesquisaPorTituloOrIsbn() { //Retorna 2 livros
        List<Livro> livroList =  livroRepository.findByTituloOrIsbn("The Avengers" , "98886-44"); //Sempre valueOf por causa da precisao
        for (Livro livro : livroList) {
            System.out.println("Dados do livro " + livro);
        }
    }

    @Test
    public void pesquisaPorDataPublicacaoBetween() { //Retorna 2 livros
        List<Livro> livroList =  livroRepository.findByDataPublicacaoBetween(LocalDate.of(2018, 1, 2), LocalDate.now()); // ENtre 2018 a hoje
        for (Livro livro : livroList) {
            System.out.println("Dados do livro " + livro);
        }
    }

    @Test
    public void listartLivrosComQueryJPQL() {
        List<Livro> livro = livroRepository.listarTodosOrdernadoPOrTituloAndPreco();
        for (Livro livros : livro) {
            System.out.println(livros);
        }
    }

    @Test
    public void listarAutoresDosLivros() { //listar autores que tenha livros
        List<Autor> autor = livroRepository.listarAutoresDosLivros();
        for (Autor autores : autor) {
            System.out.println(autores);
        }
    }

    @Test
    public void listarTitulosNaoRepetidosDosLivros() { //listar autores que tenha livros
        List<String> livro = livroRepository.listarNomesDiferentesLivros();
        for (String livros : livro) {
            System.out.println(livros);
        }
    }

    @Test
    public void listarGenerosAutoresBrasileiros() { //listar autores que tenha livros
        List<String> livro = livroRepository.listarGenerosAutoresBrasileiros();
        for (String livros : livro) {
            System.out.println(livros);
        }
    }

    @Test
    public void listarPorGeneroQueryParamTest() {
        List<Livro> resultado = livroRepository.findByGenero(GeneroLivro.MISTERIO, "dataPublicacao"); //variavel dataPublicao la da classe Livro
        for (Livro livro : resultado) {
            System.out.println("Listagem por genero: " + livro);
        }
    }

    @Test
    public void listarPorGeneroPositionalParamTest() {
        List<Livro> resultado = livroRepository.findByGeneroPositionalParametros(GeneroLivro.MISTERIO, "preco"); //variavel preco la da classe Livro, ordenar por preco
        for (Livro livro : resultado) {
            System.out.println("Listagem por genero: " + livro);
        }
    }

    @Test
    public void deletePorGeneroTest() { //Preciso de um   @Transactional  para Operacao de escrita : insert , update , delete
        livroRepository.deleteByGenero(GeneroLivro.CIENCIA);
    }

    @Test
    public void updateDataPublicacaoTest() { //Muda todas as datas para 2000
        livroRepository.updateDataPublicacao(LocalDate.of(2000, 1, 1)); //ano 2000
    }


}