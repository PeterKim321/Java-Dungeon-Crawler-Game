package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Enemy extends Entity implements GoalCondition{
	protected MovementBehaviour moveB;
	protected EnemyMovement chase;
	protected EnemyMovement flee;
	protected EnemyMovement currMovement;
	protected Dungeon dungeon;
	
	private BooleanProperty isAlive;
	
	public Enemy(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
		this.isAlive = new SimpleBooleanProperty();
		this.moveB = new MovementBehaviour(this);
		chase = new EnemyChase(this);
		flee = new EnemyFlee(this);
        currMovement = chase;
		
		isAlive.setValue(true);
	}
	
	public Dungeon getDungeon() {
		return dungeon;
	}
	

	public BooleanProperty getIsAlive() {
		return isAlive;
	}

	public void setIsAlive(boolean isAlive) {
		this.isAlive.setValue(isAlive);
	}

	public EnemyMovement getCurrMovement() {
		return currMovement;
	}
	
	@Override
	public void tick() {
		updateMovement();
		attemptMove();
	}
	
	public void attemptMove() {
		currMovement.attemptMove();
	}
	
	public void move(String moveCmd) {
		moveB.move(moveCmd);
	}	
	
	public void teleport(int x, int y) {
		moveB.teleport(x, y);
	}
	
	/**
	 * Moves the enemy to a set position, and makes checks for player collision
	 * @param x xPos of new coordinate
	 * @param y yPos of new coordinate
	 * @param moveCmd direction (String)
	 */
	public void interactMove(int x, int y, String moveCmd) {
		Entity returnedEntity = dungeon.checkForObject(x, y);
		move(moveCmd);
		if (returnedEntity instanceof Player) {
			System.out.println("Enemy collided with player!");
			((Player) returnedEntity).setIsAlive(false);
		}
	}
	
	public void attemptTeleport(int x, int y) {
		Entity returnedEntity = dungeon.checkForObject(x, y);
		teleport(x, y);
		if (returnedEntity instanceof Player) {
			System.out.println("Enemy collided with player!");
			((Player) returnedEntity).setIsAlive(false);
		}
	}

	/**
	 * Interact with the player appropriately depending on command
	 */
	@Override
    public void interactWith(Player p, String cmd) {
		p.movePlayer(cmd);
		if (p.getIsPotionActive() == false) {
        	p.setIsAlive(false);
		} else {
			killEnemy();
		}
    } 
	
	public void killEnemy() {
		dungeon.removeEntity(this);
		dungeon.goalUpdate(this);
		isAlive.setValue(false);
	}
    
	@Override
	public boolean isMoveToAble() {
		return true;
	}
	
	/**
	 * Checks enemy state, and change when player does/doesn't have potion, and whether enemy is in a certain state
	 */
	public void updateMovement() {
		if(dungeon.getPlayer().getIsPotionActive() && currMovement == chase) {
			currMovement = flee;
		} else if (!dungeon.getPlayer().getIsPotionActive() && currMovement == flee) {
			currMovement = chase;
		}
		
	}
	
	/**
	 * Returns Enemy's X Pos
	 * @return X coord
	 */
	public int getPlayerY() {
		return dungeon.getPlayer().getY();
	}
	
	/**
	 * Returns Enemy's Y Pos
	 * @return Y coord
	 */
	public int getPlayerX() {
		return dungeon.getPlayer().getX();
	}

	@Override
	public void updateGoalCheck(GoalCheck gc) {
		gc.setCurrEnemiesKilled(gc.getCurrEnemiesKilled() + 1);
		if (gc.getCurrEnemiesKilled() == gc.getTotalEnemies()) {
			System.out.println("All " + gc.getTotalEnemies() + " enemies killed");
			gc.setEnemies(true);
		}
	}

	@Override
	public void addEntity(GoalCheck gc) {
		gc.setTotalEnemies(gc.getTotalEnemies() + 1);	
	}
	
}
