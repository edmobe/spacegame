package com.edmobe.src;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.edmobe.src.input.KeyInput;
import com.edmobe.src.objects.Player;

/*
 * A class that defines the game's general parameters. Everything inside the class is looped.
 * @author edmobe
 *
 */
public class Game extends JPanel implements ActionListener{

	Timer gamelooptimer; // timer variable for the loop.
	Player player; // player object.
	Controller c; // controller object.
	
	private static final long serialVersionUID = 1L; // sets an ID to the Game class.

	private String background = "/images/bg.gif"; // background image path.
	
	public Game() {
		setFocusable(true); // its main function is allowing the player to control the game right.
			// after he runs the MainClass, without having to click on the window.
		
		gamelooptimer = new Timer(10, this); // instantiates the loop timer (10 ms -> 100 fps).
		gamelooptimer.start(); // starts the game timer.
		
		player = new Player(290, 400); // initializes the player.
		c = new Controller(this); // initializes the controller.
		
		addKeyListener(new KeyInput(player, c)); // adds key listener for every key event.
	}
	
	public void paint(Graphics g) {
		super.paint(g); // paints on the JPanel.
		
		Graphics2D g2d = (Graphics2D) g; // transfer the graphics into a Graphics2D function.
		
		g2d.drawImage(getBackgroundImage(), 0, 0, null); // draws the background.
		
		player.draw(g2d); // draws the player.
		c.render(g2d); // draws all the bullets.
	}
	
	public Image getBackgroundImage(){
		ImageIcon i = new ImageIcon(getClass().getResource(background)); // background image.
		return i.getImage(); // returns the background image.
	}

	@Override
	public void actionPerformed(ActionEvent e) { // game loop.
		player.update(); // updates the player.
		c.update(); // updates the controller.
		repaint(); // calls the paint method.
	}	
}