package org.gang.game.nine.players;

import org.gang.game.nine.Card;
import org.gang.game.nine.Rank;
import org.gang.game.nine.Suit;
import org.gang.game.nine.Table;

public class AdvancedPlayer extends PlayerBase {
	
	private boolean initialiazed = false;

	private int rowSize = Suit.values().length;

	private int colSize = Rank.values().length;
	
	private int[][] matrix = new int[rowSize][colSize];

	private int alfa = 2;

	private int beta = 3;

	private int startIndex = Table.getColumn(Rank.NINE);;


	/**
	 * @param table
	 * @param name
	 */
	public AdvancedPlayer(final Table table, final String name) {
		super(table, name);
		setTurnTime (1500);
	}


	/**
	 * @param table
	 * @param name
	 * @param alfa
	 * @param beta
	 */
	public AdvancedPlayer(final Table table, final String name, int alfa, int beta)
	{
		this(table, name);
		this.alfa = alfa;
		this.beta = beta;
	}
	
	
	@Override
	public Card makeTurn() {
		if (!initialiazed)
			initMatrix();

		int[][] avalaible = new int[rowSize][colSize];
		
		for(Suit suit: Suit.values()) {

			Rank r = table.getMostRightCard(suit);
			Rank rank = (r==null?Rank.NINE:Rank.getNextRank(r));
			
			if (rank != null) {
				avalaible [Table.getRow(suit)]
				          [Table.getColumn(rank)] = 1;
			}
			
			r = table.getMostLeftCard(suit);
			rank = (r==null?Rank.NINE:Rank.getPrevRank(r));
			
			if (rank != null) {
				avalaible[Table.getRow(suit)]
				         [Table.getColumn(rank)] = 1;				
			}
		}
		Card mostSuitable = mostSuitable (avalaible);
		
		return mostSuitable == null? null: 
			putIfIHave (
				mostSuitable.getSuit(), 
				mostSuitable.getRank()
				);
	}

	/**
	 * @param a
	 * @return
	 */
	private Card mostSuitable(int[][] a)
	{
		int[][] result = new int[rowSize][colSize];
		int i, j, max, r =0, c = 0;
		
		for(i = 0; i < rowSize; ++i) {
			for(j = 0; j < colSize; ++j) {
				result[i][j] = matrix[i][j]*a[i][j];
			}
		}
				
		max = 0;
		
		for(i = 0; i < rowSize; ++i) {
			for(j = 0; j < colSize; ++j) {
				if (result[i][j] > max)
				{
					r = j;
					c = i;
					max = result[i][j];
				}
			}
		}
		
		if (max == 0)
			return null;
		
		return new Card(Rank.getRank(r), Suit.getSuit(c));
	}
	
	
	/**
	 * 
	 */
	private void initMatrix() 
	{
		int i, j, sum;
		for(i = 0; i < cards.size(); ++i)
			matrix[Table.getRow(cards.get(i).getSuit())][Table.getColumn(cards.get(i).getRank())] = 1;

		//firts we calc the weigth 
		for(i = 0; i < rowSize; ++i)
			for(j = startIndex; j < colSize; ++j)
				matrix[i][j] = matrix[i][j]*alfa*(j + 1 - startIndex);
				
		for(i = 0; i < rowSize; ++i)
			for(j = startIndex -1; j > -1 ; --j)
				matrix[i][j] = matrix[i][j]*alfa*(startIndex + 1 - j);

		//printMatrix();
		
		//second we calc the neigbor weigth
		for(i = 0; i < rowSize; ++i)
			for(j = startIndex; j < colSize -1; ++j)
				if (matrix[i][j+1] != 0)
					matrix[i][j] = matrix[i][j]*beta;
				
		for(i = 0; i < rowSize; ++i)
			for(j = startIndex -1; j > 0 ; --j)
				if (matrix[i][j-1] != 0)
					matrix[i][j] = matrix[i][j]*beta;
		
		//printMatrix();		
		
		//third we sum all from the ends
		for(i = 0; i < rowSize; ++i){
			sum = 0;
			for(j = colSize -1; j > startIndex -1 ; --j) {
				if (matrix[i][j] != 0)
					matrix[i][j] = matrix[i][j] + sum;
				sum += matrix[i][j];
			}
		}
		
		for(i = 0; i < rowSize; ++i){
			sum = 0;
			for(j = 0; j < startIndex + 1 ; ++j) {
				if (matrix[i][j] != 0)
					matrix[i][j] = matrix[i][j] + sum;
				sum += matrix[i][j];
			}
		}

		initialiazed = true;
		
	}
	
	/**
	 * 
	 */
	private void printMatrix()
	{
		System.out.println("<<<<<<<<<<<<<<< MATRIX " + getName() + ">>>>>>>>>>>>>>>");		
		int i,j;
		for(i = 0; i < rowSize; ++i) {
			for(j = 0; j < colSize ; ++j)
				System.out.print(matrix[i][j] + " ");
			
			System.out.print("\n");
		}

		System.out.println("<<<<<<<<<<<<<<< MATRIX " + getName() + ">>>>>>>>>>>>>>>");		
	}
}
