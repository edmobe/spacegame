package com.edmobe.src.enemyrows;

import java.awt.Graphics2D;
import java.util.Random;

import com.edmobe.src.Controller;
import com.edmobe.src.Game;
import com.edmobe.src.lists.LinkedList;
import com.edmobe.src.objects.Boss;
import com.edmobe.src.objects.Enemy;

/**
 * E type enemy row.
 * 
 * @author edmobe
 *
 */
public class EnemyRowE extends EnemyRow {

	private int y = 150; // initial global row Y position
	private int x = 300; // initial global row X position
	private int angle = 0; // initial global row angle
	private int angleTimer = 5; // timer used to update the angle

	private int descendTimer = 20; // timer used to move the row down

	private Controller c; // controller object that will be used

	Random random = new Random(); // random object that will be used

	public EnemyRowE(Controller c) {

		this.c = c; // links the class with the given controller

		addEnemy(new Boss(x, 20, c)); // there will only be one boss

		int initialX = 1; // used to set the starting X position of every enemy using the boss (center) as
							// a reference

		for (int i = 1; i < 7; i++) {

			if (initialX == 4) { // if all enemies at the right side of the boss were added
				initialX = -3; // start adding enemies at the left of the boss
			}

			Enemy TempEnemy = new Enemy(x + initialX * 80, 20, c);
			TempEnemy.initialI = i;

			addEnemy(TempEnemy);

			initialX++;

		}
	}

	/**
	 * Defines the row behavior.
	 */
	@Override
	public void update() {

		if (angleTimer == 0) { // if the angle must be changed
			if (angle == 360) { // if the angle is 360
				angle = 0; // the new angle will be 0 (to avoid the integer increasing its value
							// indefinitely)
			} else {
				angle++; // the angle increases
			}
			angleTimer = 5; // the angle timer restarts
		} else {
			angleTimer--; // the angle timer decreases
		}

		for (int i = 0; i < crow.size(); i++) { // for every enemy in the linked list.

			Enemy TempEnemy = crow.get(i); // temporal enemy

			if (TempEnemy.update() == 1) { // if the enemy was deleted
				if (TempEnemy instanceof Boss) { // if the enemy was a boss
					if (crow.size() == 0) { // if the row has no enemies
						crow.clear(); // clears the row
					} else {
						Enemy randomEnemy = crow.get(random.nextInt(crow.size())); // selects a random enemy
						crow.remove(randomEnemy); // removes the random enemy

						crow.addFirst(new Boss(290, y, c)); // adds a new enemy at the start of the list

						break;
					}
				}
			}
		}

		if (descendTimer == 0) { // if the row must move down

			y = y + Game.level * 1; // updates the Y position
			descendTimer = 20; // resets the descend timer

		} else {

			for (int i = 0; i < crow.size(); i++) { // for every enemy in the linked list.

				Enemy TempEnemy = crow.get(i); // temporal enemy

				if (i == 0) { // if the enemy is the first object of the list (the enemy is the boss)
					TempEnemy.y = y;
					TempEnemy.x = x;
				} else if (TempEnemy.initialI > 3) { // at one side of the enemy
					TempEnemy.y = (int) (y - 40 * (TempEnemy.initialI - 3) * Math.sin(Math.toRadians(angle)));
					TempEnemy.x = (int) (x - 55 * (TempEnemy.initialI - 3) * Math.cos(Math.toRadians(angle)));
				} else { // at the other side of the enemy
					TempEnemy.y = (int) (y + 40 * TempEnemy.initialI * Math.sin(Math.toRadians(angle)));
					TempEnemy.x = (int) (x + 55 * TempEnemy.initialI * Math.cos(Math.toRadians(angle)));
				}

				if (TempEnemy.y >= 380) { // if the row is about to touch the player
					Game.over = true; // game over
				}

			}

			descendTimer--; // updates the descend timer
		}

	}

	@Override
	public void render(Graphics2D g2d) { // draws the display
		for (int i = 0; i < crow.size(); i++) { // for every bullet in the linked list.
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
