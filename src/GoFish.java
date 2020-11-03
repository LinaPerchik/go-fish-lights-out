import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
public class GoFish extends DuoPlay{
    // Instance variables
    // A map of numerical values of cards and their String representations
    Map<Integer, String> cardValue = new HashMap<>();
    // The main deck
    ArrayList<Integer> cardDeck = new ArrayList<>();
    // Player1's hand
    ArrayList<Integer> deck1 = new ArrayList<>();
    // Player2's hand
    ArrayList<Integer> deck2 = new ArrayList<>();
    // The counters for the number of books for each player
    int bookCounter1 = 0;
    int bookCounter2 = 0;
    /**
     * Assigns the players passed through the constructor to 2
     * instance variables
     * Fills Map cardValue
     * Gives one card from the cardDeck to each player
     *
     * @param player1 first player of the game
     * @param player2 second player of the game
     */
    GoFish(DuoPlayer player1, DuoPlayer player2) {
        super(player1, player2);
        this.fillCardValue();
        this.randomize();
        getCard(player1);
        getCard(player2);
    }

    /**
     * Fills cardValue with pairs of numerical and String
     * representations of each card
     */
    public void fillCardValue(){
        // String representation of a card
        StringBuilder cardName = new StringBuilder();
        // Numerical representation of a card
        int counter = 0;
        for(int i = 0; i < 52; i++){
            // Every first card
            if((i+4) % 4 == 0){
                cardName.append("Clubs");
                counter++;
            }
            // Every second card
            else if((i+3) % 4 == 0){
                cardName.append("Diamonds");
            }
            // Every third card
            else if((i+2) % 4 == 0){
                cardName.append("Hearts");
            }
            // Every fourth card
            else{
                cardName.append("Spades");
            }
            // Cards with special names
            if(counter == 1){
                cardName.append(" - Ace");
            }
            else if(counter == 11){
                cardName.append(" - Jack");
            }
            else if(counter == 12){
                cardName.append(" - Queen");
            }
            else if(counter == 13){
                cardName.append(" - King");
            }
            // Numbered cards
            else{
                cardName.append(" - ").append(counter);
            }
            // Adding the pair into the map
            cardValue.put(i, cardName.toString());
            cardName = new StringBuilder();
        }
    }

    /**
     * Shuffles the cards
     */
    public void randomize(){
        // Fills the deck
        for(int i = 0; i < 52; i ++){
            cardDeck.add(i);
        }
        // Shuffles the deck
        Collections.shuffle(cardDeck);
    }

    /**
     * Give one card from the top of the cardDeck to a given player
     *
     * @param player who gets the card
     */
    public void getCard(DuoPlayer player){
        System.out.println("You pulled " + cardValue.get(cardDeck.get(cardDeck.size()-1)) + " from the deck");
        // Adding the card from the top of the cardDeck to the player's deck
        if(player == player1){
            deck1.add(cardDeck.get(cardDeck.size()-1));
        }
        else{
            deck2.add(cardDeck.get(cardDeck.size()-1));
        }
        // Removing the card after the player gets it
        cardDeck.remove(cardDeck.size()-1);

    }

    /**
     * The player asks another player for a card and steals it
     * if the other player has it. Returns true if the theft
     * was a success
     *
     * @param player the player trying to guess the card
     * @param card the rank of card the player is searching for(1 - 13)
     * @return true if the other player gets the card
     */
    public boolean askForCard(DuoPlayer player,Integer card){
        // The success of the theft
        boolean success = false;
        // The list of cards to remove from the other player's deck
        ArrayList<Integer> toRemove = new ArrayList<>();
        // The attacking player's deck
        ArrayList<Integer> tempDeck;
        // The attacked player's deck
        ArrayList<Integer> enemyDeck;
        // Setting the references to the players' decks
        if(player == player1){
            tempDeck = deck1;
            enemyDeck = deck2;
        }
        else{
            tempDeck = deck2;
            enemyDeck = deck1;
        }
        // Iterating through the attacked deck
        for(int i = 0; i <= enemyDeck.size() - 1; i++){
            // Checking for all the cards of the requested rank
            // Example: if the rank equals 1  this code would get cards
            // 0, 1, 2, and 3 which are all the cards with rank 1
            if((card * 4 - 1) == enemyDeck.get(i)
                    ||(card * 4 - 2) == enemyDeck.get(i)
                    ||(card * 4 - 3) == enemyDeck.get(i)
                    ||(card * 4 - 4) == enemyDeck.get(i)){
                success = true;
                // Adding the card to the attacking player's deck
                tempDeck.add(enemyDeck.get(i));
                // Adding the card to the array of soon to be removed cards
                toRemove.add(enemyDeck.get(i));
            }
        }
        // Removing the cards that are mentioned in toRemove from the attacked deck
        for(Integer integer : toRemove){
            enemyDeck.remove(integer);
        }

        if(success){
            System.out.println("Yay! You guessed the card! You get another turn!");
        }
        else {
            System.out.println("Tough luck! You didn't guess correctly!");
        }
        return success;
    }

