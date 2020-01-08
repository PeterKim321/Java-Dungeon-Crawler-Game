package unsw.dungeon;

public class DoorOpen implements DoorState {
	
	private Door door;
	
	public DoorOpen(Door door) {
		this.door = door;
	}
	/**
	 * Moves the player through the door
	 */
	@Override
	public void DoorInteractWithPlayer(Player p, String moveCmd) {
		p.movePlayer(moveCmd);
	}
	@Override
	public boolean isMoveToable() {
		return true;
	}
}
