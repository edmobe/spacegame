package com.edmobe.src.enemyrows;

import com.edmobe.src.Controller;

public class EnemyRowFactory {
	
	private Controller c;
	
	public EnemyRowFactory(Controller c) {
		this.c = c;
	}

	public EnemyRow getEnemyRow(int type) {
		if (type == 0) {
			return new EnemyRowBasic(c);
		} else {
			return null;
		}
	}

}
