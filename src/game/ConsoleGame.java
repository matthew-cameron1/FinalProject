package game;


import level.Level;
import level.LevelLoader;
import level.Tile;
import level.TileType;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class ConsoleGame extends MazeGame {

    private String wallIndicator = "##";
    private String finishIndicator = "$$";
    private String startIndicator = "**";
    private Scanner scanner;

    /*
        Player movement icons ^ > < v

        Each player gets X amount of seconds to finish the level if they do not, they are removed from the game
        next player then begins

        Commands:

        <direction> <amount>

     */

    public String getWallIndicator() {
        return wallIndicator;
    }

    public String getFinishIndicator() {
        return finishIndicator;
    }

    public String getStartIndicator() {
        return startIndicator;
    }

    @Override
    public void display() {

        int lastY = getCurrentLevel().getTiles().get(0).getY();

        for (Tile tile : getCurrentLevel().getTiles()) {
            int y = tile.getY();
            String toPrint;
            TileType type = tile.getType();

            if (type == TileType.END) {
                toPrint = getFinishIndicator();
            } else if (type == TileType.START) {
                toPrint = getStartIndicator();
            } else if (type == TileType.WALL) {
                toPrint = getWallIndicator();
            } else {
                toPrint = "  ";
            }

            if (getCurrentlyPlaying() != null) {
                if (getCurrentlyPlaying().getX() == tile.getX() && getCurrentlyPlaying().getY() == tile.getY()) {
                    toPrint = getCurrentlyPlaying().getBoardIcon();
                }
            }

            if (y > lastY) {
                toPrint = "\n" + toPrint;
                lastY = y;
            }

            System.out.print(toPrint);
        }
    }

    @Override
    public void start() {
        LevelLoader loader = new LevelLoader(new File("level1.png"));
        List<Level> levelsLoaded = loader.load();

        addAll(levelsLoaded);
        Level current = levelsLoaded.get(0);

        setCurrentLevel(current);

        System.out.println("Welcome to MazeGame Console Edition! Enter number of players to begin: ");
        this.scanner = new Scanner(System.in);

        int players = scanner.nextInt();

        int startX = getCurrentLevel().getStart().getX();
        int startY = getCurrentLevel().getStart().getY();

        for (int i = 0; i < players; i++) {
            System.out.println("Okay player " + (i + 1) + " enter your user name:");
            String name = scanner.next();

            Player player = new Player(name, "> ", startX, startY);
            addPlayer(player);

            System.out.println(player.getName() + " has been added to the game!");
        }

        setCurrentlyPlaying(getPlayers().get(0));
        System.out.println(getCurrentlyPlaying().getName() + " Is starting!");
        scanner.nextLine();

        for (Player player : getPlayers()) {
            gameLoop(player, current);
        }
    }

    public void gameLoop(Player playing, Level current) {
        setCurrentlyPlaying(playing);
        setCurrentLevel(current);

        display();

        System.out.println("\n" + playing.getName() + " it is your turn!");
        System.out.println("\nEnter your first move:");
        String move = scanner.nextLine();
        String direction = move.split(" ")[0];
        int amount = Integer.parseInt(move.split(" ")[1]);

        String finishingMessage = "Time is up!";

        while (!move.equals(" ") && !playing.isCompleted()) {

            int x = 0;
            int y = 0;

            if (direction.equalsIgnoreCase("right")) {
                x = amount;
            } else if (direction.equalsIgnoreCase("left")) {
                x = -amount;
            } else if (direction.equalsIgnoreCase("up")) {
                y = -amount;
            } else if (direction.equalsIgnoreCase("down")) {
                y = amount;
            }

            if (!playerCanMove(playing, x, y)) {
                System.out.println("There seems to be a wall " + amount + " spaces " + direction);
                System.out.println("Try again: ");
            } else {
                getCurrentlyPlaying().move(x, y);
                display();

                if (current.tileAt(playing.getX(), playing.getY()).getType() == TileType.END) {
                    finishingMessage = "Congrats! You have made it through " + current.getName() + "!!!";
                    playing.setCompleted(true);
                } else {
                    System.out.println("\nEnter next move: ");
                }
            }

            move = scanner.nextLine();
            direction = move.split(" ")[0];
            amount = Integer.parseInt(move.split(" ")[1]);
        }

        System.out.println(finishingMessage);
    }
}
