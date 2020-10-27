abstract public class DuoPlayer {
    String name;
    DuoPlayer(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    abstract public gameState move(DuoPlay duoPlay);
}
