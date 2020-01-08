/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon { 

    private int width, height;
    private List<Entity> entities;
    private Player player;
    
    private Component goalComponent = new Leaf("exit");
    private GoalCheck goalCheck = new GoalCheck();
    
    private BooleanProperty gameFinished;
    private boolean gameWon;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
        this.goalCheck = new GoalCheck();
        this.gameFinished = new SimpleBooleanProperty();
        gameFinished.setValue(false);
        this.gameWon = false;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
        
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
        try {
        	((GoalCondition) entity).addEntity(goalCheck);
        } catch (Exception e) {
        	// Ignore
        }
    }
    
    public void removeEntity(Entity entity) {
    	entities.remove(entity);
    }
    
    public List<Entity> getEntityList() {
    	return entities;
    }
    
    /**
     * @return Returns a matching portal if such one exists, null otherwise
     */
    public Portal getOtherPortal(Portal p) {
    	for (Entity e : entities) {
    		if (e instanceof Portal && p != e && ((Portal) e).getId() == p.getId()) {
    			return (Portal) e;
    		}
    	}
    	return null;
    }
    
   /**
    * Makes every switch observe the given boulder
    * @param boulder
    */
    public void observeBoulder(Boulder boulder) {
    	for (Entity e : entities) {
    		if (e instanceof Switch) {
    			boulder.attach((Observer) e);
    		}
    	}
    }
    
    /**
     * Checks if an entity exists with the given coordinates, and return
     * one if it exists
     * @param x
     * @param y
     * @return Entity at (x,y) if it exists, null otherwise
     */
    public Entity checkForObject(int x, int y) {
    	Entity result = null;
    	for (Entity entity : entities ) {
    		if (entity != null && x == entity.getX() && y == entity.getY()) {
    			result = entity;
    		}
    	}
    	
    	return result;
    }
    
    
    public Component getGoalComponent() {
		return goalComponent;
	}

	public void setGoalComponent(Component goalComponent) {
		this.goalComponent = goalComponent;
	}

    /**
     * Updates the status on completing the goal.
     * This method is called by relevant entities that 
     * influence whether the goal has been satisfied
     * @param e
     * @param goal
     */
    public void goalUpdate(GoalCondition e) {
    	if (goalCheck.checkGoals(e, goalComponent))  {
    		this.gameWon = true;
    		finishSession();
    	}
    }

    /**
     * Ends the current session
     */
	private void finishSession() {
		// TODO Auto-generated method stub
		System.out.println("!!!!!!!!!!!!! Game finished !!!!!!!!!!!!!");
		gameFinished.setValue(true);;
		//System.exit(0);
	}
	
	/**
	 * Function that progresses the timeline of the dungeon
	 */
	public void tick() {
		for (Entity e : entities) {
			e.tick();
		}
	}
	
	/**
	 * Getter for gameStatus
	 * @return boolean on game status
	 */
	public boolean isGameFinished() {
		return gameFinished.getValue();
	}
	
	public BooleanProperty getGameFinished() {
		return gameFinished;
	}
	
	
	public boolean isGameWon() {
		return gameWon;
	}

	public void setGameWon(boolean gameWon) {
		this.gameWon = gameWon;
	}

	public void setIsGameFinished(boolean b) {
		System.out.println("!!!!Game set to finished~~~");
		this.gameFinished.setValue(b);
	}
}
