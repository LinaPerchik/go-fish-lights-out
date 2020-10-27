import java.util.Scanner;

public class Driver {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String playerName1, playerName2;
        int boardSize;
        System.out.println("Lights out!");
        System.out.println("Please enter the name of player #1: ");
        playerName1 = scanner.next();
        System.out.println("Please enter the name of player #2: ");
        playerName2 = scanner.next();
        System.out.println("Please enter the size of the board: ");
        boardSize = scanner.nextInt();
        LightsOutPlayer player1 = new LightsOutPlayer(playerName1);
        LightsOutPlayer player2 = new LightsOutPlayer(playerName2);
        LightsOut lightsOut = new LightsOut(player1, player2, boardSize);
        lightsOut.randomize();
        lightsOut.play();
    }
}
