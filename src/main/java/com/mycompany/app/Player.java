package com.mycompany.app;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String firstName, lastName;
    private String emailAddress;
    private String password;

    public Player(String firstName, String lastName, String emailAddress, String password) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public String getFirstName() {

        return firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public String getEmailAddress() {

        return emailAddress;
    }

    public String getPassword() {

        return password;
    }

    public static Player createPlayer(String first, String last, String email, String password) {

        return new Player(first, last, email, password);
    }
}
