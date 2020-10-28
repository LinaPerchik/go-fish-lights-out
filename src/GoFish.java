
import java.lang.reflect.Array;
import java.util.*;
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
        String cardName = "";
        int counter = 0;
        for(int i = 0; i < 52; i++){
            if((i+4) % 4 == 0){
                cardName += "Clubs";
                counter++;
            }
            else if((i+3) % 4 == 0){
                cardName += "Diamonds";
            }
            else if((i+2) % 4 == 0){
                cardName += "Hearts";
            }
            else{
                cardName += "Spades";
            }
            if(counter == 1){
                cardName += " - Ace";
            }
            else if(counter == 11){
                cardName += " - Jack";
            }
            else if(counter == 12){
                cardName += " - Queen";
            }
            else if(counter == 13){
                cardName += " - King";
            }
            else{
                cardName += (" - " + counter);
            }
            cardValue.put(i,cardName);
            cardName = "";
        }
    }

    public void randomize(){
        for(int i = 0; i < 52; i ++){
            cardDeck.add(i);
        }
        Collections.shuffle(cardDeck);
    }

    public void getCard(DuoPlayer player){
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
        ArrayList<Integer> toRemove= new ArrayList<>();
        if(player == player1){
            for(int i = 0; i <= deck2.size(); i++){
                if(deck2.get(i) % card >= 0 && deck2.get(i) % card <=3){
                    success = true;
                    deck1.add(deck2.get(i));
                    toRemove.add(deck2.get(i));
                }
            }
            for(Integer integer : toRemove){
                deck1.remove(integer);
            }
        }
        else{
            for(int i = 0; i <= deck1.size(); i++){
                if(deck1.get(i) % card >= 0 && deck1.get(i) % card <=3){
                    success = true;
                    deck2.add(deck1.get(i));
                    toRemove.add(deck1.get(i));
                }
            }
            for(Integer integer : toRemove){
                deck1.remove(integer);
            }
        }
        return success;
    }

    public boolean checkForNewBook(DuoPlayer player){
        ArrayList<Integer> toRemove= new ArrayList<>();
        ArrayList<Integer> tempDeck;
        int sameCard = 0;
        boolean success = false;
        if(player == player1){
            tempDeck = deck1;
        }
        else{
            tempDeck = deck2;
        }

        for (int i = 1; i <= 13; i++) {
            for(int j = 0; j <= tempDeck.size(); j++){
                if(tempDeck.get(j) % i >= 0 && tempDeck.get(j) % i <=3){
                    sameCard++;
                    toRemove.add(j);
                    if(sameCard == 4) {
                        if (player == player1) {
                            bookCounter1++;
                        } else {
                            bookCounter2++;
                        }
                        success = true;
                        sameCard = 0;
                    }
                    toRemove.add(tempDeck.get(j));
                }
            }
            for (Integer integer : toRemove) {
                tempDeck.remove(integer);
            }
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
        String stringRep = "";
        ArrayList<Integer> tempDeck;
        for(int i = 0; i < 2; i++) {
            if(i == 0) {
                stringRep += player1.name + "'s Deck: \n";
                tempDeck = deck1;
            }
            else{
                stringRep += player2.name + "'s Deck: \n";
                tempDeck = deck2;
            }
            for(int j = 0; j <= tempDeck.size(); j++){
                stringRep += (cardValue.get(tempDeck.get(j)) + "\n");
            }
            stringRep += " \n";
        }
        return stringRep;
    }
}
