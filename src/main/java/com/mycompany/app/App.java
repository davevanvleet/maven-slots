package com.mycompany.app;
import java.util.Scanner;

public class App {

    //Grabs user input
    private static Scanner sc = new Scanner(System.in);
    //Initializing the SlotPlayerList object
    private static SlotPlayerList playerList = new SlotPlayerList("Casino");
    //Initializing the EmailList object
    private static EmailList emailList = new EmailList("Generic Player");

    //Main method
    public static void main(String[] args) {

        boolean quit = false;
        welcomePlayer();

        while(!quit) {

            printMenu();
            System.out.println("Enter an option: ");
            int option = sc.nextInt();
            sc.nextLine();

            switch(option) {

                case 0:

                    System.out.println("The machine is shutting down..");
                    quit = true;
                    break;

                case 1:

                    addSlotPlayer();
                    break;

                case 2:

                    removeSlotPlayer();
                    break;

                case 3:

                    printUpdateMenu();
                    updatePlayerInformation();
                    break;

                case 4:

                    addTokens();
                    break;

                case 5:

                    addRandomTokens();
                    break;

                case 6:

                    addBalance();
                    break;

                case 7:

                    playSlots();
                    break;

                case 8:

                    printPlayers();
                    break;

                case 9:

                    searchByPlayerID();
                    break;

                case 10:

                    addPlayerPerk();
                    break;

                case 11:

                    removeTokens();
                    break;

                case 12:

                    removeRandomTokens();
                    break;

                case 13:

                    updateBalanceAfterTokenPurchase();
                    break;

                case 14:

                    removeBalance();
                    break;

                case 15:

                    bonusTokens();
                    break;

                case 16:

                    printMenu();
                    break;

                default:

                    System.out.println("ERROR Invalid Operation");
                    break;
            }
        }
    }

    /**
     * Adds a player to the playerList by accepting user input for the SlotPlayer (name, ID, age).
     * A new SlotPlayer will be created using the input provided by the user, and the addPlayer class
     * will be used to check whether or not the SlotPlayer may successfully be added to the playerList.
     */
    private static void addSlotPlayer() {

        System.out.println("Enter the player name: ");
        String name = sc.nextLine();
        System.out.println("Enter the player ID: ");
        String ID = sc.nextLine();
        System.out.println("Enter the player age: ");
        int age = sc.nextInt();

        SlotPlayer player = SlotPlayer.createSlotPlayer(name, ID, age);

        if (playerList.addPlayer(player)) {

            System.out.println("Player Name: " + name +
                    "\nPlayer ID: " + ID +
                    "\nPlayer Age: " + age);
            System.out.println();
        }
        else {

            System.out.println("The player could not be added!\n");
        }
    }

    /**
     * Removes a SlotPlayer from the playerList by accepting the SlotPlayer ID as input. The SlotPlayer
     * will be checked to see whether or not it is located within the playerDirectory. If the existing player
     * is == null, the player cannot be found. Otherwise, the removePlayer method will return true or false as
     * to whether or not the SlotPlayer can be removed from the playerList.
     */
    private static void removeSlotPlayer() {

        System.out.println("Enter the player ID: ");
        String ID = sc.nextLine();

        SlotPlayer existingPlayer = playerList.playerDirectory(ID);

        if (existingPlayer == null) {

            System.out.println("The player was not found in the list.");
            return ;
        }

        if (playerList.removePlayer(existingPlayer)) {

            System.out.println("The player was removed successfully!\n");
        }
        else {

            System.out.println("ERROR: Deleting the player.\n");
        }
    }

    /**
     * Adds tokens to the SlotPlayer within the playerDirectory if the SlotPlayer is not == null. If the SlotPlayer
     * is successfully located within the list, then the tokens will be added to the SlotPlayer within the list.
     * @return token amount
     */
    private static int addTokens() {

        System.out.println("--- Add tokens to your account ---");
        System.out.println("Enter your player ID: ");
        String ID = sc.nextLine();

        System.out.println("Add tokens: ");
        int tokensAmount = sc.nextInt();

        SlotPlayer existingPlayer = playerList.playerDirectory(ID);

        if (existingPlayer == null) {

            System.out.println("The player was not found in the list.");
            return -1;
        }

        if (playerList.addTokens(existingPlayer, tokensAmount)) {

            System.out.println("You have successfully added tokens!" +
                    "\nYour total token amount is " + existingPlayer.getTokensAmount() + ".\n");
            return existingPlayer.getTokensAmount();
        }
        else {

            System.out.println("Error adding tokens!");
            return -1;
        }
    }

