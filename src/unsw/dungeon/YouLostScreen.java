package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class YouLostScreen {
	private Stage stage;
    private String title;
    private YouLostController controller;
    private Scene scene;

    public YouLostScreen(Stage stage) throws IOException {
    	this.stage = stage;
        title = "You Lost!";

        controller = new YouLostController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("scenes/youLost.fxml"));
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
	
	public YouLostController getController() {
        return controller;
    }
	
	void setMainMenuScreen(mainMenuScreen screen) {
    	this.controller.setMainMenuScreen(screen);;
    }
	public void setDungeonScreen(DungeonGameScreen dungeonScreen) {
		this.controller.setDungeonScreen(dungeonScreen);
	}

}