package com.edmobe.src.enemyrows;

import com.edmobe.src.Controller;
import com.edmobe.src.Game;
import com.edmobe.src.objects.Enemy;

/**
 * Basic type enemy row.
 * 
 * @author edmobe
 *
 */
public class EnemyRowBasic extends EnemyRow {

	private int dir = 1;// sets enemy row direction. 1 is right, -1 is left
	private int x = 0; // global row X position

	public EnemyRowBasic(Controller c) {
		for (int i = 0; i < 5; i++) {
			addEnemy(new Enemy(i * 80, 0, c)); // adds 5 enemies.
		}
	}

	@Override
	public void update() {

		int leftBound = row.get(0).x; // starting position of the row (from left to right)
		int rightBound = row.get(row.size() - 1).x + 50; // finishing position of the row (from left to right)

		if (leftBound < 0 || rightBound > 640) { // if the row is outside of the display

			if (leftBound < 0) { // if the row is outside at the left side
				x = 0;
			} else { // if the row is outside at the right side
				x = 640 - 75 * (row.size());
			}

			for (int i = 0; i < row.size(); i++) { // for every enemy in the linked list.

				Enemy TempEnemy = row.get(i); // temporal enemy

				if (TempEnemy.y >= 360) { // if the enemy gets close enough to the player
					Game.over = true; // the game is over
				} else {
					TempEnemy.y += 20 + (Game.level - 1) * 5; // update the enemy Y position
				}

			}

			dir = -dir; // changes the row direction

		} else {

			for (int i = 0; i < row.size(); i++) { // for every enemy in the linked list.

				Enemy TempEnemy = row.get(i);

				if (TempEnemy.update() == 1) {
					x += 40;
				}
			}
		}

		for (int i = 0; i < row.size(); i++) { // for every enemy in the linked list.

			Enemy TempEnemy = row.get(i); // temporal enemy

			TempEnemy.x = x + i * 80; // updates every enemy position

		}

		x += Game.level * dir * 1; // moves the row's global X position

	}

}
