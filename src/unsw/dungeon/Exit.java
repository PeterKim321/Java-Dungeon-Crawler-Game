package unsw.dungeon;

public class Exit extends Entity implements GoalCondition {

	Dungeon dungeon;
	
	public Exit(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
	}

	/**
	 * Finishes the session
	 */
	public void interactWith(Player p, String moveCmd) {
		dungeon.goalUpdate(this);
		//finishSession();
	}
	
	/** 
	 * Close the game session, rediret to end UI
	 */
	public void finishSession() {
		// End the game, go to leaderboard or end UI
		System.out.println("Player has reached the exit! Well done!");
		System.exit(0);
	}

	@Override
	public void updateGoalCheck(GoalCheck gc) {
		gc.setExit(true);	
	}

	@Override
	public void addEntity(GoalCheck gc) {
		
	}
} 
