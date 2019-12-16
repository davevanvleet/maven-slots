package com.mycompany.app;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SlotPlayerList {

    private List<SlotPlayer> players;
    private String gamePlayed, perksGranted;
    private int tokensAmount;
    private final int tokensCost = 10;

    public SlotPlayerList(String gamePlayed) {

        this.players = new ArrayList<SlotPlayer>();
        this.gamePlayed = gamePlayed;
    }

    public int findPlayer(SlotPlayer player) {

        return this.players.indexOf(player);
    }

    public int findPlayer(String playerID) {

        for (int i = 0; i < this.players.size(); i++) {

            SlotPlayer player = this.players.get(i);

            if (player.getID().equals(playerID)) {

                return i;
            }
        }

        return -1;
    }

    public boolean addPlayer(SlotPlayer player) {

        if (findPlayer(player.getID()) >= 0) {

            System.out.println("The player ID already exists!");
            return false;
        }

        this.players.add(player);
        System.out.println("The player was added.\n");
        return true;
    }

    public boolean removePlayer(SlotPlayer player) {

        int position = findPlayer(player);

        if (position <= -1) {

            System.out.println("The player was not found.\n");
            return false;
        }

        this.players.remove(position);
        return true;
    }

    public boolean addRandomTokens(SlotPlayer player) {

        if (findPlayer(player.getID()) > -1) {

            Random rand = new Random();
            int randomTokens = rand.nextInt(50) + 1;
            player.tokens += randomTokens;
            System.out.println(randomTokens + " were added to your account.");
            return true;
        }

        System.out.println("The random tokens could not be added.\n");
        return false;
    }

    public boolean removeRandomTokens(SlotPlayer player) {

        if (findPlayer(player.getID()) > -1) {

            Random rand = new Random();
            int removeRandomTokens = rand.nextInt(50) + 1;
            player.tokens -= removeRandomTokens;
            System.out.println(removeRandomTokens + " were removed from your account.\n");
            return true;
        }

        System.out.println("The random tokens couldn't be removed.\n");
        return false;
    }

    public boolean calculateBalanceAfterTokens(SlotPlayer player, int tokens) {

        if (findPlayer(player.getID()) > 0) {

            int tokenTotalAmount = tokens * tokensCost;
            player.balance -= tokenTotalAmount;
            System.out.println("The balance on your account is " + player.getBalance() + ".");
            return true;
        }

        System.out.println("The calculation is not valid!");
        return false;
    }

    public boolean addTokens(SlotPlayer player, int tokens) {

        if (findPlayer(player.getID()) > -1) {

            player.tokens += tokens;
            return true;
        }

        System.out.println("The transaction could not be processed.\n");
        return false;
    }

    public boolean removeTokens(SlotPlayer player, int tokens) {

        if (findPlayer(player.getID()) > -1) {

            player.tokens -= tokens;
            return true;
        }

        System.out.println("The transaction could not be processed.\n");
        return false;
    }

    public boolean addBalance(SlotPlayer player, double money) {

        DecimalFormat twoPlace = new DecimalFormat("0.00");

        if (findPlayer(player.getID()) > -1) {

            player.balance += money;
            System.out.println("The player had $" + twoPlace.format(money) + " added to their account.\n" +
                    "The current balance of the account is $" + twoPlace.format(player.getBalance()) + ".\n");
            return true;
        }

        System.out.println("The player was not found in the list.");
        return false;
    }

    public boolean removeBalance(SlotPlayer player, double money) {

        if (findPlayer(player.getID()) > -1) {

            player.balance -= money;
            System.out.println("The player had " + money + " removed from their account.\n" +
                    "The current balance of the account is " + player.getBalance() + ".");
            return true;
        }

        System.out.println("The player was not found in the list.");
        return false;
    }

    public SlotPlayer playerDirectory(String ID) {

        int position = findPlayer(ID);

        if (position >= 0) {

            return this.players.get(position);
        }

        System.out.println("The player could not be found!");
        return null;
    }

    public boolean bonusForPlayers(SlotPlayer player) {

        int tokens = 50;

        if (findPlayer(player) > 0) {

            this.tokensAmount += tokens;
            System.out.println("Your token balance is now " + player.getTokensAmount() + ".");
            return true;
        }

        System.out.println("You do not meet the requirements.");
        return false;
    }

    public boolean addPlayerPerks(SlotPlayer player) {

        if (findPlayer(player) > 0) {

            if (player.balance > 1000) {

                setPlayerPerks("Platinum");
            }
            else if (player.balance > 750) {

                setPlayerPerks("Gold");
            }
            else if (player.balance > 500) {

                setPlayerPerks("Silver");
            }
            else if (player.balance > 250) {

                setPlayerPerks("Bronze");
            }
            else {

                System.out.println("No perks available.");
            }
            System.out.println("The player has been assigned the " + getPerksGranted() + " perk.");
            return true;
        }

        System.out.println("The player could not be assigned a role.");
        return false;
    }

    public boolean updateSlotPlayerInformation(SlotPlayer oldPlayer, SlotPlayer newPlayer) {

        int position = findPlayer(oldPlayer);

        if (position <= -1) {

            System.out.println("The player was not found!");
            return false;
        }

        players.set(position, newPlayer);
        return true;
    }

    public boolean updatePlayerName(SlotPlayer player, String newName) {

        if (findPlayer(player) > -1) {

            player.setName(newName);
            System.out.println("The player has been changed to " + newName + ".");
            return true;
        }

        System.out.println("The player name couldn't be changed.");
        return false;
    }

    public boolean updatePlayerID(SlotPlayer player, String ID) {

        if (findPlayer(player) > -1) {

            player.setID(ID);
            System.out.println("The player ID has been changed to " + ID + ".");
            return true;
        }

        System.out.println("The player ID couldn't be changed.");
        return false;
    }

    public boolean updatePlayerAge(SlotPlayer player, int age) {

        if (findPlayer(player) > -1) {

            player.setAge(age);
            System.out.println("The player age has been set to " + age + ".");
            return true;
        }

        System.out.println("The player age couldn't be changed.");
        return false;
    }

    public void printPlayers() {

        for (int i = 0; i < this.players.size(); i++) {

            System.out.println((i + 1) + "." + this.players.get(i).getName() + " -> " +
                    this.players.get(i).getID() + " -> " +
                    this.players.get(i).getAge() + "");
        }
        System.out.println();
    }

    public String getPerksGranted() {

        return perksGranted;
    }

    public void setPlayerPerks(String playerPerks) {

        this.perksGranted = playerPerks;
    }

    @Override
    public String toString() {

        return "You are a " + perksGranted + " player at our " + gamePlayed + ".";
    }
}

