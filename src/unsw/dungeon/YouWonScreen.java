package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class YouWonScreen {
	private Stage stage;
    private String title;
    private YouWonController controller;
    private Scene scene;

    public YouWonScreen(Stage stage) throws IOException {
    	this.stage = stage;
        title = "You Won!";

        controller = new YouWonController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("scenes/youWon.fxml"));
        loader.setController(controller);

        // load into a Parent node called root
        Parent root = loader.load();
        scene = new Scene(root, 600, 400);

    }
	
	public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
	
	public YouWonController getController() {
        return controller;
    }
	
	void setMainMenuScreen(mainMenuScreen screen) {
    	this.controller.setMainMenuScreen(screen);;
    }
}
