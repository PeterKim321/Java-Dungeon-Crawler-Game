package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class levelPickerScreen {
    private Stage stage;
    private String title;
    private levelPickerController controller;
    private Scene scene;

    public levelPickerScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Pick Your Level";

        controller = new levelPickerController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("scenes/levelPicker.fxml"));
        loader.setController(controller);
        

        // load into a Parent node called root
        Parent root = loader.load();
        scene = new Scene(root, 650, 400);
    }

    public void start() {
        controller.initialise();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public levelPickerController getController() {
        return controller;
    }
    
    void setMainMenuScreen(mainMenuScreen screen) {
    	this.controller.setMainMenuScreen(screen);
	}
    
    void setEasyDungeon(DungeonGameScreen screen) {
    	this.controller.setEasyDungeon(screen);
	}
    
    void setMedDungeon(DungeonGameScreen screen) {
    	this.controller.setMedDungeon(screen);
	}
    
    void setHardDungeon(DungeonGameScreen screen) {
    	this.controller.setHardDungeon(screen);
	}
}
