package com.edmobe.src.enemyrows;

public class EnemyRowFactory {

	public EnemyRow getEnemyRow(int type) {
		if (type == 0) {
			return new EnemyRowBasic();
		} else {
			return null;
		}
	}

}
