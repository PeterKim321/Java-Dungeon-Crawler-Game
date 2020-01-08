package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML
    private GridPane squares;

    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;
    
    private Timeline timeline;
    
    private mainMenuScreen mainMenuScreen;
    private YouLostScreen youLostScreen;
    private YouWonScreen youWonScreen;
    
    private Boolean isPlaying = false;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
    }

    @FXML
    public void play() {
    	KeyFrame kf = new KeyFrame(new Duration(500), event-> dungeon.tick());
    	
    	timeline.setCycleCount(Timeline.INDEFINITE);
    	timeline.getKeyFrames().add(kf);
    	
    	timeline.play();
    }
    
    @FXML
    public void stop() {
    	timeline.stop();
    }
    
    @FXML
    public void initialize() {
//        Image ground = new Image("/dirt_0_new.png");
    	Image ground = new Image("/ground.png");
        
        timeline = new Timeline();

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }
        for (ImageView entity : initialEntities) {
            squares.getChildren().add(entity);
        }
        dungeon.getGameFinished().addListener(new ChangeListener<Boolean>() {
        	@Override
        	public void changed(ObservableValue<? extends Boolean> observable,
        			Boolean oldValue, Boolean newValue) {
        		gotoPostGameScreen();
        		stop();
        	}

        });
        
    }
    
    public void updateIsPlaying() {
    	if (Boolean.compare(isPlaying, false) == 0) {
    		play();
    		isPlaying = true;
    	}
    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
        case UP:
            player.attemptMove("UP");
            updateIsPlaying();
            break;
        case DOWN:
            player.attemptMove("DOWN");
            updateIsPlaying();
            break;
        case LEFT:
            player.attemptMove("LEFT");
            updateIsPlaying();
            break;
        case RIGHT:
            player.attemptMove("RIGHT");
            updateIsPlaying();
            break;
        case I: // Player Inventory
        	player.printInventory();
        	break;
        case P: // Player Position
        	player.printCurrPos();
        	break;
        case SPACE: // Attack
        	player.attemptAttack();
        	break;
        case ESCAPE:
        	dungeon.setIsGameFinished(true);
        	break;
        default:
            break;
        }
    }

    void setMainMenuScreen(mainMenuScreen screen) {
    	this.mainMenuScreen = screen;
    }
    
    void setYouLostScreen(YouLostScreen screen) {
    	this.youLostScreen = screen;
    }
    
    void setYouWonScreen(YouWonScreen screen) {
    	this.youWonScreen = screen;
    }
    
    private void gotoPostGameScreen() {
    	if (dungeon.isGameWon())
    		gotoWonScreen();
    	else {
    		gotoLostScreen();	
    	}
		
	}
    
    private void gotoWonScreen() {
		youWonScreen.start();	
	}

	void gotoLostScreen() {
    	youLostScreen.start();
    }

	public mainMenuScreen getMainMenuScreen() {
		return mainMenuScreen;
	}

	public YouLostScreen getYouLostScreen() {
		return youLostScreen;
	}

	public YouWonScreen getYouWonScreen() {
		return youWonScreen;
	}
	
}

