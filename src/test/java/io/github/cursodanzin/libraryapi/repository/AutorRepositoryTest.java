package io.github.cursodanzin.libraryapi.repository;

import io.github.cursodanzin.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository autorRepository;

    @Test
    public void salvarTest() {
        Autor autor = new Autor("Neymar", LocalDate.of(1999, 3, 13), "Brasileira");
        Autor autorSalvo = autorRepository.save(autor);
        System.out.println(autorSalvo);
    }
}
