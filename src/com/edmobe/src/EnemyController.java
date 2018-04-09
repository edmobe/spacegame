package com.edmobe.src;

import java.awt.Graphics2D;

import com.edmobe.src.lists.LinkedList;
import com.edmobe.src.objects.Enemy;

public class EnemyController {

	private LinkedList<Enemy> e = new LinkedList<Enemy>(); // enemy linked list.

	Enemy TempEnemy; // temporal enemy.

	Game game; // game object.

	public EnemyController(Game game) {
		this.game = game;
	}

	public void update() {
		for (int i = 0; i < e.size(); i++) { // for every enemy in the linked list.
			TempEnemy = e.get(i);

			if (TempEnemy.y < 0) {
				removeEnemy(TempEnemy);
			} // if the enemy is out of bounds, it is deleted.

			TempEnemy.update(); // the enemy moves.
		}
	}

	public void render(Graphics2D g2d) { // draws the display
		for (int i = 0; i < e.size(); i++) { // for every enemy in the linked list.
			TempEnemy = e.get(i);

			TempEnemy.render(g2d); // the enemy is shown in the display.
		}
	}

	public void addEnemy(Enemy block) {
		e.add(block);
	}

	public void removeEnemy(Enemy block) {
		e.remove(block);
	}

}
