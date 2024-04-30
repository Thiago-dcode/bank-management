package factory;

import exceptions.DuplicateEntityException;
import org.junit.jupiter.api.Test;
import org.mybank.transaction.DepositTransaction;
import org.mybank.transaction.WithdrawTransaction;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class WithdrawTransactionFactoryTest {

    @Test
    void createDepositTransaction() throws DuplicateEntityException {
        WithdrawTransaction depositTransaction = WithdrawTransactionFactory.createWithdrawTransaction();
        assertInstanceOf( WithdrawTransaction.class,depositTransaction);
    }

    @Test
    void testCreateWithdrawTransaction() {
    }

    @Test
    void createManyWithdrawTransaction() {
    }

    @Test
    void testCreateManyWithdrawTransaction() {
    }
}