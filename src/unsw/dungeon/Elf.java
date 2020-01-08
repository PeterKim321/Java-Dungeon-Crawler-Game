package unsw.dungeon;

public class Elf extends Enemy {
	int moveCounter = 0;
	
	public Elf (Dungeon dungeon, int x, int y) {
		super(dungeon, x,y);
	}
	
	@Override
	public void tick() {
		if (moveCounter == 2) {
			super.tick();
			moveCounter = 0;
		} else {
			moveCounter++;
		}
	}
}
