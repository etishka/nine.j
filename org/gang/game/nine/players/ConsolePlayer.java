package org.gang.game.nine.players;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.gang.game.nine.Card;
import org.gang.game.nine.Suit;
import org.gang.game.nine.Table;

public class ConsolePlayer extends PlayerBase {

	/**
	 * @param table
	 */
	public ConsolePlayer(final Table table)
	{	
		super (table);
		setTurnTime (0);

		while (getName() == null || getName().equals("")) {
			System.out.print("Please, introduce youself: ");
			try {
				setName( new BufferedReader(new InputStreamReader(System.in)).readLine().trim());
			} catch (Exception e) {
				setName("unknown player");
			}
		}
		System.out.println("I'm glad to see you " + getName());
		System.out.println();

	}
		
	@Override
	public Card makeTurn()
	{
		Card result = null;

	    
		System.out.println(getName() + " your turn. Your cards are:\n");
		Collections.sort (cards , new Card.CardComparator());
		printCards(0);
		
	    ArrayList<Integer> possibleCards = getPossibleCards();
		if (possibleCards.size() == 0) {
			System.out.print("\nYou don't have a card to put \n\n");
			return null;
		}		
		
		Integer val = 0;
		
		while(true) {
			System.out.print("\nPlease enter number of card to put:");
			String token;
			try {
				token = new BufferedReader(new InputStreamReader(System.in)).readLine().trim();
			}
			catch(Exception e) {
				return null;
			}
			
			if (token.isEmpty()) {
				showHint(possibleCards);
				continue;
			}
			else if (token.equals("\\q") || token.equals("\\quit")){
				System.out.println("bye!");
				System.exit(0);
			}
				
			
			try {
				val = Integer.parseInt(token);
			}
			catch(NumberFormatException e) {
				showHint(possibleCards);
				continue;
			}
			
			if ( val < 1 || val > cards.size())
			{
				showHint(possibleCards);
				continue;
			}
			if (possibleCards.contains(val - 1))
				break;
			System.out.println("You can't put this card");
		}
		
		if (val - 1 < cards.size())
			result = table.putCard(cards.remove(val - 1));
		return result;
	}

	/**
	 * @param mode
	 */
	private void printCards(int mode)
	{
		int i = 1;
		switch(mode) {
			case 0:
				Suit s = null;
				for (int c =0; c < cards.size(); c++) {
					Card card = cards.get(c);
					if (!card.getSuit().equals(s))
						System.out.println();
					s = card.getSuit();
					System.out.print(i++ + " - " + card.toShortString() + "; ");
				}
				System.out.println();
				break;
			default:
				for (Card card : cards) {
					System.out.println(i++ + " - " + card);
				}
		}
	}
	
	/**
	 * @return
	 */
	private ArrayList<Integer> getPossibleCards()
	{
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		for( int i = 0; i < cards.size(); ++i )
		{	
			if (table.canPutCard(cards.get(i)))
				result.add(i);
		}
		return result;
	}
	
	/**
	 * @param possible
	 */
	private void showHint(ArrayList<Integer> possible)
	{
	  System.out.print("You must put one of the following cards: ");
	  for(Integer c : possible) {
		  System.out.print((c + 1) + " ");
	  }
	  System.out.print("\n");
	}
}
