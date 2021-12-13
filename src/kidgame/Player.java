/**
* The Player Class which holds the player name and other infomation. Easy to
* use in a collection and multiple players with this class. 
* This version has a option to add how many times a player played the game 
* which can be explanded in the future.
* Class: CS-170
* Section: 1
* Assignment: FInal Project
*
* @author  Ruthvicsai sivakumar
* @version 1.0
* @since   2021-12-8
*/


package kidgame;

public class Player implements Comparable<Player>{
	private String playerName;
	private int highScore;
	private int gamesPlayed;
	boolean nameCreated = false;
	
	
   /**
   * This is the default constructor
   */
	Player()
	{
		playerName = "";
		highScore = 0;
		gamesPlayed = 0;
	}
	
   /**
   * Constructor with only name, other values can be assigned later, for ex:
   * after playing the game
   * @param PlayerName This is the players name
   */
	Player(String mPlayerName)
	{
		playerName = mPlayerName;
		nameCreated = true;
		highScore = 0;
		gamesPlayed = 0;
	}

   /**
   * Constructor with only name and score
   * @param PlayerName This is the players name
   * @param mHighScore This is the players score
   */
	Player(String mPlayerName,int mHighScore)
	{
		playerName = mPlayerName;
		nameCreated = true;
		highScore = mHighScore;
		gamesPlayed = 0;
	}
	
   /**
   * Constructor with all values
   * @param PlayerName This is the players name
   * @param mHighScore This is the players score
   * @param mHighScore This is the number of times the game was played
   */
	Player(String mPlayerName,int mHighScore, int mgamesPlayed)
	{
		playerName = mPlayerName;
		nameCreated = true;
		highScore = mHighScore;
		gamesPlayed = mgamesPlayed;
	}
	
   /**
   * getter for players name
   * @return playerName
   */
	public String getPlayerName() {
		return playerName;
	}

   /**
   * setter for players name
   * @param playerName
   */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

   /**
   * getter for players score
   * @return highScore
   */
	public int getHighScore() {
		return highScore;
	}

	   /**
	   * setter for players score
	   * @param highScore
	   */
	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}

   /**
   * getter for number of games played
   * @return gamesPlayed
   */
	public int getGamesPlayed() {
		return gamesPlayed;
	}

	   /**
	   * setter for games played
	   * @param gamesPlayed
	   */
	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}
	
	   /**
	   * Checks if the names of the players match
	   * @param other The other player to be compared to
	   * @return boolean True if they share the same name, false otherwise
	   */
	public boolean equalsTo(Player other)
	{
		if(this.playerName.equals(other.playerName))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	   /**
	   * Converts the object to a sting format so it is easy to print
	   * @return String of name, score, and games played in that order
	   */
	public String toString() {
		
		return playerName + " " + highScore + " " + gamesPlayed;
		
	}

	   /**
	   * Converts the object to a sting format so it is easy to print
	   * @param o The other player to be compared to
	   * @return int -1 if the score of other player is higher, 0 if same, 1 is score of other player is lower
	   */
	@Override
	public int compareTo(Player o) {
		if(highScore < o.getHighScore())
		{
			return -1;
		}
		else if(highScore == o.getHighScore())
		{
			return 0;
		}
		else
		{
			return 1;
		}
	}

}
