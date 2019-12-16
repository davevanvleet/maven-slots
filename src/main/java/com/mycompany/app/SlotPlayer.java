package com.mycompany.app;

public class SlotPlayer {

    private String name, ID;
    private int age;
    public double balance;
    public int tokens;

    private SlotPlayer(String name, String ID, int age) {

        this.name = name;
        this.age = age;
        this.ID = ID;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setAge(int age) {

        this.age = age;
    }

    public int getAge() {

        return age;
    }

    public void setID(String ID) {

        this.ID = ID;
    }

    public String getID() {

        return ID;
    }

    public double getBalance() {

        return balance;
    }

    public int getTokensAmount() {

        return tokens;
    }

    public static SlotPlayer createSlotPlayer(String name, String ID, int age) {

        return new SlotPlayer(name, ID, age);
    }
}
