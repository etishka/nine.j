package org.gang.game.nine;

public enum Rank {
	//TODO: look for actual scores
	SIX  (9, '6'),
	SEVEN(8, '7'),
	EIGHT(7, '8'),
	NINE (6, '9'),
	TEN  (5, 'T'),
	JACK (4, 'J'),
	QUEEN(3, 'Q'),
	KING (2, 'K'),
	ACE  (1, 'A');
	
	private int score;
	private char letter;
	
	Rank(int score, char letter)
	{
		this.score = score;
		this.letter = letter;
	}

	public int getScore() {
		return score;
	}
	
	public char getLetter()
	{
		return letter;
	}
	
	public int toInteger()
	{
		switch (letter)
		{
		case '6': return 0;
		case '7': return 1;
		case '8': return 2;
		case '9': return 3;
		case 'T': return 4;
		case 'J': return 5;
		case 'Q': return 6;
		case 'K': return 7;
		case 'A': return 8;
		}
		return -1;
	}
	
	static public Rank getNextRank(Rank rank) {
		switch (rank) {
		case SIX: 	return SEVEN;
		case SEVEN: return EIGHT;
		case EIGHT: return NINE;
		case NINE: 	return TEN;
		case TEN: 	return JACK;
		case JACK: 	return QUEEN;
		case QUEEN: return KING;
		case KING: 	return ACE;
		case ACE: 	return null;
		}
		return null;
	}
	
	static public Rank getPrevRank(Rank rank) {
		switch (rank) {
		case SIX: 	return null;
		case SEVEN: return SIX;
		case EIGHT: return SEVEN;
		case NINE: 	return EIGHT;
		case TEN: 	return NINE;
		case JACK: 	return TEN;
		case QUEEN: return JACK;
		case KING: 	return QUEEN;
		case ACE: 	return KING;
		}
		return null;
	}
	

	static public Rank getRank(int value) {
		switch (value) {
		case 0:	return Rank.SIX;
		case 1:	return Rank.SEVEN;
		case 2:	return Rank.EIGHT;
		case 3:	return Rank.NINE;
		case 4:	return Rank.TEN;
		case 5:	return Rank.JACK;
		case 6:	return Rank.QUEEN;
		case 7:	return Rank.KING;
		case 8:	return Rank.ACE;
		}
		return null;
	}
}
