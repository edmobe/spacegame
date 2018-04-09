package com.edmobe.src.objects;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

import com.edmobe.src.EnemyEntity;
import com.edmobe.src.GlobalPosition;

public class Enemy extends GlobalPosition implements EnemyEntity {

	private int velX = 5; // x axis speed.

	public Enemy(int x, int y) {
		super(x, y);
	}

	private String enemyimage = "/images/enemy.png"; // enemy image path.

	public void update() { // update enemy's changing parameters.
		x += velX;
	}
	
	public void render(Graphics2D g2d) {
		g2d.drawImage(getImage(), x, y, null); // draws enemy image on the screen.
	}

	public Image getImage() {
		ImageIcon icon = new ImageIcon(getClass().getResource(enemyimage)); // enemy image.
		return icon.getImage(); // returns the player image resized.
	}

	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

}
