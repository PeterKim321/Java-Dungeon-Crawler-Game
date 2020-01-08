package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Potion extends Entity {
	
	private int potionDuration = 20;
	private Player p = null;
	private BooleanProperty isInUse;
	
	public Potion(int x, int y) {
		super(x, y);
		isInUse = new SimpleBooleanProperty(false);
	}
	/**
	 * Adds potion to player's inventory and moves them
	 */
	public void interactWith(Player p, String moveCmd) {
		this.p = p;
		BooleanProperty newBool = new SimpleBooleanProperty(true);
		if (!p.hasPotion() && p.getIsPotionActive() == false && potionDuration > 0) {				
			p.addToInv(this);
			p.setIsPotionActive(true);
			this.getIsInUse().setValue(newBool.getValue());
			System.out.println("Potion collected - potion effect ON");
			this.getIsOnMap().setValue(false);
		}	
		p.movePlayer(moveCmd);
	}
	/**
	 * Tracks how long the potion is active for
	 */
	@Override
	public void tick() {
		if (potionDuration > 0) {
			if (p == null) {
				return;
			}

			potionDuration--;
			if (p.hasPotion() &&  potionDuration <= 0) {
				BooleanProperty newBool = new SimpleBooleanProperty(false);
				p.rmvFromInv(this);
				p.setIsPotionActive(false);
				this.isInUse.setValue(newBool.getValue());
				System.out.println("potion effect FINISHED");
			}
		}
	}
	
	@Override
	public boolean isMoveToAble() {
		return true;
	}
	
	public BooleanProperty getIsInUse() {
		return isInUse;
	}
	public void setIsInUse(BooleanProperty isInUse) {
		this.isInUse = isInUse;
	}
}