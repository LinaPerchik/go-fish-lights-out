abstract public class DuoPlay {

    DuoPlayer player1, player2;
    gameState gs;
    DuoPlay(DuoPlayer player1, DuoPlayer player2) {
        this.player1 = player1;
        this.player2 = player2;
    }
    public void play(){
        gs = gameState.IN_PROGRESS;
        String winningQuote = player2.getName();
        while (gs == gameState.IN_PROGRESS){
            System.out.println(player1.getName() + "'s turn!");
            gs = player1.move(this);
            if(gs != gameState.IN_PROGRESS) {
                winningQuote = player1.getName();
                break;
            }
            else{
                System.out.println(player2.getName() + "'s turn!");
                gs = player2.move(this);
            }
        }
        System.out.println(winningQuote + " won!");
    }

}
