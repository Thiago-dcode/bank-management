package org.mybank;

import exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {
    private Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank("Santander","Calle mayor 24");
    }

    @Test
    void shouldThrowErrorIfUserIsNotAuthenticated() {
        assertThrows(UnauthorizedException.class, () -> bank.isUserAuthenticated());
    }
    @Test
    void shouldRegisterAnewUser() throws DuplicateEntityException {

        User user = bank.registerUser("Manolo",30);
        assertInstanceOf(User.class, user);
        assertEquals("Manolo",user.getName());
    }
    @Test
    void shouldThrowErrorIfAlreadyExistAUser() throws DuplicateEntityException {
         bank.registerUser("thiago",30);
        assertThrows(DuplicateEntityException.class, () -> bank.registerUser("thiago",30));
    }
    @Test
    void shouldThrowErrorIfUserAttemptToLoginButDoesNotExist()  {

        assertThrows(UserNotFoundException.class,()->bank.loginUser("Thiago"));
    }
    @Test
    void shouldTNothrowErrorIfUserAttemptToLogin() throws UserNotFoundException, DuplicateEntityException, UnauthorizedException {
        bank.registerUser("Javier",30);
        bank.loginUser("Javier");
        assertTrue(bank.isLoggedIn());
        bank.isUserAuthenticated();
        assertEquals("Javier",User.getUser("Javier").getName());
    }
    @Test
    void shouldThrowErrorWhenUserAttemptToCreateAccountButIsNotAuthenticated()  {
        assertThrows(UnauthorizedException.class, () -> bank.createAccount("Savings"));
    }
    @Test
    void shouldNotThrowErrorCreateAccount() throws DuplicateEntityException, UnauthorizedException, UserNotFoundException {
        bank.registerUser("Lala",30);
        bank.loginUser("Lala");
        bank.createAccount("Savings");

    }

    @Test
    void ShouldReturnNullWhenAccountDoesNotExist() throws DuplicateEntityException, UnauthorizedException, UserNotFoundException, AccountNotFoundException {
        bank.registerUser("kiko",30);
        bank.loginUser("kiko");
         assertNull(bank.findAccount0rError(20));
    }
    @Test
    void ShouldReturnAccountWhenAccountExist() throws DuplicateEntityException, UnauthorizedException, UserNotFoundException, AccountNotFoundException {
        bank.registerUser("Alex",30);
        bank.loginUser("Alex");
        Account savings = bank.createAccount("Savings");
        assertEquals("Savings", bank.findAccount0rError(savings.getId()).getName());

    }
    @Test
    void ShouldDeposit() throws DuplicateEntityException, UnauthorizedException, UserNotFoundException, AccountNotFoundException {
        bank.registerUser("Buffer",30);
        bank.loginUser("Buffer");
        Account savings = bank.createAccount("Savings");
        assertTrue(bank.deposit(savings.getId(), 2000));
        assertEquals(2000,savings.getBalance());


    }
    @Test
    void ShouldDepositThrowError() throws DuplicateEntityException, UnauthorizedException, UserNotFoundException, AccountNotFoundException {
        bank.registerUser("Johny",30);
        bank.loginUser("Johny");
        Account savings = bank.createAccount("Savings");
        assertThrows(InvalidAmountException.class,()->bank.deposit(savings.getId(), -2000));

    }
    @Test
    void ShouldWithdraw() throws DuplicateEntityException, UnauthorizedException, UserNotFoundException, AccountNotFoundException {
        bank.registerUser("Manola",30);
        bank.loginUser("Manola");
        Account savings = bank.createAccount("Savings",2000);
        assertTrue(bank.withdraw(savings.getId(), 2000));
        assertEquals(0,savings.getBalance());


    }
    @Test
    void ShouldWithdrawThrowError() throws DuplicateEntityException, UnauthorizedException, UserNotFoundException, AccountNotFoundException {
        bank.registerUser("Lavaliere",30);
        bank.loginUser("Lavaliere");
        Account savings = bank.createAccount("Savings");
        assertThrows(InsufficientFundsException.class,()->bank.withdraw(savings.getId(), 2000));

    }


}