package io.github.cursodanzin.libraryapi.service;

import io.github.cursodanzin.libraryapi.model.Autor;
import io.github.cursodanzin.libraryapi.model.Livro;
import io.github.cursodanzin.libraryapi.model.enums.GeneroLivro;
import io.github.cursodanzin.libraryapi.repository.AutorRepository;
import io.github.cursodanzin.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    AutorRepository autorRepository;
    @Autowired
    LivroRepository livroRepository;

    // CAMpos classe livro (titulo, ... , nomeArquivo -> id.png)
    @Transactional
    public void salvarLivroComFoto() {

        // salva o livro
        // livroRepository.save(livro);

        // pega o id do livro = livro.getId();
        // UUID id = livro.getId;

        // salvar foto do livro -> bucket na nuvem
        // bucketService.salvar(livro.getFoto(), id + ".png");

        // atualizar o nome do arquivo que foi salvo
        // livro.setNomeArquivoFOto(id + ".png");

        // livroRepository.save(livro); Nao precisa disso novamente
    }

    @Transactional
    public void atualizacaoSemAtualizar() {
        //Somente isso e o suficiente
        Livro livro = livroRepository.findById(UUID.fromString("422acc31-7786-4775-9f9a-e893d69b6c47")).orElse(null);
        livro.setDataPublicacao(LocalDate.of(2024, 6 , 1));

//        livroRepository.save(livro); //Atualizar o livro
    }

    @Transactional
    public void executar() {
        //Salvar o autor
        Autor autor = new Autor("Daniel", LocalDate.of(1999, 3, 13), "Alemã");
        autorRepository.save(autor);

        //Salva o livro
        Livro livro = new Livro("98886-44", "Livro Daniel", LocalDate.of(1980, 1, 2), BigDecimal.valueOf(100), autor, GeneroLivro.FICCAO);
//        livroRepository.saveAndFlush(livro); Se usar isso ele manda para o banco mas nao inseri la
        livroRepository.save(livro);

        if (autor.getNome().equals("Daniel")) {
            throw  new RuntimeException("Rollback! ");
        }
    }
}
