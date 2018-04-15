package com.edmobe.src.inputAndOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import com.edmobe.src.Controller;
import com.edmobe.src.objects.Bullet;
import com.edmobe.src.objects.Player;

public class Server extends Thread {

	private static Socket s;
	private static ServerSocket ss;
	private static InputStreamReader isr;
	private static BufferedReader br;
	private static String message;
	
	private Player player;
	private Controller c;



	public Server(Player player, Controller c) {
		
		this.player = player;
		this.c = c;
		
	}
	
	@Override
	public void run() {

		try {

			ss = new ServerSocket(5000);

			while (true) {

				s = ss.accept();
				isr = new InputStreamReader(s.getInputStream());
				br = new BufferedReader(isr);
				message = br.readLine();

				if (message.charAt(1) == 't') {
					c.addBullet(new Bullet(player.x + 17, player.y - 50));
				} else if (message.charAt(0) == 'r') {
					player.moveRight();
				} else if (message.charAt(0) == 'l') {
					player.moveLeft();
				} else if (message.charAt(0) == 'c') {
					player.stopMoving();
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getMessage() {
		return message;
	}

}
