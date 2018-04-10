package com.edmobe.src.objects;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import com.edmobe.src.GlobalPosition;

public class Bullet extends GlobalPosition {

	private String bulletimage = "/images/bullet.png"; // player image path.

	public Bullet(int x, int y) {
		super(x, y);
		
		width = 20;
		height = 80;
	}

	public void update() {
		y -= 10; // moves the bullet.
	}

	public void render(Graphics2D g2d) {
		g2d.drawImage(getImage(), x, y, null); // draws bullet image on the screen.
	}

	public int getY() {
		return y; // gets y axis position of the bullet.
	}

	public Image getImage() {
		ImageIcon icon = new ImageIcon(getClass().getResource(bulletimage)); // bullet image.
		return icon.getImage(); // returns the bullet image resized.
	}

}
