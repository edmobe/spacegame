package com.edmobe.src.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.edmobe.src.Controller;
import com.edmobe.src.objects.Bullet;
import com.edmobe.src.objects.Player;

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
		if (key == KeyEvent.VK_SPACE) {
			if (c.bulletDelay == 0) {
				c.addBullet(new Bullet(player.x + 17, player.y - 50));
				c.bulletDelay = 50;
			}
		}
		// adds a bullet when the space key is pressed and the player hasn't shoot recently.
	}

	public void keyReleased(KeyEvent event) { // when a key is released.
		player.keyReleased(event); // calls player's keyReleased method.
	}
}