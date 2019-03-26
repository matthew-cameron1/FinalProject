package game;

import entity.Player;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import level.Level;
import level.LevelLoader;

import java.io.File;

public class GUIGame extends MazeGame {

    private Group group;

    private AnimationTimer gameLoop;

    private Stage primary;

    private boolean isSwitchScreen, called = false;

    private GameScene scene;

    public GUIGame(Group group) {
        this.group = group;
        this.scene = new GameScene(group, 512, 512, this);
    }

    @Override
    public void display() {
        Player player = getCurrentlyPlaying();

        if (isSwitchScreen) {
            gameLoop.stop();
            Image background = new Image("Buttons and TitleScreen/Title Screen.png");
            ImageView bg = new ImageView(background);
            bg.setFitHeight(600);
            bg.setFitWidth(550);
            group.getChildren().add(bg);

            VBox box = new VBox();
            box.setAlignment(Pos.CENTER);
            box.setPadding(new Insets(20));

            Label label = new Label("Time taken in level: " + player.getRecentTime());
            label.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
            box.getChildren().add(label);

            int nextIndex = getPlayers().indexOf(player);
            Player next = getPlayers().get(nextIndex + 1);
            Label nextPlayer = new Label(next.getId() + " are you ready to begin " + getCurrentLevel().getName() + "?");
            nextPlayer.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
            box.getChildren().add(nextPlayer);


            Button start = new Button("Start");
            start.setPrefWidth(150);
            start.setOnAction(e -> {

                setCurrentlyPlaying(next);
                this.isSwitchScreen = false;
                next.getTimer().start();
                toGame(this.primary);

            });
            start.setAlignment(Pos.BOTTOM_CENTER);
            box.getChildren().add(start);

            group.getChildren().add(box);
        } else {

            for (Node node : group.getChildren()) {
                if (node instanceof Rectangle) {
                    Rectangle rectangle = (Rectangle) node;
                    if (rectangle.getFill() != Color.BLUE) {
                        continue;
                    }
                    if (rectangle.getX() != player.getX() || rectangle.getY() != player.getY()) {
                        rectangle.setTranslateX(player.getX() * 16);
                        rectangle.setTranslateY(player.getY() * 16);
                    }
                }
            }
        }
    }

    public void toGame(Stage primaryStage) {
        this.primary = primaryStage;


        scene.displayLevelTest();
        primaryStage.setScene(scene);
        primaryStage.show();
        start();
        Player currentlyPlaying = getCurrentlyPlaying();
        currentlyPlaying.getTimer().start();

        scene.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();

            switch (code) {
                case W:
                    if (!playerCanMove(currentlyPlaying, 0 ,-1))
                        return;
                    currentlyPlaying.move(0, -1);
                    break;
                case S:
                    if (!playerCanMove(currentlyPlaying, 0 ,1))
                        return;
                    currentlyPlaying.move(0, 1);
                    break;
                case D:
                    if (!playerCanMove(currentlyPlaying, 1 ,0))
                        return;
                    currentlyPlaying.move(1, 0);
                    break;
                case A:
                    if (!playerCanMove(currentlyPlaying, -1 ,0))
                        return;
                    currentlyPlaying.move(-1, 0);
                    break;
            }
        });
    }

    @Override
    public void playerFinished(Player player) {
        isSwitchScreen = true;
        player.setCompleted(true);

        if (getPlayers().size() == 1) {
            if (getAvailableLevels().size() == 1) {
                //Display end game screen!
            }
            else {
                setCurrentLevel(getAvailableLevels().get(getAvailableLevels().indexOf(getCurrentLevel()) + 1));
            }
        }

        if (getPlayers().indexOf(getCurrentlyPlaying()) == getPlayers().size() - 1) {
            //This is the last player in our loop!
            if (getAvailableLevels().size() == 1) {
                //Display end game screen.
            }
            else {
                if (getAvailableLevels().indexOf(getCurrentLevel()) == getAvailableLevels().size() - 1) {
                    //Display end game screen
                }
                else {
                    setCurrentLevel(getAvailableLevels().get(getAvailableLevels().indexOf(getCurrentLevel()) + 1));
                }
            }
        }
    }

    @Override
    public void start() {
        if (getCurrentlyPlaying() == null) {
            setCurrentlyPlaying(getPlayers().get(0));
        }
        Player player = getCurrentlyPlaying();
        int centerx = player.getX() * 16 + 16 / 2;
        int centery = player.getY() * 16 + 16 / 2;
        System.out.println(centery);
        Rectangle rect = new Rectangle(player.getX(), player.getY() - 30, 16, 16);
        rect.setFill(Color.BLUE);
        group.getChildren().add(rect);

        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                display();
            }
        };
        gameLoop.start();
    }

    @Override
    public void setup() {
        LevelLoader loader = new LevelLoader(new File("level 6-2.png"));
        addAll(loader.load());

        Level first = getAvailableLevels().get(0);
        setCurrentLevel(first);
    }
}
