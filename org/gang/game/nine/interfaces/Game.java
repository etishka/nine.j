package org.gang.game.nine.interfaces;


/*
 *
 * */
public interface Game {

	public void startNewGame() throws Exception;

	/**
	 * Give all cards from the deck to Players
	 * Deck should be empty after this operation. All the cards are on hands 
	 * TODO: sort cards - just for nice visualization
	 */
	public void dealTheCards();
}

