package unsw.dungeon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CreditsMenuController {

    @FXML
    private Button backToMenyBtn;

    private mainMenuScreen mainMenuScreen;
    
    @FXML
    void goToMainMenu(ActionEvent event) {
    	mainMenuScreen.start();
    }

    void setMainMenuScreen(mainMenuScreen screen) {
    	this.mainMenuScreen = screen;
	}
}
