package io.github.cursodanzin.libraryapi.repository;

import io.github.cursodanzin.libraryapi.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class TransacoesTest {
    @Autowired
    TransacaoService transacaoService;


//    Commit -> COnfirmar as alteracoes
//    Rollback -> desfazer as alteracoes
    @Test
    public void transacaoSimples() { //Se der erro e tudo isso aqui, vai ter um rollback :
        // salvar um livro
        // salvar o autor
        // alugar o livro
        // enviar um email pra quem aluga
        // notificar que o livro saiu da livraria

        transacaoService.executar();
    }

    @Test
    public void transacaoEstadoManaged() {
        transacaoService.atualizacaoSemAtualizar();
    }
}
