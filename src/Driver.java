import java.util.Scanner;

/**
 * A class used to test the 2 games. Lights out and go fish.
 *
 * @author Polina Ibragimova
 * @version 10/26/2020
 */
public class Driver {
    public static void main(String[] args){
        // Declare the variables
        Scanner scanner = new Scanner(System.in);
        String playerName1, playerName2;
        int boardSize;

        System.out.println("Lights out!");
        // Initializing the variables with the user input
        System.out.println("Please enter the name of player #1: ");
        playerName1 = scanner.next();
        System.out.println("Please enter the name of player #2: ");
        playerName2 = scanner.next();
        System.out.println("Please enter the size of the board: ");
        boardSize = scanner.nextInt();
        // Creating objects representing the 2 players
        LightsOutPlayer player1 = new LightsOutPlayer(playerName1);
        LightsOutPlayer player2 = new LightsOutPlayer(playerName2);
        // Creating an object representing the game of lights out
        LightsOut lightsOut = new LightsOut(player1, player2, boardSize);
        // Turning random lights on and off
        lightsOut.randomize();
        // Starting the game
        lightsOut.play();
    }
}
