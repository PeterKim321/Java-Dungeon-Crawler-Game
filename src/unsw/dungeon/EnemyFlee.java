package unsw.dungeon;

public class EnemyFlee extends EnemyMovement {
	
	public EnemyFlee(Enemy enemy) {
		super(enemy);
	}
	
	/**
	 * Attempt to move in a certain direction (Closer/Further from player depending on state)
	 */ 
	@Override
	public void attemptMove() {
		int newXPos, newYPos;
		String[] commands = {"UP", "DOWN", "LEFT", "RIGHT"};	
		
		int finalYPos = 0;
		int finalXPos = 0;
		String finalMoveCmd = "";
		double finalDist = Integer.MIN_VALUE;
		
		for (String moveCmd : commands) {
			newXPos = enemy.getX();
			newYPos = enemy.getY();
			
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
	    	
	    	if (isValidMove(newXPos, newYPos)) {
	    		double enemyDistanceFromPlayer = distanceFromPlayer(newXPos, newYPos);
	    		if (enemyDistanceFromPlayer > finalDist) {
	    			finalDist = enemyDistanceFromPlayer;
	    			finalMoveCmd = moveCmd;
	    			finalYPos = newYPos;
	    			finalXPos = newXPos;
	    		}
	    	}
		}

		enemy.interactMove(finalXPos, finalYPos, finalMoveCmd);
	}			
	
}
