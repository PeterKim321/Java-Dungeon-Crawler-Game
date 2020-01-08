package unsw.dungeon;

public class SpikeInactive implements SpikeState {

	@Override
	public SpikeState tick() {
		return new SpikeActive();
	}

	@Override
	public void interactWith(Player p, String moveCmd) {
		p.movePlayer(moveCmd);
	}

	@Override
	public boolean getIsActive() {
		return false;
	}

}
