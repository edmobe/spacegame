package com.edmobe.src.objects;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import com.edmobe.src.GlobalPosition;

/*
 * Player object.
 */
public class Player extends GlobalPosition{
	
	private String playerimage = "/images/spaceship.png";

	public Player(int x, int y){
		super(x, y);
	}
	
	public void update(){
		//x += 1;
		y += 1;
	}
	
	public void draw(Graphics2D g2d){
		g2d.drawImage(getPlayerImage(), x, y, null);
	}
	
	public Image getPlayerImage(){
		ImageIcon icon = new ImageIcon(getClass().getResource(playerimage)); 
		Image newsize = icon.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_DEFAULT);
		icon = new ImageIcon(newsize);
		return icon.getImage();
	}
	
}
