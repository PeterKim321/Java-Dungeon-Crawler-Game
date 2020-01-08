package unsw.dungeon;

public class SwitchOff implements SwitchState {
	
	private Switch sw;
	
	public SwitchOff(Switch sw) {
		this.sw = sw;
	}
	/**
	 * Activates the switch by changing state to SwitchOn
	 */
	@Override
	public void activate() {
		sw.setSwitchState(sw.getSwitchOn());
		System.out.println("Switch turned on");
	}
	/**
	 * Does nothing
	 */
	@Override
	public void deactivate() {
		return;	
	}

}
