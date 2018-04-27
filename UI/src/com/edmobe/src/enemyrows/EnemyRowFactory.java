package com.edmobe.src.enemyrows;

import com.edmobe.src.Controller;

/**
 * Creates rows of enemies
 * @author edmobe
 *
 */
public class EnemyRowFactory {

	private Controller c;

	public EnemyRowFactory(Controller c) {
		this.c = c;
	}

	public EnemyRow getEnemyRow(int type) {
		if (type == 0) {
			return new EnemyRowBasic(c);
		} else if (type == 1) {
			return new EnemyRowA(c);
		} else if (type == 2) {
			return new EnemyRowB(c);
		} else if (type == 3) {
			return new EnemyRowC(c);
		} else if (type == 4) {
			return new EnemyRowD(c);
		} else {
			return new EnemyRowE(c);
		}
	}

}
