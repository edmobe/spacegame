package com.edmobe.src;

import java.awt.Rectangle;

/*
 * This class is used to assign coordinates.
 * @author edmobe
 */
public class GlobalPosition {
	public int x;
	public int y;
	public int width;
	public int height;

	public GlobalPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
}