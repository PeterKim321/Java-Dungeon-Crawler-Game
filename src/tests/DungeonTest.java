package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Boulder;
import unsw.dungeon.Door;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Key;
import unsw.dungeon.Portal;
import unsw.dungeon.Switch;
import unsw.dungeon.SwitchOff;
import unsw.dungeon.SwitchOn;

class DungeonTest {

	@Test
	void testGetAvailablePortal1() {
		Dungeon d = new Dungeon(100, 100);
		Portal p1 = new Portal(1, 1, 1);
		d.addEntity(p1);
		Portal p2 = new Portal(10, 10, 1);
		assertEquals(d.getOtherPortal(p2), p1);
	}
	
	@Test
	void testEntityAddAndRemove() {
		Dungeon d = new Dungeon(100, 100);
		Portal p = new Portal(1, 1, 1);
		
		assertEquals(d.getEntityList().size(), 0);
		d.addEntity(p);
		assertEquals(d.getEntityList().size(), 1);
		d.removeEntity(p);
		assertEquals(d.getEntityList().size(), 0);
	}
	
	@Test 
	void testBoulderObserver() {
		Dungeon d = new Dungeon(100, 100);
		Boulder b = new Boulder(1,1);
		Switch s = new Switch(d, 1,1);
		
		d.addEntity(b);
		d.addEntity(s);
		
		b.notifyObservers();
		assert(s.getSwitchState() instanceof SwitchOff);
		d.observeBoulder(b);
		assert(s.getSwitchState() instanceof SwitchOff);
		b.notifyObservers();
		assert(s.getSwitchState() instanceof SwitchOn);
	}
	
	@Test
	void testGetAvailablePortal2() {
		Dungeon d = new Dungeon(100, 100);
		Portal p1 = new Portal(1, 1, 1);
		d.addEntity(p1);
		assertEquals(d.getOtherPortal(p1), null);
	}
	
	@Test
	void testCheckForObject1() {
		Dungeon d = new Dungeon(100, 100);
		Door door = new Door(1, 1, 1);
		d.addEntity(door);
		assertEquals(d.checkForObject(1, 1), door);
	}
	
	@Test
	void testCheckForObject2() {
		Dungeon d = new Dungeon(100, 100);
		assertEquals(d.checkForObject(1, 1), null);
	}

}
