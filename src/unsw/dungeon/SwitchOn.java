package unsw.dungeon;

public class SwitchOn implements SwitchState {

	private Switch sw;
	
	public SwitchOn(Switch sw) {
		this.sw = sw;
	}
	
	/**
	 * Does nothing
	 */
	@Override
	public void activate() {
		return;
	}
	/**
	 * Deactivates the switch by changing state to SwitchOff
	 */
	@Override
	public void deactivate() {
		sw.setSwitchState(sw.getSwitchOff());
		System.out.println("Switch turned off");
	}

}
