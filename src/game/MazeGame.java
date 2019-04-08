package game;

import entity.Player;
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

    public boolean newPlayerMove(Player player, int distX, int distY) {
        int x = player.getX();
        int y = player.getY();

        int futureX = x + (distX * 16);
        int futureY = y + (distY * 16);

        Tile next = currentLevel.tileAt(futureX / 16, futureY / 16);

        if (next != null) {
            if (next.getType() == TileType.END) {
                player.setCompleted(true);
                playerFinished(player);
            }
        }

        if (next == null) {
            return false;
        }

        return next.getType() != TileType.WALL;
    }

    public boolean playerCanMove(Player player, int distX, int distY) {
        int x = player.getX();
        int y = player.getY();

        System.out.println("Player coords: (" + x + ", " + y + ")");

        int newX = 0;
        int newY = 0;

        boolean canMove = true;

        if (distX > 0) {
            newX = x + distX;
        }

        if (distX < 0) {
            newX = x - distX;
        }

        if (distY > 0) {
            newY = y + distY;
        }

        if (distY < 0) {
            newY = y - distY;
        }

        if (newX != x) {

            if (newX < x) {
                for (int x1 = newX; x1 <= x; x1++) {
                    if (getCurrentLevel().tileAt(x1, y).getType() == TileType.WALL) {
                        canMove = false;
                    }
                }
            }
            else {
                for (int x1 = x; x1 <= newX; x1++) {
                    if (getCurrentLevel().tileAt(x1, y).getType() == TileType.WALL) {
                        canMove = false;
                    }
                }
            }

        }
        else if (newY != y) {
            if (newY < y) {
                for (int y1 = newY; y1 <= x; y1++) {
                    if (getCurrentLevel().tileAt(x, y1).getType() == TileType.WALL) {
                        canMove = false;
                    }
                }
            }
            else {
                for (int y1 = y; y1 <= newY; y1++) {
                    if (getCurrentLevel().tileAt(x, y1).getType() == TileType.WALL) {
                        canMove = false;
                    }
                }
            }
        }

        return canMove;
    }

    public abstract void playerFinished(Player player);

    public abstract void display();

    public abstract void start();

    public abstract void setup();
}
