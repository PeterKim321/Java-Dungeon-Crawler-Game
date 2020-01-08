package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.ImageView;

public class DoorClosed implements DoorState {

private Door door;
	
	public DoorClosed(Door door) {
		this.door = door;
	}
	/**
	 * Opens the door and moves the player through the door if they
	 * have the corresponding key, otherwise does nothing
	 */
	@Override
	public void DoorInteractWithPlayer(Player p, String moveCmd) {
			if (p.hasKeyWithID(door.getId())) {
				BooleanProperty newBool = new SimpleBooleanProperty(true);
				
				Key k = p.getKeyWithID(door.getId());
				k.setisInUseToFalse();
				door.setDoorState(door.getOpenState());
				p.rmvKeyFromInv();
				p.movePlayer(moveCmd);
			}
		return;
	}
	@Override
	public boolean isMoveToable() {
		return false;
	}
	
}
