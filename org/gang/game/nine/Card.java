package org.gang.game.nine;

import java.util.Comparator;

public class Card {

	private Rank rank;

	private Suit suit;
	
	public static class CardComparator implements Comparator
	{
		public int compare (Object o1, Object o2)
		{
			Card c1 = (Card)o1, c2 = (Card) o2;
			
			if (c1.getSuit().equals(c2.getSuit()))
				return (c1.getRank().toInteger() - c2.getRank().toInteger());
			else
				return c1.getSuit().toInteger() - c2.getSuit().toInteger();
		}
	}

	public Card (Rank rank, Suit suit)
	{
		this.rank = rank;
		this.suit = suit;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (!(obj instanceof Card))
			return false;

		return ((rank == ((Card)obj).rank) && (suit == ((Card)obj).suit));
	}
	
	public boolean equals(Suit suit, Rank rank)
	{
		return (rank == this.rank) && (suit == this.suit);
	}

	public String toString()
	{
		return new String(rank.toString() + ", " + suit.toString());
	}
	
	public String toShortString()
	{
		return new String(rank.getLetter() +""+ suit.getLetter());
	}

	public Rank getRank(){
		return this.rank;
	}

	public Suit getSuit() {
		return suit;
	}
}



