package com.edmobe.src;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * A class that defines the game's general parameters.
 * @author edmobe
 *
 */
public class Game extends JPanel{

	
	private static final long serialVersionUID = 1L;

	public Game() {
		setFocusable(true);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D) g;
	}	
}