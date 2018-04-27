package com.edmobe.src.enemyrows;

import java.awt.Graphics2D;

import com.edmobe.src.lists.CDLinkedList;
import com.edmobe.src.lists.LinkedList;
import com.edmobe.src.objects.Enemy;

/**
 * This class is used to make the six enemy rows.
 * @author edmobe
 *
 */
public abstract class EnemyRow {

	protected static LinkedList<Enemy> row = new LinkedList<Enemy>(); // creates a double linked List
	protected static CDLinkedList<Enemy> crow = new CDLinkedList<Enemy>(); // creates a circular double linked list

	protected Enemy TempEnemy; // temporal enemy
	
	/**
	 * Every enemy row must have an update, so this is an abstract void.
	 */
	public abstract void update();

	/**
	 * Renders every enemy in the list.
	 * @param g2d {@code Graphics2D} object
	 */
	public void render(Graphics2D g2d) { // draws the display
		for (int i = 0; i < row.size(); i++) { // for every bullet in the linked list.
			TempEnemy = row.get(i);

			TempEnemy.render(g2d); // the bullet is shown in the display.
		}
	}

	/**
	 * Adds an enemy to the row.
	 * @param block {@code Enemy} object
	 */
	public void addEnemy(Enemy block) {
		row.add(block);
	}

	/**
	 * Removes an enemy from the row.
	 * @param block {@code Enemy} object
	 */
	public void removeEnemy(Enemy block) {
		row.remove(block);
	}
	
	/**
	 * Deletes every enemy in the row.
	 */
	public void emptyRow() {
		row.clear();
		crow.clear();
	}
	
	/**
	 * Gets the enemy row.
	 * @return row of enemies
	 */
	public LinkedList<Enemy> getRow() {
		return row;
	}
}
