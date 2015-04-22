package org.gang.game.nine.players;

import java.util.ArrayList;

import org.gang.game.nine.Card;
import org.gang.game.nine.Rank;
import org.gang.game.nine.Suit;
import org.gang.game.nine.Table;
import org.gang.game.nine.interfaces.Player;


public abstract class PlayerBase implements Player 
{


	public PlayerBase(final Table table)
	{
		this.table = table;
	}

	public PlayerBase(final Table table, final String name)
	{
		this(table);
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see org.gang.game.nine.players.PlayerI#addCard(org.gang.game.nine.Card)
	 */
	public void addCard (Card card)
	{
		cards.add(card);
	}
	
	/* (non-Javadoc)
	 * @see org.gang.game.nine.players.PlayerI#makeTurn()
	 */
	public abstract Card makeTurn();
	
	public int IHave (Suit suit, Rank rank)
	{
		for (int index = 0; index < cards.size(); index++)
		{
			Card card = cards.get(index);
			if (card.getSuit().equals(suit) && card.getRank().equals(rank))
				return index;
		}
				
		return -1;
	}
	
	protected Card putIfIHave(Suit suit, Rank rank) {

		int cardIndex = IHave (suit, rank);

		if (cardIndex != -1) // and i have such card
			return table.putCard(cards.remove(cardIndex)); // then put it,

		return null;
	}
	
	public String toString()
	{
		return "Name:" + name + ", num of cards="+cards.size();
	}

	/* (non-Javadoc)
	 * @see org.gang.game.nine.players.PlayerI#getCards()
	 */
	public ArrayList<Card> getCards() {
		return cards;
	}

	/* (non-Javadoc)
	 * @see org.gang.game.nine.players.PlayerI#getName()
	 */
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see org.gang.game.nine.players.PlayerI#setName(java.lang.String)
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public int getTurnTime() {
		return turnTime;
	}

	public void setTurnTime(int turnTime) {
		this.turnTime = turnTime;
	}	
	
	
	protected ArrayList<Card> cards = new ArrayList<Card>();

	private String name;

	// private int money;
	
	protected final Table table;
	
	private int turnTime;

}
