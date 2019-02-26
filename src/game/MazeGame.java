package game;

import level.Level;
import level.Tile;
import level.TileType;

import java.util.ArrayList;
import java.util.List;

public abstract class MazeGame {

    private List<Player> players = new ArrayList<>();
    private List<Level> availableLevels = new ArrayList<>();
    private Level currentLevel = null;
    private Player currentlyPlaying = null;

    public void addAll(List<Level> levels) {
        this.availableLevels.addAll(levels);
    }

    public void addLevel(Level level) {
        this.availableLevels.add(level);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setAvailableLevels(List<Level> availableLevels) {
        this.availableLevels = availableLevels;
    }

    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }

    public List<Level> getAvailableLevels() {
        return availableLevels;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void setCurrentlyPlaying(Player currentlyPlaying) {
        this.currentlyPlaying = currentlyPlaying;
    }

    public Player getCurrentlyPlaying() {
        return currentlyPlaying;
    }

    public boolean playerCanMove(Player player, int distX, int distY) {
        int x = player.getX();
        int y = player.getY();

        int checkX = player.getX();
        int checkY = player.getY();



        if (distX > 0) {
            //RIGHT
            checkX = x + distX;
        }
        else if (distX < 0) {
            //LEFT
            System.out.println("Left");
            checkX = x + distX;
        }

        if (distY > 0) {
            //DOWN
            checkY = y + distY;
        }
        else if (distY < 0) {
            //UP
            checkY = y + distY;
        }

        int xDiff = checkX - x;
        int yDiff = checkY - y;

        boolean conflict = false;

        for (int x1 = 0; x1 < xDiff; x1++) {
            for (int y1 = 0; y1 < yDiff; y1++) {
                Tile at = currentLevel.tileAt(x1, y1);

                if (at.getType() == TileType.WALL) {
                    conflict = true;
                }
            }
        }

        Tile nextTile = currentLevel.tileAt(checkX, checkY);

        return !conflict && nextTile.getType() != TileType.WALL;
    }

    public abstract void display();
    public abstract void start();
}
