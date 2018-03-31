package com.edmobe.src;

import java.awt.Graphics2D;

import java.util.LinkedList;

//import com.edmobe.src.lists.LinkedList;
import com.edmobe.src.objects.Bullet;

public class Controller {
	
	private LinkedList<Bullet> b = new LinkedList<Bullet>();
	
	Bullet TempBullet;
	
	Game game;
	
	
	public Controller(Game game) {
		this.game = game;
	}
	

	public void update() {
		for (int i = 0; i < b.size(); i++) { //cambiar
			TempBullet = b.get(i);
			
			if (TempBullet.getY() < 0) {removeBullet(TempBullet);}
			
			TempBullet.update();
		}
	}
	
	public void render (Graphics2D g2d) {
		for (int i = 0; i < b.size(); i++) { //cambiar
			TempBullet = b.get(i);
			
			TempBullet.render(g2d);
		}
	}

	public void addBullet(Bullet block) {
		b.addLast(block);
	}
	
	public void removeBullet(Bullet block) {
		b.remove(block);
	}
	
}
