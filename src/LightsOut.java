import java.util.Random;

public class LightsOut extends DuoPlay{
    int[][] board;
    LightsOut(DuoPlayer player1, DuoPlayer player2, int boardSize) {
        super(player1, player2);
        board = new int[boardSize][boardSize];
    }
    public void randomize(){
        Random random = new Random();
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++) {
                board[i][j] = random.nextInt(2);
            }
        }
    }
    public void press(int row, int column){
        board[row][column] = changeState(board[row][column]);
        if(row != 0){
            board[row - 1][column] = changeState(board[row - 1][column]);
        }
        if(row != board.length - 1){
            board[row + 1][column] = changeState(board[row + 1][column]);
        }
        if(column != 0){
            board[row][column - 1] = changeState(board[row][column - 1]);
        }
        if(column != board.length - 1){
            board[row][column + 1] = changeState(board[row][column + 1]);
        }
    }
    public static int changeState(int value){
        if(value == 1){
            return 0;
        }
        else{
            return 1;
        }
    }
    public boolean isDark(){
        boolean isDark = true;
        for (int[] ints : board) {
            for (int j = 0; j < board.length; j++) {
                if (ints[j] == 1) {
                    isDark = false;
                    break;
                }
            }
        }
        return isDark;
    }


    public String toString(){
        StringBuilder stringRep = new StringBuilder();
        for(int i = -1; i < board.length; i++){
            if(i != -1){
                stringRep.append(String.format("%2d", i));
            }
            else{
                stringRep.append(String.format("%2s", " "));
            }
            for(int j = 0; j < board.length; j++){
                if(i == -1){
                    stringRep.append(String.format("%2d", j));
                }
                else{
                    if (board[i][j] == 1) {
                        stringRep.append(String.format("%2s", "x"));
                    } else {
                        stringRep.append(String.format("%2s", "_"));
                    }
                }
            }
            stringRep.append("\n");
        }
        return stringRep.toString();
    }
}
