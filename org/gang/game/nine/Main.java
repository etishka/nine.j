package org.gang.game.nine;

import org.gang.game.nine.interfaces.Game;

public class Main {
	/**
	 * Main function
	 * 
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		Game game = new ConsoleGame(4);
		game.startNewGame();
	}

}
