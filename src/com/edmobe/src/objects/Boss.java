package com.edmobe.src.objects;

import java.awt.Image;

import javax.swing.ImageIcon;

import com.edmobe.src.Controller;

public class Boss extends Enemy{

	String bossimage = "/images/enemy.png"; // boss image path.
	
	public Boss(int x, int y, Controller c) {
		super(x, y, c);	
	}
	
	public Image getImage() {
		ImageIcon icon = new ImageIcon(getClass().getResource(bossimage)); // enemy image.
		return icon.getImage(); // returns the player image resized.

	}
}
