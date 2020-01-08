package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Elf;
import unsw.dungeon.Enemy;
import unsw.dungeon.EnemyChase;
import unsw.dungeon.EnemyFlee;
import unsw.dungeon.Entity;
import unsw.dungeon.Player;
import unsw.dungeon.Potion;
import unsw.dungeon.Sword;
import unsw.dungeon.Wall;

class EnemyTest {
	@Test
	void testAC1() {
		Dungeon d = new Dungeon(100, 100);
		Player p = new Player(d, 0, 0);
		Elf e = new Elf(d, 2, 0);
		Elf e2 = new Elf(d, 1, 0);
		Elf e3 = new Elf(d, 0, 1);
		Elf e4 = new Elf(d, 1, 0);
		Sword s = new Sword(10,10);
		
		// Add sword to Inv
		p.addToInv(s);
		
		// Nothing happens, enemy out of range
		assertEquals(getNumEnemy(d), 0);
		d.addEntity(e);
		d.addEntity(p);
		assertEquals(getNumEnemy(d), 1);
		p.attemptAttack();
		
		e.killEnemy();
		assertEquals(getNumEnemy(d), 0);
		
		// Player kill enemy
		d.addEntity(e2);
		assertEquals(getNumEnemy(d), 1);
		p.attemptAttack();
		assertEquals(0, getNumEnemy(d));
		
		// Player kill multiple enemies
		d.addEntity(e4);
		d.addEntity(e3);
		assertEquals(2, getNumEnemy(d));
		p.attemptAttack();
		assertEquals(0, getNumEnemy(d));
		
		// Attack gets blocked by Wall (refer to console out)
		Wall w = new Wall(1,0);
		d.addEntity(w);
		p.attemptAttack();
	}
	
	@Test
	void testAC2() {
		Dungeon d = new Dungeon(100, 100);
		Player p = new Player(d, 1, 0);
		Elf e = new Elf(d, 2, 0);
		
		d.addEntity(e);
		d.addEntity(p);
		
		assertTrue(p.isPlayerAlive());
		p.attemptMove("RIGHT");
		assertFalse(p.isPlayerAlive());
	}
	
	@Test
	void testAC3() {
		Dungeon d = new Dungeon(100, 100);
		Player p = new Player(d, 1, 0);
		Elf e = new Elf(d, 2, 0);

		d.addEntity(e);
		d.addEntity(p);
		
		assertTrue(p.isPlayerAlive());
		e.interactWith(p, "LEFT");
		assertFalse(p.isPlayerAlive());
	}
	
	@Test
	void testInteractWith1() {
		Dungeon d = new Dungeon(100,100);
		Player p = new Player(d, 1, 1);
		Elf e = new Elf(d, 2, 1);
		
		d.addEntity(e);
		d.addEntity(p);
		
		assertEquals(1, getNumEnemy(d));
		e.killEnemy();
		assertEquals(0, getNumEnemy(d));
	}
	
	@Test
	void testInteractWith2() {
		Dungeon d = new Dungeon(100,100);
		Player p = new Player(d, 1, 1);
		Elf e = new Elf(d, 2, 1);
		
		d.addEntity(e);
		d.addEntity(p);
		
		// Game will go into Post Game Screen. Currently enemy num should not
		// change
		assertEquals(1, getNumEnemy(d));
		e.interactWith(p, "RIGHT");
		assertEquals(1, getNumEnemy(d));
	}
	
	@Test
	void testState() {
		Dungeon d = new Dungeon(100,100);
		Player p = new Player(d, 1, 1);
		Elf e = new Elf(d, 2, 1);
		Potion potion = new Potion(1,2);
		
		d.addEntity(potion);
		d.addEntity(e);
		d.addEntity(p);
		d.setPlayer(p);
		
		assert(e.getCurrMovement() instanceof EnemyChase);
		potion.interactWith(p, "DOWN");
		e.updateMovement();
		assert(e.getCurrMovement() instanceof EnemyFlee);
		
		p.rmvFromInv(potion);
		p.setIsPotionActive(false);
		e.updateMovement();
		assert(e.getCurrMovement() instanceof EnemyChase);
	}
	
	@Test
	void testMove() {
		Dungeon d = new Dungeon(100,100);
		Player p = new Player(d, 1, 1);
		Elf e = new Elf(d, 5, 1);
		
		d.addEntity(e);
		d.addEntity(p);
		d.setPlayer(p);
		
		// Moves closer to the player
		assertEquals(5, e.getX());
		assertEquals(1, e.getY());
		e.getCurrMovement().attemptMove();
		assertEquals(4, e.getX());
		assertEquals(1, e.getY());
		e.getCurrMovement().attemptMove();
		assertEquals(3, e.getX());
		assertEquals(1, e.getY());
	}
	
	private int getNumEnemy(Dungeon d) {
		int currNumEnemy = 0;
		
		for (Entity e : d.getEntityList()) {
			if (e instanceof Enemy) {
				currNumEnemy++;
			}
		}
		
		return currNumEnemy;
	}

}
