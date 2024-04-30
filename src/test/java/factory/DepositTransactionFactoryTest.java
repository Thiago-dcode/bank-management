package factory;

import exceptions.DuplicateEntityException;
import org.junit.jupiter.api.Test;
import org.mybank.transaction.DepositTransaction;

import static org.junit.jupiter.api.Assertions.*;

class DepositTransactionFactoryTest {

    @Test
    void createDepositTransaction() throws DuplicateEntityException {
        DepositTransaction depositTransaction = DepositTransactionFactory.createDepositTransaction();
        assertInstanceOf( DepositTransaction.class,depositTransaction);
    }

    @Test
    void testCreateDepositTransaction() {
    }

    @Test
    void createManyDepositTransaction() {
    }

    @Test
    void testCreateManyDepositTransaction() {
    }
}