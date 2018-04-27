package com.edmobe.src.objects;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import com.edmobe.src.GlobalPosition;

/**
 * Bullet object
 * 
 * @author edmobe
 *
 */
public class Bullet extends GlobalPosition {

	private String bulletimage = "/images/bullet.png"; // player image path.

	public Bullet(int x, int y) {
		super(x, y);

		width = 20;
		height = 80;
	}

	/**
	 * Moves the bullet
	 */
	public void update() {
		y -= 10;
	}

	/**
	 * Draws the bullet image on the screen
	 * 
	 * @param g2d
	 *            {@code Graphics2D} object
	 */
	public void render(Graphics2D g2d) {
		g2d.drawImage(getImage(), x, y, null); // draws bullet image on the screen.
	}

	/**
	 * Gets the bullet's Y coordinate
	 * 
	 * @return bullet's Y coordinate
	 */
	public int getY() {
		return y; // gets y axis position of the bullet.
	}

	/**
	 * Gets the image of the bullet for the display.
	 * 
	 * @return the {@code Image} object
	 */
	public Image getImage() {
		ImageIcon icon = new ImageIcon(getClass().getResource(bulletimage)); // bullet image.
		return icon.getImage(); // returns the bullet image resized.
	}

}
