package org.gang.game.nine;

public enum Suit {
	DIAMONDS('D'),
	SPADES('S'),
	HEART('H'),
	CLUBS('C');

    private char letter;

    private Suit(char letter){
        this.letter = letter;
    }

    public char getLetter (){
        return letter;
    }

	static public Suit getSuit (int value)
	{
		switch (value)
		{
			case 0: return Suit.DIAMONDS;
			case 1: return Suit.SPADES;
			case 2: return Suit.HEART;
			case 3: return Suit.CLUBS;
		}
		return null;
	}
	
	public int toInteger()
	{
		switch (letter){
		case 'D': return 0;
		case 'S': return 1;
		case 'H': return 2;
		case 'C': return 3;
		}

		return -1;
	}
}