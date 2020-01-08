package unsw.dungeon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class mainMenuController {

    @FXML
    private Button helpButton;

    @FXML
    private Button creditsButton;

    @FXML
    private Button playButton;

    @FXML
    private ImageView titleImage;
    
    private HowToPlayScreen howTo;

    private CreditsMenuScreen creditsScreen;
    
    private levelPickerScreen levelPickerScreen;

    
    @FXML
    void clickedImage(MouseEvent event) {

    }

    @FXML
    void getCreditsMenu(ActionEvent event) {
    	creditsScreen.start();
    }

    @FXML
    void getHelpMenu(ActionEvent event) {
    	howTo.start();
    }

    @FXML
    void startGame(ActionEvent event) {
    	levelPickerScreen.start();
    }
    
    void setHowToScreen(HowToPlayScreen howTo) {
    	this.howTo = howTo; 
    }

    void setCreditsScreen(CreditsMenuScreen screen) {
    	this.creditsScreen = screen;
    }
    
    void setLevelPickerScreen(levelPickerScreen screen) {
    	this.levelPickerScreen = screen;
    }
}

