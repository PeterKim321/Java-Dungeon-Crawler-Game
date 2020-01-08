package unsw.dungeon;

public class SpikeActive implements SpikeState {

	@Override
	public SpikeState tick() {
		return new SpikeInactive();
	}

	@Override
	public void interactWith(Player p, String moveCmd) {
		p.movePlayer(moveCmd);
		p.setIsAlive(false);
	}

	@Override
	public boolean getIsActive() {
		return true;
	}

}
