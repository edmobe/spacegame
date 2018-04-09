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
			c.addBullet(new Bullet(player.x + 23, player.y - 10));
		}
		// adds a bullet when the space key is pressed.
	}

	public void keyReleased(KeyEvent event) { // when a key is released.
		player.keyReleased(event); // calls player's keyReleased method.
	}
}