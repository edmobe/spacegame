package com.edmobe.src.objects;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

import com.edmobe.src.Controller;

/**
 * Boss object
 * @author edmobe
 *
 */
public class Boss extends Enemy {

	String bossimage = "/images/boss"; // boss image path.

	Random random = new Random();

	public Boss(int x, int y, Controller c) {
		super(x, y, c);

		health = random.nextInt(4) + 2; // creates a random health boss
	}

	@Override
	public Image getImage() {
		ImageIcon icon = new ImageIcon(getClass().getResource(bossimage + health + ".png")); // enemy image.
		return icon.getImage(); // returns the player image resized.

	}
}
