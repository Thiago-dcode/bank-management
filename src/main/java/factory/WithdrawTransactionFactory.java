package factory;

import com.github.javafaker.Faker;
import exceptions.DuplicateEntityException;
import org.mybank.Account;
import org.mybank.transaction.WithdrawTransaction;

public class WithdrawTransactionFactory {
    static Faker faker = new Faker();


    public static WithdrawTransaction createWithdrawTransaction() throws DuplicateEntityException {
        Account account = AccountFactory.createAccount();
        return createWithdrawTransaction(account);
    }
    public static WithdrawTransaction createWithdrawTransaction(Account account) throws DuplicateEntityException {
        WithdrawTransaction withdrawTransaction = new WithdrawTransaction(account,faker.number().randomDouble(0,100,500));
        withdrawTransaction.execute();
        return withdrawTransaction;
    }
    public static void createManyWithdrawTransaction(int n) throws DuplicateEntityException {
        for (int i = 0; i < n; i++) {
            createWithdrawTransaction();
        }
    }
    public static void createManyWithdrawTransaction(Account account,int n) throws DuplicateEntityException {
        for (int i = 0; i < n; i++) {
            createWithdrawTransaction(account);
        }
    }
}