    /**
     * Removes tokens from the SlotPlayer by grabbing the SlotPlayer ID and the amount of tokens in which should
     * be removed from the SlotPlayer within the playerList. If the player is == null, the player does not exist
     * within the list. Otherwise, if the player is found in the directory and the amount is valid the amount of
     * SlotPlayer tokens will be returned.
     * @return token amount
     */
    private static int removeTokens() {

        System.out.println("--- Remove tokens from your account ---");
        System.out.println("Enter your player ID: ");
        String ID = sc.next();
        sc.nextLine();

        System.out.println("Remove tokens: ");
        int tokenAmount = sc.nextInt();

        SlotPlayer existingPlayer = playerList.playerDirectory(ID);

        if (existingPlayer == null) {

            System.out.println("The player was not found in the list.");
            return -1;
        }

        if (playerList.removeTokens(existingPlayer, tokenAmount)) {

            System.out.println("You have successfully removed tokens!" +
                    "\nYour total token amount is " + existingPlayer.getTokensAmount() + ".");
            return existingPlayer.getTokensAmount();
        }
        else {

            System.out.println("Error removing tokens!");
            return -1;
        }
    }

    /**
     * Adds a random amount of tokens to an existing SlotPlayer within the playerList. If the existing SlotPlayer
     * == null, then the value returned is -1. Otherwise, a random number of tokens will be added to the SlotPlayer
     * within the playerList.
     * @return token amount
     */
    private static int addRandomTokens() {

        System.out.println("Enter your player ID: ");
        String ID = sc.nextLine();

        SlotPlayer existingPlayer = playerList.playerDirectory(ID);

        if (existingPlayer == null) {

            System.out.println("The player ID was not found.");
            return -1;
        }

        if (playerList.addRandomTokens(existingPlayer)) {

            System.out.println("Your token balance is now: " + existingPlayer.getTokensAmount() + ".\n");
        }
        else {

            System.out.println("Tokens were not added.\n");
        }

        return existingPlayer.getTokensAmount();
    }

    /**
     * Removes a random number of tokens from the SlotPlayer within the playerList. If the existing player == null,
     * then the value returned is -1. Otherwise, a random number of tokens will be removed from the SlotPlayer
     * within the playerList.
     * @return token amount
     */
    private static int removeRandomTokens() {

        System.out.println("Enter your player ID: ");
        String ID = sc.nextLine();

        SlotPlayer existingPlayer = playerList.playerDirectory(ID);

        if (existingPlayer == null) {

            System.out.println("The player ID was not found.");
            return -1;
        }

        if (playerList.removeRandomTokens(existingPlayer)) {

            System.out.println("Your total token balance is now: " + existingPlayer.getTokensAmount() + ".\n");
        }
        else {

            System.out.println("Tokens were not removed.\n");
        }

        return existingPlayer.getTokensAmount();
    }

    /**
     * Find the player and add bonus tokens for the player.
     */
    private static void bonusTokens() {

        System.out.println("Enter the player ID: ");
        String ID = sc.nextLine();

        SlotPlayer player = playerList.playerDirectory(ID);

        if (player == null) {

            System.out.println("The player was not found in the list.");
            return ;
        }

        if (playerList.bonusForPlayers(player)) {

            System.out.println("Bonus tokens were successfully added to your account.");
        }
        else {

            System.out.println("Unable to process bonus.");
        }
    }

    /**
     * Adds the balance to the SlotPlayer within the playerList.
     */
    private static void addBalance() {

        System.out.println("Enter the player ID: ");
        String ID = sc.nextLine();

        SlotPlayer player = playerList.playerDirectory(ID);

        if (player == null) {

            System.out.println("The player was not found in the list.");
            return ;
        }

        System.out.println("Add to balance: ");
        double balance = sc.nextDouble();
        sc.nextLine();

        if (playerList.addBalance(player, balance)) {

            System.out.println("The balance was added successfully.");
        }
        else {

            System.out.println("Invalid balance was entered.");
        }
    }

    /**
     * Removes the balance from the SlotPlayer within the playerList.
     */
    private static void removeBalance() {

        System.out.println("Enter the player ID: ");
        String ID = sc.nextLine();

        SlotPlayer player = playerList.playerDirectory(ID);

        if (player == null) {

            System.out.println("The player was not found in the list.");
            return ;
        }

        System.out.println("Remove a balance: ");
        double balance = sc.nextDouble();

        if (playerList.removeBalance(player, balance)) {

            System.out.println("The balance was removed successfully.");
        }
        else {

            System.out.println("Invalid balance was entered.");
        }
    }

    /**
     * Calculates and updates the balance of the SlotPlayer after tokens are purchased.
     */
    private static void updateBalanceAfterTokenPurchase() {

        System.out.println("Enter the player ID: ");
        String ID = sc.nextLine();

        SlotPlayer player = playerList.playerDirectory(ID);

        if (player == null) {

            System.out.println("The player was not found in the list.");
            return ;
        }

        System.out.println("Enter the token amount: ");
        int tokens = sc.nextInt();

        if (playerList.calculateBalanceAfterTokens(player, tokens)) {

            System.out.println("The balance of your account was updated.");
        }
        else {

            System.out.println("The balance of your account could not be updated.");
        }
    }

