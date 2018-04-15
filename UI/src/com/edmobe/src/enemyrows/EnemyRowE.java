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

		addEnemy(new Boss(x, 20, c));
		
		int initialX = 1;

		for (int i = 1; i < 7; i++) {
			
			if (initialX == 4) {
				initialX = -3;
			}

			Enemy TempEnemy = new Enemy(x + initialX * 80, 20, c);
			TempEnemy.initialI = i;

			addEnemy(TempEnemy);
			
			initialX++;

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

			if (TempEnemy.update() == 1) {
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
				} else if (TempEnemy.initialI > 3) {
					TempEnemy.y = (int) (y - 40 * (TempEnemy.initialI - 3) * Math.sin(Math.toRadians(angle)));
					TempEnemy.x = (int) (x - 55 * (TempEnemy.initialI - 3) * Math.cos(Math.toRadians(angle)));
				} else {
					TempEnemy.y = (int) (y + 40 * TempEnemy.initialI * Math.sin(Math.toRadians(angle)));
					TempEnemy.x = (int) (x + 55 * TempEnemy.initialI * Math.cos(Math.toRadians(angle)));
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
