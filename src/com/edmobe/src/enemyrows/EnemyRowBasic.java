package com.edmobe.src.enemyrows;

import com.edmobe.src.objects.Enemy;

public class EnemyRowBasic extends EnemyRow {

	public EnemyRowBasic() {
		for (int i = 0; i < 5; i++) {
			addEnemy(new Enemy(i * 80, 0));
		}
	}

	@Override
	public void update() {

		if (row.get(0).x < 0 || row.get(row.size() - 1).x > 600) {

			for (int i = 0; i < row.size(); i++) { // for every enemy in the linked list.

				Enemy TempEnemy = row.get(i);

				TempEnemy.setVelX(-TempEnemy.getVelX());
				
				if (TempEnemy.y > 400) {
					TempEnemy.y = 0;
				} else {TempEnemy.y += 20;}

			}

		}

		for (int i = 0; i < row.size(); i++) { // for every enemy in the linked list.

			Enemy TempEnemy = row.get(i);
			
			TempEnemy.update();

		}

	}
}
