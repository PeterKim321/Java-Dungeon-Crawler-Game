package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class Entity {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y;
    private BooleanProperty isOnMap;

    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Entity(int x, int y) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.isOnMap = new SimpleBooleanProperty();
        isOnMap.setValue(true);
        
    }

    public BooleanProperty getIsOnMap() {
		return isOnMap;
	}

	public void setIsOnMap(BooleanProperty isOnMap) {
		this.isOnMap = isOnMap;
	}

	public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }
    
    /**
     * Checks if entities a and b have the same position
     * @param a
     * @param b
     * @return True if a and b have the same position, false otherwise
     */
    public Boolean checkPosition(Entity a, Entity b) {
    	if ( (a.getX() == b.getX()) && (a.getY() == b.getY()) ) {
    		return true;
    	}
    	return false;
    }
    
    /**
     * Called when the player attempts to move to the block 
     * containing this entity. Is overridden by subentities.
     * @param p Player
     * @param moveCmd Direction the player is moving as a string
     */
    public void interactWith(Player p, String moveCmd) {
    	return;
    }
    /**
     * 
     * @return True if other moveable entities can move on this entity	 
     */
	public boolean isMoveToAble() {
		return false;
	}

	public void tick() {
		return;
	}
    
    
    
}
