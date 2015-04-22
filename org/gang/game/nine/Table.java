package org.gang.game.nine;

import javax.swing.table.DefaultTableModel;

import static org.gang.game.nine.Rank.SIX;
import static org.gang.game.nine.Rank.SEVEN;
import static org.gang.game.nine.Rank.EIGHT;
import static org.gang.game.nine.Rank.NINE;
import static org.gang.game.nine.Rank.TEN;
import static org.gang.game.nine.Rank.JACK;
import static org.gang.game.nine.Rank.QUEEN;
import static org.gang.game.nine.Rank.KING;
import static org.gang.game.nine.Rank.ACE;

/**   | 0 1 2 3  4 5 6 7 8
 * ---+-------------------
 * D 0| 6 7 8 9 10 J Q K A
 * S 1| 6 7 8 9 10 J Q K A
 * H 2| 6 7 8 9 10 J Q K A
 * C 3| 6 7 8 9 10 J Q K A 
 * @author asmirnov
 */
public class Table 
	extends DefaultTableModel
{
	/**
	 * @param rows
	 * @param columns
	 */
	private Table (int rows, int columns)
	{
		super (rows, columns);
		numberOfCards = 0;		
	}
	
	public static Table getTable(int r, int c)
	{
		if ( table == null)
			table = new Table (r,c);

		return table;
	}
	
	public void clearTable()
	{
		for (int i = 0; i < getRowCount(); i++ )
			for (int j = 0; j < getColumnCount(); j++)
			{
				Card card = (Card)getValueAt(i, j);
				if (card != null)
				{
					setValueAt(null, i, j);
					Deck.getDeck().addCard(card);
				}
			}				
	}
	
	/**
	 * @param row
	 * @return
	 */
	public boolean isRowEmpty( Suit suit)
	{
		for (int column=0; column<getColumnCount(); column++)
			if ( getValueAt( getRow (suit), column) != null )
				return false;
		return true;
	}
	
	/**
	 * @param card
	 * @return
	 */
	public Card putCard(Card card)
	{
		setValueAt(card, getRow(card.getSuit()), getColumn(card.getRank()));
		
		numberOfCards++;
		
		return card;
	}
	
	/**
	 * @param row
	 * @return
	 */
	public Rank getMostRightCard(Suit suit) {
		
		Rank[] ranks = {ACE,KING,QUEEN,JACK,TEN,NINE,EIGHT,SEVEN,SIX};

		for (Rank rank:ranks) {
			if (getValueAt(getRow(suit), getColumn(rank)) != null)
				return rank;
		}
		return null;
	}
	
	/**
	 * @param suit
	 * @return
	 */
	public Rank getMostLeftCard(Suit suit) {
		
		Rank[] ranks = {SIX,SEVEN,EIGHT,NINE,TEN,JACK,QUEEN,KING,ACE};
		
		for (Rank rank:ranks) {
			if (getValueAt(getRow(suit), getColumn(rank)) != null)
				return rank;
		}
		return null;
	}
	
	/**
	 * @param suit
	 * @return
	 */
	static public int getRow(Suit suit) {
		switch (suit) {
		case DIAMONDS:			return 0;
		case SPADES:			return 1;
		case HEART:			return 2;
		case CLUBS:			return 3;
		}
		return -1;
	}
	
	
	/**
	 * @param rank
	 * @return
	 */
	static public int getColumn(Rank rank)
	{
		switch (rank)
		{
		case SIX:   return 0;
		case SEVEN: return 1;
		case EIGHT: return 2;
		case NINE:  return 3;
		case TEN:   return 4;
		case JACK:  return 5;
		case QUEEN: return 6;
		case KING:  return 7;
		case ACE:   return 8;
		}
		return -1;		
	}
	
	public String toString()
	{
		StringBuffer result = new StringBuffer();
		
		char[] rowName = {'D','S','H','C'};
		
		result.append("  | ");
		for (Rank r:Rank.values())
			result.append(r.getLetter()+" ");
		
		result.append("\n--+--------------------\n");
		
		for ( int row = 0; row < getRowCount(); row++)
		{
			result.append(rowName[row]+" | ");
			for (int column = 0; column < getColumnCount(); column++)
			{
				Card card = (Card)getValueAt(row, column);
				if (card == null)
					result.append("- ");
				else
					result.append(card.getRank().getLetter()+" ");
			}
			
			result.append("\n");
		}
		
		
		return result.toString();
	}
	

	/**
	 * @return
	 */
	public int getNumberOfCards() {
		return numberOfCards;
	}

	public boolean canPutCard(Card card)
	{
		if (card == null)
			return false;
		else if (card.getRank() == NINE)
			return true;
		else if (isRowEmpty(card.getSuit()))
			return false;
		else
		{
			Suit suit = card.getSuit();
			return (
				 card.equals (suit, Rank.getPrevRank (getMostLeftCard (suit) ) ) || 
				 card.equals (suit, Rank.getNextRank (getMostRightCard(suit) ) ) 
				);
		}
	}
	
	private int numberOfCards;
	
	private static Table table = null;
}
