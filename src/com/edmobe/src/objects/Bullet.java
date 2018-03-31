package com.edmobe.src.objects;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;


public class Bullet {
	
	private int x;
	private int y;
	
	private Image bulletImage; // bullet image object.
	private String bulletimage = "/images/bullet.png"; // player image path.
	
	public Bullet(int x, int y) {
		this.x = x;
		this.y = y;
		
		bulletImage = getBulletImage(); // defines the image object for each bullet.
	}
	
	public void update() {
		y -= 10; // moves the bullet.
	}
	
	public void render(Graphics2D g2d){
		g2d.drawImage(bulletImage, x, y, null); // draws bullet image on the screen.
	}

	public int getY() {
		return y; // gets y axis position of the bullet.
	}
	
	public Image getBulletImage() {
		ImageIcon icon = new ImageIcon(getClass().getResource(bulletimage)); // bullet image.
		Image newsize = icon.getImage().getScaledInstance(5, 20, java.awt.Image.SCALE_DEFAULT);
			// new image, resizes icon.
		icon = new ImageIcon(newsize); // instantiation of the resized image.
		return icon.getImage(); // returns the bullet image resized.
	}

}
