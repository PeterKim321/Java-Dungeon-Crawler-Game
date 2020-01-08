package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;
    private int width = 0;
    private int height = 0;
    
    private int numKeys = 0;
    private int numDoors = 0;

    //Images
    private Image playerImage;
    private Image wallImage;
    private Image closedDoorImage;
    private Image openDoorImage;
    private Image exitImage;
    private Image boulderImage;
    private Image keyImage;
    private Image swordImage;
    private Image portalImage;
    private Image pressurePlateImage;
    private Image enemyElfImage;
    private Image enemyHoundImage;
    private Image potionImage;
    private Image treasureImage;
    private Image spikeInactiveImage;
    private Image spikeActiveImage;
    private Image swordDisplay5;
    private Image swordDisplay4;
    private Image swordDisplay3;
    private Image swordDisplay2;
    private Image swordDisplay1;
    private Image displayBorder;
    private Image gnomeImage;
    private Image redKeyImage;
    private Image greenKeyImage;
    private Image blueKeyImage;
    private Image redDoorImage;
    private Image greenDoorImage;
    private Image blueDoorImage;

    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        playerImage = new Image("/human_new.png");
        wallImage = new Image("/wall.png");
        closedDoorImage = new Image("/closed_door.png");
        openDoorImage = new Image("/open_door.png");
        exitImage = new Image("/exit.png");
        boulderImage = new Image("/boulder.png");
        keyImage = new Image("/key.png");
        swordImage = new Image("/greatsword_1_new.png");
        portalImage = new Image("/portal.png");
        pressurePlateImage = new Image("/pressure_plate.png");
        enemyElfImage = new Image("/deep_elf_master_archer.png");
        enemyHoundImage = new Image("/hound.png");
        potionImage = new Image("/blue_potion.png");
        treasureImage = new Image("gold_pile.png");
        spikeInactiveImage = new Image("/spikesClosed.png");
        spikeActiveImage = new Image("/spikesOpen.png");
        
        swordDisplay5 = new Image("/sword_5.png");
        swordDisplay4 = new Image("/sword_4.png");
        swordDisplay3 = new Image("/sword_3.png");
        swordDisplay2 = new Image("/sword_2.png");
        swordDisplay1 = new Image("/sword_1.png");
        displayBorder = new Image("/displayInvBorder.png");
        
        gnomeImage = new Image("/gnome.png");
        
        redKeyImage = new Image("/keyRed.png");
        greenKeyImage = new Image("/keyGreen.png");
        blueKeyImage = new Image("/keyBlue.png");
        redDoorImage = new Image("/doorClosedRed.png");
        greenDoorImage = new Image("/doorClosedGreen.png");
        blueDoorImage = new Image("/doorClosedBlue.png");
    }

    @Override
    public void onLoad(Entity player) {
    	width = ((Player) player).getDungeonWidth();
    	height = ((Player) player).getDungeonHeight();
    			
        ImageView view = new ImageView(playerImage);
        addEntity(player, view);
    }

    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        addEntity(wall, view);
    }
    
    @Override
    public void onLoad(Door door) {
    	ImageView view = null;
    	numDoors++;
 
    	switch (numDoors) {
    	case 1:
    		view = new ImageView(redDoorImage);
    		break;
    	case 2:
    		view = new ImageView(greenDoorImage);
    		break;
    	case 3:
    		view = new ImageView(blueDoorImage);
    		break;
    	}
    	addEntity(door, view);
    }

    @Override
    public void onLoad(Exit exit) {
    	ImageView view = new ImageView(exitImage);
    	addEntity(exit, view);
    }
    
    @Override
    public void onLoad(Potion potion) {
    	ImageView view = new ImageView(potionImage);
    	addEntity(potion, view);
    }
    
    @Override
    public void onLoad(Sword sword) {
    	ImageView view = new ImageView(swordImage);
    	addEntity(sword, view);
    }
    
    @Override
    public void onLoad(Treasure treasure) {
    	ImageView view = new ImageView(treasureImage);
    	addEntity(treasure, view);
    }
    
    @Override
    public void onLoad(Key key) {
    	ImageView view = null;
    	numKeys++;
 
    	switch (numKeys) {
    	case 1:
    		view = new ImageView(redKeyImage);
    		break;
    	case 2:
    		view = new ImageView(greenKeyImage);
    		break;
    	case 3:
    		view = new ImageView(blueKeyImage);
    		break;
    	}
    	addEntity(key, view);
    }
    
    @Override
    public void onLoad(Elf elf) {
    	ImageView view = new ImageView(enemyElfImage);
    	addEntity(elf, view);
    }
    
    @Override
    public void onLoad(Hound hound) {
    	ImageView view = new ImageView(enemyHoundImage);
    	addEntity(hound, view);
    }

    @Override
    public void onLoad(Portal portal) {
    	ImageView view = new ImageView(portalImage);
    	addEntity(portal, view);
    }
    
    @Override
    public void onLoad(Boulder boulder) {
    	ImageView view = new ImageView(boulderImage);
    	addEntity(boulder, view);
    }
    
    @Override
    public void onLoad(Switch sw) {
    	ImageView view = new ImageView(pressurePlateImage);
    	addEntity(sw, view);
    }
    
    @Override
    public void onLoad(Spike spike) {
    	ImageView view = new ImageView(spikeInactiveImage);
    	addEntity(spike, view);
    }
    
    @Override
    public void onLoad(Border border) {
    	ImageView view = new ImageView(displayBorder);
    	addEntity(border, view);
    }
    
    @Override
	public void onLoad(Gnome gnome) {
    	ImageView view = new ImageView(gnomeImage);
    	addEntity(gnome, view);
	}
    
    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entities.add(view);
    }

    public void addToDisplayInv(Sword entity, Node node) {
    	int offset = 1;
    	GridPane.setColumnIndex(node, width);
		GridPane.setRowIndex(node, offset);
		((ImageView) node).setImage(swordDisplay5);
    }
    
    public void addToDisplayInv(Potion entity, Node node) {
    	int offset = 2;
    	GridPane.setColumnIndex(node, width);
		GridPane.setRowIndex(node, offset);
    }
    
    public void addToDisplayInv(Key entity, Node node) {
    	int offset = 3;
    	GridPane.setColumnIndex(node, width);
		GridPane.setRowIndex(node, offset);
    }


    public void rmvFromDisplayInv(Entity entity, Node node) {
    	GridPane.setColumnIndex(node, 1000);
		GridPane.setRowIndex(node, 1000);
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });
        entity.getIsOnMap().addListener(new ChangeListener<Boolean>() {
        	@Override
        	public void changed(ObservableValue<? extends Boolean> observable,
        			Boolean oldValue, Boolean newValue) {
        		if (entity instanceof Sword) {
        			addToDisplayInv( ((Sword) entity), node);
        		} else {
        			if (!(entity instanceof Potion) && !(entity instanceof Key)) {
	        			GridPane.setColumnIndex(node, 1000);
	            		GridPane.setRowIndex(node, 1000);
        			}
        		}
        	}
        });
        if (entity instanceof Sword) {
            ((Sword) entity).getDuirabilityProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable,
                        Number oldValue, Number newValue) {
                	System.out.println("Sword remaining: " + newValue);
                    if (newValue.intValue() == 0) {
                		GridPane.setRowIndex(node, 1000);
                    } else {
                    	switch (newValue.intValue()) {
                    		case 4:
                    			((ImageView) node).setImage(swordDisplay4);
                    			break;
                    		case 3:
                    			((ImageView) node).setImage(swordDisplay3);
                    			break;
                    		case 2:
                    			((ImageView) node).setImage(swordDisplay2);
                    			break;
                    		case 1:
                    			((ImageView) node).setImage(swordDisplay1);
                    			break;
                    	}
                    }
                }
            });
        }
        if (entity instanceof Potion) {
        	((Potion) entity).getIsInUse().addListener(new ChangeListener<Boolean>() {
	        	@Override
	        	public void changed(ObservableValue<? extends Boolean> observable,
	        			Boolean oldValue, Boolean newValue) {
	        		if (newValue == true) {
	        			System.out.println("add to display inv");
            			addToDisplayInv( ((Potion) entity), node);
	        		} else {
	        			rmvFromDisplayInv(entity, node);
	        		}
	        	}
        	});
        }
        if (entity instanceof Key) {
        	((Key) entity).getIsInUse().addListener(new ChangeListener<Boolean>() {
	        	@Override
	        	public void changed(ObservableValue<? extends Boolean> observable,
	        			Boolean oldValue, Boolean newValue) {
	        		if (newValue == true) {
            			addToDisplayInv( ((Key) entity), node);
	        		} else {
	        			rmvFromDisplayInv(entity, node);
	        		}
	        	}
        	});
        }
        if (entity instanceof Door) {
        	((Door) entity).getIsOpen().addListener(new ChangeListener<Boolean>() {
            	@Override
            	public void changed(ObservableValue<? extends Boolean> observable,
            			Boolean oldValue, Boolean newValue) {
            		((ImageView) node).setImage(openDoorImage);
            	}
            });
        }
        if (entity instanceof Enemy) {
        	((Enemy) entity).getIsAlive().addListener(new ChangeListener<Boolean>() {
            	@Override
            	public void changed(ObservableValue<? extends Boolean> observable,
            			Boolean oldValue, Boolean newValue) {
            		GridPane.setColumnIndex(node, 1000);
            		GridPane.setRowIndex(node, 1000);
            	}
            });
        }
        if (entity instanceof Spike) {
        	((Spike) entity).getSpikeIsActive().addListener(new ChangeListener<Boolean>() {
            	@Override
            	public void changed(ObservableValue<? extends Boolean> observable,
            			Boolean oldValue, Boolean newValue) {
            		if (newValue) {
            			((ImageView) node).setImage(spikeActiveImage);
            		} else {
            			((ImageView) node).setImage(spikeInactiveImage);
            		}
            	}
            });
        }
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities);
    }

 

}
