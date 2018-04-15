package com.edmobe.src.enemyrows;

import java.awt.Graphics2D;
import java.util.Random;

import com.edmobe.src.Controller;
import com.edmobe.src.Game;
import com.edmobe.src.lists.Node;
import com.edmobe.src.lists.LinkedList;
import com.edmobe.src.objects.Boss;
import com.edmobe.src.objects.Enemy;

public class EnemyRowD extends EnemyRow {

	private int dir = 1;
	private int x = 0;

	private Controller c;

	Random random = new Random();

	public EnemyRowD(Controller c) {

		this.c = c;

		for (int i = 0; i < 5; i++) {
			addEnemy(new Boss(i * 80, 0, this.c));
		}

		bubbleSort();

	}

	@Override
	public void update() {

		int leftBound = crow.get(0).x;
		int rightBound = crow.get(crow.size() - 1).x + 50;

		if (leftBound < 0 || rightBound > 640) {

			if (leftBound < 0) {
				x = 0;
			} else {
				x = 640 - 75 * (crow.size());
			}

			for (int i = 0; i < crow.size(); i++) { // for every enemy in the linked list.

				Enemy TempEnemy = crow.get(i);

				if (TempEnemy.y >= 360) {
					Game.over = true;
				} else {
					TempEnemy.y += 20 + (Game.level - 1) * 5;
				}

			}

			dir = -dir;

		} else {

			for (int i = 0; i < crow.size(); i++) { // for every enemy in the linked list.

				Enemy TempEnemy = crow.get(i);

				int updateId = TempEnemy.update();

				if (updateId == 1) {
					x += 40;
					bubbleSort();
				} else if (updateId == 2) {
					bubbleSort();
				}
			}
		}

		for (int i = 0; i < crow.size(); i++) { // for every enemy in the linked list.

			Enemy TempEnemy = crow.get(i);

			TempEnemy.x = x + i * 80;

		}

		x += Game.level * dir * 1;

	}

	public void bubbleSort() {
		
		if (crow.size() > 1) {
			
			boolean wasChanged;

			do {				
				Node<Enemy> current = crow.getHead();
				Node<Enemy> previous = crow.getEnd();
				Node<Enemy> next = crow.getHead().next;
				wasChanged = false;
				
				/*
				 *Special case. Otherwise, the next while would loop because it's a circular list.
				 */
				if (crow.size() == 2) {
					if (current.data.health < next.data.health) {
						crow.setHead(next);
						crow.setEnd(next);
					}
				}

				while (next != crow.getHead()) {

					if (current.data.health < next.data.health) {

						wasChanged = true;

						if (current == crow.getHead()) {

							crow.setHead(next);

						}

						if (current.next == crow.getEnd()) {

							crow.setEnd(current);

						}

						Node<Enemy> sig = next.next;

						next.next = current;
						next.previous = previous;
						current.next = sig;
						current.previous = next;
						sig.previous = current;
						previous.next = next;

						previous = next;
						next = current.next;

					} else {
						previous = current;
						current = next;
						next = current.next;
					}
				}
			} while (wasChanged);

		}

	}

	@Override
	public void render(Graphics2D g2d) { // draws the display
		for (int i = 0; i < crow.size(); i++) { // for every bullet in the linked list.
			TempEnemy = crow.get(i);

			TempEnemy.render(g2d); // the bullet is shown in the display.
		}
	}

	@Override
	public void addEnemy(Enemy block) {
		crow.add(block);
	}

	@Override
	public void removeEnemy(Enemy block) {
		crow.remove(block);
	}

	@Override
	public LinkedList<Enemy> getRow() {
		return crow;
	}
}
