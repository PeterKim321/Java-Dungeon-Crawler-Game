package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class mainMenuScreen {

    private Stage stage;
    private String title;
    private mainMenuController controller;
    private Scene scene;

    public mainMenuScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Main Menu";

        controller = new mainMenuController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("scenes/mainMenu.fxml"));
        loader.setController(controller);

        // load into a Parent node called root
        Parent root = loader.load();
        scene = new Scene(root);
    }

    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
    

    public Stage getStage() {
		return stage;
	}

	public mainMenuController getController() {
        return controller;
    }
    

    public void setHowToScreen(HowToPlayScreen howTo) {
    	this.controller.setHowToScreen(howTo);
    }

    void setCreditsScreen(CreditsMenuScreen screen) {
    	this.controller.setCreditsScreen(screen);
    }
    
    void setLevelPickerScreen(levelPickerScreen screen) {
    	this.controller.setLevelPickerScreen(screen);
    }
}
