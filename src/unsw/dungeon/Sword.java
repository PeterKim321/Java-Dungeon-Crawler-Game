package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Sword extends Entity {

	private IntegerProperty durability;
	
	public Sword(int x, int y) {
		super(x, y);
		this.durability = new SimpleIntegerProperty(5);
	}
	/**
	 * Adds sword to player's inventory if they are not
	 * already holding a sword, and moves the player
	 */
	public void interactWith(Player p, String moveCmd) {
		if (!p.hasSword()) {		
			p.addToInv(this);
			System.out.println("Sword collected");
			this.getIsOnMap().setValue(false);
			p.rmvItemFromDungeon(this);
		}
		p.movePlayer(moveCmd);	
	}
	/**
	 * Getter function for durability
	 * @return
	 */
	public int getDurability() {
		return durability.getValue();
	}
	
	public IntegerProperty getDuirabilityProperty() {
		return durability;
	}
	
	/**
	 * Setter function for durability
	 * @param durability
	 */
	public void setDurability(int durability) {
		IntegerProperty result = new SimpleIntegerProperty(durability);
		
		this.durability.setValue(result.getValue());
	}
	
	/**
	 * Reduce durability of sword
	 */
	public Boolean reduceDurability() {
		int newDurability = durability.getValue() - 1;
		IntegerProperty result = new SimpleIntegerProperty(newDurability);
		
		this.setDurability(result.getValue());
		
		if (durability.getValue() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean isMoveToAble() {
		return true;
	}
}
