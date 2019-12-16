package com.mycompany.app;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.ArrayList;
import java.util.List;

//Email list class to add Players to the email list.
public class EmailList {

    private List<Player> players;
    private List<String> emailList;
    private String playerType;

    public EmailList(String playerType) {

        this.playerType = playerType;
        this.players = new ArrayList<Player>();
        this.emailList = new ArrayList<String>();
    }

    private boolean addPlayer(Player player) {

        if (findPlayer(player.getEmailAddress()) > 0) {

            System.out.println("The player already exists!");
            return false;
        }

        players.add(player);
        System.out.println("The player was successfully added to the mailing list.");
        return true;
    }

    private boolean removePlayer(Player player) {

        if (findPlayer(player.getEmailAddress()) > 0) {

            System.out.println("The player has been removed.");
            players.remove(player);
            return true;
        }

        System.out.println("The player could not be found.");
        return false;
    }

    public boolean addPlayerToMailing(Player player) {

        if (findPlayer(player.getEmailAddress()) > 0) {

            emailList.add(player.getEmailAddress());
            System.out.println("The email has been added successfully.");
            return true;
        }

        System.out.println("The email could not be added.");
        return false;
    }

    public boolean removePlayerFromMailing(Player player) {

        if (findPlayer(player.getEmailAddress()) > 0) {

            emailList.remove(player.getEmailAddress());
            System.out.println("The email address has been removed.");
            return true;
        }

        System.out.println("The operation was invalid.");
        return false;
    }

    public int findPlayer(Player player) {

        return players.indexOf(player);
    }

    public int findPlayer(String emailAddress) {

        for (int i = 0; i < players.size(); i++) {

            Player player = players.get(i);

            if (player.getEmailAddress().equals(emailAddress)) {

                return i;
            }
        }

        return -1;
    }

    public boolean isValidEmailAddress(String emailAddress) {

        boolean result = false;

        if (findPlayer(emailAddress) > 0) {

            try {

                InternetAddress emailValid = new InternetAddress(emailAddress);
                emailValid.validate();
                result = true;
            }
            catch (AddressException e) {

                System.out.println("The email " + emailAddress + " caught an exception.");
            }
        }

        return result;
    }
}
