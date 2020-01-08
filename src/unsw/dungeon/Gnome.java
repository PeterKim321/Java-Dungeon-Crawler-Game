package unsw.dungeon;

public class Gnome extends Enemy {
	
	int moveCounter = 0;
	
	public Gnome(Dungeon dungeon, int x, int y) {
		super(dungeon, x,y);
		chase = new GnomeChase(this);
		flee = new GnomeFlee(this);
        currMovement = chase;
	}

	@Override
	public void tick() {
		if (moveCounter == 1) {
			super.tick();
			moveCounter = 0;
		} else {
			moveCounter++;
		}
	}

}
