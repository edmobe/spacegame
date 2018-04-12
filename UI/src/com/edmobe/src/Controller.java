package com.edmobe.src;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

import com.edmobe.src.enemyrows.EnemyRowFactory;
import com.edmobe.src.input.Server;
import com.edmobe.src.enemyrows.EnemyRow;
import com.edmobe.src.lists.LinkedList;
import com.edmobe.src.objects.Bullet;
import com.edmobe.src.objects.Enemy;

public class Controller {
	
	Thread server = new Server("server");

	private LinkedList<Bullet> bulletList = new LinkedList<Bullet>(); // list of all the bullets.
	private EnemyRowFactory enemyRowFactory = new EnemyRowFactory(this);
	private EnemyRow enemyRow;
	private boolean waiting = true; // waiting for server;
	
	private String waitingimage = "/images/waiting.png"; // waiting server image path.
	private String gameoverimage = "/images/gameover.png"; // game over image path.

	private Random random = new Random();

	Bullet TempBullet; // temporal bullet.
	Enemy TempEnemy; // temporal enemy.

	public Game game; // game object.

	public int bulletDelay = 0;

	public Controller(Game game) {
		
		Game.randomRowType = random.nextInt(6);
		Game.nextRowType = random.nextInt(6);
		this.game = game;
		
		server.start();
		
		setNewRandom();

		enemyRow = enemyRowFactory.getEnemyRow(4); // creates a new EnemyRow based on the random input.
	}

	public void update() {
		
		for (int i = 0; i < bulletList.size(); i++) { // for every bullet in the linked list.
			TempBullet = bulletList.get(i);

			if (TempBullet.getY() < 0) {
				removeBullet(TempBullet);
			} // if the bullet is out of bounds, it is deleted.

			TempBullet.update(); // the bullet moves.
		}
		
		if (bulletDelay > 0) {
			bulletDelay--;
		}
		
		if (!Game.over) {

			if (enemyRow.getRow().size() == 0) { // level up

				setNewRandom();

				enemyRow = enemyRowFactory.getEnemyRow(Game.randomRowType);

				Game.level += 1;
			}

			
			if (((Server) server).getMessage() == null) {
				waiting = true;
			} else {
				waiting = false;
			enemyRow.update();
			}
		} else {
			enemyRow.emptyRow();
		}
		
	}

	public void render(Graphics2D g2d) { // draws the display
		
		if (Game.over) {
			g2d.drawImage(getGameOverImage(), 125, 100, null);
		} else {
			g2d.setFont(new Font("monospace", Font.BOLD, 15));
			g2d.setColor(new Color(255, 255, 255));
			g2d.drawString("Level: " + Game.level, 20, 420);
			g2d.drawString("Score: " + Game.score, 20, 450);
			g2d.drawString("Current Row: " + getRowString(Game.randomRowType), 450, 420);
			g2d.drawString("Next Row: " + getRowString(Game.nextRowType), 450, 450);
		}
		
		
		for (int i = 0; i < bulletList.size(); i++) { // for every bullet in the linked list.
			TempBullet = bulletList.get(i);

			TempBullet.render(g2d); // the bullet is shown in the display.
		}

		enemyRow.render(g2d);
		
		if (waiting) {
			g2d.drawImage(getWaitingImage(), 100, 100, null); // draws waiting image on the screen.
		}
		
		
	}

	public void setNewRandom() {
		Game.randomRowType = Game.nextRowType;
		Game.nextRowType = random.nextInt(2);
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

	public Image getWaitingImage() {
		ImageIcon icon = new ImageIcon(getClass().getResource(waitingimage)); // player image.
		return icon.getImage(); // returns the player image resized.
	}
	
	public Image getGameOverImage() {
		ImageIcon icon = new ImageIcon(getClass().getResource(gameoverimage)); // player image.
		return icon.getImage(); // returns the player image resized.
	}
	
	public String getRowString(int type) {
		if (type == 0) {
			return "Basic";
		} else if (type == 1) {
			return "A Class";
		} else if (type == 2) {
			return "B Class";
		} else if (type == 3) {
			return "C Class";
		} else if (type == 4) {
			return "D Class";
		} else {
			return "E Class";
		}
	}
}
