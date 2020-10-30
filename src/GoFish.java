import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
public class GoFish extends DuoPlay{
    Map<Integer, String> cardValue = new HashMap<>();
    ArrayList<Integer> cardDeck = new ArrayList<>();
    ArrayList<Integer> deck1 = new ArrayList<>();
    ArrayList<Integer> deck2 = new ArrayList<>();
    int bookCounter1 = 0;
    int bookCounter2 = 0;
    /**
     * Assigns the players passed through the constructor to 2
     * instance variables
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
    public void fillCardValue(){
        StringBuilder cardName = new StringBuilder();
        int counter = 0;
        for(int i = 0; i < 52; i++){
            if((i+4) % 4 == 0){
                cardName.append("Clubs");
                counter++;
            }
            else if((i+3) % 4 == 0){
                cardName.append("Diamonds");
            }
            else if((i+2) % 4 == 0){
                cardName.append("Hearts");
            }
            else{
                cardName.append("Spades");
            }
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
            else{
                cardName.append(" - ").append(counter);
            }
            cardValue.put(i, cardName.toString());
            cardName = new StringBuilder();
        }
    }

    public void randomize(){
        for(int i = 0; i < 52; i ++){
            cardDeck.add(i);
        }
        Collections.shuffle(cardDeck);
    }

    public void getCard(DuoPlayer player){
        System.out.println("You pulled " + cardValue.get(cardDeck.get(cardDeck.size()-1)) + " from the deck");
        if(player == player1){
            deck1.add(cardDeck.get(cardDeck.size()-1));
        }
        else{
            deck2.add(cardDeck.get(cardDeck.size()-1));
        }
        cardDeck.remove(cardDeck.size()-1);

    }
    public boolean askForCard(DuoPlayer player,Integer card){
        boolean success = false;
        ArrayList<Integer> toRemove = new ArrayList<>();
        ArrayList<Integer> tempDeck;
        ArrayList<Integer> enemyDeck;
        if(player == player1){
            tempDeck = deck1;
            enemyDeck = deck2;
        }
        else{
            tempDeck = deck2;
            enemyDeck = deck1;
        }
        for(int i = 0; i <= enemyDeck.size() - 1; i++){
            if((card * 4 - 1) == enemyDeck.get(i)
                    ||(card * 4 - 2) == enemyDeck.get(i)
                    ||(card * 4 - 3) == enemyDeck.get(i)
                    ||(card * 4 - 4) == enemyDeck.get(i)){
                success = true;
                tempDeck.add(enemyDeck.get(i));
                toRemove.add(enemyDeck.get(i));
            }
        }

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

    public boolean checkForNewBook(DuoPlayer player){
        ArrayList<Integer> toRemove= new ArrayList<>();
        ArrayList<Integer> tempDeck;
        int sameCard;
        boolean success = false;
        String rank;
        if(player == player1){
            tempDeck = deck1;
        }
        else{
            tempDeck = deck2;
        }

        for (int i = 1; i <= 13; i++) {
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
            toRemove.clear();
            sameCard = 0;
            for (int j = 0; j <= tempDeck.size() - 1; j++) {
                if ((i * 4 - 1) == tempDeck.get(j)
                        || (i * 4 - 2) == tempDeck.get(j)
                        || (i * 4 - 3) == tempDeck.get(j)
                        || (i * 4 - 4) == tempDeck.get(j)) {
                    sameCard++;
                    toRemove.add(tempDeck.get(j));
                    if (sameCard == 4) {
                        System.out.println("You got a book of " + rank);
                        if (player == player1) {
                            bookCounter1++;
                        } else {
                            bookCounter2++;
                        }
                        success = true;
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

    public int rankToNum(String rank){
        String rankC = "Clubs - " + rank;
        String rankD = "Diamonds - " + rank;
        String rankH = "Hearts - " + rank;
        String rankS = "Spades - " + rank;
        int cardNum = 0;
        int rankNum;
        for (Map.Entry<Integer, String> mapElement : cardValue.entrySet()) {
            int key = mapElement.getKey();
            // Finding the value
            String value = mapElement.getValue();
            if(value.equals(rankC) || value.equals(rankD) || value.equals(rankH) || value.equals(rankS)){
                cardNum = key;
                break;
            }
        }
        rankNum = (cardNum / 4) + 1;
        return rankNum;
    }

    public String toString(){
        StringBuilder stringRep = new StringBuilder();
        ArrayList<Integer> tempDeck;
        for(int i = 0; i < 2; i++) {
            if(i == 0) {
                System.out.println(player1.name + " has " + bookCounter1 + " books");
                stringRep.append(player1.name).append("'s Deck: \n");
                tempDeck = deck1;
            }
            else{
                System.out.println(player2.name + " has " + bookCounter2 + " books");
                stringRep.append(player2.name).append("'s Deck: \n");
                tempDeck = deck2;
            }
            for(int j = 0; j <= tempDeck.size() - 1; j++){
                stringRep.append(cardValue.get(tempDeck.get(j))).append("\n");
            }
            stringRep.append(" \n");
        }
        return stringRep.toString();
    }

    public boolean goFishInDeck(int card, DuoPlayer player){
        boolean success = false;
        ArrayList<Integer> tempDeck;
        if(player == player1) {
            tempDeck = deck1;
        }
        else {
            tempDeck = deck2;
        }
        getCard(player);
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
