import java.util.Random;

/**
 * A subclass of DuoPlay.
 * Represents a game of lights out on a n by n board
 *
 * @author Polina Ibragimova
 * @version 26.10.20
 */
public class LightsOut extends DuoPlay{
    // Instance variable
    int[][] board;

    /**
     * @param player1 first player of the game
     * @param player2 second player of the game
     * @param boardSize the number of tiles in each row and column
     */
    LightsOut(DuoPlayer player1, DuoPlayer player2, int boardSize) {
        super(player1, player2);
        // Creating board with boardSize by boardSize tiles
        board = new int[boardSize][boardSize];
    }

    /**
     *  Randomly fills a given 2d array with 1s and 0s
     */
    public void randomize(){
        Random random = new Random();
        // Going through the array
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++) {
                // Felling the given tile with 0 or 1
                board[i][j] = random.nextInt(2);
            }
        }
    }

    /**
     * Changes the state of a tile and also the state of the tiles to the
     * north, south east and west if possible (the item is not on the border)
     *
     * @param row the number of rows in the array
     * @param column the number of columns in the array
     */
    public void press(int row, int column){
        // Changing the state of an item
        board[row][column] = changeState(board[row][column]);
        // Checking if there is an item to the north and changing its state if it exists
        if(row != 0){
            board[row - 1][column] = changeState(board[row - 1][column]);
        }
        // Checking if there is an item to the south and changing its state if it exists
        if(row != board.length - 1){
            board[row + 1][column] = changeState(board[row + 1][column]);
        }
        // Checking if there is an item to the west and changing its state if it exists
        if(column != 0){
            board[row][column - 1] = changeState(board[row][column - 1]);
        }
        // Checking if there is an item to the east and changing its state if it exists
        if(column != board.length - 1){
            board[row][column + 1] = changeState(board[row][column + 1]);
        }
    }

    /**
     * Changes 1 to 0 and any other int to 1
     * @param value int to be changed
     * @return changed int
     */
    public static int changeState(int value){
        if(value == 1){
            return 0;
        }
        else{
            return 1;
        }
    }

    /**
     *
     * Checks if all the items in the board are dark(equal 0)
     * @return true if every item equals 0
     */
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

    /**
     * A formatted String representation of the board
     *
     * @return instance variable board formatted in a way that every 0 is an "_"
     *         and every 1 is a "x" symbol. Includes numbers representing rows
     *         and columns
     */
    public String toString(){
        StringBuilder stringRep = new StringBuilder();
        // Going through every row starting with the row of column numbers(-1)
        for(int i = -1; i < board.length; i++){
            // Column numbers
            if(i != -1){
                stringRep.append(String.format("%2d", i));
            }
            // Space to align
            else{
                stringRep.append(String.format("%2s", " "));
            }
            // Going through every column
            for(int j = 0; j < board.length; j++){
                // If it is not the roe with column numbers add the row number
                if(i == -1){
                    stringRep.append(String.format("%2d", j));
                }
                // "X" symbol if the light is on (1) and "_" if the light is off (0)
                else{
                    if (board[i][j] == 1) {
                        stringRep.append(String.format("%2s", "x"));
                    } else {
                        stringRep.append(String.format("%2s", "_"));
                    }
                }
            }
            // New line at the end of the row
            stringRep.append("\n");
        }
        return stringRep.toString();
    }
}
