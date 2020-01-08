package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Boulder;
import unsw.dungeon.Composite;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Elf;
import unsw.dungeon.Enemy;
import unsw.dungeon.Exit;
import unsw.dungeon.Leaf;
import unsw.dungeon.Player;
import unsw.dungeon.Switch;
import unsw.dungeon.Treasure;

class GoalTest {

	@Test
	void testExit() {
		Dungeon dungeon = new Dungeon(100, 100);
		Player player = new Player(dungeon, 1, 1);
		dungeon.addEntity(player);
		dungeon.setGoalComponent(new Leaf("exit"));
		Exit exit = new Exit(dungeon, 2, 1);
		dungeon.addEntity(exit);
		player.attemptMove("RIGHT");
		assertTrue(dungeon.isGameFinished());
	}
	
	@Test
	void testTreasure() {
		Dungeon dungeon = new Dungeon(100, 100);
		Player player = new Player(dungeon, 1, 1);
		dungeon.addEntity(player);
		dungeon.setGoalComponent(new Leaf("treasure"));
		Treasure treasure = new Treasure(dungeon, 2, 1);
		dungeon.addEntity(treasure);
		player.attemptMove("RIGHT");
		assertTrue(dungeon.isGameFinished());
	}
	
	@Test
	void testBoulder() {
		Dungeon dungeon = new Dungeon(100, 100);
		Player player = new Player(dungeon, 1, 1);
		dungeon.addEntity(player);
		dungeon.setGoalComponent(new Leaf("boulders"));
		Switch sw = new Switch(dungeon, 3, 1);
		dungeon.addEntity(sw);
		Boulder boulder = new Boulder(2, 1);
		dungeon.addEntity(boulder);
		boulder.attach(sw);
		player.attemptMove("RIGHT");
		assertTrue(dungeon.isGameFinished());
	}
	
	@Test
	void testEnemy() {
		Dungeon dungeon = new Dungeon(100, 100);
		Player player = new Player(dungeon, 1, 1);
		dungeon.addEntity(player);
		dungeon.setGoalComponent(new Leaf("enemies"));
		Enemy enemy = new Elf(dungeon, 2, 1);
		dungeon.addEntity(enemy);
		player.setIsPotionActive(true);
		player.attemptMove("RIGHT");
		assertTrue(dungeon.isGameFinished());
	}
	
	@Test
	void testExitAndTreasure() {
		Dungeon dungeon = new Dungeon(100, 100);
		Player player = new Player(dungeon, 1, 1);
		dungeon.addEntity(player);
		
		Composite head = new Composite("AND");
		head.add(new Leaf("exit"));
		head.add(new Leaf("treasure"));
		dungeon.setGoalComponent(head);
		
		Treasure treasure = new Treasure(dungeon, 2, 1);
		dungeon.addEntity(treasure);
		player.attemptMove("RIGHT");
		assertFalse(dungeon.isGameFinished());
		
		Exit exit = new Exit(dungeon, 3, 1);
		dungeon.addEntity(exit);
		player.attemptMove("RIGHT");
		assertTrue(dungeon.isGameFinished());
	}
	
	@Test
	void testExitOrTreasure1() {
		Dungeon dungeon = new Dungeon(100, 100);
		Player player = new Player(dungeon, 1, 1);
		dungeon.addEntity(player);
		
		Composite head = new Composite("OR");
		head.add(new Leaf("exit"));
		head.add(new Leaf("treasure"));
		dungeon.setGoalComponent(head);
		
		Treasure treasure = new Treasure(dungeon, 2, 1);
		dungeon.addEntity(treasure);
		player.attemptMove("RIGHT");
		assertTrue(dungeon.isGameFinished());
	}
	
	@Test
	void testExitOrTreasure2() {
		Dungeon dungeon = new Dungeon(100, 100);
		Player player = new Player(dungeon, 1, 1);
		dungeon.addEntity(player);
		
		Composite head = new Composite("OR");
		head.add(new Leaf("exit"));
		head.add(new Leaf("treasure"));
		dungeon.setGoalComponent(head);
		
		Exit exit = new Exit(dungeon, 2, 1);
		dungeon.addEntity(exit);
		player.attemptMove("RIGHT");
		assertTrue(dungeon.isGameFinished());
	}
	

}
