package level;

import java.util.ArrayList;
import java.util.List;

public class Level {

    private List<Tile> tiles = new ArrayList<>();
    private String name;

    private Tile start;
    private Tile end;

    public Level(String name) {
        this.name = name;
    }

    public void addTile(Tile tile) {
        this.tiles.add(tile);
    }

    public String getName() {
        return name;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public Tile getStart() {
        return start;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStart(Tile start) {
        this.start = start;
    }

    public Tile getEnd() {
        return end;
    }

    public void setEnd(Tile end) {
        this.end = end;
    }

    public Tile tileAt(int x, int y) {
        Tile match = null;
        for (Tile tile : tiles) {

            if (tile.getX() == x && tile.getY() == y) {
                match = tile;
            }
        }
        return match;
    }
}
