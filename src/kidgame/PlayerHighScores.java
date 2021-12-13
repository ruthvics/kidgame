/**
* Inherits PlayerList and has a couple more functionality to store highScores
* Class: CS-170
* Section: 1
* Assignment: Final Project
*
* @author  Ruthvicsai sivakumar
* @version 1.0
* @since   2021-12-8
*/
package kidgame;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;

public class PlayerHighScores extends PlayerList {
	

	   /**
	   * Defualt Constructor calls {@link #initalizeWriter()} and {@link #fileToList()}
	   * 
	   */
	PlayerHighScores()
	{
		initalizeWriter();
		fileToList();
	}

	
	   /**
	   * Adds a new player and score. Checks if it is top 5 score, and stores accordingly
	   * @param newPlayer A object of Player class to add to the score
	   * @return boolean true if played added to top 5 scores, false if not
	   */
	public boolean newPlayerScore(Player newPlayer)
	{
		if(newPlayer.getPlayerName().equals("") || newPlayer.getPlayerName().equals(" "))
		{
			return false;
		}
		if(numberOfPlayers() >= 5)
		{
			if(compareScores(newPlayer) < 0)
			{
				return false;
				
			}
			else
			{
				removeLowestPlayer();
				addPlayer(newPlayer);
				return true;
			}
		}
		else
		{
			addPlayer(newPlayer);
			return true;
		}
	}
	
	   /**
	   * Compares the scores of all the players in the list to the passed in player
	   * @param newPlayer A object of Player to compare to the rest of the Players in the list
	   * @return largerThan The number of items it is larger than
	   */
	protected int compareScores(Player newPlayer)
	{
		if(newPlayer.compareTo(Collections.min(players)) <= 0)
		{
			return -99;
		}
		else
		{
			int  largerThan = 0;
			
			for(int i = 0; i < players.size(); i++) 
			{
				if(newPlayer.compareTo(players.get(i)) > 0)
				{
					largerThan++;
				}
			}
		
			return largerThan;
		}
		
	}
	
	   /**
	   * Saves all items in the list to file, uses inherited methods
	   */
	public void saveToFile()
	{

		File dataFile = new File("playersavefile.txt");
		try {
			Files.deleteIfExists(dataFile.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		initalizeWriter();
		
		for(int i = 0; i < players.size(); i++)
		{
			writeToFile(entryBegin + players.get(i).getPlayerName() + seperateData + players.get(i).getHighScore()
					+ seperateData + players.get(i).getGamesPlayed() + entryEnd);
		}
	}
	
	   /**
	   * Adds player the list, checks if player is already in list 
	   * @param newPlayer The player to add to the list
	   * @return boolean If passes specifications an is added, then true, otherwise false
	   */
	@Override
	protected boolean addPlayer(Player newPlayer)
	{
		
		if(/*players.size() < 5 &&*/ searchPlayers(newPlayer) < 0) {
			players.add(newPlayer);
			return true;
		}
		else
		{
			System.out.println("To many players in list already");
			return false;
		}
	}
	
	   /**
	   * Automatically removes player with lowest score to make for a new high score
	   */
	protected void removeLowestPlayer()
	{
		Player lowestScore = Collections.min(players);
		players.remove(lowestScore);
		saveToFile();
		
	}
	
	
}
