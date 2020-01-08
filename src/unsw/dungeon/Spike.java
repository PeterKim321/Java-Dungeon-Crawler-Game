package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.Random;

public class Spike extends Entity {
	
	private SpikeState spikeState;
	
	private Player p = null;
	private BooleanProperty spikeIsActive;
	private int maxCounterTick;

	public Spike(int x, int y) {
		super(x, y);
		spikeState = new SpikeInactive();
		spikeIsActive = new SimpleBooleanProperty();
		spikeIsActive.setValue(false);
		maxCounterTick = getRandNum();
	}
	
	public SpikeState getSpikeState() {
		return spikeState;
	}
	
	public BooleanProperty getSpikeIsActive() {
		return spikeIsActive;
	}

	private int counter = 0;
	
	public int getRandNum() {
		int min = 2;
		int max = 6;
		
	    Random random = new Random();
	    return random.nextInt(max - min) + min;
	}
	
	@Override
	public void tick() {
		counter++;
		if (!spikeIsActive.getValue() && counter < maxCounterTick) {
			return;
		}
		spikeState = spikeState.tick();
		spikeIsActive.setValue(spikeState.getIsActive());
		if (p != null && p.getX() == getX() && p.getY() == getY()) {
			spikeState.interactWith(p, "");
		}
		counter = 0;
	}
	
	@Override
	public boolean isMoveToAble() {
		return true;
	}
	
	@Override
	public void interactWith(Player p, String moveCmd) {
		spikeState.interactWith(p, moveCmd);
		this.p = p;
	}
	
	

}
