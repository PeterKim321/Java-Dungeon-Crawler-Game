package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Boulder;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Elf;
import unsw.dungeon.Player;
import unsw.dungeon.Portal;

class PortalTest {

	@Test
	void testInteractWith() {
		Player player1 = new Player(null, 1, 1);
		Portal p1 = new Portal(2, 1, 1);
		Portal p2 = new Portal(10, 10, 1);
		p1.setOtherPortal(p2);
		p2.setOtherPortal(p1);
		p1.interactWith(player1, "RIGHT");
		assertEquals(player1.getX(), 10);
		assertEquals(player1.getY(), 10);
		Player player2 = new Player(null, 11, 10);
		p2.interactWith(player2, "LEFT");
		assertEquals(player2.getX(), 2);
		assertEquals(player2.getY(), 1);
	}
	
	@Test
	void testOtherEntities() {
		Dungeon d = new Dungeon(100, 100);
		Player player1 = new Player(d, 1, 1);
		Portal p1 = new Portal(3, 1, 1);
		Portal p2 = new Portal(10, 10, 1);
		d.addEntity(player1);
		d.addEntity(p1);
		d.addEntity(p2);
		p1.setOtherPortal(p2);
		p2.setOtherPortal(p1);
		
		Boulder b = new Boulder(2, 1);
		d.addEntity(b);
		
		// Nothing happens when attempting to pass boulder through portal
		player1.attemptMove("RIGHT");
		assertEquals(1, player1.getX());
		assertEquals(1, player1.getY());
		assertEquals(2, b.getX());
		assertEquals(1, b.getY());
		
		Elf e = new Elf(d, 4, 1);
		
		// Enemy cannot move through portal
		assertFalse(e.getCurrMovement().isValidMove(3, 1));
		
	}

}
