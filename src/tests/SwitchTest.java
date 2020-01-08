package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Boulder;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Switch;
import unsw.dungeon.SwitchOff;
import unsw.dungeon.SwitchOn;

class SwitchTest {
	@Test
	void testClassCreation() {
		Dungeon d = new Dungeon(100, 100);
		Player p = new Player(d, 1, 1);
		Switch sw = new Switch(d, 2, 1);
		
		d.addEntity(p);
		d.addEntity(sw);
		
		assert(sw.getSwitchState() instanceof SwitchOff);
	}
	
	@Test
	void testUpdateObserved() {
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
	
	@Test
	void testPlayerInteraction1() {
		Dungeon d = new Dungeon(100, 100);
		Player p = new Player(d, 1, 1);
		Switch testSwitch = new Switch(d, 2, 1);
		
		d.addEntity(p);
		d.addEntity(testSwitch);
		
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 1);
		testSwitch.interactWith(p, "RIGHT");
		assertEquals(p.getX(), 2);
		assertEquals(p.getY(), 1);
	}
	
	@Test
	void testPlayerInteraction2() {
		Dungeon d = new Dungeon(100, 100);
		Player p = new Player(d, 1, 1);
		Switch testSwitch = new Switch(d, 1, 0);
		
		d.addEntity(p);
		d.addEntity(testSwitch);
		
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 1);
		testSwitch.interactWith(p, "UP");
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 0);
	}
	
	@Test
	void testPlayerInteraction3() {
		Dungeon d = new Dungeon(100, 100);
		Player p = new Player(d, 1, 1);
		Switch testSwitch = new Switch(d, 0, 1);
		
		d.addEntity(p);
		d.addEntity(testSwitch);
		
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 1);
		testSwitch.interactWith(p, "LEFT");
		assertEquals(p.getX(), 0);
		assertEquals(p.getY(), 1);
	}
	
	@Test
	void testPlayerInteraction4() {
		Dungeon d = new Dungeon(100, 100);
		Player p = new Player(d, 1, 1);
		Switch testSwitch = new Switch(d, 1, 2);
		
		d.addEntity(p);
		d.addEntity(testSwitch);
		
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 1);
		testSwitch.interactWith(p, "DOWN");
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 2);
	}
	
	@Test
	void testIsMoveToAble() {
		Dungeon d = new Dungeon(100, 100);
		Switch testSwitch = new Switch(d, 3, 3);
		assertEquals(testSwitch.isMoveToAble(), true);
	}

}
