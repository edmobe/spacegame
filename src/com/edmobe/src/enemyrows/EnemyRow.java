package com.edmobe.src.enemyrows;

import java.awt.Graphics2D;

import com.edmobe.src.lists.LinkedList;
import com.edmobe.src.objects.Enemy;

public abstract class EnemyRow {

	protected static LinkedList<Enemy> row = new LinkedList<Enemy>();

	protected Enemy TempEnemy;
	
	public void update() {}

	public void render(Graphics2D g2d) { // draws the display
		for (int i = 0; i < row.size(); i++) { // for every bullet in the linked list.
			TempEnemy = row.get(i);

			TempEnemy.render(g2d); // the bullet is shown in the display.
		}
	}
	
	public void center() {}

	public void addEnemy(Enemy block) {
		row.add(block);
	}

	public void removeEnemy(Enemy block) {
		row.remove(block);
	}
	
	public LinkedList<Enemy> getRow() {
		return row;
	}
}
