package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;
    private List<Entity> inventory;
    private MovementBehaviour moveB;
    private BooleanProperty isPotionActive;
    private Boolean isAlive;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.moveB = new MovementBehaviour(this);
        this.inventory = new ArrayList<Entity>();
        this.isPotionActive = new SimpleBooleanProperty(false);
        this.isAlive = true;
    }
    
    /**
     * Getter function for isAlive
     * @return True if alive, false otherwise
     */
    public Boolean isPlayerAlive() {
		return isAlive;
	}
    
    /**
     * Setter function for isAlive
     */
    public void setIsAlive(Boolean b) {
		this.isAlive = b;
		if (b == false) {
			dungeon.setIsGameFinished(!b);
		}
	}
    
    /**
     * Getter function for isPotionActive
     * @return True if potion is active, false otherwise
     */
    public Boolean getIsPotionActive() {
		return isPotionActive.getValue();
	}
    
    public BooleanProperty getBooleanPropertyIsPotionActive() {
    	return isPotionActive;
    }
    
    /**
     * Setter function for isPotionActive
     * @param isPotionActive
     */
	public void setIsPotionActive(Boolean isPotionActive) {
		BooleanProperty result = new SimpleBooleanProperty(isPotionActive);
		
		this.isPotionActive.setValue(result.getValue());
	}
	/**
	 * Adds the given entity to the player's inventory
	 * @param e
	 */
	public void addToInv(Entity e) {
    	this.inventory.add(e);
    }
	/**
	 * Removes the given entity to the player's inventory
	 * @param e
	 */
	public void rmvFromInv(Entity e) {
		this.inventory.remove(e);
	}
	/**
	 * Removes the key from the inventory
	 */
	public void rmvKeyFromInv() {
		inventory.removeIf(e -> (e instanceof Key));
	}
	
	/**
	 * Decrease durability of sword, and remove once it hits 0
	 */
	public void decreaseDurabilityOfSword() {
		for (Entity e : inventory) {
			if (e instanceof Sword) {
				if(!((Sword) e).reduceDurability()) {
					rmvFromInv(e);
				}
				break;
			}
		}
	}
    
    /**
     * Attempts to move the player given input direction.
     * Note that the top left block has coords (0,0)
     * If collding with an entity, will perform the appropriate actions
     * depending on said entity
     * @param moveCmd A string describing direction
     */
    public void attemptMove(String moveCmd) {
    	int newXPos = getX();
    	int newYPos = getY();
    	
    	switch (moveCmd) {
        case "UP":
        	newYPos -= 1;	
            break;
        case "DOWN":
        	newYPos += 1;
        	break;
        case "LEFT":
        	newXPos -= 1;
        	break;
        case "RIGHT":
        	newXPos += 1;
        	break;
    	}
    	interactWith(newXPos, newYPos, moveCmd);
    	
    }
    
    /** 
     * Carries out the appropriate actions when player is
     * attempting to move to (x,y)
     * @param x x coord, the player is going to move to
     * @param y y coord, the player is going to move to
     * @param moveCmd Direction as a string
     */
    public void interactWith(int x, int y, String moveCmd) {
    	Entity returnedEntity = dungeon.checkForObject(x, y);
    	if (returnedEntity == null ) { 
    		movePlayer(moveCmd);
    	} else {
    		returnedEntity.interactWith(this, moveCmd);
    	}
    }
    
    /**
     * Checks if an entity exists at (x,y).
     * @param x
     * @param y
     * @return Entity at (x,y) if it exists, null otherwise
     */
    public Entity checkForObject(int x, int y) {
    	return dungeon.checkForObject(x, y);
    }
    
    /** 
     * Moves the player 1 block in the specified direction
     * @param moveCmd Direction as a string
     */
    public void movePlayer(String moveCmd) {
    	moveB.move(moveCmd);	
	}
    
    /**
     * Checks for open tiles in the surrouding block (dist = 1 unit), and attacks in all possible direction
     */
    public void attemptAttack() {
    	if (this.hasSword()) {
			int newXPos = this.getX();
			int newYPos = this.getY();
			List<Pair<Integer, Integer>> validAttackCoordinates = new ArrayList<Pair<Integer, Integer>>();
			
			for (int i = 1; i <= 4; i++) {
				newXPos = this.getX();
				newYPos = this.getY();
				
		    	switch (i) {
		        case 1:
		        	newYPos -= 1;
		            break;
		        case 2:
		        	newYPos += 1;
		        	break;
		        case 3:
		        	newXPos -= 1;
		        	break;
		        case 4:
		        	newXPos += 1;
		        	break;
		    	}
		    	
		    	if (isValidMove(newXPos, newYPos)) {
		    		Pair <Integer, Integer> coordinate =  new Pair <Integer, Integer> (newXPos, newYPos); 
		    		validAttackCoordinates.add(coordinate);
		    	}
			}
	
			this.attack(validAttackCoordinates);
    	} else {
    		System.out.println("Player does not have sword");
    	}
    }
    
    public void attack(List<Pair<Integer, Integer>> validAttackCoordinates) {
    	// Testing (debug code)
    	String dir = "empty";
    	System.out.println("Curr Pos: (" + this.getX() + ", " + this.getY() + ") || Can Attack:");
    	for (Pair<Integer, Integer> pos : validAttackCoordinates) {
    		System.out.print(">>> x: " + pos.getL() + " || y: " + pos.getR());
        	if (pos.getL() > this.getX()) {
        		dir = "RIGHT";
        	} else if (pos.getL() < this.getX()) {
        		dir = "LEFT";
        	} else if (pos.getR() < this.getY()) {
        		dir = "UP";
        	} else if (pos.getR() > this.getY()) {
        		dir = "DOWN";
        	}
        	
        	System.out.println(" || Dir: " + dir);
    	}
    	
    	// Code (implementation)
    	for (Pair<Integer, Integer> pos : validAttackCoordinates) {
    		checkEnemiesToKill(pos.getL(), pos.getR());
    	}
    	this.decreaseDurabilityOfSword();
    }
    
    public void checkEnemiesToKill(int x, int y) {
    	Entity returnedEntity = dungeon.checkForObject(x, y);
    	if (returnedEntity instanceof Enemy) { 
    		System.out.println("enemy killed (this is in player)"); 
			((Enemy) returnedEntity).killEnemy();
    	}
    }
    
	public Boolean isValidMove(int x, int y) {
		Entity returnedEntity = this.dungeon.checkForObject(x, y);
		if (returnedEntity == null || returnedEntity.isMoveToAble()) {
    		return true;
    	} else {
    		return false;
    	}
	}
    
    /**
     * Teleports player to (x,y)
     * @param x
     * @param y
     */
    public void teleportPlayer(int x, int y) {
    	moveB.teleport(x, y);
    }
    
    /**
     * 
     * @return True if player has a key in their inventory, 
     * false otherwise
     */
    public boolean hasKey() {
    	for (Entity e : inventory) {
    		if (e instanceof Key)
    			return true;
    	}
    	return false;
    }
    
    public Key getKeyWithID(int id) {
    	for (Entity e : inventory) {
    		if (e instanceof Key && ((Key) e).getId() == id)
    			return ((Key) e);
    	}
    	return null;
    }
    
    
    /**
     * 
     * @param id
     * @return True if player has the key with the corresponding id
     * in their inventory, false otherwise
     */
    
    public boolean hasKeyWithID(int id) {
    	for (Entity e : inventory) {
    		if (e instanceof Key && ((Key) e).getId() == id)
    			return true;
    	}
    	return false;
    }
    
    /**
     * @return returns the number of treasure the player has in their inventory
     */
    public int hasTreasure() {
    	int n = 0;
    	
    	for (Entity e : inventory) {
    		if (e instanceof Treasure) {
    			n++;
    		}
    	}
    	
    	return n;
    }
    
    /**
     * 
     * @return True if player has a sword in their inventory,
     * false otherwise
     */
    public boolean hasSword() {
    	for (Entity e : inventory) {
    		if (e instanceof Sword)
    			return true;
    	}
    	return false;
    }
    /**
     * 
     * @return True if player has a potion in their inventory,
     * false otherwise
     */
    public boolean hasPotion() {
    	for (Entity e : inventory) {
    		if (e instanceof Potion)
    			return true;
    	}
    	return false;
    }
    /**
     * Prints the entities inside the player's inventory to stdout
     */
    public void printInventory() {
    	System.out.println("Player Inventory:");
    	if (inventory.size() == 0) {
    		System.out.println("- Inventory is currently empty!");
    	} else {
	    	for (Entity e : inventory) {
	    		System.out.println("- " + e);
	    	}
    	}
    }
    
    public void rmvItemFromDungeon(Entity e) {
    	this.dungeon.removeEntity(e);
    }
    
    /**
     * Prints the current position of the player to stdout
     */
    public void printCurrPos() {
    	System.out.println("x: " + getX() + " || y: " + getY());
    }

	@Override
	public String toString() {
		return "Player at (" + getX() + ", " + getY() + ")";
	}
    
	@Override
	public boolean isMoveToAble() {
		return !isPotionActive.getValue();
	}

	public int getDungeonWidth() {
		// TODO Auto-generated method stub
		return dungeon.getWidth();
	}

	public int getDungeonHeight() {
		// TODO Auto-generated method stub
		return dungeon.getHeight();
	}
    
}
