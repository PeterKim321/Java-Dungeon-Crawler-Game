package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;

public class levelPickerController {

    @FXML
    private Button hardBtn;

    @FXML
    private Button medBtn;

    @FXML
    private Button easyBtn;
    
    @FXML
    private Tooltip easyTooltip;
    
    @FXML
    private Tooltip mediumTooltip;
    
    @FXML
    private Tooltip hardTooltip;

    @FXML
    private Button backToMenuBtn;

    private mainMenuScreen mainMenuScreen;
    
    private DungeonGameScreen easyDungeon;
    
    private DungeonGameScreen medDungeon;
    
    private DungeonGameScreen hardDungeon;
    
    public void initialise() {
    	easyTooltip.setShowDelay(Duration.millis(10));
    	mediumTooltip.setShowDelay(Duration.millis(10));
    	hardTooltip.setShowDelay(Duration.millis(10));
    }
    
    @FXML
    void backToMenu(ActionEvent event) {
    	mainMenuScreen.start();
    }

    @FXML
    void loadEasyLevel(ActionEvent event) {
    	easyDungeon.start();
    }

    @FXML
    void loadMedLevel(ActionEvent event) {
    	medDungeon.start();
    }
    
    @FXML
    void loadHardLevel(ActionEvent event) {
    	hardDungeon.start();
    }

    void setMainMenuScreen(mainMenuScreen screen) {
    	this.mainMenuScreen = screen;
	}
    
    void setEasyDungeon(DungeonGameScreen screen) {
    	this.easyDungeon = screen;
	}
    
    void setMedDungeon(DungeonGameScreen screen) {
    	this.medDungeon = screen;
	}
    
    void setHardDungeon(DungeonGameScreen screen) {
    	this.hardDungeon = screen;
	}
}
