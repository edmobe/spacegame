package com.edmobe.src;

import javax.swing.JFrame;

public class MainClass {
	
	public static void main(String args[]){
		JFrame frame = new JFrame();
		frame.pack();
		frame.setSize(640, 480);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}