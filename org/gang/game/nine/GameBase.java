package org.gang.game.nine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.gang.game.nine.interfaces.Game;
import org.gang.game.nine.interfaces.Player;
import org.gang.game.nine.players.PlayerBase;

public abstract class GameBase implements Game {

	protected ArrayList<PlayerBase> players;

	protected int numberOfPlayers;

	private Deck deck = Deck.getDeck();

	protected ArrayList<String> playersNames = getPlayersNames();

	protected Table table = Table.getTable(Suit.values().length,
			Rank.values().length);

	public GameBase() {
		super();
	}

	/**
	 * Give all cards from the deck to Players
	 * Deck should be empty after this operation. All the cards are on hands 
	 * TODO: sort cards - just for nice visualization
	 */
	public void dealTheCards() {
		int playerIndex = 0;
		for (Card card = deck.getCard(); card != null; card = deck.getCard()) 
		{
			players.get(playerIndex).addCard(card);
			playerIndex = (++playerIndex) % numberOfPlayers;
		}
	}

	/**
	 * Return array of player names
	 * @return Return array of player names
	 * TODO: get player names from game settings
	 */
	private ArrayList<String> getPlayersNames() {
		return new ArrayList<String>(Arrays.asList(new String[] { "Mark",
				"Sam", "Jim", "Brain", "5th Player", "6th Player" }));
	}

	/**
	 * DEBUG: print all player's cards
	 */
	private void printPlayers() {
		for (Player p : players) {
			System.out.println(p);
			System.out.println("===");
			printCards(p.getCards());
			System.out.println();
		}
	}

	/**
	 * DEBUG: print specified card collection
	 * @param cards
	 */
	private void printCards(List<Card> cards) {
		for (Card card : cards)
			System.out.println(card);
	}

}