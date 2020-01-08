package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Boulder;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Elf;
import unsw.dungeon.Enemy;
import unsw.dungeon.Entity;
import unsw.dungeon.Key;
import unsw.dungeon.Player;
import unsw.dungeon.Potion;
import unsw.dungeon.Switch;
import unsw.dungeon.Sword;
import unsw.dungeon.Treasure;
import unsw.dungeon.Wall;

class PlayerTest {

	@Test
	void testAttemptMove1() {
		Dungeon d = new Dungeon(100, 100);
		Player p = new Player(d, 1, 1);
		d.addEntity(p);
		d.addEntity(new Wall(2, 1));
		p.attemptMove("RIGHT");
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 1);
	}
	
	@Test
	void testAttemptMove2() {
		Dungeon d = new Dungeon(100, 100);
		Player p = new Player(d, 1, 1);
		d.addEntity(p);
		p.attemptMove("DOWN");
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 2);
	}
	
	@Test
	void testTeleport() {
		Dungeon d = new Dungeon(100, 100);
		Player p = new Player(d, 1, 1);
		d.addEntity(p);
		
		assertEquals(1, p.getX());
		assertEquals(1, p.getY());
		p.teleportPlayer(10, 20);
		assertEquals(10, p.getX());
		assertEquals(20, p.getY());
	}
	
	@Test
	void testHasKey() {
		Player p = new Player(null, 1, 1);
		Key key = new Key(10, 10, 1);
		p.addToInv(key);
		assertTrue(p.hasKey());
		p.rmvKeyFromInv();
		assertFalse(p.hasKey());
	}
	
	@Test
	void testHasKeyWithID() {
		Player p = new Player(null, 1, 1);
		Key key = new Key(10, 10, 1);
		p.addToInv(key);
		assertTrue(p.hasKeyWithID(1));
	}
	
	@Test
	void testHasSword() {
		Player p = new Player(null, 1, 1);
		Sword sword = new Sword(10, 10);
		p.addToInv(sword);
		assertTrue(p.hasSword());
		p.rmvFromInv(sword);
		assertFalse(p.hasSword());
	}
	
	@Test
	void testHasPotion() {
		Player p = new Player(null, 1, 1);
		Potion potion = new Potion(10, 10);
		p.addToInv(potion);
		assertTrue(p.hasPotion());
		p.rmvFromInv(potion);
		assertFalse(p.hasPotion());
	}
	
	@Test
	void testHasTreasure() {
		Dungeon d = new Dungeon(100, 100);
		Player p = new Player(null, 1, 1);
		Treasure treasure = new Treasure(d, 10, 10);
		p.addToInv(treasure);
		assertEquals(p.hasTreasure(), 1);
		p.rmvFromInv(treasure);
		assertEquals(p.hasTreasure(), 0);
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
		p.attemptAttack();
		assertTrue(p.hasSword());
		p.attemptAttack();
		
		assertFalse(p.hasSword());	
	}
	
	@Test
	void testAttack() {
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
