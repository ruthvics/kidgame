/**
* Manages a List of Players for easy operations. Also stores player info to a file
* to save and use later
* Class: CS-170
* Section: 1
* Assignment: Final Project
*
* @author  Ruthvicsai sivakumar
* @version 1.0
* @since   2021-12-8
*/

package kidgame;

import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PlayerList {
	
	//predefined for easy alteration in the future
	protected final String entryBegin = "||";
	protected final String seperateData = "\\";
	protected final String entryEnd = "||*";
	
	
	protected ArrayList<Player> players;
	private File saveFile;
	private FileWriter fileCom;
	private FileReader fileRead;
	protected ArrayList<Character> readIn = new ArrayList<Character>();
	private boolean emptyList = true;
	
	   /**
	   * returns number of players in the list currently
	   * @return players.size() 
	   */
	protected int numberOfPlayers()
	{
		return players.size();
	}
	
	   /**
	   * Default Constructor, creates a file to store all player info
	   * also loads any information in the file into the proper arrays
	   * calls initalizeWrited and fileToList() 
	   * See {@link #initalizeWriter()}
	   * See {@link #fileToList()}
	   */
	PlayerList()
	{
		players = new ArrayList<Player>();
		saveFile = new File("playersavefile.txt");
		initalizeWriter();
		
		fileToList();
	}
	
	   /**
	   * Sets up the writer and created a new file if none exists. 
	   * Prepares the Writer to start writing to the file
	   */
	protected void initalizeWriter()
	{
		if(!saveFile.exists())
		{
			try {
				saveFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
				e.printStackTrace();
			}
		}
		
		try {
			fileCom = new FileWriter("playersavefile.txt", true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	public ArrayList<Player> getPlayerList()
	{
		loadPlayersToList();
		return players;
	}
	
	   /**
	   * Uses a Reader to flush all information passed in through inputString 
	   * into the file
	   * @param inputString A string which will be copied exactly and directly into the save file
	   */
	protected boolean writeToFile(String inputString)
	{
		boolean success = true;
		try {
			fileCom.write(inputString);
			fileCom.write(System.getProperty("line.separator"));
			fileCom.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			success = false;
			e.printStackTrace();
		}
		return success;
	}
	
	   /**
	   * Reads the file one character at a time into a character array readIn
	   * @param empty returns true or false depending on if file is empty or not
	   */
	protected boolean readIntoCharArray()
	{
		boolean empty = false;
		
		try {
			fileRead = new FileReader("playersavefile.txt");
		} catch (FileNotFoundException e) {
			System.out.println("Can not find and read file");
			e.printStackTrace();
		}
		
		try {
            int content;
            
            while ((content = fileRead.read()) != -1) {
            	readIn.add((char)content);
            }

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(readIn.isEmpty())
		{
			empty = true;
		}
		return !empty;
	}
	
	   /**
	   * Expands the Player Object into seperate data to read and store in file. Then writes into file.
	   * Will only write in if the name is not already in the file/list
	   * @param newPlayer a Player Object to add to the list, which is then written to file
	   */
	protected boolean addPlayer(Player newPlayer)
	{
		if(searchPlayers(newPlayer) < 0)
		{
			writeToFile(entryBegin + newPlayer.getPlayerName() + seperateData + newPlayer.getHighScore() + 
					seperateData + newPlayer.getGamesPlayed() + entryEnd);
			emptyList = false;
		}
		else
		{
			System.out.println("This player name is already taken: " + newPlayer.getPlayerName());
			return false;
		}
		return true;
	}
	
	   /**
	   * Uses the read in char array with all the data in the file to separate out the data 
	   * using the respective . Stores information into a ArrayList.
	   * @return Boolean true if successfull, false if not
	   */
	public boolean loadPlayersToList()
	{
		String name = "", 
			   score = "", 
			   gamesPlayed = "";
		Player newPlayer;
		
		if(!readIn.isEmpty())
		{
			int cnt = 2;
			
			
			while(readIn.size() > cnt)
			{
			
				while(!readIn.get(cnt).equals(seperateData.charAt(0)))
				{
					name = name + readIn.get(cnt);
					cnt++;
				}
				cnt++;
				while(!readIn.get(cnt).equals(seperateData.charAt(0)))
				{
					score = score + readIn.get(cnt);
					cnt++;
				}
				cnt++;
				while(!readIn.get(cnt).equals('|'))
				{
					gamesPlayed = gamesPlayed + readIn.get(cnt);
					cnt++;
				}
				
				
				newPlayer = new Player(name,Integer.parseInt(score), Integer.parseInt(gamesPlayed));
				
				if(searchPlayers(newPlayer)< 0)
				{
					players.add(new Player(name,Integer.parseInt(score), Integer.parseInt(gamesPlayed)));
				}
				else
				{
				}
				
				
				name = "";
				score = "";
				gamesPlayed = "";
				cnt = cnt + 6;
			}	
			return true;
		}
		else
		{
			if(emptyList = true)
			{
				return false;
			}
			else
			{
				loadPlayersToList();
				return true;
			}

		}
	}
	
	   /**
	   * Searches the list of players to see if any with same name exists
	   * @param target The player to search the list for
	   * @return i the index of the player which matches, other wise -99 if no match
	   */
	protected int searchPlayers(Player target)
	{
		for(int i = 0; i < players.size(); i++)
		{
			
			if(players.get(i).equalsTo(target))
			{
				return i;
			}
		}
		
		return -99;
	}
	
	   /**
	   * Searches the list of players with only the name. Similar to other one but this only takes in the name as a String.
	   * @param targetPlayerName String with the name to search
	   * @return i the index of the player which matches, other wise -99 if no match
	   */
	public int searchPlayers(String targetPlayerName)
	{
		for(int i = 0; i < players.size(); i++)
		{
			
			if(players.get(i).getPlayerName().equals(targetPlayerName))
			{
				return i;
			}
		}
		return -99;
	}
	
	   /**
	   * Calls {@link #readIntoCharArray()} and {@link #loadPlayersToList()} together and checks to see if they both were successful
	   * @return Boolean if both successful return ture, otherwise false
	   */
	protected boolean fileToList()
	{
		boolean readIntoCharArray = readIntoCharArray();
		boolean readIntoList = loadPlayersToList();
		
		// both will be true if both finish fully and have players in the list
		if(readIntoCharArray == true && readIntoList == true)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	   /**
	   * Prints full list, useful for debugging or viewing contents
	   */
	public void printList()
	{
		for(int i = 0; i < players.size(); i++)
		{
			System.out.println(players.get(i));
		}
	}
	
	   /**
	   * Searches list to find index of Player with the same name, then deletes the player
	   * @param playerToDelete The player to delete by name
	   * @return Boolean true if successful, false if not found or list is empty
	   */
	public boolean removePlayer(String playerToDelete)
	{
		int index = searchPlayers(playerToDelete);
		
		if(players.isEmpty() || index < 0)
		{
			return false;
		}
		
		return true;
	}
	
	
}


