package com.edmobe.src;

import java.awt.Graphics2D;

import com.edmobe.src.enemyrows.EnemyRowFactory;
import com.edmobe.src.enemyrows.EnemyRow;
import com.edmobe.src.lists.LinkedList;
import com.edmobe.src.objects.Bullet;
import com.edmobe.src.objects.Enemy;

public class Controller {

	private LinkedList<Bullet> b = new LinkedList<Bullet>(); // bullet linked list.
	private EnemyRowFactory ef = new EnemyRowFactory();
	private EnemyRow e;

	Bullet TempBullet; // temporal bullet.
	Enemy TempEnemy; // temporal enemy.

	Game game; // game object.

	public Controller(Game game) {
		this.game = game;
		e = ef.getEnemyRow(0);
	}

	public void update() {
		for (int i = 0; i < b.size(); i++) { // for every bullet in the linked list.
			TempBullet = b.get(i);

			if (TempBullet.getY() < 0) {
				removeBullet(TempBullet);
			} // if the bullet is out of bounds, it is deleted.

			TempBullet.update(); // the bullet moves.
		}

		e.update();

	}

	public void render(Graphics2D g2d) { // draws the display
		for (int i = 0; i < b.size(); i++) { // for every bullet in the linked list.
			TempBullet = b.get(i);

			TempBullet.render(g2d); // the bullet is shown in the display.
		}

		e.render(g2d);
	}

	public void addBullet(Bullet block) {
		b.add(block);
	}

	public void removeBullet(Bullet block) {
		b.remove(block);
	}
}
