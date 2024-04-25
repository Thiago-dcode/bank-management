package org.mybank;

import exceptions.DuplicateEntityException;

import java.util.HashMap;

public class User {
    private int id;
    private final String name;
    private static int total = 0;
    private int age;
    private static HashMap<String,User> users = new HashMap<>();
    public User( String name, int age) throws DuplicateEntityException {
        if(users.containsKey(name)) {
           throw new DuplicateEntityException("User already exists");
        }
    this.name = name;
    this.age = age;
    total++;
    this.id = total;
    users.put(name, this);
    Bank.addUser(name);
    }
    public static void addUser(User user) {
        users.put(user.name, user);
    }
    public static boolean userExists(String name) {
        return users.containsKey(name);
    }
    public static User getUser(String name) {
        return users.get(name);
    }
    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public int getAge(){
        return this.age;
    }
    public static int getTotal(){
        return total;
    }

    public void save(){
        //save the user in the db;
    }

}
