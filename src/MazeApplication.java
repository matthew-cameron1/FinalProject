import entity.Player;
import game.GUIGame;
import game.MazeGame;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MazeApplication extends Application {

    private TextField field;
    private Label playerName;
    private AnimationTimer gameLoop;
    private static Group group = new Group();
    private static MazeGame game = new GUIGame(group);


    private static MazeGame getGame() {
        return game;
    }

    public static void main(String[] args) {
        getGame().setup();
        MazeApplication application = new MazeApplication();

        launch(args);
    }

    public void setGame(MazeGame game) {
        this.game = game;
    }

    public Label getPlayerName() {
        return playerName;
    }

    public TextField getField() {
        return this.field;
    }


    public void start(Stage primaryStage) {

        System.out.println("Setting up Application");

        primaryStage.setTitle("Maze Game");

        VBox vb = new VBox();
        vb.setAlignment(Pos.TOP_CENTER);

        Label holder = new Label("Maze game");
        playerName = new Label("Enter player to start");
        vb.getChildren().add(holder);
        vb.getChildren().add(playerName);


        VBox vb2 = new VBox();
        vb2.setAlignment(Pos.BOTTOM_CENTER);

        HBox box = new HBox();
        box.setAlignment(Pos.CENTER_LEFT);
        box.setPadding(new Insets(20));

        box.getChildren().add(new Label("Enter player: "));

        field = new TextField();
        field.setMaxWidth(100.0);
        field.setMinWidth(50.0);
        box.getChildren().add(field);

        vb.getChildren().add(box);

        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);

        Button player = new Button("Add Player");
        Button start = new Button("Start Game");
        player.setOnAction(event -> {
            String playerName = field.getText();

            if (playerName.equals("")) {
                System.out.println("Something wrong with player");
                return;
            }

            game.addPlayer(new Player(playerName, "", game.getCurrentLevel().getStart().getX(), game.getCurrentLevel().getStart().getY()));
        });

        start.setOnAction(event -> {
            if (game.getPlayers().size() == 0)
                return;

            toGame(primaryStage);
        });

        buttons.getChildren().addAll(player, start);

        box.getChildren().add(buttons);

        primaryStage.setScene(new Scene(vb, 350, 150));
        primaryStage.show();
    }

    public void toGame(Stage primaryStage) {
        GameScene gameScene = new GameScene(group, 512, 512, (GUIGame) game);

        gameScene.displayLevelTest();
        primaryStage.setScene(gameScene);
        primaryStage.show();
        game.start();
        Player currentlyPlaying = game.getCurrentlyPlaying();

        gameScene.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();

            switch (code) {
                case W:
                    if (!game.playerCanMove(currentlyPlaying, 0 ,-1))
                        return;
                    currentlyPlaying.move(0, -1);
                    break;
                case S:
                    if (!game.playerCanMove(currentlyPlaying, 0 ,1))
                        return;
                    currentlyPlaying.move(0, 1);
                    break;
                case D:
                    if (!game.playerCanMove(currentlyPlaying, 1 ,0))
                        return;
                    currentlyPlaying.move(1, 0);
                    break;
                case A:
                    if (!game.playerCanMove(currentlyPlaying, -1 ,0))
                        return;
                    currentlyPlaying.move(-1, 0);
                    break;
            }
        });

        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                game.display();
            }
        };
        gameLoop.start();
    }
}
