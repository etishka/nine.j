package org.gang.game.nine;

import java.util.ArrayList;

public class Deck {

	/**
	 * Make new Deck of known Cards and known Suits. Calculate number of cards in the deck.  
	 */
	private Deck()
	{
		cards = new ArrayList<Card>();
		
		for (Rank c:Rank.values())
			for (Suit s: Suit.values())
				cards.add(new Card (c, s));
	}
	
	public void addCard(Card card)
	{
	    //TODO: ensure card is not in already
		cards.add(card);
	}
	
	/**
	 * Initially shuffle the deck
	 * Override this method if want implement another shuffle algorithm
	 */
	public void shuffle()
	{
		for (int c1 = 0; c1 < cards.size(); c1++ )
		{
			Card tmp = cards.get(c1);

			int c2 = ((int)(Math.random()*100))%cards.size();
			
			cards.set(c1, cards.get(c2));
			cards.set(c2, tmp);
			
		}
	}
	
	/**
	 * Produce new card deck, shuffle them
	 * @return new shuffled deck
	 */
	public static Deck getDeck()
	{
		if (deck == null)
			deck = new Deck();

		return deck;
	}
	
	
	/**
	 * Get random card from the deck
	 * number of cards in the deck decreases by one after this operation.
	 * @return
	 */
	public Card getCard()
	{
		Card result = null;
		
		if (cards.size() > 0)
		{
			int index = ((int)(Math.random()*100))%cards.size();
			result = cards.remove(index);
		}
		
		return result;
	}
	
	public String toString()
	{
		StringBuffer buf = new StringBuffer();
		for (Card card:cards)
			buf.append(card+"\n");
		buf.append("====\n");
		buf.append("Total:"+cards.size());
		return buf.toString();
	}

	private static Deck deck;
	
	private ArrayList<Card> cards;	
}
