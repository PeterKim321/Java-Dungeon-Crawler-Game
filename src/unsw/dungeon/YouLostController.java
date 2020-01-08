package unsw.dungeon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class YouLostController {
	
	private mainMenuScreen mainMenu;
	private DungeonGameScreen dungeonScreen;
	
	@FXML
    private Button retryButton;

    @FXML
    private Button exitButton;

    @FXML
    void exitToMainMenu(ActionEvent event) {
    	mainMenu.start();
    }

    @FXML
    void retryLevel(ActionEvent event) {
    	dungeonScreen.start();
    }
    
    public void setMainMenuScreen(mainMenuScreen mainMenu) {
        this.mainMenu = mainMenu;
    }

	public void setDungeonScreen(DungeonGameScreen dungeonScreen) {
		this.dungeonScreen = dungeonScreen;
	}
    
}
