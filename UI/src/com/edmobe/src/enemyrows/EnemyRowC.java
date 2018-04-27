package com.edmobe.src.enemyrows;

import java.awt.Graphics2D;
import java.util.Random;

import com.edmobe.src.Controller;
import com.edmobe.src.Game;
import com.edmobe.src.lists.LinkedList;
import com.edmobe.src.objects.Boss;
import com.edmobe.src.objects.Enemy;

/**
 * C type enemy row.
 * 
 * @author edmobe
 *
 */
public class EnemyRowC extends EnemyRow {

	private int dir = 1;// sets enemy row direction. 1 is right, -1 is left
	private int x = 0; // global row X position

	private Controller c; // controller object that will be used

	Random random = new Random(); // random object that will be used

	public EnemyRowC(Controller c) {

		this.c = c; // links the class with the given controller

		int randomPos = random.nextInt(5); // sets the boss initial position in the row

		for (int i = 0; i < 5; i++) {
			if (i == randomPos) {
				addEnemy(new Boss(i * 80, 0, c)); // there will only be one boss
			} else {
				addEnemy(new Enemy(i * 80, 0, c));
			}
		}

		crow.shuffle(); // shuffles the list
	}

	/**
	 * Defines the row behavior.
	 */
	@Override
	public void update() {

		int leftBound = crow.get(0).x; // starting position of the row (from left to right)
		int rightBound = crow.get(crow.size() - 1).x + 50; // finishing position of the row (from left to right)

		if (leftBound < 0 || rightBound > 640) { // if the row is outside of the display

			if (leftBound < 0) { // if the row is outside at the left side
				x = 0;
			} else { // if the row is outside at the right side
				x = 640 - 75 * (crow.size());
			}

			for (int i = 0; i < crow.size(); i++) { // for every enemy in the linked list

				Enemy TempEnemy = crow.get(i); // temporal enemy

				if (TempEnemy.y >= 360) {
					Game.over = true;
				} else {
					TempEnemy.y += 20 + (Game.level - 1) * 5;
				}

			}

			dir = -dir;

		} else {

			for (int i = 0; i < crow.size(); i++) { // for every enemy in the linked list

				Enemy TempEnemy = crow.get(i); // temporal enemy

				if (TempEnemy.update() == 1) { // if the enemy was deleted
					if (TempEnemy instanceof Boss) { // if the enemy was a boss
						if (crow.size() == 0) { // if the row has no enemies
							crow.clear();
						} else { // if the row still has enemies
							Enemy firstEnemy = crow.get(0);
							crow.add(new Boss(firstEnemy.x, firstEnemy.y, c));

							crow.remove(firstEnemy);

							crow.shuffle();

							break;
						}
					} else { // if the enemy was not a boss
						x += 40; // centers the row
					}
				}
			}
		}

		for (int i = 0; i < crow.size(); i++) { // for every enemy in the linked list

			Enemy TempEnemy = crow.get(i); // temporal enemy

			TempEnemy.x = x + i * 80; // updates every enemy position

		}

		x += Game.level * dir * 1; // moves the row's global X position

	}

	@Override
	public void render(Graphics2D g2d) { // draws the display
		for (int i = 0; i < crow.size(); i++) { // for every bullet in the linked list
			TempEnemy = crow.get(i);

			TempEnemy.render(g2d); // the bullet is shown in the display.
		}
	}

	@Override
	public void addEnemy(Enemy block) {
		crow.add(block);
	}

	@Override
	public void removeEnemy(Enemy block) {
		crow.remove(block);
	}

	@Override
	public LinkedList<Enemy> getRow() {
		return crow;
	}
}
