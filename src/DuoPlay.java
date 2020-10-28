/**
 * Abstract class representing a game with 2 players
 *
 * @author Polina Ibragimova
 * @version 26.10.20
 */
abstract public class DuoPlay {

    // Instance variables
    DuoPlayer player1, player2;
    // The state of the game (WON, IN_PROGRESS, DRAW)
    gameState gs;

    /**
     * Assigns the players passed through the constructor to 2
     * instance variables
     *
     * @param player1 first player of the game
     * @param player2 second player of the game
     */
    DuoPlay(DuoPlayer player1, DuoPlayer player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    /**
     * Represents a game where players 1 and 2 make a move one a time
     * starting with player 1
     */
    public void play(){
        // Setting the game state in progress
        gs = gameState.IN_PROGRESS;
        // Getting a name to put in the winning quote in case player 2 wins
        String winningQuote = player2.getName();
        // While there is no winner or no draw
        while (gs == gameState.IN_PROGRESS){
            // Player1's move
            System.out.println(player1.getName() + "'s turn!");
            gs = player1.move(this);
            // If he or she won or the draw happened
            if(gs != gameState.IN_PROGRESS) {
                // Changing the name for the winning quote
                winningQuote = player1.getName();
                // Exiting the loop to prevent player 2 from making a move
                break;
            }
            // If the game did not end
            else{
                // Player2's move
                System.out.println(player2.getName() + "'s turn!");
                gs = player2.move(this);
            }
        }
        // If someone won
        if(gs == gameState.WON) {
            System.out.println(winningQuote + " won!");
        }
        // If it was a draw
        else{
            System.out.println("It's a draw!");
        }
    }

}
