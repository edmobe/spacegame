package com.edmobe.src;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.edmobe.src.objects.Player;

/*
 * A class that defines the game's general parameters. Everything inside the class is looped.
 * @author edmobe
 *
 */
public class Game extends JPanel implements ActionListener{

	Timer gamelooptimer;
	Player player;
	
	private static final long serialVersionUID = 1L;

	private String background = "/images/bg.gif";
	
	public Game() {
		setFocusable(true);
		
		gamelooptimer = new Timer(10, this);
		gamelooptimer.start();
		
		player = new Player(50, 50);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.drawImage(getBackgroundImage(), 0, 0, null);
		
		player.draw(g2d);
	}
	
	public Image getBackgroundImage(){
		ImageIcon i = new ImageIcon(getClass().getResource(background));
		return i.getImage();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		player.update();
		repaint();
	}	
}