package com.edmobe.src.enemyrows;

import java.util.Random;

import com.edmobe.src.Controller;
import com.edmobe.src.Game;
import com.edmobe.src.objects.Boss;
import com.edmobe.src.objects.Enemy;

/**
 * A type enemy row.
 * 
 * @author edmobe
 *
 */
public class EnemyRowA extends EnemyRow {

	private int dir = 1; // sets enemy row direction. 1 is right, -1 is left
	private int x = 0; // global row X position

	Random random = new Random(); // random object that will be used

	public EnemyRowA(Controller c) {

		int randomPos = random.nextInt(5); // sets the boss initial position in the row

		for (int i = 0; i < 5; i++) {
			if (i == randomPos) {
				addEnemy(new Boss(i * 80, 0, c)); // there will only be one boss
			} else {
				addEnemy(new Enemy(i * 80, 0, c));
			}
		}
	}

	/**
	 * Defines the row behavior.
	 */
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

				Enemy TempEnemy = row.get(i); // temporal enemy

				if (TempEnemy.update() == 1) { // if the enemy was deleted
					if (TempEnemy instanceof Boss) { // if the enemy was the boss
						row.clear(); // clears the row
					} else {
						x += 40; // centers the row
					}
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
