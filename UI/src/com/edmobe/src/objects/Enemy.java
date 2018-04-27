package com.edmobe.src.objects;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

import com.edmobe.src.Controller;
import com.edmobe.src.Game;
import com.edmobe.src.GlobalPosition;
import com.edmobe.src.Physics;

/**
 * Enemy object
 * 
 * @author edmobe
 *
 */
public class Enemy extends GlobalPosition {

	private String enemyimage = "/images/enemy.png"; // enemy image path.

	private Controller c;

	public int health;
	public int initialI; // object position in the row

	public Enemy(int x, int y, Controller c) {

		super(x, y);

		health = 1;
		width = 50;
		height = 50;

		this.c = c;
	}

	public int update() { // update enemy's changing parameters.
		if (Physics.BulletCollision(this, c.getBullets())) { // if this object collided with a bullet
			health--; // decreases its health
			Game.score++; // increases the score
			if (health == 0) { // if the health is now 0
				c.removeEnemy(this); // removes the enemy from the row
				return 1; // deletion id
			}
			return 2; // reducing health id
		}

		return 0; // no collision id
	}

	/**
	 * Draws enemy image on the screen
	 * 
	 * @param g2d
	 *            {@code Graphics2D} object
	 */
	public void render(Graphics2D g2d) {
		g2d.drawImage(getImage(), x, y, null);
	}

	/**
	 * Gets the image of the enemy for the display.
	 * 
	 * @return the {@code Image} object
	 */
	public Image getImage() {
		ImageIcon icon = new ImageIcon(getClass().getResource(enemyimage)); // enemy image.
		return icon.getImage(); // returns the player image resized.
	}

	/**
	 * Gets the enemy's health
	 * 
	 * @return enemy's health
	 */
	public int getHealth() {
		return health;
	}
}
