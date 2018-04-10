package com.edmobe.src;

import java.awt.Graphics2D;

import com.edmobe.src.enemyrows.EnemyRowFactory;
import com.edmobe.src.enemyrows.EnemyRow;
import com.edmobe.src.lists.LinkedList;
import com.edmobe.src.objects.Bullet;
import com.edmobe.src.objects.Enemy;

public class Controller {

	private LinkedList<Bullet> bulletList = new LinkedList<Bullet>(); // list of all the bullets.
	private EnemyRowFactory enemyRowFactory = new EnemyRowFactory(this);
	private EnemyRow enemyRow;

	Bullet TempBullet; // temporal bullet.
	Enemy TempEnemy; // temporal enemy.

	public Game game; // game object.
	
	public int bulletDelay = 0;

	public Controller(Game game) {
		this.game = game;
		
		enemyRow = enemyRowFactory.getEnemyRow(0); // creates a new EnemyRow based on the random input.
	}

	public void update() {
		for (int i = 0; i < bulletList.size(); i++) { // for every bullet in the linked list.
			TempBullet = bulletList.get(i);

			if (TempBullet.getY() < 0) {
				removeBullet(TempBullet);
			} // if the bullet is out of bounds, it is deleted.

			TempBullet.update(); // the bullet moves.
		}
		
		if (enemyRow.getRow().size() == 0) { // level up
			enemyRow = enemyRowFactory.getEnemyRow(0);
			Game.level += 1;
		}
		
		if (bulletDelay > 0) {
			bulletDelay--;
		}
		
		enemyRow.update();
	}

	public void render(Graphics2D g2d) { // draws the display
		for (int i = 0; i < bulletList.size(); i++) { // for every bullet in the linked list.
			TempBullet = bulletList.get(i);

			TempBullet.render(g2d); // the bullet is shown in the display.
		}

		enemyRow.render(g2d);
	}

	public void addBullet(Bullet block) {
		bulletList.add(block);
	}

	public void removeBullet(Bullet block) {
		bulletList.remove(block);
	}

	public void removeEnemy(Enemy block) {
		enemyRow.removeEnemy(block);
	}
	
	public LinkedList<Bullet> getBullets() {
		return bulletList;
	}
	
	public EnemyRow getEnemies() {
		return enemyRow;
	}
}
