package unsw.dungeon;

/**
 * Checks whether the goal is satisfied.
 * @author Jiwoo
 *
 */
public class GoalCheck {
    private boolean exit = false;
    private boolean enemies = false;
    private boolean boulders = false;
    private boolean treasure = false;
    
    private int totalEnemies = 0;
    private int totalSwitches = 0;
    private int totalTreasures = 0;
    
    private int currEnemiesKilled = 0;
    private int currSwitchesActivated = 0;
    private int currTreasuresCollected = 0;
    
    /**
     * Checks if goal conditions are satisfied, updates relevant values
     * Keeps a counter of enemies killed, switches activated, treasures collected
     * @param e
     * @param goal
     * @param goalComponent
     * @return True if goal conditions satisfied, false otherwise
     */
    public boolean checkGoals(GoalCondition e, Component goalComponent) {
    	
    	e.updateGoalCheck(this);
    	
    	if (goalComponent.getValue(exit, enemies, boulders, treasure)) {
    		return true;
    	} else
    		exit = false;
    		return false;
    }

	public boolean isExit() {
		return exit;
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}

	public int getCurrEnemiesKilled() {
		return currEnemiesKilled;
	}

	public void setCurrEnemiesKilled(int currEnemiesKilled) {
		this.currEnemiesKilled = currEnemiesKilled;
	}

	public int getCurrSwitchesActivated() {
		return currSwitchesActivated;
	}

	public void setCurrSwitchesActivated(int currSwitchesActivated) {
		this.currSwitchesActivated = currSwitchesActivated;
	}

	public int getCurrTreasuresCollected() {
		return currTreasuresCollected;
	}

	public void setCurrTreasuresCollected(int currTreasuresCollected) {
		this.currTreasuresCollected = currTreasuresCollected;
	}

	public boolean isEnemies() {
		return enemies;
	}

	public void setEnemies(boolean enemies) {
		this.enemies = enemies;
	}

	public boolean isBoulders() {
		return boulders;
	}

	public void setBoulders(boolean boulders) {
		this.boulders = boulders;
	}

	public boolean isTreasure() {
		return treasure;
	}

	public void setTreasure(boolean treasure) {
		this.treasure = treasure;
	}

	public int getTotalEnemies() {
		return totalEnemies;
	}

	public void setTotalEnemies(int totalEnemies) {
		this.totalEnemies = totalEnemies;
	}

	public int getTotalSwitches() {
		return totalSwitches;
	}

	public void setTotalSwitches(int totalSwitches) {
		this.totalSwitches = totalSwitches;
	}

	public int getTotalTreasures() {
		return totalTreasures;
	}

	public void setTotalTreasures(int totalTreasures) {
		this.totalTreasures = totalTreasures;
	}
	
}