    /**
     * Updates the player information depending on the player selection.
     */
    private static void updatePlayerInformation() {

        System.out.println("Enter the player ID: " );
        String ID = sc.nextLine();

        SlotPlayer oldPlayer = playerList.playerDirectory(ID);

        if (oldPlayer == null) {

            System.out.println("The player was not found in the list.");
            return ;
        }

        boolean quit = false;

        do {

            printUpdateMenu();
            int choice = sc.nextInt();

            switch(choice) {

                case 0:

                    System.out.println("The update tool will now exit.");
                    quit = true;
                    break;

                case 1:

                    System.out.println("Enter the new player name: ");
                    String newName = sc.next();
                    sc.nextLine();

                    if (playerList.updatePlayerName(oldPlayer, newName)) {

                        System.out.println("The player name has been set to " + newName);
                    }
                    else {

                        System.out.println("Could not update player name.");
                    }
                    break;

                case 2:

                    System.out.println("Enter the new player ID: ");
                    String newID = sc.next();
                    sc.nextLine();

                    if (playerList.updatePlayerID(oldPlayer, newID)) {

                        System.out.println("The player ID has been set to " + newID);
                    }
                    else {

                        System.out.println("Couldn't update player ID.");
                    }
                    break;

                case 3:

                    System.out.println("Enter the new player age: ");
                    int newAge = sc.nextInt();
                    sc.nextLine();

                    if (playerList.updatePlayerAge(oldPlayer, newAge)) {

                        System.out.println("The player age has been set to " + newAge);
                    }
                    else {

                        System.out.println("Couldn't update player age.");
                    }
                    break;

                case 4:

                    System.out.println("Enter the new player name: ");
                    String updateName = sc.next();
                    System.out.println("Enter the new player ID: ");
                    String updateID = sc.next();
                    System.out.println("Enter the new player age: ");
                    int updateAge = sc.nextInt();

                    SlotPlayer newPlayers = SlotPlayer.createSlotPlayer(updateName, updateID, updateAge);

                    if (playerList.updateSlotPlayerInformation(oldPlayer, newPlayers)) {

                        System.out.println("Successfully updated all player information.");
                    }
                    else {

                        System.out.println("Could not update all player information.");
                    }
                    break;

                case 5:

                    System.out.println("Enter an existing user ID: ");
                    String userID = sc.next();
                    sc.nextLine();

                    SlotPlayer player = playerList.playerDirectory(userID);

                    if (player == null) {

                        System.out.println("The contact does not exist.");
                    }

                    System.out.println("Enter the new player name: ");
                    String playerName = sc.next();
                    System.out.println("Enter the new player ID: ");
                    String playerID = sc.next();
                    System.out.println("Enter the new player age: ");
                    int playerAge = sc.nextInt();

                    SlotPlayer playerSwap = SlotPlayer.createSlotPlayer(playerName, playerID, playerAge);

                    if (playerList.updateSlotPlayerInformation(player, playerSwap)) {

                        System.out.println("Player information: " + playerName +
                                " -> " + playerID +
                                " -> " + playerAge);
                    }
                    else {

                        System.out.println("The players couldn't be swapped.");
                    }
                    break;

                default:

                    System.out.println("The operation was invalid.");
                    break;
            }
        } while(!quit);
    }

    /**
     * Adds a perk to a SlotPlayer depending on their current balance.
     */
    private static void addPlayerPerk() {

        System.out.println("Enter the player ID: ");
        String ID = sc.nextLine();

        SlotPlayer player = playerList.playerDirectory(ID);

        if (player == null) {

            System.out.println("The player was not found in the list.");
            return ;
        }

        if (playerList.addPlayerPerks(player)) {

            System.out.println("The player perks were assigned successfully.");
        }
        else {

            System.out.println("The perk could not be assigned.");
        }
    }

    /**
     * Adds a multiplier ot the amount of tokens earned by the SlotPlayer.
     * @param tokensEarned
     * @return token amount
     */
    private static int multiplier(int tokensEarned) {

        int multiplier = (int) (Math.random() * 5) + 1;

        return tokensEarned * multiplier;
    }

    /**
     * Query the playerList and search for a SlotPlayer using a player ID.
     */
    private static void searchByPlayerID() {

        System.out.println("Enter the player ID: ");
        String ID = sc.nextLine();

        SlotPlayer player = playerList.playerDirectory(ID);

        if (player == null) {

            System.out.println("The player was not found in the list.");
            return ;
        }

        System.out.println("The player name: " + player.getName() +
                "\nThe player age: " + player.getAge() +
                "\nThe player balance: " + player.getBalance());
    }

