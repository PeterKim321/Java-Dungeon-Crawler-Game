package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Key;
import unsw.dungeon.Player;

class KeyTest {

	@Test
	void testPickup() {
		Dungeon d = new Dungeon(100, 100);
		Player p = new Player(d, 1, 1);
		Key key = new Key(2, 1, 0);
		key.interactWith(p, "RIGHT");
		assertTrue(p.hasKey());
		assertTrue(p.hasKeyWithID(0));
		assertEquals(p.getX(), 2);
		assertEquals(p.getY(), 1);
	}
	
	@Test
	void testHasKey() {
		Player p = new Player(null, 1, 1);
		Key k1 = new Key(10, 10, 10);
		assertFalse(p.hasKeyWithID(0));
		p.addToInv(k1);
		Key key = new Key(2, 1, 0);
		key.interactWith(p, "RIGHT");
		assertTrue(p.hasKey());
		assertFalse(p.hasKeyWithID(0));
		
		// Player cannot hold 2 keys at the same time
		assertTrue(p.hasKeyWithID(10));
		assertEquals(p.getX(), 2);
		assertEquals(p.getY(), 1);
	}

}