    /**
     * Checks if there is a book(4 cards of the same rank)
     * If there is a book the method deletes all the cards that
     * are a part of it and increases the bookCounter for the player
     * who had the book
     *
     * @param player the player whose deck is being checked for a book
     * @return true if there is at least one book
     */
    public boolean checkForNewBook(DuoPlayer player){
        // The list of cards to remove from the other player's deck
        ArrayList<Integer> toRemove = new ArrayList<>();
        // The reference to the player's deck
        ArrayList<Integer> tempDeck;
        // Counter of cards of the same rank
        int sameCard;
        // Success of finding the book
        boolean success = false;
        String rank;
        // Setting the reference to the player's deck
        if(player == player1){
            tempDeck = deck1;
        }
        else{
            tempDeck = deck2;
        }
        //Iterating through every rank
        for (int i = 1; i <= 13; i++) {
            // String representation of a rank
            if(i == 1){
                rank = "Ace";
            }
            else if( i < 11){
                rank = String.valueOf(i);
            }
            else if(i == 11){
                rank = "Jack";
            }
            else if(i == 12){
                rank = "Queen";
            }
            else{
                rank = "King";
            }
            // Resetting for each rank do nothing is removed if there is no books
            toRemove.clear();
            sameCard = 0;
            // Iterating through every card in the player's hand
            for (int j = 0; j <= tempDeck.size() - 1; j++) {
                // Checking for all the cards of rank i
                if ((i * 4 - 1) == tempDeck.get(j)
                        || (i * 4 - 2) == tempDeck.get(j)
                        || (i * 4 - 3) == tempDeck.get(j)
                        || (i * 4 - 4) == tempDeck.get(j)) {
                    // Incrementing if card of rank i is found
                    sameCard++;
                    // Adding the card to be removed
                    toRemove.add(tempDeck.get(j));
                    // If there are 4 cards of the same rank
                    if (sameCard == 4) {
                        System.out.println("You got a book of " + rank);
                        // Incrementing the bookCounter
                        if (player == player1) {
                            bookCounter1++;
                        } else {
                            bookCounter2++;
                        }
                        success = true;
                        // Removing the 4 cards that form the book from the player's deck
                        for (Integer integer : toRemove) {
                            tempDeck.remove(integer);
                        }

                    }
                }
            }

        }
        if(success){
            System.out.println("Yay! You got a new book! You get another turn!");
        }
        else {
            System.out.println("Tough luck! No new books from you! It's another player's turn");
        }
        return success;
    }

    /**
     * Returns the numerical value of a rank (1-13)
     * @param rank rank to be converted into a number
     * @return numerical value of a rank
     */
    public int rankToNum(String rank){
        // Right formatting to search in the map
        String rankC = "Clubs - " + rank;
        String rankD = "Diamonds - " + rank;
        String rankH = "Hearts - " + rank;
        String rankS = "Spades - " + rank;
        // Numerical representation of a card
        int cardNum = 0;
        // Numerical representation of a rank
        int rankNum;
        // Going through every element of cardValue
        for (Map.Entry<Integer, String> mapElement : cardValue.entrySet()) {
            // Numerical representation of a card (0 - 51)
            int key = mapElement.getKey();
            // String representation of a card
            String value = mapElement.getValue();
            // If any String representation of the rank equals a String representation of a card
            if(value.equals(rankC) || value.equals(rankD) || value.equals(rankH) || value.equals(rankS)){
                cardNum = key;
                break;
            }
        }
        // Learning the rank of the card
        rankNum = (cardNum / 4) + 1;
        return rankNum;
    }

    /**
     * Returns string representation of the class in a form of number
     * of books that each player has and their hand
     *
     * @return string representation of the class
     */
    public String toString(){
        //String representation
        StringBuilder stringRep = new StringBuilder();
        // Reference to the player's deck
        ArrayList<Integer> tempDeck;
        // Going through every player
        for(int i = 0; i < 2; i++) {
            // Player 1
            if(i == 0) {
                // Number of books
                System.out.println(player1.name + " has " + bookCounter1 + " books");
                stringRep.append(player1.name).append("'s Deck: \n");
                // Reference to the player1's deck
                tempDeck = deck1;
            }
            // Player 2
            else{
                // Number of books
                System.out.println(player2.name + " has " + bookCounter2 + " books");
                stringRep.append(player2.name).append("'s Deck: \n");
                // Reference to the player2's deck
                tempDeck = deck2;
            }
            // Concatenating every card
            for(int j = 0; j <= tempDeck.size() - 1; j++){
                stringRep.append(cardValue.get(tempDeck.get(j))).append("\n");
            }
            stringRep.append(" \n");
        }
        return stringRep.toString();
    }

    /**
     * The player pulls one card from the deck and checks if
     * it was the card he or she asked for
     *
     * @param card the card the player is looking for
     * @param player the player pulling the card from the deck
     * @return true if the card the player pulled out was
     *         the card the player asked for
     */
    public boolean goFishInDeck(int card, DuoPlayer player){
        // The success of pulling out the card the user asked for
        boolean success = false;
        // Reference to the player's deck
        ArrayList<Integer> tempDeck;
        if(player == player1) {
            tempDeck = deck1;
        }
        else {
            tempDeck = deck2;
        }
        // Getting one card from the top of the deck
        getCard(player);
        // If the card that the player pulled out was the same he or she requested
        if((card * 4 - 1) == (tempDeck.get(tempDeck.size()-1))
                ||(card * 4 - 2) == (tempDeck.get(tempDeck.size()-1))
                ||(card * 4 - 3) == (tempDeck.get(tempDeck.size()-1))
                ||(card * 4 - 4) == (tempDeck.get(tempDeck.size()-1)))
        {
            success = true;
        }
        if(success){
            System.out.println("Yay! You successfully fished out the right card from the deck!" +
                    " You get another turn");
        }
        else {
            System.out.println("Tough luck! You didn't fish out the right card!");
        }
        return success;
    }

}
