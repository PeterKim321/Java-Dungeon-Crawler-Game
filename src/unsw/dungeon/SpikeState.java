package unsw.dungeon;

public interface SpikeState {
	
	public SpikeState tick();
	
	public void interactWith(Player p, String moveCmd);

	public boolean getIsActive();

}
