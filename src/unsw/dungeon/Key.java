package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Key extends Entity {

	private int id;
	private BooleanProperty isInUse;
	
	public Key(int x, int y, int id) {
		super(x, y);
		this.id = id;
		isInUse = new SimpleBooleanProperty(false);
	}
	/**
	 * Adds key to the player's inventory if they do not
	 * already have a key, and move the player
	 */
	public void interactWith(Player p, String moveCmd) {
		if (!p.hasKey()) {		
			BooleanProperty newBool = new SimpleBooleanProperty(true);
			p.addToInv(this);
			System.out.println("Key " + id + " collected");
			this.getIsOnMap().setValue(false);
			this.getIsInUse().setValue(newBool.getValue());
			p.rmvItemFromDungeon(this);
		}
		p.movePlayer(moveCmd);
	}
	
	public void setisInUseToFalse() {
		BooleanProperty newBool = new SimpleBooleanProperty(false);
		this.getIsInUse().setValue(newBool.getValue());
	}
	
	public BooleanProperty getIsInUse() {
		return isInUse;
	}
	
	public void setIsInUse(BooleanProperty isInUse) {
		this.isInUse = isInUse;
	}
	
	/**
	 * Getter function for key id
	 * @return
	 */
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Key (" + getX() + ", " + getY() + ")";
	}
	
	@Override
	public boolean isMoveToAble() {
		return true;
	}

}
