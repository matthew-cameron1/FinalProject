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
            }
            else if (type == TileType.START) {
                toPrint = getStartIndicator();
            }
            else if (type == TileType.WALL) {
                toPrint = getWallIndicator();
            }
            else {
                toPrint = "  ";
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
        setCurrentLevel(levelsLoaded.get(0));

        System.out.println("Welcome to MazeGame Console Edition! Enter number of players to begin: ");
        Scanner scanner = new Scanner(System.in);

        int players = scanner.nextInt();

        int startX = getCurrentLevel().getStart().getX();
        int startY = getCurrentLevel().getStart().getY();

        for (int i = 0; i < players; i++) {
            System.out.println("Okay player " + (i + 1) + " enter your user name:");
            String name = scanner.next();

            Player player = new Player(name, String.valueOf(i + 1), startX, startY);
            addPlayer(player);

            System.out.println(player.getName() + " has been added to the game!");
        }

        display();
    }
}
