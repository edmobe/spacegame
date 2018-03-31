package com.edmobe.src;

import javax.swing.JFrame;

// import com.edmobe.src.lists.LinkedList;
// import com.edmobe.src.objects.Bullet;

/**
 * Game's main class. Creates a frame.
 * @author edmobe
 *
 */
public class MainClass {
	
	public static void main(String args[]){		
		JFrame frame = new JFrame(); // new frame instantiated.
		frame.pack(); // frame positioned on the display.
		frame.setSize(640, 480); // setting frame dimensions.
		frame.setResizable(false); // the window will not be resizable.
		frame.setLocationRelativeTo(null); // when the game starts, the window will be positioned
			// at the center of the screen.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // when the exit button is pressed,
			// the console execution will stop.
		frame.add(new Game()); // the game is initialized and added to the frame.
		frame.setVisible(true); // the window will be visible.
	}
}