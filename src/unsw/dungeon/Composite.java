package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class Composite implements Component {

	private String operator; 
	private List<Component> children;
		
	public Composite(String operator) {
		this.operator = operator;
		this.children = new ArrayList<Component>();
	}
	
	@Override
	public boolean getValue(boolean exit, boolean enemies, boolean boulders, boolean treasure) {
		boolean result = false;
		switch (operator) {
		case "AND":
			result = true;
			for (Component c : children) {
				if (!c.getValue(exit, enemies, boulders, treasure)) {
					result = false;
				}
			}
			break;
		case "OR":
			result = false;
			for (Component c : children) {
				if (c.getValue(exit, enemies, boulders, treasure)) {
					result = true;
				}
			}
			break;
		}
		return result;
	}
	
	public void add(Component c) {
		children.add(c);
	}
	
	public void remove(Component c) {
		children.remove(c);
	}
	
	

}
