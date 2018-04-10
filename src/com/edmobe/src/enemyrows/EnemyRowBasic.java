package com.edmobe.src.enemyrows;

import com.edmobe.src.Controller;
import com.edmobe.src.Game;
import com.edmobe.src.objects.Enemy;

public class EnemyRowBasic extends EnemyRow {

	private int dir = 1;
	private int x = 0;

	public EnemyRowBasic(Controller c) {
		for (int i = 0; i < 5; i++) {
			addEnemy(new Enemy(i * 80, 0, c));
		}
	}

	@Override
	public void update() {

		int leftBound = row.get(0).x;
		int rightBound = row.get(row.size() - 1).x + 50;

		if (leftBound < 0 || rightBound > 640) {

			if (leftBound < 0) {
				System.out.println("LEFT");
				x = 0;
			} else {
				System.out.println("RIGHT");
				x = 640 - 75 * (row.size());
			}

			for (int i = 0; i < row.size(); i++) { // for every enemy in the linked list.

				Enemy TempEnemy = row.get(i);

				if (TempEnemy.y > 400) {
					TempEnemy.y = 0;
				} else {
					TempEnemy.y += 20;
				}

			}

			dir = -dir;

		} else {

			for (int i = 0; i < row.size(); i++) { // for every enemy in the linked list.

				Enemy TempEnemy = row.get(i);

				System.out.println(i + ": x = " + TempEnemy.x + ", y = " + TempEnemy.y
						+ "        Cantidad de enemigos: " + row.size());

				if (TempEnemy.update()) {
					x += 40;
				}
			}
		}

		for (int i = 0; i < row.size(); i++) { // for every enemy in the linked list.

			Enemy TempEnemy = row.get(i);

			TempEnemy.x = x + i * 80;

		}

		x += Game.level * dir * 1;

	}

}
