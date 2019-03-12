package game;

import entity.Player;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import level.Level;
import level.LevelLoader;

import java.io.File;

public class GUIGame extends MazeGame {

    private Group group;

    public GUIGame(Group group) {
        this.group = group;
    }

    @Override
    public void display() {
        Player player = getCurrentlyPlaying();

        for (Node node : group.getChildren()) {
            if (node instanceof Rectangle) {
                Rectangle rectangle = (Rectangle) node;
                if (rectangle.getFill() != Color.BLUE) {
                    continue;
                }
                if (rectangle.getX() != player.getX() || rectangle.getY() != player.getY()) {
                    rectangle.setTranslateX(player.getX()*16);
                    rectangle.setTranslateY(player.getY()*16);
                }
            }
        }
    }

    @Override
    public void start() {
        setCurrentlyPlaying(getPlayers().get(0));
        Player player = getCurrentlyPlaying();
        int centerx = player.getX()*16 + 16/2;
        int centery = player.getY()*16 + 16/2;
        System.out.println(centery);
        Rectangle rect = new Rectangle(player.getX(), player.getY()-30, 16, 16);
        rect.setFill(Color.BLUE);
        group.getChildren().add(rect);
    }

    @Override
    public void setup() {
        LevelLoader loader = new LevelLoader(new File("level 6-2.png"));
        addAll(loader.load());

        Level first = getAvailableLevels().get(0);
        setCurrentLevel(first);
    }
}
