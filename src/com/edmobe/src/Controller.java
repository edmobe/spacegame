package com.edmobe.src;

import java.awt.Graphics2D;

import com.edmobe.src.lists.LinkedList;
import com.edmobe.src.objects.Bullet;

public class Controller {
	
	private LinkedList<Bullet> b = new LinkedList<Bullet>(); // bullet linked list.
	
	Bullet TempBullet; // temporal bullet.
	
	Game game; // game object.
	
	
	public Controller(Game game) {
		this.game = game;
	}
	

	public void update() {
		for (int i = 0; i < b.size(); i++) { // for every bullet in the linked list.
			TempBullet = b.get(i);
			
			if (TempBullet.getY() < 0) {removeBullet(TempBullet);} // if the bullet is out of
				// bounds, it is deleted.
			
			TempBullet.update(); // the bullet moves.
		}
	}
	
	public void render (Graphics2D g2d) {
		for (int i = 0; i < b.size(); i++) { // for every bullet in the linked list.
			TempBullet = b.get(i);
			
			TempBullet.render(g2d); // the bullet is shown in the display.
		}
	}

	public void addBullet(Bullet block) {
		b.add(block);
	}
	
	public void removeBullet(Bullet block) {
		b.remove(block);
	}
	
}
