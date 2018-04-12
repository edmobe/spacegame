package com.edmobe.src.objects;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

import com.edmobe.src.Controller;
import com.edmobe.src.Game;
import com.edmobe.src.GlobalPosition;
import com.edmobe.src.Physics;

public class Enemy extends GlobalPosition {

	private String enemyimage = "/images/enemy.png"; // enemy image path.

	private Controller c;

	public int health;
	
	public Enemy(int x, int y, Controller c) {
		
		super(x, y);
		
		health = 1;
		width = 50;
		height = 50;

		this.c = c;
	}

	public boolean update() { // update enemy's changing parameters.
		if (Physics.BulletCollision(this, c.getBullets())) {
			health --;
			Game.score++;
			if (health == 0) {
				c.removeEnemy(this);
				return true;
			}
		}
		
		return false;
	}

	public void render(Graphics2D g2d) {
		g2d.drawImage(getImage(), x, y, null); // draws enemy image on the screen.
	}

	public Image getImage() {
		ImageIcon icon = new ImageIcon(getClass().getResource(enemyimage)); // enemy image.
		return icon.getImage(); // returns the player image resized.
	}
	
	public int getHealth() {
		return health;
	}
}
