import java.util.Scanner;

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

    @Override
    public gameState move(DuoPlay duoPlay, DuoPlayer duoPlayer) {
        String card;
        int cardInt;
        Scanner scanner = new Scanner(System.in);
        do{
                if((((duoPlayer == ((GoFish)duoPlay).player1) && (((GoFish)duoPlay).deck2.isEmpty())) ||
                        ((duoPlayer == ((GoFish)duoPlay).player2) && (((GoFish)duoPlay).deck1.isEmpty())))) {
                    System.out.println("Your opponent has no more cards");

                    if(((duoPlayer == ((GoFish)duoPlay).player1) && !(((GoFish)duoPlay).deck2.isEmpty())) ||
                            ((duoPlayer == ((GoFish)duoPlay).player2) && !(((GoFish)duoPlay).deck1.isEmpty())))
                            ((GoFish)duoPlay).checkForNewBook(duoPlayer);
                    break ;
                }
                else {
                    System.out.println(duoPlay.toString());
                    System.out.println("Please enter the rank of the card you are asking for " +
                            "(Starting with a capital for words and use numbers for numbers): ");
                    card = scanner.next();
                    cardInt = ((GoFish) duoPlay).rankToNum(card);
                }
        }
        while (((((duoPlayer == ((GoFish)duoPlay).player1) && !(((GoFish)duoPlay).deck2.isEmpty())) ||
                ((duoPlayer == ((GoFish)duoPlay).player2) && !(((GoFish)duoPlay).deck1.isEmpty()))) &&
                (((GoFish)duoPlay).askForCard(duoPlayer,cardInt)))
                ||
                (!(((GoFish)duoPlay).cardDeck.isEmpty()) &&
                ((GoFish)duoPlay).goFishInDeck(cardInt,duoPlayer))
                ||
                ((((duoPlayer == ((GoFish)duoPlay).player1) && !(((GoFish)duoPlay).deck2.isEmpty())) ||
                ((duoPlayer == ((GoFish)duoPlay).player2) && !(((GoFish)duoPlay).deck1.isEmpty()))) &&
                ((GoFish)duoPlay).checkForNewBook(duoPlayer))

        );
        if ((((GoFish)duoPlay).bookCounter1) > 6 || (((GoFish)duoPlay).bookCounter2) > 6) {
            return gameState.WON;
        }
        else {
            return gameState.IN_PROGRESS;
        }
    }
    /*
    public static void goFishLoop(String card, Scanner scanner, DuoPlay duoPlay, DuoPlayer duoPlayer){
        System.out.println(duoPlay.toString());
        System.out.println("Please enter the rank of the card you are asking for " +
                "(Starting with a capital for words and use numbers for numbers): ");
        card = scanner.next();
        int cardInt = ((GoFish)duoPlay).rankToNum(card);
        if((((duoPlayer == ((GoFish)duoPlay).player1) && !(((GoFish)duoPlay).deck2.isEmpty())) ||
                ((duoPlayer == ((GoFish)duoPlay).player2) && !(((GoFish)duoPlay).deck1.isEmpty()))) &&
                (((GoFish)duoPlay).askForCard(duoPlayer,cardInt))){
              goFishLoop(card, scanner, duoPlay, duoPlayer);
        }
        else if(!(((GoFish)duoPlay).cardDeck.isEmpty()) &&
                ((GoFish)duoPlay).goFishInDeck(cardInt,duoPlayer)) {
            goFishLoop(card,scanner,duoPlay,duoPlayer);

        }
        else if((((duoPlayer == ((GoFish)duoPlay).player1) && !(((GoFish)duoPlay).deck2.isEmpty())) ||
                ((duoPlayer == ((GoFish)duoPlay).player2) && !(((GoFish)duoPlay).deck1.isEmpty()))) &&
                ((GoFish)duoPlay).checkForNewBook(duoPlayer)){
            goFishLoop(card,scanner,duoPlay,duoPlayer);
        }

    }
     */
}
