package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Door extends Entity implements Observer{

	private DoorState openState;
	private DoorState closedState;
	
	private DoorState doorState;
	
	private BooleanProperty isOpen;
	
	private int id;
	
	public Door(int x, int y, int id) {
		super(x, y);
		openState = new DoorOpen(this);
		closedState = new DoorClosed(this);
		doorState = closedState;
		this.id = id;
		this.isOpen = new SimpleBooleanProperty();
		isOpen.setValue(false);
	}
	
	public Door(int x, int y, int id, String s) {
		super(x, y);
		openState = new DoorOpen(this);
		closedState = new DoorClosed(this);
		doorState = openState;
		this.id = id;
		this.isOpen = new SimpleBooleanProperty();
		isOpen.setValue(true);
	}
	/**
	 * Getter function for door id
	 * @return
	 */
	public int getId() {
		return id;
	}
	/**
	 * Getter function for doorState
	 * @return
	 */
	public DoorState getDoorState() {
		return doorState;
	}
	
	public BooleanProperty getIsOpen() {
		return isOpen;
	}
	/**
	 * Setter function for doorState.
	 * Prints relevant information to stdout for debugging purposes.
	 * @param newState
	 */
	public void setDoorState(DoorState newState) {
		if (newState instanceof DoorOpen && doorState instanceof DoorClosed) {
			isOpen.setValue(true);
			System.out.println("Door " + id + " opened");
		} else if (newState instanceof DoorClosed && doorState instanceof DoorOpen) {
			System.out.println("Door " + id + " closed");
		}
		this.doorState = newState;
	}
	/**
	 * Getter function for openState
	 * @return
	 */
	public DoorState getOpenState() {
		return openState;
	}
	/**
	 * Getter functino for closedState
	 * @return
	 */
	public DoorState getClosedState() {
		return closedState;
	}
	/**
	 * Interacts with the player appropriately depending
	 * on the door state
	 */
	public void interactWith(Player p, String moveCmd) {
		doorState.DoorInteractWithPlayer(p, moveCmd);
	}

	@Override
	public void updateObserved(Subject sub) {
		if (!(sub instanceof Switch))
			return;
		Switch sw = (Switch) sub;
		if (sw.getSwitchState() instanceof SwitchOn) {
			setDoorState(openState);
		} else {
			setDoorState(closedState);
		}
	}

	@Override
	public String toString() {
		return "Door (" + getX() + ", " + getY() + ")";
	}
	
	@Override
	public boolean isMoveToAble() {
		return doorState.isMoveToable();
	}
	
	

}
