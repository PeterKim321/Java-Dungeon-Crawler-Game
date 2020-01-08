package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Sword;

class SwordTest {

	@Test
	void testClassCreation() {
		Dungeon d = new Dungeon(100, 100);
		Sword s = new Sword(2,1);
		
		d.addEntity(s);
		
		assertEquals(s.getDurability(), 5);
		assertEquals(s.getX(), 2);
		assertEquals(s.getY(), 1);
		assertEquals(s.isMoveToAble(), true);
	}
	
	@Test
	void testInteraction1() {
		Dungeon d = new Dungeon(100, 100);
		Player p = new Player(d, 1, 1);
		Sword s = new Sword(2,1);
		
		d.addEntity(p);
		d.addEntity(s);
		
		assertEquals(p.hasSword(), false);
		s.interactWith(p, "RIGHT");
		assertEquals(p.hasSword(), true);
		assertEquals(p.getX(), 2);
		assertEquals(p.getY(), 1);
		
	}
	
	@Test
	void testInteraction2() {
		Dungeon d = new Dungeon(100, 100);
		Player p = new Player(d, 1, 1);
		Sword s = new Sword(1,0);
		
		d.addEntity(p);
		d.addEntity(s);
		
		assertEquals(p.hasSword(), false);
		s.interactWith(p, "UP");
		assertEquals(p.hasSword(), true);
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 0);
		
	}
	
	@Test
	void testInteraction3() {
		Dungeon d = new Dungeon(100, 100);
		Player p = new Player(d, 1, 1);
		Sword s = new Sword(0,1);
		
		d.addEntity(p);
		d.addEntity(s);
		
		assertEquals(p.hasSword(), false);
		s.interactWith(p, "LEFT");
		assertEquals(p.hasSword(), true);
		assertEquals(p.getX(), 0);
		assertEquals(p.getY(), 1);
		
	}
	
	@Test
	void testInteraction4() {
		Dungeon d = new Dungeon(100, 100);
		Player p = new Player(d, 1, 1);
		Sword s = new Sword(1,2);
		
		d.addEntity(p);
		d.addEntity(s);
		
		assertEquals(p.hasSword(), false);
		s.interactWith(p, "DOWN");
		assertEquals(p.hasSword(), true);
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 2);
		
	}
	
	@Test
	void testSwordDurability() {
		Dungeon d = new Dungeon(100, 100);
		Player p = new Player(d, 0, 0);
		Sword s = new Sword(10,10);
		
		p.attemptAttack(); // Fail, cause no sword
		assertFalse(p.hasSword());
		p.addToInv(s);
		assertTrue(p.hasSword());
		
		p.attemptAttack();
		p.attemptAttack();
		p.attemptAttack();
		
		assertEquals(2, s.getDurability());
		
		p.attemptAttack();
		assertTrue(p.hasSword());
		p.attemptAttack();
		
		assertFalse(p.hasSword());	
	}

}
