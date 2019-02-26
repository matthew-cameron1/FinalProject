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
        Scanner scanner = new Scanner(System.in);

        int players = scanner.nextInt();

        int startX = getCurrentLevel().getStart().getX();
        int startY = getCurrentLevel().getStart().getY();

        for (int i = 0; i < players; i++) {
            System.out.println("Okay player " + (i + 1) + " enter your user name:");
            String name = scanner.next();

            Player player = new Player(name, ">", startX, startY);
            addPlayer(player);

            System.out.println(player.getName() + " has been added to the game!");
        }

        setCurrentlyPlaying(getPlayers().get(0));

        Player playing = getCurrentlyPlaying();

        System.out.println(getCurrentLevel().tileAt(1, 1).getType().toString());


        int playerSeconds = 120 * 1000;

        display();


        System.out.print("\nEnter your first move:");
        scanner.nextLine();
        String move = scanner.nextLine();
        String direction = move.split(" ")[0];
        int amount = Integer.parseInt(move.split(" ")[1]);

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

                if (playing.getX() == current.getEnd().getX() && playing.getY() == current.getEnd().getY()) {
                    playing.setCompleted(true);
                }
                System.out.println("\nEnter next move: ");
            }

            move = scanner.nextLine();
            direction = move.split(" ")[0];
            amount = Integer.parseInt(move.split(" ")[1]);
        }

        System.out.println("UH OH we ran out of time");
    }
}
