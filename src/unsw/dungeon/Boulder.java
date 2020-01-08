package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class Boulder extends Entity implements Subject {

	private MovementBehaviour moveB;
	private List<Observer> observers;
	
	public Boulder(int x, int y) {
		super(x, y);
		this.moveB = new MovementBehaviour(this);
		this.observers = new ArrayList<Observer>();
	}
	/**
	 * Moves the player and the boulder if valid,
	 * otherwise does nothing
	 */
	public void interactWith(Player p, String moveCmd) {
		Entity entity = null;
		switch(moveCmd) {
		case "UP":
			entity = p.checkForObject(getX(), getY()-1);
			break;
		case "DOWN":
			entity = p.checkForObject(getX(), getY()+1);
			break;
		case "LEFT":
			entity = p.checkForObject(getX()-1, getY());
			break;
		case "RIGHT":
			entity = p.checkForObject(getX()+1, getY());
			break;
		}
		if (entity == null || entity.isMoveToAble() ) {
			moveB.move(moveCmd);
			p.movePlayer(moveCmd);
			notifyObservers();
		}
		
		return;
	}
	@Override
	public void attach(Observer obs) {
		observers.add(obs);
	}

	@Override
	public void detach(Observer obs) {
		observers.remove(obs);
	}

	@Override
	public void notifyObservers() {
		for (Observer obs : observers) {
			obs.updateObserved(this);
		}
	}

}
