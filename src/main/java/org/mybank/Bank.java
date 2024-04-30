package org.mybank;

import exceptions.*;
import org.mybank.transaction.DepositTransaction;
import org.mybank.transaction.TransferTransaction;
import org.mybank.transaction.WithdrawTransaction;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

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
    public static Set<String> getUsersUsername(){
        return userAccounts.keySet();
    }
    public static void addUser(String username) {
        if(userAccounts == null){
            userAccounts = new HashMap<>();
        }
        if(userAccounts.containsKey(username)) return;
        userAccounts.putIfAbsent(username, new LinkedList<>());
    }
    public static void addAccount(String username,Account account) {
        userAccounts.get(username).add(account);
    }

    public  User getUserLogin() {
        return userLogin;
    }

    public Account createAccount(String accountName) throws UnauthorizedException {
        isUserAuthenticated();

        return new Account(accountName,userLogin,0);
    }
    public Account createAccount(String accountName,double amount) throws UnauthorizedException {
        isUserAuthenticated();
        return new Account(accountName,userLogin,amount);
    }
    public User registerUser(String username,int age) throws DuplicateEntityException {
        if(userAccounts.containsKey(username)){
            throw new DuplicateEntityException("User already exists");
        }
        return new User(username,age);
    }
    public User loginUser(String username) throws UserNotFoundException {
        if(!userAccounts.containsKey(username)){
            throw new UserNotFoundException();
        }
        this.userLogin = User.getUser(username);
        return userLogin;
    }
    public User loginUser(User user) throws UserNotFoundException {
        if(!userAccounts.containsKey(user.getName())){
            throw new UserNotFoundException();
        }
        this.userLogin =user;
        return userLogin;
    }
    public DepositTransaction deposit( int accountId, double amount) throws UserNotFoundException, UnauthorizedException, AccountNotFoundException, InvalidAmountException {

            Account account = findAccount0rError(accountId);
            DepositTransaction depositTransaction = new DepositTransaction(account, amount);
            depositTransaction.execute();
            return depositTransaction;


    }
    public WithdrawTransaction withdraw( int accountId, double amount) throws UserNotFoundException, UnauthorizedException, AccountNotFoundException, InvalidAmountException, InsufficientFundsException {

        Account account = findAccount0rError(accountId);
        WithdrawTransaction withdrawTransaction = new WithdrawTransaction(account, amount);
        withdrawTransaction.execute();
        return withdrawTransaction;


    }
    public TransferTransaction transfer( int accountId,String usernameTo, int accountIdTo, double amount) throws UserNotFoundException, UnauthorizedException, AccountNotFoundException, InvalidAmountException, InsufficientFundsException {
        Account account = findAccount0rError(accountId);
        Account accountTo = findAccount0rError(usernameTo,accountIdTo);
        TransferTransaction transferTransaction = new TransferTransaction(account, amount,accountTo);
        transferTransaction.execute();
        return transferTransaction;


    }
    public boolean isLoggedIn(){
        return this.userLogin != null;
    }
    public void isUserAuthenticated() throws UnauthorizedException {
        if(!isLoggedIn()){
            throw new UnauthorizedException();
        }
    }
    public Account findAccount0rError( int accountId) throws  UnauthorizedException {
        isUserAuthenticated();
        return findAccount(userLogin.getName(), accountId);
    }
    public Account findAccount0rError( String username, int accountId) throws UserNotFoundException, AccountNotFoundException, UnauthorizedException {
        isUserAuthenticated();
        return findAccount(username, accountId);
    }
    public LinkedList<Account> getUserAccounts() {
        return userAccounts.get(userLogin.getName());
    }
    public static LinkedList<Account> getUserAccounts(String username) {
        return userAccounts.get(username);
    }
    public static HashMap<String,LinkedList<Account>> getUserAccountsMap() {
        return userAccounts;
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

    public String getName() {
        return this.bankName;
    }
}
