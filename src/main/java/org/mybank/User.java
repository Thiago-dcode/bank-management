package org.mybank;

public class User {
    private final int id;
    private final String name;
    private int age;
    public User(int id, String name, int age) {
    this.id = id;
    this.name = name;
    this.age = age;

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

    public void save(){
        //save the user in the db;
    }

}
