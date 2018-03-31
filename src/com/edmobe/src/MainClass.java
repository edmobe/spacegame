package com.edmobe.src;

import javax.swing.JFrame;

//import java.util.LinkedList;
//import com.edmobe.src.lists.LinkedList;
//import com.edmobe.src.objects.Bullet;

/**
 * Game's main class. Creates a frame.
 * @author edmobe
 *
 */
public class MainClass {
	
	public static void main(String args[]){
		/*
		Bullet b1 = new Bullet(0, 0);
		Bullet b2 = new Bullet(0, 0);
		Bullet b3 = new Bullet(1, 1);
		Bullet b4 = new Bullet(2, 2);
		b1.setId("b1");
		b2.setId("b2");
		b3.setId("b3");
		b4.setId("b4");
		LinkedList<Bullet> l1 = new LinkedList<Bullet>();
		System.out.println(l1.toString() + " " + l1.size());
		l1.addLast(b1);
		System.out.println(l1.toString() + " " + l1.size());
		l1.addLast(b2);
		System.out.println(l1.toString() + " " + l1.size());
		l1.addLast(b3);
		System.out.println(l1.toString() + " " + l1.size());
		l1.addLast(b4);
		System.out.println(l1.toString() + " " + l1.size());
		/*
		l1.removeFirst();
		System.out.println(l1.toString() + " " + l1.size());
		l1.remove(b2);
		System.out.println(l1.toString() + " " + l1.size());
		*/
		
		
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