package com.edmobe.src;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * A class that defines the game's general parameters. Everything inside the class is looped.
 * @author edmobe
 *
 */
public class Game extends JPanel implements ActionListener{

	Timer gamelooptimer;
	
	private static final long serialVersionUID = 1L;

	public Game() {
		setFocusable(true);
		
		gamelooptimer = new Timer(10, this);
		gamelooptimer.start();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D) g;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}	
}