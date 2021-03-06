package com.edmobe.src.objects;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import com.edmobe.src.Game;
import com.edmobe.src.GlobalPosition;

/**
 * Player object
 * 
 * @author edmobe
 *
 */
public class Player extends GlobalPosition {

	private int velX = 0; // x axis speed

	private String playerimage = "/images/player.png"; // player image path

	public Player(int x, int y) {
		super(x, y); // x and y from GlobalPosition

		width = 50;
		height = 50;
	}

	/**
	 * Update player's changing parameters
	 */
	public void update() {
		if (x == 0 && Game.left) {
			velX = 0;
		} // setting left side limit
		else if (x == 590 && Game.right) {
			velX = 0;
		} // setting right side limit
		x += velX; // modifies x position based on the keyboard inputs
	}

	/**
	 * Runs when a key is pressed
	 * 
	 * @param event
	 *            event key
	 */
	public void keyPressed(KeyEvent event) {
		int key = event.getKeyCode(); // gets the pressed key code for the following validations

		if (key == KeyEvent.VK_RIGHT && !Game.paused) { // if the right button is pressed
			moveRight();
		} else if (key == KeyEvent.VK_LEFT && !Game.paused) { // if the left button is pressed
			moveLeft();
		}
	}

	/**
	 * Runs when a key is released
	 * 
	 * @param event
	 */
	public void keyReleased(KeyEvent event) {
		int key = event.getKeyCode(); // gets the pressed key code for the following validations

		if (key == KeyEvent.VK_RIGHT) { // if the right button is released
			if (Game.left == false) {
				velX = 0;
			} // if the player is not going left, the player stops
			Game.right = false; // the player is not moving right anymore
		} else if (key == KeyEvent.VK_LEFT) { // if the left button is released
			if (Game.right == false) {
				velX = 0;
			} // if the player is not going right, the player stops
			Game.left = false; // the player is not moving left anymore
		}
	}

	/**
	 * Moves the player to the right
	 */
	public void moveRight() {
		velX = 5; // increase the x position.
		Game.left = false;
		Game.right = true; // the player object moves right.
	}

	/**
	 * Moves the player to the left
	 */
	public void moveLeft() {
		velX = -5; // decrease the x position.
		Game.right = false;
		Game.left = true; // the player object moves left.
	}

	/**
	 * Stops the player movement
	 */
	public void stopMoving() {
		velX = 0;
	}

	/**
	 * Displays the player on the screen
	 * 
	 * @param g2d
	 *            {@code Graphics2D} object
	 */
	public void render(Graphics2D g2d) {
		g2d.drawImage(getImage(), x, y, null); // draws player image on the screen.
	}

	/**
	 * Gets the image of the player for the display.
	 * 
	 * @return the {@code Image} object
	 */
	public Image getImage() {
		ImageIcon icon = new ImageIcon(getClass().getResource(playerimage)); // player image.
		return icon.getImage(); // returns the player image resized.
	}

}