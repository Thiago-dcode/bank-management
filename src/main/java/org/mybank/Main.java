package org.mybank;


import exceptions.*;
import factory.AccountFactory;
import factory.UserFactory;
import org.mybank.transaction.DepositTransaction;
import org.mybank.transaction.TransferTransaction;
import org.mybank.transaction.WithdrawTransaction;
import utils.Utils;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args)  {
        Bank bank = new Bank("Santander", "Calle Mayor");
        Scanner in = new Scanner(System.in);
       try{
           AccountFactory.createManyAccountsByUsers(UserFactory.createManyUsers(3),3);
       }
       catch (Exception e){
                Utils.exit(e.getMessage());
       }
        System.out.println("*** Welcome to " + bank.getName() + " Bank ***");
        System.out.println("You can register or Login if you already have an account");
        System.out.println("Press 1 to register or 2 to login");
        int choice = in.nextInt();
        in.nextLine();
        User user = switch (choice) {
            case 1 -> {
                System.out.println("** INIT REGISTER PROCESS **");
                yield handleRegister(in, bank);
            }
            case 2 -> {
                System.out.println("** INIT LOGIN PROCESS **");
                yield handleLogin(in, bank);
            }
            default -> null;
        };
        if (user == null) {
            Utils.exit();
        }
        if (choice == 1) {
            System.out.println("** INIT LOGIN PROCESS **");
            user = handleLogin(in, bank);
        }
        assert user != null;

        //User is logged in.
        System.out.println("** "+ user.toString() + " **");
        handleOperations(in, bank);


    }


    private static void handleOperations(Scanner in, Bank bank){
        System.out.println("What do you want to do?");
        System.out.println("1 to create a new account or 2 to manage an account");
         int choice = in.nextInt();
        in.nextLine();
        switch (choice) {
            case 1 -> {
                System.out.println("** INIT ACCOUNT CREATION PROCESS **");
                handleAccountCreation(in, bank);


            }
            case 2 -> {
                LinkedList<Account> accounts = bank.getUserAccounts();
                if (accounts.isEmpty()) {
                    System.out.println("You don't have any accounts");
                    handleOperations(in, bank);
                    return;
                }
                System.out.println("Choose an account: (type te id of the account to choose one)");
                printUserAccounts(accounts,true,-1);
                int accountId = in.nextInt();
                Account account = null;
                try{
                     account = bank.findAccount0rError(accountId);
                }
                catch(UnauthorizedException e){
                    Utils.exit(e.getMessage());
                }
                if (account == null) {
                    System.out.println("Doesn't exist an account with id " + accountId);
                    handleOperations(in, bank);
                    return;
                }

                in.nextLine();
                System.out.println("Which operation do you want to do?");
                System.out.println("1 to deposit 2 to withdraw 3 to transfer to another account");
                int operation = in.nextInt();
                in.nextLine();
                switch (operation) {
                    case 1 -> {
                        handleDeposit(in,accountId,bank);
                    }
                    case 2 -> {
                        handleWithdraw(in,accountId,bank);
                    }
                    case 3 ->{
                        handleTransfer(in,accountId,bank);
                    }
                }

            }
        }
        System.out.println("Would you like to do anything else?(1=yes, 2=no)");
        choice = in.nextInt();
        switch (choice) {
            case 1 -> {
                handleOperations(in, bank);
            }
            case 2 -> {
                Utils.exit();
            }
        }
    }
    private static void handleAccountCreation(Scanner in, Bank bank) {
        System.out.println("Choose an account name: ");
        String name = in.nextLine();
        try {
            Account newAccount = bank.createAccount(name);
            System.out.println("Account created successfully!");
            System.out.println(newAccount.toString());
            System.out.println("Would you like to deposit on your new account?");
            System.out.println("Press 1 to deposit on your new account or any key to continue");
            int choice = in.nextInt();
            if (choice == 1) {
                handleDeposit(in, newAccount.getId(), bank);
            }
        } catch (Exception ue) {
            System.out.println(ue.getMessage() + ", exiting the program");
            System.exit(0);
        }

    }

    private static void handleDeposit(Scanner in, int accountId, Bank bank) {
        System.out.println("Insert your money: ");
        try {
            double amount = in.nextDouble();
            in.nextLine();
            DepositTransaction depositTransaction=   bank.deposit(accountId, amount);
            System.out.println("Account deposited successfully!");
            System.out.println(depositTransaction.toString());
        } catch (InvalidAmountException ie) {
            //handle invalid amount
        } catch (Exception ue) {
            Utils.exit(ue.getMessage() + ", exiting the program");

        }
    }
    private static void handleWithdraw(Scanner in, int accountId, Bank bank) {
        System.out.println("How many money would you like to withdraw: ");
        try {
            double amount = in.nextDouble();
            in.nextLine();
           WithdrawTransaction withdrawTransaction= bank.withdraw(accountId, amount);
            System.out.println("Withdrawn successfully!");
            System.out.println(withdrawTransaction.toString());
        }
        catch (InsufficientFundsException ie) {
            System.out.println("Insufficient funds!");
            System.out.println("Do you want to continue withdraw? 1 to try again.");
            int choice = in.nextInt();
            in.nextLine();
            if (choice == 1) {
                handleWithdraw(in, accountId, bank);
            }
        }
        catch (Exception ue) {
            Utils.exit(ue.getMessage() + ", exiting the program");
        }
    }
    private static void printUsers(Set<String> users) {

        users.forEach(user -> System.out.println("** "+ user + " **"));
    }

    private static void handleTransfer(Scanner in, int accountId, Bank bank) {
        System.out.println("Who do you want to transfer to another account?");
         printUsers(Bank.getUsersUsername());
        System.out.println("Type the USERNAME of the user you want to transfer: ");
        String username = in.nextLine();
        in.nextLine();
        System.out.println("Which account of that user do you to transfer? ");
        printUserAccounts(Bank.getUserAccounts(username),false,accountId);
        System.out.println("Type the ID of the user account you want to transfer: ");
        int accountToId = in.nextInt();
        in.nextLine();
        System.out.println("How much money would you like to transfer: ");
        try {
            double amount = in.nextDouble();
            TransferTransaction transferTransaction = bank.transfer(accountId,username,accountToId, amount);
            System.out.println("Transfer successfully!");
            System.out.println(transferTransaction.toString());
        }
        catch (InsufficientFundsException ie) {
            System.out.println("Insufficient funds!");
            System.out.println("Do you want to continue withdraw? 1 to try again.");
            int choice = in.nextInt();
            in.nextLine();
            if (choice == 1) {
                handleWithdraw(in, accountId, bank);
            }
        }
        catch (Exception ue) {
            Utils.exit(ue.getMessage() + ", exiting the program");
        }
    }


    private static void printUserAccounts(LinkedList<Account> accounts,Boolean showBalance, int accountIdToAvoid) {
        System.out.println("These are your accounts: ");
        for (Account account : accounts) {
            if (accountIdToAvoid == account.getId()) {
                continue;
            }
            System.out.println("** "+account.toString(showBalance) + " **");
        }
    }

    private static User handleLogin(Scanner in, Bank bank) {
        System.out.println("Enter your name: ");
        String name = in.nextLine();
        try {
           User loginUser = bank.loginUser(name);
            System.out.println("Login successfully!");
            return loginUser;
        } catch (UserNotFoundException e) {
            System.out.println("That user doesn't exist");
            System.out.println("1 to try again 2 to register or 3 to exit");
            int choice = in.nextInt();
            in.nextLine();
            return switch (choice) {
                case 1 -> handleLogin(in, bank);
                case 2 -> {
                  User user  =  handleRegister(in, bank);
                 try{
                     assert user != null;
                     yield bank.loginUser(user);
                 }
                 catch (Exception ex){
                     Utils.exit(e.getMessage() + ", exiting the program");

                 }
                  yield user;
                }
                default -> null;
            };
        }


    }

    private static User handleRegister(Scanner in, Bank bank) {

        System.out.println("Enter your name: ");
        String name = in.nextLine();
        System.out.println("Enter your age: ");
        int age = in.nextInt();
        in.nextLine();
        try {
            User newUser = bank.registerUser(name, age);
            System.out.println("You have successfully registered!");
            return newUser;
        } catch (DuplicateEntityException e) {

            System.out.println("Username already exists!");
            System.out.println("Do you want to try again?(1=yes, 2=no)");
            int choice = in.nextInt();
            in.nextLine();
            if (choice == 1) return handleRegister(in, bank);
            return null;


        }
    }
}