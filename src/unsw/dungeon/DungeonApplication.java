package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

public class DungeonApplication extends Application {
	DungeonGameScreen easyDungeon;
	DungeonGameScreen medDungeon;
	DungeonGameScreen hardDungeon;
	Stage stage;
	
	@Override
    public void start(Stage primaryStage) throws IOException {
		stage = primaryStage;
		
        mainMenuScreen mainMenuScreen = new mainMenuScreen(primaryStage);
        easyDungeon = new DungeonGameScreen(primaryStage, "easyMaze.json");
        medDungeon = new DungeonGameScreen(primaryStage, "mediumDungeon.json");
        hardDungeon = new DungeonGameScreen(primaryStage, "portalMaze.json");
        
        HowToPlayScreen howToScreen = new HowToPlayScreen(primaryStage);
        YouLostScreen youLostScreen = new YouLostScreen(primaryStage);
        YouWonScreen youWonScreen = new YouWonScreen(primaryStage);
        CreditsMenuScreen creditsScreen = new CreditsMenuScreen(primaryStage);
        levelPickerScreen levelPickerScreen = new levelPickerScreen(primaryStage);
        
        // Set necessary relationships between screens
        mainMenuScreen.setCreditsScreen(creditsScreen);
        mainMenuScreen.setHowToScreen(howToScreen);
        mainMenuScreen.setLevelPickerScreen(levelPickerScreen);
        
        levelPickerScreen.setEasyDungeon(easyDungeon);
        levelPickerScreen.setMedDungeon(medDungeon);
        levelPickerScreen.setHardDungeon(hardDungeon);
        
        youLostScreen.setMainMenuScreen(mainMenuScreen);
        easyDungeon.setYouLostScreen(youLostScreen);
        medDungeon.setYouLostScreen(youLostScreen);
        hardDungeon.setYouLostScreen(youLostScreen);
        
        youWonScreen.setMainMenuScreen(mainMenuScreen);
        easyDungeon.setYouWonScreen(youWonScreen);
        medDungeon.setYouWonScreen(youWonScreen);
        hardDungeon.setYouWonScreen(youWonScreen);
        
        howToScreen.setMainMenuScreen(mainMenuScreen);
        creditsScreen.setMainMenuScreen(mainMenuScreen);
        levelPickerScreen.setMainMenuScreen(mainMenuScreen);
        
        mainMenuScreen.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}