package org.gang.game.nine;

import java.util.ArrayList;

import org.gang.game.nine.players.AdvancedPlayer;
import org.gang.game.nine.players.ConsolePlayer;
import org.gang.game.nine.players.PlayerBase;

//TODO: include accounting
//TODO: add persistence...SQLLite?
public class ConsoleGame extends GameBase {
	
	/**
	 * Construct new game
	 * 
	 * initialize deck, players, table, give cards to players
	 * @param num - number of players. Usually 4
	 */
	public ConsoleGame(int num) {
		
		this.numberOfPlayers = num;
		
		this.players = new ArrayList<PlayerBase>(numberOfPlayers);
		
		Copyright.showCopyright();
		
		//TODO: make more flexible - several human players are possible to. net version?
		int p = 0;
		for (; p < numberOfPlayers-1; p++) {
     		players.add(new AdvancedPlayer(table, playersNames.get(p)));
			//players.add(new SimplePlayer(table, playersNames.get(p)));
		}
		
		players.add(new ConsolePlayer(table));

		dealTheCards();
	}


	/**
	 * Game controller 
	 * @throws Exception 
	 */
	public void startNewGame() throws Exception {
		
		int curPlayer = 0;
		players.get(curPlayer).makeTurn();
		int iter = 0;
		
		while (players.get(curPlayer).getCards().size() != 0) {
			// next player's turn
			curPlayer = (++curPlayer) % numberOfPlayers;

			String change;

			Card newcard = players.get(curPlayer).makeTurn();
			if (newcard == null)
				change = "Nothing changed";
			else
				change = "+" + newcard.toString();

			System.out.println("Turn of " + players.get(curPlayer).getName());
			System.out.println(table.toString());
			System.out.println("-------------------------");
			System.out.println(change + "\n\n");
			Thread.sleep(players.get(curPlayer).getTurnTime());			
			

			if (++iter > 2000)
				throw new Exception ("too many iterations!");

		}
		System.out.println(players.get(curPlayer).getName()+" wins!");
		
	}
}
