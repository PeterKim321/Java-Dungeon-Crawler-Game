package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HowToPlayScreen {
	
	private Stage stage;
    private String title;
    private HowToPlayController controller;
    private Scene scene;
	private mainMenuScreen mainMenuScreen;

    public HowToPlayScreen(Stage stage) throws IOException {
    	this.stage = stage;
        title = "How to Play Screen";

        controller = new HowToPlayController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("scenes/howToPlay.fxml"));
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
	
	public HowToPlayController getController() {
        return controller;
    }
	
	void setMainMenuScreen(mainMenuScreen screen) {
    	this.controller.setMainMenuScreen(screen);;
    }

}
