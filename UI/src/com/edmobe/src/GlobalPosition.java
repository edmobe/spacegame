package com.edmobe.src;

import java.awt.Rectangle;

/**
 * This class is used to assign coordinates.
 * 
 * @author edmobe
 */
public class GlobalPosition {
	public int x;
	public int y;
	public int width; // width of the object
	public int height; // height of the object

	/**
	 * {@code GlobalPosition} constructor.
	 * 
	 * @param x
	 *            the specified X coordinate
	 * @param y
	 *            the specified Y coordinate
	 */
	public GlobalPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Sets the bounds for the object (used in collisions).
	 * 
	 * @return the {@code Rectangle} object.
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
}