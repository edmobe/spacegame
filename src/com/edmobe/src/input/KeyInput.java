package com.edmobe.src.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.edmobe.src.objects.Player;

public class KeyInput extends KeyAdapter{
	
	Player player; // player object.
	
	public KeyInput(Player player) {
		this.player = player;
	}
	
	public void keyPressed(KeyEvent event) { // when a key is pressed.
		player.keyPressed(event); // calls player's keyPressed method.
	}
	
	public void keyReleased(KeyEvent event) { // when a key is released.
		player.keyReleased(event); //calls player's keyReleased method.
	}	
}