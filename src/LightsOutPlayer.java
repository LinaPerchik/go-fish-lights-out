import java.util.Scanner;

public class LightsOutPlayer extends DuoPlayer{


    LightsOutPlayer(String name) {
        super(name);
    }

    @Override
    public gameState move(DuoPlay duoPlay) {
        int row, column;
        System.out.println(duoPlay.toString());
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the row to be pressed: ");
        row = scanner.nextInt();
        System.out.println("Please enter the column to be pressed: ");
        column = scanner.nextInt();
        ((LightsOut)duoPlay).press(row, column);
        if(((LightsOut) duoPlay).isDark()){
            return gameState.WON;
        }
        else{
            return gameState.IN_PROGRESS;
        }
    }

}
