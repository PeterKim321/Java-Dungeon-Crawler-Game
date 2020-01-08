package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Wall;

class WallTest {

	@Test
	void testInteractWith() {
		Dungeon d = new Dungeon(100,100);
		Player p = new Player(d, 1, 1);
		Wall w1 = new Wall(0, 1);
		Wall w2 = new Wall(1, 0);
		Wall w3 = new Wall(1, 2);
		Wall w4 = new Wall(2, 1);
		
		d.addEntity(w1);
		d.addEntity(w2);
		d.addEntity(w3);
		d.addEntity(w4);
		d.addEntity(p);
		
		w1.interactWith(p, "LEFT");
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 1);
		
		w2.interactWith(p, "UP");
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 1);
		
		w3.interactWith(p, "DOWN");
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 1);
		
		w4.interactWith(p, "RIGHT");
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 1);
	}
}