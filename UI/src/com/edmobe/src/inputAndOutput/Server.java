package com.edmobe.src.inputAndOutput;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.edmobe.src.Controller;
import com.edmobe.src.Game;
import com.edmobe.src.objects.Bullet;
import com.edmobe.src.objects.Player;


/**
 * Game's server for the phone input
 * @author edmobe
 *
 */
public class Server extends Thread {

	private static Socket s;
	private static ServerSocket ss;
	private static DataInputStream din;
	private static DataOutputStream dout;

	private static String messageIn;
	private static String messageOut;

	private Player player;
	private Controller c;

	public Server(Player player, Controller c) {

		this.player = player;
		this.c = c;

	}

	@Override
	public void run() {
		
		try {
			
			ss = new ServerSocket(5000); // creates a new ServerSocket at port 5000
			s = ss.accept(); // creates a new socket

			din = new DataInputStream(s.getInputStream()); // gets the data input stream from the socket
			dout = new DataOutputStream(s.getOutputStream()); // gets the data output stream from the socket

			while (true) { // loop
				
				sendMessage();
				
				System.out.println("Send: " + messageOut); // for debugging
				System.out.println("Recieve:" + messageIn); // for debugging

				messageIn = din.readUTF(); // gets the input as a String
				
				if (messageIn != null) { // if there is an input message
					if (messageIn.charAt(1) == 't' && !Game.paused) { // if "shoot" is true
						if (c.bulletDelay == 0) { // if the player has not shot recently
							c.addBullet(new Bullet(player.x + 17, player.y - 50)); // creates a bullet
							if (Game.level < 5) { // if the game is starting
								c.bulletDelay = 35 - Game.level;
							} else { // for high levels
								c.bulletDelay = 30;
							}
						}
					} else if (messageIn.charAt(0) == 'r' && !Game.paused) { // if the direction is right
						player.moveRight();
					} else if (messageIn.charAt(0) == 'l' && !Game.paused) { // if the direction is left
						player.moveLeft();
					} else if (messageIn.charAt(0) == 'c' && !Game.paused) { // if the direction is center
						player.stopMoving();
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Gives the input message
	 * @return input message ({@code String})
	 */
	public static String getMessage() {
		return messageIn;
	}

	/**
	 * Sends a message to the phone
	 */
	public void sendMessage() {
		try {
			messageOut = Game.level + "/" + Game.score + "/" + Game.getRowString(Game.randomRowType) + "/"
					+ Game.getRowString(Game.nextRowType); // gives the game statistics as a string.
			dout.writeUTF(messageOut); // sends the game statistics using the socket
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
