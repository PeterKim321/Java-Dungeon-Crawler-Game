package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DungeonGameScreen {

    private Stage stage;
    private String title;
    private Scene scene;
    DungeonControllerLoader dungeonLoader;
    DungeonController controller;
    
    private Stage stageTmp;
    private String fileTmp;

    public DungeonGameScreen(Stage stage, String file) throws IOException {
    	this.stageTmp = stage;
    	this.fileTmp = file;
    	
        this.stage = stage;
        title = "Dungeon Game";
        
        Text textOverlay = new Text("testtst");
        textOverlay.setFont(Font.font("Sans serif", FontWeight.NORMAL, FontPosture.REGULAR, 32));

        dungeonLoader = new DungeonControllerLoader(file);
        controller = dungeonLoader.loadController();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("scenes/DungeonView.fxml"));
        loader.setController(controller);

        // load into a Parent node called root
        Parent root = loader.load();
        scene = new Scene(root);
        
        root.requestFocus();
    }

    public void start() {
    	controller.getYouLostScreen().setDungeonScreen(this);
    	DungeonController tmp = controller;
    	try {
    		dungeonLoader = null;
    		controller = null;
	    	dungeonLoader = new DungeonControllerLoader(fileTmp);
	        controller = dungeonLoader.loadController();
	        controller.setMainMenuScreen(tmp.getMainMenuScreen());
	        controller.setYouLostScreen(tmp.getYouLostScreen());
	        controller.setYouWonScreen(tmp.getYouWonScreen());
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("scenes/DungeonView.fxml"));
	        loader.setController(controller);
	        Parent root = loader.load();
	        scene = new Scene(root);
	        root.requestFocus();
        
    	} catch (Exception e) {
    		
    	}
        
    	
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public DungeonController getController() {
        return controller;
    }
    
    void setMainMenuScreen(mainMenuScreen screen) {
    	this.controller.setMainMenuScreen(screen);
	}
	
	void setYouLostScreen(YouLostScreen screen) {
    	this.controller.setYouLostScreen(screen);;
    }
	
	void setYouWonScreen(YouWonScreen screen) {
    	this.controller.setYouWonScreen(screen);;
    }
	
	
}