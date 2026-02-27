package io.github.cursodanzin.libraryapi.repository;

import io.github.cursodanzin.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository autorRepository;

    @Test
    public void salvarTest() {
        Autor autor = new Autor("Neymar", LocalDate.of(1999, 3, 13), "Brasileira");
        Autor autorSalvo = autorRepository.save(autor);
        System.out.println("Autor salvo " + autorSalvo);
    }

    @Test
    public void atualizarTest() {
        UUID id = UUID.fromString("fe6e8d43-cc28-4270-b608-4aa7504d94cb");
        Optional<Autor> possivelAutor = autorRepository.findById(id);

        if (possivelAutor.isPresent()) {
            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do autor: "); //Se o id do autor existe
            System.out.println(autorEncontrado);
            //Modificar a data de nascimento :
            autorEncontrado.setDataNascimento(LocalDate.of(1960, 1, 30));
            autorRepository.save(autorEncontrado);
        }

    }
}
