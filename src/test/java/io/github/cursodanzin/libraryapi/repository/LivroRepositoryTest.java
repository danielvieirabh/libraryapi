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

    @Test
    public void salvarCascadeTest() { //Inserir no banco com o Cascade , puxar mais de um com o Cascade
        Autor autor = new Autor("Joao", LocalDate.of(1999, 3, 13), "Brasileira");
        Livro livro = new Livro("98886-44", "UFO", LocalDate.of(1980, 1, 2), BigDecimal.valueOf(100), autor, GeneroLivro.FICCAO);
        livroRepository.save(livro);
    }
}