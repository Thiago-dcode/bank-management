package org.mybank;

public class User {
    private int id;
    private final String name;
    private static int total = 0;
    private int age;
    public User( String name, int age) {
    this.name = name;
    this.age = age;
    total++;
    this.id = total;
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
