package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.MovementBehaviour;
import unsw.dungeon.Player;

class MovementBehaviourTest {

	@Test
	void testMoveUp() {
		Player p = new Player(null, 1, 1);
		MovementBehaviour moveB = new MovementBehaviour(p);
		moveB.move("UP");
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 0);
	}
	
	@Test
	void testMoveDown() {
		Player p = new Player(null, 1, 1);
		MovementBehaviour moveB = new MovementBehaviour(p);
		moveB.move("DOWN");
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 2);
	}
	
	@Test
	void testMoveLeft() {
		Player p = new Player(null, 1, 1);
		MovementBehaviour moveB = new MovementBehaviour(p);
		moveB.move("LEFT");
		assertEquals(p.getX(), 0);
		assertEquals(p.getY(), 1);
	}
	
	@Test
	void testMoveRight() {
		Player p = new Player(null, 1, 1);
		MovementBehaviour moveB = new MovementBehaviour(p);
		moveB.move("RIGHT");
		assertEquals(p.getX(), 2);
		assertEquals(p.getY(), 1);
	}
	
	@Test
	void testTeleport() {
		Player p = new Player(null, 1, 1);
		MovementBehaviour moveB = new MovementBehaviour(p);
		moveB.teleport(10, 10);;
		assertEquals(p.getX(), 10);
		assertEquals(p.getY(), 10);
	}

}
