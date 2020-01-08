package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;
    private List<Entity> visualEntities;
    
    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
        visualEntities = new ArrayList<Entity>();
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        
        loadInventoryEntities(dungeon);
        
        JSONObject goalCondition = json.getJSONObject("goal-condition");
        
        Component gc = loadGoalConditions(dungeon, goalCondition);
        dungeon.setGoalComponent(gc);
        
        return dungeon;
    }
    private void loadInventoryEntities(Dungeon dungeon) {
		Wall wall1 = new Wall(dungeon.getWidth(), 0);
		onLoad(wall1);
		dungeon.addEntity(wall1);
		
		Wall wall2 = new Wall(dungeon.getWidth(), dungeon.getHeight() - 1);
		onLoad(wall2);
		dungeon.addEntity(wall2);
		
		for (int i=1; i<dungeon.getHeight()-1; i++) {
			Border border = new Border(dungeon.getWidth(), i);
        	onLoad(border);
        	dungeon.addEntity(border);
		}
		for (int j=0; j<dungeon.getHeight(); j++) {
			Wall wall = new Wall(dungeon.getWidth()+1, j);
        	onLoad(wall);
        	dungeon.addEntity(wall);
		}
	}

	/**
     * Loads the goal conditions recursively in a composite pattern design
     * Returns the root node in the tree of components
     * @param dungeon
     * @param goalCondition
     * @return
     */
    private Component loadGoalConditions(Dungeon dungeon, JSONObject goalCondition) {
		String goal = goalCondition.getString("goal");
		Component result = null;
		switch (goal) {
		case "exit":
		case "enemies":
		case "boulders":
		case "treasure":
			result = new Leaf(goal);
			break;
		case "AND":
		case "OR":
			result = new Composite(goal);
			JSONArray subgoals = goalCondition.getJSONArray("subgoals");
			for (int i = 0; i < subgoals.length(); i++) {
	            ((Composite) result).add(loadGoalConditions(dungeon, subgoals.getJSONObject(i)));
	        }
			break;
		}
		
		return result;
	}

	private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");
        int id;

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall);
            entity = wall;
            break;
        case "door":
        	id = json.getInt("id");
        	Door door = new Door(x, y, id);
        	onLoad(door);
        	entity = door;
        	break;
        case "exit":
        	Exit exit = new Exit(dungeon, x, y);
        	onLoad(exit);
        	entity = exit;
        	break;
        case "sword":
        	Sword sword = new Sword(x, y);
        	onLoad(sword);
        	entity = sword;
        	visualEntities.add(entity);
        	break;
        case "key":
        	id = json.getInt("id");
        	Key key = new Key(x, y, id);
        	onLoad(key);
        	entity = key;
        	break;
        case "enemy": 			// QUICKFIX
        case "enemyElf":
        	Elf elf = new Elf(dungeon, x, y);
        	onLoad(elf);
        	entity = elf;
        	break;
        case "enemyHound":
        	Hound hound = new Hound(dungeon, x, y);
        	onLoad(hound);
        	entity = hound;
        	break;
        case "enemyGnome":
        	Gnome gnome = new Gnome(dungeon, x, y);
        	onLoad(gnome);
        	entity = gnome;
        	break;
        case "portal":
        	id = json.getInt("id");
        	Portal portal = new Portal(x, y, id);
        	Portal otherP = dungeon.getOtherPortal(portal);
        	System.out.println("THIS P " + portal + " OTHER P " + otherP);
        	if (otherP != null) {
        		portal.setOtherPortal(otherP);
        		otherP.setOtherPortal(portal);
        	}
        	onLoad(portal);
        	entity = portal;
        	break;
        case "boulder":
        	Boulder boulder = new Boulder(x, y);
        	dungeon.observeBoulder(boulder);
        	onLoad(boulder);
        	entity = boulder;
        	break;
        case "treasure":
        	Treasure treasure = new Treasure(dungeon, x, y);
        	onLoad(treasure);
        	entity = treasure;
        	break;
        case "switch":
        	Switch sw = new Switch(dungeon, x,y);
        	onLoad(sw);
        	entity = sw;
        	break;
        case "potion":
        	Potion potion = new Potion(x, y);
        	onLoad(potion);
        	entity = potion;
        	break;
        case "spike":
        	Spike spike = new Spike(x, y);
        	onLoad(spike);
        	entity = spike;
        	break;
        case "displayBorder":
        	Border border = new Border(x, y);
        	onLoad(border);
        	entity = border;
        	break;
        }
        dungeon.addEntity(entity);
    }
    
    //List of abstract methods for the other entities
    public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);
    
    public abstract void onLoad(Exit exit);
    
    public abstract void onLoad(Door door);
    
    public abstract void onLoad(Sword sword);
    
    public abstract void onLoad(Potion potion);
    
    public abstract void onLoad(Treasure treasure);
    
    public abstract void onLoad(Key key);
    
    public abstract void onLoad(Elf elf);
    
    public abstract void onLoad(Hound elf);
    
    public abstract void onLoad(Portal portal);
    
    public abstract void onLoad(Boulder boulder);
    
    public abstract void onLoad(Switch sw);
    
    public abstract void onLoad(Spike spike);
    
    public abstract void onLoad(Border boder);
    
    public abstract void onLoad(Gnome gnome);
}
