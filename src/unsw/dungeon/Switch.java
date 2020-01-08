package unsw.dungeon;

public class Switch extends Entity implements Observer, GoalCondition{
	
	private SwitchState switchOn;
	private SwitchState switchOff;
	
	private SwitchState switchState;
	
	private Dungeon dungeon;
	
	public Switch(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
		switchOn = new SwitchOn(this);
		switchOff = new SwitchOff(this);
		switchState = switchOff;
	}

	/**
	 * Getter function for switchOn
	 * @return
	 */
	public SwitchState getSwitchOn() {
		return switchOn;
	}
	/**
	 * Getter function for switchOff
	 * @return
	 */
	public SwitchState getSwitchOff() {
		return switchOff;
	}
	/**
	 * Getter function for switchState
	 * @return
	 */
	public SwitchState getSwitchState() {
		return switchState;
	}
	/**
	 * Setter function for switchState
	 * Updates if changing state
	 * @param switchState
	 */
	public void setSwitchState(SwitchState newState) {		
		if (newState instanceof SwitchOn && switchState instanceof SwitchOff) {
			dungeon.goalUpdate(this);
		} else if (newState instanceof SwitchOff && switchState instanceof SwitchOn) {
			dungeon.goalUpdate(this);
		}
		this.switchState = newState;
		
	}
	/**
	 * Moves the player onto the switch
	 */
	public void interactWith(Player p, String moveCmd) {
		p.movePlayer(moveCmd);
	}

	@Override
	public String toString() {
		return "Switch (" + getX() + ", " + getY() + ")";
	}
	
	@Override
	public boolean isMoveToAble() {
		return true;
	}

	private Boulder currB = null;
	
	@Override
	public void updateObserved(Subject sub) {
		if (!(sub instanceof Boulder)) {
			return;
		}
		Boulder b = (Boulder) sub;
		if (b.getX() == this.getX() && b.getY() == this.getY()) {
			currB = b;
			switchState.activate();
		} else if (b == currB) {
			switchState.deactivate();
		}
	}

	@Override
	public void updateGoalCheck(GoalCheck gc) {
		if (switchState instanceof SwitchOff) {
			gc.setCurrSwitchesActivated(gc.getCurrSwitchesActivated() + 1);
		} else { 
			gc.setCurrSwitchesActivated(gc.getCurrSwitchesActivated() - 1);
		}
		
		if (gc.getCurrSwitchesActivated() == gc.getTotalSwitches()) {
			System.out.println("All "+ gc.getTotalSwitches() +" switches activated");
			gc.setBoulders(true);
		} else {
			gc.setBoulders(false);
		}
	}

	@Override
	public void addEntity(GoalCheck gc) {
		gc.setTotalSwitches(gc.getTotalSwitches() + 1);
	}

}
