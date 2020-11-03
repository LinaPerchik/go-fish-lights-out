import java.util.Scanner;

/**
 * A subclass of DuoPlayer.
 * Represents a player of go fish
 *
 * @author Polina Ibragimova
 * @version 26.10.20
 */
public class GoFishPlayer extends DuoPlayer {
    /**
     * Sets the instance variable name equal to the argument
     * passed into the constructor
     *
     * @param name the player's name
     */
    GoFishPlayer(String name) {
        super(name);
    }

    /**
     *
     * @param duoPlay the game that is being played by the 2 players
     * @param duoPlayer the player making a move
     * @return the state of the game after a move
     */
    @Override
    public gameState move(DuoPlay duoPlay, DuoPlayer duoPlayer) {
        String card;
        int cardInt;
        Scanner scanner = new Scanner(System.in);
        // The player's turn that happens no matter what
        do{
                // If the opponent's deck is empty
                if((((duoPlayer == ((GoFish)duoPlay).player1) && (((GoFish)duoPlay).deck2.isEmpty())) ||
                        ((duoPlayer == ((GoFish)duoPlay).player2) && (((GoFish)duoPlay).deck1.isEmpty())))) {
                    System.out.println("Your opponent has no more cards");
                    // If the player's deck is not empty check for new book and end the turn
                    if(((duoPlayer == ((GoFish)duoPlay).player1) && !(((GoFish)duoPlay).deck1.isEmpty())) ||
                            ((duoPlayer == ((GoFish)duoPlay).player2) && !(((GoFish)duoPlay).deck2.isEmpty()))) {
                        ((GoFish) duoPlay).checkForNewBook(duoPlayer);
                    }
                    break ;
                }
                // If the opponent's deck is not empty
                else {
                    // Print the player, the hands and the book numbers
                    System.out.println(duoPlayer.getName() + "'s turn!");
                    System.out.println(duoPlay.toString());
                    System.out.println("Please enter the rank of the card you are asking for " +
                            "(Starting with a capital for words and use numbers for numbers): ");
                    card = scanner.next();
                    // Turning String representation of the user input into an int
                    cardInt = ((GoFish)duoPlay).rankToNum(card);
                }
        }
        // The player gets another turn if one of the conditions bellow are true
        // 1. The opponent's deck is not empty and the player successfully
        // took the card from their opponent
        // OR 2. The main deck is not empty and the player successfully
        // pulled out the card that he or she asked for
        // OR 3. The player's deck is not empty and the player successfully
        // acquired a new book (set of 4 cards of the same rank)
        while (((((duoPlayer == ((GoFish)duoPlay).player1) && !(((GoFish)duoPlay).deck2.isEmpty())) ||
                ((duoPlayer == ((GoFish)duoPlay).player2) && !(((GoFish)duoPlay).deck1.isEmpty()))) &&
                (((GoFish)duoPlay).askForCard(duoPlayer,cardInt)))
                ||
                (!(((GoFish)duoPlay).cardDeck.isEmpty()) &&
                ((GoFish)duoPlay).goFishInDeck(cardInt,duoPlayer))
                ||
                ((((duoPlayer == ((GoFish)duoPlay).player1) && !(((GoFish)duoPlay).deck1.isEmpty())) ||
                ((duoPlayer == ((GoFish)duoPlay).player2) && !(((GoFish)duoPlay).deck2.isEmpty()))) &&
                ((GoFish)duoPlay).checkForNewBook(duoPlayer))

        );
        // If someone has more than half books (BY THE END OF THEIR TURN)
        if ((((GoFish)duoPlay).bookCounter1) > 6 || (((GoFish)duoPlay).bookCounter2) > 6) {
            return gameState.WON;
        }
        // Next person's turn
        else {
            return gameState.IN_PROGRESS;
        }
    }

}
