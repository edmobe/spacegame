package com.edmobe.src.input;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import com.sun.glass.events.KeyEvent;

public class Server {
	
	private static Socket s;
	private static ServerSocket ss;
	private static InputStreamReader isr;
	private static BufferedReader br;
	private static String message;
	private static int dir; // 0 = center, -1 = left, 1 = right.
	
		
	public static void main(String[] args) throws AWTException {
		
		try {
			
			ss = new ServerSocket(4000);
			Robot robot = new Robot();
			
			while (true) {
				
				s = ss.accept();
				isr = new InputStreamReader(s.getInputStream());
				br = new BufferedReader(isr);
				message = br.readLine();
				
				System.out.println(message);
				
				if (message.charAt(1) == 't') {
					robot.keyPress(KeyEvent.VK_SPACE);
					robot.keyRelease(KeyEvent.VK_SPACE);
				} else if (message.charAt(0) == 'r') {
					dir = 1;
				} else if (message.charAt(0) == 'l') {
					dir = -1;
				} else if (message.charAt(0) == 'c') {
					dir = 0;
				}
				
				move(robot);
				
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void move(Robot robot) {
		
		if (dir == 1) {
			robot.keyPress(KeyEvent.VK_RIGHT);
			robot.keyRelease(KeyEvent.VK_LEFT);
		} else if (dir == -1) {
			robot.keyPress(KeyEvent.VK_LEFT);
			robot.keyRelease(KeyEvent.VK_RIGHT);
		} else {
			robot.keyRelease(KeyEvent.VK_RIGHT);
			robot.keyRelease(KeyEvent.VK_LEFT);
		}
		
	}
	
}
