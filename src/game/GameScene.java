package game;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import level.Level;
import level.Tile;
import level.TileType;


public class GameScene extends Scene {

    public GUIGame game;
    private Group group;

    public GameScene(Group root, int width, int height, GUIGame game) {
        super(root, width, height);
        this.group = root;
        this.game = game;
    }

    public void displayLevelTest() {
        Level level = game.getCurrentLevel();
        for (Tile tile : level.getTiles()) {
            ImageView img = new ImageView();
            if (tile.getType() == TileType.WALL) {
                img.setImage(new Image("resources/textures/stone12-1.png.png"));
            } else if (tile.getType() == TileType.PLAYABLE) {
                img.setImage(new Image("resources/textures/plank-1.png.png"));
            } else {
                Rectangle rect = new Rectangle(tile.getX() * 16, tile.getY() * 16, 16, 16);
                rect.setFill(Color.rgb(tile.getColor().getRed(), tile.getColor().getGreen(), tile.getColor().getBlue()));
                group.getChildren().add(rect);
            }
            img.setFitWidth(16);
            img.setFitHeight(16);
            img.setX(tile.getX() * 16);
            img.setY(tile.getY() * 16);
            group.getChildren().add(img);
        }
    }

    public void displayLevel() {
        Level level = game.getCurrentLevel();

        System.out.println(level.getTiles().size());
        for (Tile tile : level.getTiles()) {
            Rectangle rect = new Rectangle(tile.getX() * 16, tile.getY() * 16, 16, 16);
            rect.setFill(Color.rgb(tile.getColor().getRed(), tile.getColor().getGreen(), tile.getColor().getBlue()));
            group.getChildren().add(rect);
        }
    }
}
