package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CreditsMenuScreen {
    private Stage stage;
    private String title;
    private CreditsMenuController controller;
    private Scene scene;

    public CreditsMenuScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Credits";

        controller = new CreditsMenuController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("scenes/credit.fxml"));
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

    public CreditsMenuController getController() {
        return controller;
    }
    
    void setMainMenuScreen(mainMenuScreen screen) {
    	this.controller.setMainMenuScreen(screen);
	}
}
