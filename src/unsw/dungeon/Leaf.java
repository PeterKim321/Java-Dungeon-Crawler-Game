package unsw.dungeon;

public class Leaf implements Component {
	
	private String condition;
	
	public Leaf(String condition) {
		this.condition = condition;
	}
	
	@Override
	public boolean getValue(boolean exit, boolean enemies, boolean boulders, boolean treasure) {
		switch (condition) {
		case "exit":
			return exit;
		case "enemies":
			return enemies;
		case "boulders":
			return boulders;
		case "treasure":
			return treasure;
		}
		
		return false;
	}
 
}
