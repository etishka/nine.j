package org.gang.game.nine.interfaces;

import java.util.ArrayList;

import org.gang.game.nine.Card;
import org.gang.game.nine.Rank;
import org.gang.game.nine.Suit;

public interface Player {

	public abstract void addCard(Card card);

	public abstract Card makeTurn();

	public abstract int IHave(Suit suit, Rank rank);

	public abstract ArrayList<Card> getCards();

	public abstract String getName();

	public abstract void setName(String name);
	
	public abstract int getTurnTime();

}