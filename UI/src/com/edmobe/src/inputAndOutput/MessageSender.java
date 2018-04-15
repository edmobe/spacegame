package com.edmobe.src.inputAndOutput;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import com.edmobe.src.Game;

public class MessageSender extends Thread {

	@Override
	public void run() {
		
		while (true) {
			try {
				Socket s = new Socket("192.168.1.130", 5001);
				PrintWriter pw = new PrintWriter(s.getOutputStream());
				pw.write(Game.level + "/" + Game.score + "/" + Game.getRowString(Game.randomRowType) + "/"
						+ Game.getRowString(Game.nextRowType));
				pw.flush();
				pw.close();
				s.close();

			} catch (IOException e) {

			}
		}

	}

}
