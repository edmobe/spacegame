package com.edmobe.src.objects;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;


public class Bullet {
	
	private int x;
	private int y;
	private String id;
	
	private String bulletimage = "/images/bullet.png"; // player image path.
	
	public Bullet(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void tick() {
		y -= 10;
	}
	
	public void draw(Graphics2D g2d){
		g2d.drawImage(getBulletImage(), x, y, null); // draws bullet image on the screen.
	}
	
	public Image getBulletImage(){
		ImageIcon icon = new ImageIcon(getClass().getResource(bulletimage)); // player image.
		Image newsize = icon.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_DEFAULT);
			// new image, resizes icon.
		icon = new ImageIcon(newsize); // instantiation of the resized image.
		return icon.getImage(); // returns the player image resized.
	}

}
