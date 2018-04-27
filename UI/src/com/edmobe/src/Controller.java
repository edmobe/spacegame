package com.edmobe.src;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

import com.edmobe.src.enemyrows.EnemyRowFactory;
import com.edmobe.src.inputAndOutput.MessageSender;
import com.edmobe.src.inputAndOutput.Server;
import com.edmobe.src.enemyrows.EnemyRow;
import com.edmobe.src.lists.LinkedList;
import com.edmobe.src.objects.Bullet;
import com.edmobe.src.objects.Enemy;
import com.edmobe.src.objects.Player;

/**
 * This class is used to control game events.
 * 
 * @author edmobe
 * @see EnemyRow
 * @see EnemyRowFactory
 * @see Random
 * @see Bullet
 * @see Server
 * @see MessageSender
 */
public class Controller {

	private LinkedList<Bullet> bulletList = new LinkedList<Bullet>(); // a list of with every bullet object of the game
	private EnemyRowFactory enemyRowFactory = new EnemyRowFactory(this); // a factory for every class of enemy row
	private EnemyRow enemyRow; // an object that contains a row of enemies
	private boolean waiting = true; // boolean that determines if the server is not ready

	private String waitingimage = "/images/waiting.png"; // "waiting server" image path
	private String waiting2image = "/images/waiting2.png"; // "press to use keyboard" server image path
	private String gameoverimage = "/images/gameover.png"; // "game over" image path

	private Random random = new Random();

	private Bullet TempBullet; // temporal bullet

	private Thread server; // a Thread that receives client data
	private Thread messageSender; // a Thread that sends data to the client

	public int bulletDelay = 0; // used to control how often a bullet can be created

	/**
	 * {@code Controller} constructor.
	 * 
	 * @param player
	 *            used to link the {@code Game} with the {@code Server}
	 */
	public Controller(Player player) {

		Game.randomRowType = random.nextInt(6); // defines the current row type id
		Game.nextRowType = random.nextInt(6); // defines the next row type id

		server = new Server(player, this); // instantiates the Server
		messageSender = new MessageSender(); // instantiates the MessageSender

		server.start(); // starts the Server thread
		messageSender.start(); // starts the MessageSender thread

		setNewRandom();

		enemyRow = enemyRowFactory.getEnemyRow(Game.randomRowType); // creates a new EnemyRow based on the random input
	}

	/**
	 * Verifies every interaction in the game. It is a loop.
	 */
	public void update() {

		updateBullets();

		if (!Game.over) {

			if (enemyRow.getRow().size() == 0) { // level up

				setNewRandom(); // new row class required

				enemyRow = enemyRowFactory.getEnemyRow(Game.randomRowType); // creates the new enemy row

				Game.level += 1; // increases level
			}

			if (!Game.usePhone && ((Server) server).getMessage() == null) {
				waiting = true; // the game waits if the phone is not being used or there is no message received
			} else {
				waiting = false;
				enemyRow.update(); // the enemy row must be updated constantly
			}

		} else {
			enemyRow.emptyRow(); // if the game is over, the enemy row disappears
		}

	}

	/**
	 * Used to draw every object in the display.
	 * 
	 * @param g2d
	 *            used to display images and text
	 * @see Graphics2D
	 */
	public void render(Graphics2D g2d) {

		if (Game.over) {
			g2d.drawImage(getGameOverImage(), 125, 100, null);
		} else {
			g2d.setFont(new Font("monospace", Font.BOLD, 15));
			g2d.setColor(new Color(255, 255, 255));
			g2d.drawString("Level: " + Game.level, 20, 420);
			g2d.drawString("Score: " + Game.score, 20, 450);
			g2d.drawString("Current Row: " + Game.getRowString(Game.randomRowType), 450, 420);
			g2d.drawString("Next Row: " + Game.getRowString(Game.nextRowType), 450, 450);
		}

		for (int i = 0; i < bulletList.size(); i++) { // for every bullet in the linked list
			TempBullet = bulletList.get(i);

			TempBullet.render(g2d); // the bullet is shown in the display
		}

		enemyRow.render(g2d);

		if (waiting) {
			g2d.drawImage(getWaitingImage(), 100, 100, null); // draws waiting image on the screen
			g2d.drawImage(getWaiting2Image(), 160, 150, null); // draws waiting2 image on the screen
		}

	}

	/**
	 * Travels through the list of bullets to update every object. Updates the
	 * bullet delay timer.
	 */
	public void updateBullets() {
		for (int i = 0; i < bulletList.size(); i++) { // for every bullet in the linked list
			TempBullet = bulletList.get(i);

			if (TempBullet.getY() < 0) {
				removeBullet(TempBullet);
			} // if the bullet is out of bounds, it is deleted from the list of bullets

			TempBullet.update(); // the bullet moves
		}

		if (bulletDelay > 0) {
			bulletDelay--; // updates the bullet delay
		}
	}

	/**
	 * Sets the new row the random number previously established and creates a new
	 * random id to the next row list.
	 */
	public void setNewRandom() {
		Game.randomRowType = Game.nextRowType;
		Game.nextRowType = random.nextInt(6);
	}

	/**
	 * Adds a {@code Bullet} to the list of bullets.
	 * 
	 * @param block
	 *            {@code Bullet} to be added
	 */
	public void addBullet(Bullet block) {
		bulletList.add(block);
	}

	/**
	 * Removes a {@code Bullet} from the list of bullets.
	 * 
	 * @param block
	 *            {@code Bullet} to be added
	 */
	public void removeBullet(Bullet block) {
		bulletList.remove(block);
	}

	/**
	 * Removes an {@code Enemy} from the list of enemies.
	 * 
	 * @param block
	 *            {@code Enemy} to be added
	 */
	public void removeEnemy(Enemy block) {
		enemyRow.removeEnemy(block);
	}

	/**
	 * Gets the list of bullets.
	 * 
	 * @return a {@code LinkedList} of {@code Bullet} objects
	 */
	public LinkedList<Bullet> getBullets() {
		return bulletList;
	}

	/**
	 * Gets the list of enemies.
	 * 
	 * @return the {@code EnemyRow} object
	 */
	public EnemyRow getEnemies() {
		return enemyRow;
	}

	/**
	 * Gets the image of waiting for the display.
	 * 
	 * @return the {@code Image} object
	 */
	public Image getWaitingImage() {
		ImageIcon icon = new ImageIcon(getClass().getResource(waitingimage)); // player image
		return icon.getImage(); // returns the player image resized
	}

	/**
	 * Gets the image of waiting2 for the display.
	 * 
	 * @return the {@code Image} object
	 */
	public Image getWaiting2Image() {
		ImageIcon icon = new ImageIcon(getClass().getResource(waiting2image)); // player image
		return icon.getImage(); // returns the player image resized
	}

	/**
	 * Gets the image of game over for the display.
	 * 
	 * @return the {@code Image} object
	 */
	public Image getGameOverImage() {
		ImageIcon icon = new ImageIcon(getClass().getResource(gameoverimage)); // player image
		return icon.getImage(); // returns the player image resized
	}

}
