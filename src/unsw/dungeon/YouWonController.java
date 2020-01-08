package unsw.dungeon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class YouWonController {
	
	private mainMenuScreen mainMenu;
	
	@FXML
    private Button backToMainMenuButton;

    @FXML
    void backToMainMenu(ActionEvent event) {
    	mainMenu.start();
    }
    
    public void setMainMenuScreen(mainMenuScreen mainMenu) {
        this.mainMenu = mainMenu;
    }
}