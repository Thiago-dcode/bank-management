package factory;

import com.github.javafaker.Faker;
import exceptions.DuplicateEntityException;
import org.mybank.Account;
import org.mybank.transaction.DepositTransaction;

public class DepositTransactionFactory {
    static Faker faker = new Faker();


    public static DepositTransaction createDepositTransaction() throws DuplicateEntityException {
        Account account = AccountFactory.createAccount();
        return createDepositTransaction(account);
    }
    public static DepositTransaction createDepositTransaction(Account account) throws DuplicateEntityException {
        DepositTransaction depositTransaction = new DepositTransaction(account,faker.number().randomDouble(3,100,5000));
        depositTransaction.execute();
        return depositTransaction;
    }
    public static void createManyDepositTransaction(int n) throws DuplicateEntityException {
        for (int i = 0; i < n; i++) {
            createDepositTransaction();
        }
    }
    public static void createManyDepositTransaction(Account account,int n) throws DuplicateEntityException {
        for (int i = 0; i < n; i++) {
            createDepositTransaction(account);
        }
    }
}
