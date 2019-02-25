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

    public boolean playerCanMove(Player player, int dirX, int dirY) {
        int x = player.getX();
        int y = player.getY();

        int checkX = 0;
        int checkY = 0;

        if (dirX > 0) {
            //RIGHT
            checkX = x + dirX;
        }
        else if (dirX < 0) {
            //LEFT
            checkX = x - dirX;
        }

        if (dirY > 0) {
            //DOWN
            checkY = y + dirY;
        }
        else if (dirY < 0) {
            //UP
            checkY = y - dirY;
        }
        Tile nextTile = currentLevel.tileAt(checkX, checkY);

        return !(nextTile.getType() == TileType.WALL);
    }

    public abstract void display();
    public abstract void start(MazeGame game);
}
