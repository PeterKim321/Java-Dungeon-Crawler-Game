package unsw.dungeon;

public class EnemyMovement {
	protected Enemy enemy;
	
	public EnemyMovement(Enemy enemy) {
		this.enemy = enemy;
	}
	
	
	public void attemptMove() {
		return;
	}
	
	/**
     * Checks whether moving to a given set of coordinates is possible
     */
	public Boolean isValidMove(int x, int y) {
		Entity returnedEntity = enemy.dungeon.checkForObject(x, y);
		if (returnedEntity == null || (returnedEntity.isMoveToAble())
			&& !(returnedEntity instanceof Enemy)) {
			return true;
    	} else {
    		return false;
    	}
	}
	
	/**
     * Calculates the Euclidean distance between the enemy and the player
     * @param x1 xPos of enemy
     * @param y1 yPos of enemy
     * @return distance between player and enemy
     */
	public double distanceFromPlayer(int x1, int y1) {
		int playerX = enemy.getPlayerX();
		int playerY = enemy.getPlayerY();
		
		return Math.sqrt((playerY - y1) * (playerY - y1) + (playerX - x1) * (playerX - x1));
	}
}
