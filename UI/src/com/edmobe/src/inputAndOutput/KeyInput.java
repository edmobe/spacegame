package com.edmobe.src.inputAndOutput;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.edmobe.src.Controller;
import com.edmobe.src.Game;
import com.edmobe.src.objects.Bullet;
import com.edmobe.src.objects.Player;

/**
 * Detects key inputs
 * 
 * @author edmobe
 *
 */
public class KeyInput extends KeyAdapter {

	Player player;
	Controller c;

	public KeyInput(Player player, Controller c) {
		this.player = player;
		this.c = c;
	}

	public void keyPressed(KeyEvent event) { // when a key is pressed.
		int key = event.getKeyCode();

		player.keyPressed(event); // calls player's keyPressed method.
		if (key == KeyEvent.VK_SPACE) { // if the key pressed was space
			if (c.bulletDelay == 0) { // if the player has not shot recently
				c.addBullet(new Bullet(player.x + 17, player.y - 50)); // creates a bullet
				if (Game.level < 5) { // if the game is starting
					c.bulletDelay = 35 - Game.level;
				} else { // for high levels
					c.bulletDelay = 30;
				}
			}
		} else if (key == KeyEvent.VK_S) { // if the key pressed is space
			Game.usePhone = !Game.usePhone; // the phone will not be used
		}
	}

	public void keyReleased(KeyEvent event) { // when a key is released.
		player.keyReleased(event); // calls player's keyReleased method.
	}
}