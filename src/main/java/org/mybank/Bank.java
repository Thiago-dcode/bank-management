package org.mybank;

import exceptions.*;
import org.mybank.transaction.DepositTransaction;
import org.mybank.transaction.TransferTransaction;
import org.mybank.transaction.WithdrawTransaction;

import java.util.HashMap;
import java.util.LinkedList;

public class Bank {
    private String bankName;
    private String bankAddress;
    private static HashMap<String, LinkedList<Account>> userAccounts;
    private User userLogin;

    public Bank(String bankName, String bankAddress) {
        this.bankName = bankName;
        this.bankAddress = bankAddress;
        userAccounts = new HashMap<>();
        this.userLogin = null;
    }

    public static void addUser(String username) {
        userAccounts.putIfAbsent(username, new LinkedList<>());
    }
    public static void addAccount(String username,Account account) {
        userAccounts.get(username).add(account);
    }
    public Account createAccount(String accountName) throws UnauthorizedException {
        isUserAuthenticated();
        Account account = new Account(accountName,userLogin,0);
        userAccounts.get(userLogin.getName()).add(account);
        return account;
    }
    public Account createAccount(String accountName,double amount) throws UnauthorizedException {
        isUserAuthenticated();
        Account account = new Account(accountName,userLogin,amount);
        userAccounts.get(userLogin.getName()).add(account);
        return account;
    }
    public User registerUser(String username,int age) throws DuplicateEntityException {
        if(userAccounts.containsKey(username)){
            throw new DuplicateEntityException("User already exists");
        }
        User newUser = new User(username,age);
        userAccounts.put(username,new LinkedList<Account>() );
        User.addUser(newUser);
        return newUser;
    }
    public void loginUser(String username) throws UserNotFoundException {
        if(!userAccounts.containsKey(username)){
            throw new UserNotFoundException();
        }
        this.userLogin = User.getUser(username);
    }
    public boolean deposit( int accountId, double amount) throws UserNotFoundException, UnauthorizedException, AccountNotFoundException, InvalidAmountException {

            Account account = findAccount0rError(accountId);
            DepositTransaction depositTransaction = new DepositTransaction(account, amount);
            depositTransaction.execute();
            return true;


    }
    public boolean withdraw( int accountId, double amount) throws UserNotFoundException, UnauthorizedException, AccountNotFoundException, InvalidAmountException, InsufficientFundsException {

        Account account = findAccount0rError(accountId);
        WithdrawTransaction withdrawTransaction = new WithdrawTransaction(account, amount);
        withdrawTransaction.execute();
        return true;


    }
    public boolean transfer( int accountId,String usernameTo, int accountIdTo, double amount) throws UserNotFoundException, UnauthorizedException, AccountNotFoundException, InvalidAmountException, InsufficientFundsException {
        Account account = findAccount0rError(accountId);
        Account accountTo = findAccount0rError(usernameTo,accountIdTo);
        TransferTransaction transferTransaction = new TransferTransaction(account, amount,accountTo);
        transferTransaction.execute();
        return true;


    }
    public boolean isLoggedIn(){
        return this.userLogin != null;
    }
    public void isUserAuthenticated() throws UnauthorizedException {
        if(!isLoggedIn()){
            throw new UnauthorizedException();
        }
    }
    public Account findAccount0rError( int accountId) throws UserNotFoundException, AccountNotFoundException, UnauthorizedException {
        isUserAuthenticated();
        return findAccount(userLogin.getName(), accountId);
    }
    public Account findAccount0rError( String username, int accountId) throws UserNotFoundException, AccountNotFoundException, UnauthorizedException {
        isUserAuthenticated();
        return findAccount(username, accountId);
    }
    private Account findAccount(String username, int accountId){
        LinkedList<Account> accounts = userAccounts.get(username);
        if(accounts == null){
            userAccounts.put(username, new LinkedList<Account>());
            return null;
        }
        for(Account account : accounts){
            if(account.getId() == accountId){
                return account;
            }
        }
        return null;
    }

}
