package com.edmobe.src.enemyrows;

import java.util.Random;

import com.edmobe.src.Controller;
import com.edmobe.src.Game;
import com.edmobe.src.objects.Boss;
import com.edmobe.src.objects.Enemy;

public class EnemyRowB extends EnemyRow {

	private int dir = 1;
	private int x = 0;
	private int bossSwitcher = 70;

	Random random = new Random();

	public EnemyRowB(Controller c) {

		int randomPos = random.nextInt(5);

		for (int i = 0; i < 5; i++) {
			if (i == randomPos) {
				addEnemy(new Boss(i * 80, 0, c));
			} else {
				addEnemy(new Enemy(i * 80, 0, c));
			}
		}
	}

	@Override
	public void update() {

		int leftBound = row.get(0).x;
		int rightBound = row.get(row.size() - 1).x + 50;

		if (leftBound < 0 || rightBound > 640) {

			if (leftBound < 0) {
				x = 0;
			} else {
				x = 640 - 75 * (row.size());
			}

			for (int i = 0; i < row.size(); i++) { // for every enemy in the linked list.

				Enemy TempEnemy = row.get(i);

				if (TempEnemy.y >= 360) {
					Game.over = true;
				} else {
					TempEnemy.y += 20 + (Game.level - 1) * 5;
				}

			}

			dir = -dir;

		} else {

			for (int i = 0; i < row.size(); i++) { // for every enemy in the linked list.

				Enemy TempEnemy = row.get(i);

				if (TempEnemy.update() == 1) {
					if (TempEnemy instanceof Boss) {
						row.clear();
					} else {
						x += 40;
					}
				}
			}
		}

		for (int i = 0; i < row.size(); i++) { // for every enemy in the linked list.

			Enemy TempEnemy = row.get(i);

			TempEnemy.x = x + i * 80;

		}

		x += Game.level * dir * 1;
		bossSwitcher--;
		
		if (bossSwitcher == 0) {
			
			bossSwitcher = random.nextInt(100) + 40;

			row.shuffle();
		}

	}
	
	public int getBossPos() {
		for (int i = 0; i < row.size(); i++) { // for every enemy in the linked list.

			Enemy TempEnemy = row.get(i);

			if (TempEnemy instanceof Boss) {
				return i;
			}
			
		}
		return 0;
	}

}