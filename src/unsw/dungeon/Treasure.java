package unsw.dungeon;

public class Treasure extends Entity implements GoalCondition{
	
	Dungeon dungeon;

	public Treasure(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
	}
	/**
	 * Adds treasure to the player's inventory if they
	 * do not have one, and moves the player
	 */
	public void interactWith(Player p, String moveCmd) {
		p.addToInv(this);
		System.out.println("Treasure collected");
		this.getIsOnMap().setValue(false);
		dungeon.goalUpdate(this);
		p.rmvItemFromDungeon(this);
		p.movePlayer(moveCmd);	
	}
	
	@Override
	public boolean isMoveToAble() {
		return true;
	}
	@Override
	public void updateGoalCheck(GoalCheck gc) {
		gc.setCurrTreasuresCollected(gc.getCurrTreasuresCollected() + 1);
		if (gc.getCurrTreasuresCollected() == gc.getTotalTreasures()) {
			System.out.println("All "+ gc.getTotalTreasures() + " treasures collected");
			gc.setTreasure(true);
		}
	}
	@Override
	public void addEntity(GoalCheck gc) {
		gc.setTotalTreasures(gc.getTotalTreasures() + 1);
	}
}
