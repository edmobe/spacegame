package com.edmobe.src;

import com.edmobe.src.lists.LinkedList;
import com.edmobe.src.objects.Bullet;
import com.edmobe.src.objects.Enemy;

/**
 * Manages game's collisions.
 * 
 * @author edmobe
 *
 */
public class Physics {

	/**
	 * Verifies if a collision occurred.
	 * 
	 * @param e
	 *            enemy
	 * @param b
	 *            list of bullets
	 * @return boolean that indicates if the collision occurred
	 */
	public static boolean BulletCollision(Enemy e, LinkedList<Bullet> b) {

		for (int i = 0; i < b.size(); i++) {

			if (e.getBounds().intersects(b.get(i).getBounds())) {
				b.remove(b.get(i));
				return true;
			}

		}

		return false;
	}

}