    /**
     * Validating the email depending on whether or not the InternetAddress is
     * considered valid.
     */
    private static void validateEmail() {

        System.out.println("Enter your email address: ");
        String emailAddress = sc.nextLine();

        if (emailList.isValidEmailAddress(emailAddress)) {

            System.out.println("The email " + emailAddress + " has been validated.");
        }
        else {

            System.out.println("The email " + emailAddress + " could not be validated.");
        }
    }

    /**
     * Play slots 1 through 7 and check to determine whether or not the wheels line up
     * with 3 of a kind.
     */
    private static void playSlots() {

        String answer;
        int wheelOne, wheelTwo, wheelThree, tokensEarned;

        System.out.println("Enter the player ID: ");
        String ID = sc.nextLine();
        SlotPlayer player = playerList.playerDirectory(ID);

        do {

            player.tokens -= 1;
            wheelOne = (int) (Math.random() * 7) + 1;
            wheelTwo = (int) (Math.random() * 7) + 1;
            wheelThree = (int) (Math.random() * 7) + 1;

            if (wheelOne == 1 && wheelTwo == 1) {

                tokensEarned = 1;
                player.tokens += tokensEarned;
                System.out.println("You have earned " + tokensEarned + " with a grand total of " + player.getTokensAmount() + " tokens.");
            }
            else if (wheelOne == 2 && wheelTwo == 2 && wheelThree == 2) {

                tokensEarned = 2;
                player.tokens += tokensEarned;
                System.out.println("You have earned " + tokensEarned + " with a grand total of " + player.getTokensAmount() + " tokens.");
            }
            else if (wheelOne == 3 && wheelTwo == 3) {

                tokensEarned = 3;
                player.tokens += tokensEarned;
                System.out.println("You have earned " + tokensEarned + " with a grand total of " + player.getTokensAmount() + " tokens.");
            }
            else if (wheelOne == 4 && wheelTwo == 4) {

                tokensEarned = 4;
                player.tokens += tokensEarned;
                System.out.println("You have earned " + tokensEarned + " with a grand total of " + player.getTokensAmount() + " tokens.");
            }
            else if (wheelOne == 5 && wheelTwo == 5) {

                tokensEarned = 5;
                player.tokens += tokensEarned;
                System.out.println("You have earned " + tokensEarned + " with a grand total of " + player.getTokensAmount() + " tokens.");
            }
            else if (wheelOne == 6 && wheelTwo == 6) {

                tokensEarned = 6;
                player.tokens += tokensEarned;
                System.out.println("You have earned " + tokensEarned + " with a grand total of " + player.getTokensAmount() + " tokens.");
            }
            else if (wheelOne == 7 && wheelTwo == 7 && wheelThree == 7) {

                tokensEarned = 7;
                System.out.println("You have earned " + multiplier(tokensEarned) + " after the multiplier!");
                int multiplierEarned = multiplier(tokensEarned);
                player.tokens += multiplierEarned;
                System.out.println("You have a total of " + player.getTokensAmount() + ".");
            }
            else {

                System.out.println("You lose! You now have " + player.getTokensAmount() + ".");
            }

            System.out.println("Would you like to spin again? Y/N");
            answer = sc.next();
            sc.nextLine();

        } while (answer.equalsIgnoreCase("Y"));
    }

    /**
     * Prints the update menu when the user decides to update player information.
     */
    private static void printUpdateMenu() {

        System.out.println("Make a selection to update player information: " +
                "\n0 - Exit the tool" +
                "\n1 - Update player name" +
                "\n2 - Update player ID" +
                "\n3 - Update player age" +
                "\n4 - Update all information");
    }

    /**
     * Prints the list of SlotPlayers within the playerList.
     */
    private static void printPlayers() {

        playerList.printPlayers();
    }

    private static void welcomePlayer() {

        System.out.println("Welcome to the Slot Machine Game!");
    }

    /**
     * Prints the available options menu to the user.
     */
    private static void printMenu() {

        System.out.println("0 - Exit the program" +
                "\n1 - Add a new player" +
                "\n2 - Remove a player" +
                "\n3 - Update player information" +
                "\n4 - Add tokens" +
                "\n5 - Add random tokens" +
                "\n6 - Add balance" +
                "\n7 - Play the slots" +
                "\n8 - Print the list of players" +
                "\n9 - Search player by ID" +
                "\n10 - Add player perks" +
                "\n11 - Remove tokens" +
                "\n12 - Remove random tokens" +
                "\n13 - Update balance after purchase" +
                "\n14 - Remove a balance" +
                "\n15 - Bonus tokens" +
                "\n16 - Display the menu");
    }
}

