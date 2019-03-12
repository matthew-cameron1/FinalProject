import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GuiDesign extends Application {
    private TextField field;
    private Label playerName;


    public static void main(String[] args) {
        launch(args);
    }

    public Label getPlayerName() {
        return playerName;
    }

    public TextField getField() {
        return this.field;
    }

 

    public void start(Stage primaryStage) {

       

        primaryStage.setTitle("Maze Game");


        VBox vb = new VBox();
        vb.setAlignment(Pos.TOP_CENTER);

        Label holder = new Label("Maze game");
        playerName = new Label("Enter player to start");
        vb.getChildren().add(holder);
        vb.getChildren().add(playerName);

        
        VBox vb2= new VBox();
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
        
       

        buttons.getChildren().addAll(player);

        box.getChildren().add(buttons);

        primaryStage.setScene(new Scene(vb, 350, 150));
        primaryStage.show();


}
 class PlayerListener implements EventHandler<ActionEvent> {
       private GuiDesign name;
       public PlayerListener(GuiDesign name) {
    	   this.name=name;
    	   
    	   
       }
	@Override
	public void handle(ActionEvent event) {
		
	}
       
 }
}