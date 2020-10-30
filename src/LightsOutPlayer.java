import java.util.Scanner;

/**
 * A subclass of DuoPlayer.
 * Represents a player of lights out
 *
 * @author Polina Ibragimova
 * @version 26.10.20
 */
public class LightsOutPlayer extends DuoPlayer{


    /**
     * Sets the instance variable name equal to the argument
     * passed into the constructor
     *
     * @param name the player's name
     */
    LightsOutPlayer(String name) {
        super(name);
    }

    /**
     * Presses one tile on the board and checks if the move ended in
     * a victory or the game is still in progress
     *
     * @param duoPlay the game that is being played by the 2 players
     * @return the state of the game after a move
     */
    @Override
    public gameState move(DuoPlay duoPlay, DuoPlayer duoPlayer) {
        int row, column;
        System.out.println(duoPlay.toString());
        // Prompting user input (the location to be pressed)
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the row to be pressed: ");
        row = scanner.nextInt();
        System.out.println("Please enter the column to be pressed: ");
        column = scanner.nextInt();
        // Pressing the tile at a given location and changing the state
        // of it and the tiles surrounding it
        ((LightsOut)duoPlay).press(row, column);
        // Checking if the move ended in all lights being dark and declaring
        // a victory if that is the case
        if(((LightsOut)duoPlay).isDark()){
            return gameState.WON;
        }
        // Continue the game if there is at least one light
        else{
            return gameState.IN_PROGRESS;
        }
    }

}
