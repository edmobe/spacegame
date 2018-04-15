package com.edmobe.src.enemyrows;

import java.awt.Graphics2D;
import java.util.Random;

import com.edmobe.src.Controller;
import com.edmobe.src.Game;
import com.edmobe.src.lists.LinkedList;
import com.edmobe.src.objects.Boss;
import com.edmobe.src.objects.Enemy;

public class EnemyRowC extends EnemyRow {

	private int dir = 1;
	private int x = 0;
	
	private Controller c;

	Random random = new Random();

	public EnemyRowC(Controller c) {
		
		this.c = c;

		int randomPos = random.nextInt(5);

		for (int i = 0; i < 5; i++) {
			if (i == randomPos) {
				addEnemy(new Boss(i * 80, 0, c));
			} else {
				addEnemy(new Enemy(i * 80, 0, c));
			}
		}
		
		crow.shuffle();
	}

	@Override
	public void update() {

		int leftBound = crow.get(0).x;
		int rightBound = crow.get(crow.size() - 1).x + 50;

		if (leftBound < 0 || rightBound > 640) {

			if (leftBound < 0) {
				x = 0;
			} else {
				x = 640 - 75 * (crow.size());
			}

			for (int i = 0; i < crow.size(); i++) { // for every enemy in the linked list.

				Enemy TempEnemy = crow.get(i);

				if (TempEnemy.y >= 360) {
					Game.over = true;
				} else {
					TempEnemy.y += 20 + (Game.level - 1) * 5;
				}

			}

			dir = -dir;

		} else {

			for (int i = 0; i < crow.size(); i++) { // for every enemy in the linked list.

				Enemy TempEnemy = crow.get(i);

				if (TempEnemy.update() == 1) {
					if (TempEnemy instanceof Boss) {
						if (crow.size() == 0) {
							crow.clear();
						} else {
							Enemy firstEnemy = crow.get(0);
							crow.add(new Boss(firstEnemy.x, firstEnemy.y, c));
							
							crow.remove(firstEnemy);
							
							crow.shuffle();
							
							break;
						}
					} else {
						x += 40;
					}
				}
			}
		}

		for (int i = 0; i < crow.size(); i++) { // for every enemy in the linked list.

			Enemy TempEnemy = crow.get(i);

			TempEnemy.x = x + i * 80;

		}

		x += Game.level * dir * 1;

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
