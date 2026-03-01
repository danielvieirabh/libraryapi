package io.github.cursodanzin.libraryapi.repository;

import io.github.cursodanzin.libraryapi.model.Autor;
import io.github.cursodanzin.libraryapi.model.Livro;
import io.github.cursodanzin.libraryapi.model.enums.GeneroLivro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
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
        Livro livro = new Livro("66666-44", "UFO", LocalDate.of(1980, 1, 2), BigDecimal.valueOf(100), autor, GeneroLivro.FICCAO);
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


}