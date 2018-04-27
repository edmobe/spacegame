package com.edmobe.src.enemyrows;

import java.awt.Graphics2D;
import java.util.Random;

import com.edmobe.src.Controller;
import com.edmobe.src.Game;
import com.edmobe.src.lists.Node;
import com.edmobe.src.lists.LinkedList;
import com.edmobe.src.objects.Boss;
import com.edmobe.src.objects.Enemy;

/**
 * D type enemy row.
 * 
 * @author edmobe
 *
 */
public class EnemyRowD extends EnemyRow {

	private int dir = 1;// sets enemy row direction. 1 is right, -1 is left
	private int x = 0; // global row X position

	private Controller c; // controller object that will be used

	Random random = new Random(); // random object that will be used

	public EnemyRowD(Controller c) {

		this.c = c; // links the class with the given controller

		for (int i = 0; i < 5; i++) {
			addEnemy(new Boss(i * 80, 0, this.c)); // there will only be bosses
		}

		bubbleSort(); // bubble sorts the list

	}

	/**
	 * Defines the row behavior.
	 */
	@Override
	public void update() {

		int leftBound = crow.get(0).x; // starting position of the row (from left to right)
		int rightBound = crow.get(crow.size() - 1).x + 50; // finishing position of the row (from left to right)

		if (leftBound < 0 || rightBound > 640) { // if the row is outside of the display

			if (leftBound < 0) { // if the row is outside at the left side
				x = 0;
			} else { // if the row is outside at the right side
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

				Enemy TempEnemy = crow.get(i); // temporal enemy

				int updateId = TempEnemy.update(); // updates the enemy and gets the update id

				if (updateId == 1) { // if the enemy was deleted
					x += 40; // centers the row
					bubbleSort(); // sorts the row
				} else if (updateId == 2) { // if the enemy lost health but did not die
					bubbleSort(); // sorts the row
				}
			}
		}

		for (int i = 0; i < crow.size(); i++) { // for every enemy in the linked list.

			Enemy TempEnemy = crow.get(i); // temporal enemy

			TempEnemy.x = x + i * 80; // updates every enemy position

		}

		x += Game.level * dir * 1; // moves the row's global X position

	}

	/**
	 * Bubble sort method for a circular double linked list.
	 */
	public void bubbleSort() {

		if (crow.size() > 1) { // if the list has more than one objects (has to be sorted)

			boolean wasChanged; // tells if the list was changed

			do {
				Node<Enemy> current = crow.getHead(); // current enemy (starts in the head)
				Node<Enemy> previous = crow.getEnd(); // previous enemy (which is the end)
				Node<Enemy> next = crow.getHead().next; // next enemy
				wasChanged = false; // has not been changed

				if (crow.size() == 2) { // Special case. Otherwise, the next while would loop because it's a circular
										// list.
					if (current.data.health < next.data.health) { // if the two objects must be swapped
						crow.setHead(next);
						crow.setEnd(next);
					}
				}

				while (next != crow.getHead()) { // for all the list

					if (current.data.health < next.data.health) { // if the two objects must be swapped

						wasChanged = true; // the list was changed

						if (current == crow.getHead()) { // if the node to move is the head

							crow.setHead(next); // the new head will be the actual next

						}

						if (current.next == crow.getEnd()) { // if the node to move is before the end

							crow.setEnd(current); // the end will be this node

						}

						Node<Enemy> sig = next.next; // sig = node at two positions after the current node

						/*
						 * Node swap algorithm
						 */
						next.next = current;
						next.previous = previous;
						current.next = sig;
						current.previous = next;
						sig.previous = current;
						previous.next = next;

						previous = next;
						next = current.next;

					} else { // if the two objects must not be swapped
						/*
						 * Move to the next node
						 */
						previous = current;
						current = next;
						next = current.next;
					}
				}
			} while (wasChanged); // repeats the process while the list changes

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
