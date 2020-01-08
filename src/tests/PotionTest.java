package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Elf;
import unsw.dungeon.Player;
import unsw.dungeon.Potion;
import unsw.dungeon.Sword;

class PotionTest {

	@Test
	void potionPickUp() {
		Dungeon d = new Dungeon(100, 100);
		Player p = new Player(d, 1,1);
		Potion potion1 = new Potion(2,1);
		Potion potion2 = new Potion(2,2);
		Potion potion3 = new Potion(1,2); 
		Potion potion4 = new Potion(1,0); // Down
		
		d.addEntity(potion4);
		d.addEntity(potion3);
		d.addEntity(potion2);
		d.addEntity(potion1);
		d.addEntity(p);
		
		// Player initially does not have potion
		assertEquals(p.hasPotion(), false);
		assertEquals(p.getIsPotionActive(), false);
		
		potion1.interactWith(p, "DOWN");
		assertEquals(p.hasPotion(), true);
		assertEquals(p.getIsPotionActive(), true);
		potion1.interactWith(p, "UP"); // potion effect activates again, since it's not removed from grid
		
		// Removes potion after set time period
		p.rmvFromInv(potion1);
		p.setIsPotionActive(false);
		assertEquals(p.hasPotion(), false);
		assertEquals(p.getIsPotionActive(), false);
		
		potion1.interactWith(p, "RIGHT");
		assertEquals(p.hasPotion(), true);
		assertEquals(p.getIsPotionActive(), true);
		
		// Removes potion after set time period
		p.rmvFromInv(potion1);
		p.setIsPotionActive(false);
		assertEquals(p.hasPotion(), false);
		assertEquals(p.getIsPotionActive(), false);
		
		potion1.interactWith(p, "UP");
		assertEquals(p.hasPotion(), true);
		assertEquals(p.getIsPotionActive(), true);
		
		// Removes potion after set time period
		p.rmvFromInv(potion1);
		p.setIsPotionActive(false);
		assertEquals(p.hasPotion(), false);
		assertEquals(p.getIsPotionActive(), false);

		potion1.interactWith(p, "LEFT");
		assertEquals(p.hasPotion(), true);
		assertEquals(p.getIsPotionActive(), true);
		
		// Removes potion after set time period
		p.rmvFromInv(potion1);
		p.setIsPotionActive(false);
		assertEquals(p.hasPotion(), false);
		assertEquals(p.getIsPotionActive(), false);
	}
	
	@Test
	void testPotionInteraction() {
		Dungeon d = new Dungeon(100, 100);
		Player p = new Player(d, 1,1);
		Potion potion = new Potion(2,1);
		
		d.addEntity(p);
		d.addEntity(potion);
		
		// Player initially does not have potion
		assertEquals(p.hasPotion(), false);
		assertEquals(p.getIsPotionActive(), false);
		
		// Player moves to pick up Potion
		potion.interactWith(p, "RIGHT");
		assertEquals(p.hasPotion(), true);
		assertEquals(p.getIsPotionActive(), true);
		
		// Removes potion after set time period
		p.rmvFromInv(potion);
		p.setIsPotionActive(false);
		assertEquals(p.hasPotion(), false);
		assertEquals(p.getIsPotionActive(), false);
	}
	
	@Test
	void testMovable() {
		Potion p = new Potion (0,0);
		
		assertEquals(p.isMoveToAble(), true);
	}
	
	@Test
	void testEnemyWithPotion() {
		Dungeon d = new Dungeon(100, 100);
		Player p = new Player(d, 1,1);
		Potion potion = new Potion(2,1);
		Elf e = new Elf(d, 3, 1);
		
		d.addEntity(p);
		d.addEntity(potion);
		d.addEntity(e);
		
		p.attemptMove("RIGHT");
		assertEquals(p.hasPotion(), true);
		assertEquals(p.getIsPotionActive(), true);
		
		p.attemptMove("RIGHT");
		assertEquals(p.getX(), 3);
		assertEquals(p.getY(), 1);
		
		// Enemy dead
		assertFalse(d.getEntityList().contains(e));
		
	}
	
	

}
