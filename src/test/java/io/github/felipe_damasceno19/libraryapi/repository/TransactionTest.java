package io.github.felipe_damasceno19.libraryapi.repository;

import io.github.felipe_damasceno19.libraryapi.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class TransactionTest {

    @Autowired
    TransactionService transactionService;

    /**
     * commit -> confirmar alterações
     * Rollback -> desfazer as alterações
     */
    @Test
    void simpleTransaction(){

        transactionService.execute();
    }

}
