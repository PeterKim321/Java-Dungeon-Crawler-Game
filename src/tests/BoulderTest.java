package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Boulder;
import unsw.dungeon.Door;
import unsw.dungeon.DoorClosed;
import unsw.dungeon.DoorOpen;
import unsw.dungeon.DoorState;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Portal;
import unsw.dungeon.Wall;
import unsw.dungeon.Switch;
import unsw.dungeon.SwitchOff;
import unsw.dungeon.SwitchOn;

public class BoulderTest {
	@Test
	void testAC4() {
		Dungeon d = new Dungeon(100, 100);
		Player p = new Player(d, 1, 1);
		Boulder boulder1 = new Boulder(2, 1);
		Boulder boulder2 = new Boulder(3, 1);
		
		d.addEntity(p);
		d.addEntity(boulder2);
		d.addEntity(boulder1);
		
		// Boulder is stacked, hence boulder1 does not move
		boulder1.interactWith(p, "RIGHT");
		assertEquals(boulder1.getX(), 2);
		assertEquals(boulder1.getY(), 1);
	}
	
	@Test
	void testAC3() {
		Dungeon d = new Dungeon(100, 100);
		Player p = new Player(d, 2, 1);
		Boulder boulder1 = new Boulder(3, 1);
		Boulder boulder2 = new Boulder(1, 1);
		Wall w = new Wall(4,1);
		Portal portal = new Portal(0, 1, 0);
		
		d.addEntity(p);
		d.addEntity(w);
		d.addEntity(boulder1);
		d.addEntity(portal);
		
		// Boulder cannot move due to wall
		boulder1.interactWith(p, "RIGHT");
		assertEquals(boulder1.getX(), 3);
		assertEquals(boulder1.getY(), 1);
		
		// Boulder cannot move due to portal
		boulder2.interactWith(p, "LEFT");
		assertEquals(boulder2.getX(), 1);
		assertEquals(boulder2.getY(), 1);
	
	}
	
	@Test
	// Test moving bolder in all 4 directions
	void testAttemptMove1() {
		Dungeon d = new Dungeon(100, 100);
		Player p = new Player(d, 0, 0);
		Boulder boulder = new Boulder(0, 1);
		
		d.addEntity(p);
		d.addEntity(boulder);
		
		boulder.interactWith(p, "DOWN");
		assertEquals(boulder.getX(), 0);
		assertEquals(boulder.getY(), 2);		
		
		boulder.interactWith(p, "UP");
		assertEquals(boulder.getX(), 0);
		assertEquals(boulder.getY(), 1);	
		
		boulder.interactWith(p, "RIGHT");
		assertEquals(boulder.getX(), 1);
		assertEquals(boulder.getY(), 1);	
		
		boulder.interactWith(p, "LEFT");
		assertEquals(boulder.getX(), 0);
		assertEquals(boulder.getY(), 1);	
		
		boulder.interactWith(p, "DOWN");
		boulder.interactWith(p, "DOWN");
		assertEquals(boulder.getX(), 0);
		assertEquals(boulder.getY(), 3);
	}
	
	@Test
	// Test wall collision
	void testAttemptMove2() {
		Dungeon d = new Dungeon(100, 100);
		Player p = new Player(d, 0, 0);
		Boulder boulder = new Boulder(0, 1);
		
		d.addEntity(p);
		d.addEntity(boulder);
		
		boulder.interactWith(p, "RIGHT");
		assertEquals(boulder.getX(), 1);
		assertEquals(boulder.getY(), 1);
	}
	
	@Test
	void testObserverPattern() {
		Dungeon d = new Dungeon(100, 100);
		Player p = new Player(d, 0, 0);
		Boulder boulder = new Boulder(3, 3);
		Switch testSwitch = new Switch(d, 3, 3);
		
		d.addEntity(p);
		d.addEntity(boulder);
		d.addEntity(testSwitch);
		
		boulder.attach(testSwitch);
		assert(testSwitch.getSwitchState() instanceof SwitchOff);
		boulder.notifyObservers();
		assert(testSwitch.getSwitchState() instanceof SwitchOn);
		
		boulder.interactWith(p, "DOWN");
		boulder.notifyObservers();
		assert(testSwitch.getSwitchState() instanceof SwitchOff);
		
		boulder.interactWith(p, "UP");
		boulder.notifyObservers();
		assert(testSwitch.getSwitchState() instanceof SwitchOn);
		
		// State doesn't change, since testSwitch is detached as observer
		boulder.detach(testSwitch);
		boulder.interactWith(p, "DOWN");
		boulder.notifyObservers();
		assert(testSwitch.getSwitchState() instanceof SwitchOn);
	}
}
