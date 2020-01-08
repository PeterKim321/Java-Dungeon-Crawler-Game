package unsw.dungeon;

public class GnomeFlee extends EnemyMovement {
	
	public GnomeFlee(Enemy enemy) {
		super(enemy);
	}
	
	@Override
	public void attemptMove() {
		
		int width = enemy.dungeon.getWidth();
		int height = enemy.dungeon.getHeight();
		
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
	    	
			double enemyDistanceFromPlayer = distanceFromPlayer(newXPos, newYPos);
			if (enemyDistanceFromPlayer > finalDist) {
				finalDist = enemyDistanceFromPlayer;
				finalMoveCmd = moveCmd;
			
				while (newXPos >= 0 && newXPos < width && newYPos >= 0 && newYPos < height) {
					if (isValidMove(newXPos, newYPos)) {
						finalYPos = newYPos;
						finalXPos = newXPos;
						break;
					}	
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
		    	}		 
			}	
		}

		enemy.attemptTeleport(finalXPos, finalYPos);
	}
}
