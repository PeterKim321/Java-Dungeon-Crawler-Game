package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Treasure;

class TreasureTest {
	
	@Test
	void testInteractWith1() {
		Dungeon d = new Dungeon(100, 100);
		Player p = new Player(d, 0, 0);
		Treasure tr = new Treasure(d, 1, 0);
		tr.interactWith(p, "RIGHT");
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 0);
		assertEquals(1, p.hasTreasure());
	}
	
	@Test
	void testInteractWith2() {
		Dungeon d = new Dungeon(100, 100);
		Player p = new Player(d, 1, 1);
		Treasure tr = new Treasure(d, 1, 0);
		tr.interactWith(p, "UP");
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 0);
		assertEquals(1, p.hasTreasure());
	}
	
	@Test
	void testInteractWith3() {
		Dungeon d = new Dungeon(100, 100);
		Player p = new Player(d, 1, 1);
		Treasure tr = new Treasure(d, 1, 2);
		tr.interactWith(p, "DOWN");
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 2);
		assertEquals(1, p.hasTreasure());
	}
	
	@Test
	void testInteractWith4() {
		Dungeon d = new Dungeon(100, 100);
		Player p = new Player(d, 1, 1);
		Treasure tr = new Treasure(d, 0, 1);
		tr.interactWith(p, "LEFT");
		assertEquals(p.getX(), 0);
		assertEquals(p.getY(), 1);
		assertEquals(1, p.hasTreasure());
	}

	@Test
	void testIsMoveToAble() {
		Treasure tr = new Treasure(new Dungeon(100, 100), 0,0);
		assertTrue(tr.isMoveToAble());
	}
}
