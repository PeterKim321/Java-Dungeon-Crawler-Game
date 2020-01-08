package unsw.dungeon;

/**
 * Class used to move other entities
 * 
 * Assumes that all movements are valid, and have been checked before
 * calling these functions
 * @author z5209624
 *
 */
public class MovementBehaviour {
	
	private Entity e;
	//private Dungeon dungeon;
	
	public MovementBehaviour(Entity e) {
		this.e = e;
		//this.dungeon = dungeon;
	}
	/**
	 * Moves the entity given the direction as input
	 * @param moveCmd Direction of movement as a string
	 */
	public void move(String moveCmd) {
		switch (moveCmd) {
        case "UP":
        	moveUp();
            break;
        case "DOWN":
        	moveDown();
        	break;
        case "LEFT":
        	moveLeft();
        	break;
        case "RIGHT":
        	moveRight();
        	break;
    	}	
	}
	/**
	 * Moves the entity up 1 block
	 */
	public void moveUp() {
        //if (e.getY() > 0)
            e.y().set(e.getY() - 1);
    }
	/**
	 * Moves the entity down 1 block
	 */
    public void moveDown() {
        //if (e.getY() < dungeon.getHeight() - 1)
            e.y().set(e.getY() + 1);
    }
    /**
	 * Moves the entity left 1 block
	 */
    public void moveLeft() {
        //if (e.getX() > 0)
            e.x().set(e.getX() - 1);
    }
    /**
	 * Moves the entity right 1 block
	 */
    public void moveRight() {
        //if (e.getX() < dungeon.getWidth() - 1)
            e.x().set(e.getX() + 1);
    }
    /**
     * Teleports the entity to (x,y)
     * @param destX
     * @param destY
     */
    public void teleport(int destX, int destY) {
    	e.x().set(destX);
    	e.y().set(destY);
    }
	
}
