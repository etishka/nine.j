package org.gang.game.nine.players;

import static org.gang.game.nine.Rank.NINE;
import static org.gang.game.nine.Suit.DIAMONDS;

import org.gang.game.nine.Card;
import org.gang.game.nine.Rank;
import org.gang.game.nine.Suit;
import org.gang.game.nine.Table;

public class SimplePlayer extends PlayerBase {
	
	public SimplePlayer(final Table table, final String name)
	{
		super(table, name);
		setTurnTime (300);
	}

	@Override
	public Card makeTurn() {
		// Simple algorithm

		// If there's no cards on the table
		int iCard =	 IHave (DIAMONDS, NINE);
		
		if (table.getNumberOfCards() == 0 )
			if (iCard != -1)
				// and I have 9 diamonds, put it, return
				return table.putCard(cards.remove(iCard));
			else
				// and I have no 9 diamonds, just return
				return null;
			
		// run through suits
		// put next or prev card if possible, return
		for (Suit suit:Suit.values())
		{
			if ( table.isRowEmpty(suit) ) // do not put nine on this step, put neighbours instead
				continue;
			
			Rank rank = Rank.getNextRank (table.getMostRightCard(suit));
			
			if (rank != null){
				Card result = putIfIHave(suit, rank);
				if (result != null)
					return result;
			}
			
			rank = Rank.getPrevRank (table.getMostLeftCard(suit));
			
			if (rank != null){
				Card result = putIfIHave(suit, rank);
				if (result != null)
					return result;
			}


		}
		
		// put nine if possible, return
		for (Suit suit:Suit.values())
		{
			iCard = IHave (suit, NINE); 
			if (iCard != -1 )
				return table.putCard(cards.remove(iCard));
		}		
		
		// put nothing, return
		return null;
	}
}
