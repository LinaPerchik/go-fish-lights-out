/**
 * Abstract class representing a player of a game
 *
 * @author Polina Ibragimova
 * @version 26.10.20
 */
abstract public class DuoPlayer {
    // Instance variables
    String name;

    /**
     * Sets the instance variable name equal to the argument
     * passed into the constructor
     *
     * @param name the player's name
     */
    DuoPlayer(String name){
        this.name = name;
    }

    /**
     * Returns the name
     *
     * @return the name instance variable
     */
    public String getName() {
        return name;
    }

    /**
     * Represents a move that a player can make
     *
     * @param duoPlay the game that is being played by the 2 players
     * @param duoPlayer the player making a move
     * @return enum showing whether tha game is in progress, has
     *         been won, or ended in a draw(IN_PROGRESS, WON, DRAW.
     */
    abstract public gameState move(DuoPlay duoPlay, DuoPlayer duoPlayer);
}
