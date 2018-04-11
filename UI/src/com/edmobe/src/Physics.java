package com.edmobe.src;

import com.edmobe.src.lists.LinkedList;
import com.edmobe.src.objects.Bullet;
import com.edmobe.src.objects.Enemy;

public class Physics {

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
