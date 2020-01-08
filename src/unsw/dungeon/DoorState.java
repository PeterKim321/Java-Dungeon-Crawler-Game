package unsw.dungeon;

public interface DoorState {
	
	public void DoorInteractWithPlayer(Player p, String moveCmd);

	public boolean isMoveToable();
}
