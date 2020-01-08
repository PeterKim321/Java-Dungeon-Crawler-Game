package unsw.dungeon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HowToPlayController {
	
	private mainMenuScreen mainMenu;

    @FXML
    private Button backButton;
    
    @FXML
    void backFromHowToPlay(ActionEvent event) {
    	mainMenu.start();
    }
    
    public void setMainMenuScreen(mainMenuScreen mainMenu) {
        this.mainMenu = mainMenu;
    }


}