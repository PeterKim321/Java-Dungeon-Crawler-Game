package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Door;
import unsw.dungeon.DoorClosed;
import unsw.dungeon.Key;
import unsw.dungeon.Player;
import unsw.dungeon.Switch;

class DoorTest {
	@Test
	void testAC1() {
		Door door = new Door(2, 1, 1);
		assert(door.getDoorState() instanceof DoorClosed);
	}
	
	

	@Test
	void testClosed() {
		Player p = new Player(null, 1, 1);
		Door door = new Door(2, 1, 1);
		assertFalse(door.isMoveToAble());
		door.interactWith(p, "RIGHT");
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 1);
		assertFalse(door.isMoveToAble());
	}
	
	@Test
	void testOpenWithKey() {
		Player p = new Player(null, 1, 1);
		Door door = new Door(2, 1, 1);
		Key key = new Key(10, 10, 1);
		p.addToInv(key);
		assertTrue(p.hasKeyWithID(1));
		door.interactWith(p, "RIGHT");
		assertEquals(p.getX(), 2);
		assertEquals(p.getY(), 1);
		assertTrue(door.isMoveToAble());
		assertFalse(p.hasKeyWithID(1));
	}
	
	@Test
	void testWithWrongKey() {
		Player p = new Player(null, 1, 1);
		Door door = new Door(2, 1, 1);
		Key key = new Key(10, 10, 2);
		p.addToInv(key);
		door.interactWith(p, "RIGHT");
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 1);
		assertFalse(door.isMoveToAble());
	}
	

}
