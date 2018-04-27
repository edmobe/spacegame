package com.edmobe.src;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.edmobe.src.enemyrows.EnemyRow;
import com.edmobe.src.inputAndOutput.KeyInput;
import com.edmobe.src.inputAndOutput.Server;
import com.edmobe.src.lists.LinkedList;
import com.edmobe.src.objects.Bullet;
import com.edmobe.src.objects.Player;

/**
 * A class that defines the game's general parameters.
 * 
 * @author edmobe
 *
 */
public class Game extends JPanel implements ActionListener {

	private Timer gamelooptimer; // timer variable for the loop
	private Player player; // player object
	private Controller c; // controller object

	public LinkedList<Bullet> bulletList; // list of bullets
	public EnemyRow enemyRow; // actual row of enemies

	public static int level = 1; // actual level
	public static int score = 0; // actual score
	public static int randomRowType; // actual random row type
	public static int nextRowType; // next random row type

	public static boolean over; // determines if the game is over

	public static boolean useKeyboard; // determines if the phone will be used
	public static boolean paused; // determines if the game is paused
	public static boolean right; // the player moves right
	public static boolean left; // the player moves left

	private static final long serialVersionUID = 1L; // sets an ID to the Game class

	private String background = "/images/bg.gif"; // background image path
	
	private Thread server; // a Thread that receives client data

	/**
	 * {@code Game} constructor.
	 */
	public Game() {

		setFocusable(true); // its main function is allowing the player to control the game right
		// after he runs the MainClass, without having to click on the window

		player = new Player(290, 420); // initializes the player
		c = new Controller(player); // initializes the controller

		bulletList = c.getBullets(); // creates a list of bullets
		enemyRow = c.getEnemies(); // creates the enemy row object

		addKeyListener(new KeyInput(player, c)); // adds key listener for every key event
		
		server = new Server(player, c); // instantiates the Server
		server.start(); // starts the Server thread
		
		gamelooptimer = new Timer(10, this); // instantiates the loop timer (10 ms -> 100 fps)
		gamelooptimer.start(); // starts the game timer

	}

	/**
	 * Paints every image and text on the display.
	 * 
	 * @param g
	 *            {@code Graphics} object
	 */
	public void paint(Graphics g) {
		super.paint(g); // paints on the JPanel

		Graphics2D g2d = (Graphics2D) g; // transfer the graphics into a Graphics2D function

		g2d.drawImage(getBackgroundImage(), 0, 0, null); // draws the background

		c.render(g2d); // draws all the bullets
		player.render(g2d); // draws the player
	}

	/**
	 * Gets the background image.
	 * 
	 * @return the {@code Image} object of the background
	 */
	public Image getBackgroundImage() {
		ImageIcon i = new ImageIcon(getClass().getResource(background)); // background image
		return i.getImage(); // returns the background image
	}

	/**
	 * Game loop.
	 * 
	 * @param e
	 *            {@code ActionEvent} object
	 */
	@Override
	public void actionPerformed(ActionEvent e) { // game loop
		player.update(); // updates the player
		c.update(); // updates the controller
		repaint(); // calls the paint method
	}

	/**
	 * Is an adapter. Transforms the row type from integer to {@code String} in
	 * order to display it on the phone.
	 * 
	 * @param type
	 *            integer that indicates the row type
	 * @return {@code String} that indicates the row type
	 */
	public static String getRowString(int type) {
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

	/**
	 * Gets the {@code Player} object.
	 * 
	 * @return {@code Player} object.
	 */
	public Player getPlayer() {
		return player;
	}
}