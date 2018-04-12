package com.edmobe.src.enemyrows;

import java.awt.Graphics2D;
import java.util.Random;

import com.edmobe.src.Controller;
import com.edmobe.src.Game;
import com.edmobe.src.lists.LinkedList;
import com.edmobe.src.objects.Boss;
import com.edmobe.src.objects.Enemy;

public class EnemyRowE extends EnemyRow {

	private int y = 150;
	private int x = 300;
	private int angle = 0;
	private int angleTimer = 5;

	private int descendTimer = 20;

	private Controller c;

	Random random = new Random();

	public EnemyRowE(Controller c) {

		this.c = c;

		addEnemy(new Boss(x, y, c));

		for (int i = 0; i < 6; i++) {

			addEnemy(new Enemy(x + i * 55, y, c));

		}
	}

	@Override
	public void update() {

		if (angleTimer == 0) {
			if (angle == 360) {
				angle = 0;
			} else {
				angle++;
			}
			angleTimer = 5;
		} else {
			angleTimer--;
		}

		for (int i = 0; i < crow.size(); i++) { // for every enemy in the linked list.

			Enemy TempEnemy = crow.get(i);

			if (TempEnemy.update()) {
				if (TempEnemy instanceof Boss) {
					if (crow.size() == 0) {
						crow.clear();
					} else {
						Enemy randomEnemy = crow.get(random.nextInt(crow.size()));
						crow.remove(randomEnemy);

						crow.addFirst(new Boss(290, y, c));

						break;
					}
				} 
			}
		}

		if (descendTimer == 0) {

			y = y + Game.level * 1;
			descendTimer = 20;

		} else {

			for (int i = 0; i < crow.size(); i++) { // for every enemy in the linked list.

				Enemy TempEnemy = crow.get(i);
				
				
				if (i == 0) {
					TempEnemy.y = y;
					TempEnemy.x = x;
				} else if (i > 3) {
					TempEnemy.y = (int) (y - 40 * (i - 3) * Math.sin(Math.toRadians(angle)));
					TempEnemy.x = (int) (x - 55 * (i - 3) * Math.cos(Math.toRadians(angle)));
				} else {
					TempEnemy.y = (int) (y + 40 * i * Math.sin(Math.toRadians(angle)));
					TempEnemy.x = (int) (x + 55 * i * Math.cos(Math.toRadians(angle)));
				}

				if (TempEnemy.y >= 380) {
					Game.over = true;
				}

			}

			descendTimer--;
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
