import game.ConsoleGame;

public class Application {

    public static void main(String[] args) {
        ConsoleGame game = new ConsoleGame();
        game.start(game);
    }
}
