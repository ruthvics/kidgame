/**
* Manages and creates the levels, keeps track of what letters have been used, makes sure letters are random.
* Class: CS-170
* Section: 1
* Assignment: Final Project
*
* @author  Ruthvicsai sivakumar
* @version 1.0
* @since   2021-12-8
*/


package kidgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameControl {
	private ArrayList<Letter> alphabet = new ArrayList<Letter>();
	private ArrayList<Letter> remainingList = new ArrayList<Letter>();
	private ArrayList<Letter> level = new ArrayList<Letter>();
	private ArrayList<Letter> randList = new ArrayList<Letter>();
	private final int numOfButtons = 6;
	private Random rand = new Random();


	   /**
	   * Default Constructor, sets all the letters to their chosen attributes
	   * Sets up the full list of characters to play with
	   */
	GameControl()
	{
		setAllLetters();
		remainingList.addAll(alphabet);
		randList.addAll(alphabet);
	}
	
	   /**
	   * Adds all 26 letters to the alphabet with their character and respective image
	   */
	private void setAllLetters()
	{
		alphabet.add(new Letter('A', "477ba82d1e6e5823268654a84ce5ce11.png"));
		alphabet.add(new Letter('B', "unnamed.png"));
		alphabet.add(new Letter('C', "unnamed copy.png"));
		alphabet.add(new Letter('D', "clipart2233.png"));
		alphabet.add(new Letter('E', "clipart4671.png"));
		alphabet.add(new Letter('F', "clipart10401.png"));
		alphabet.add(new Letter('G', "clipart129454.png"));
		alphabet.add(new Letter('H', "clipart8412.png"));
		alphabet.add(new Letter('I', "clipart871037.png"));
		alphabet.add(new Letter('J', "clipart65209.png"));
		alphabet.add(new Letter('K', "clipart10964.png"));
		alphabet.add(new Letter('L', "clipart11987.png"));
		alphabet.add(new Letter('M', "clipart5219.png"));
		alphabet.add(new Letter('N', "clipart20483.png"));
		alphabet.add(new Letter('O', "clipart1331.png"));
		alphabet.add(new Letter('P', "clipart5558.png"));
		alphabet.add(new Letter('Q', "clipart1107325.png"));
		alphabet.add(new Letter('R', "clipart64018.png"));
		alphabet.add(new Letter('S', "clipart54866.png"));
		alphabet.add(new Letter('T', "clipart863.png"));
		alphabet.add(new Letter('U', "clipart4315.png"));
		alphabet.add(new Letter('V', "clipart10649.png"));
		alphabet.add(new Letter('W', "clipart15791.png"));
		alphabet.add(new Letter('X', "clipart397450.png"));
		alphabet.add(new Letter('Y', "clipart27316.png"));
		alphabet.add(new Letter('Z', "clipart6517.png"));
		
	}
	
	   /**
	   * Generates a random letter from the list of unused letters
	   * @return Letter A letter object with the letter and image set
	   */
	private Letter getRandomLetter()
	{
		if(remainingList.size() > 0)
		{
			int index = rand.nextInt(remainingList.size());
			return remainingList.get(index);
		}
		else
		{
			return new Letter('#');
		}

	}
	
	   /**
	   * Generates a random "level"
	   * An ArrayList of letters is generated to be used as the level. The letter choices and answers. 
	   * @return level An ArrayList which contains the answer (the first letter in the array), and 6 other random options to choose from
	   */
	public ArrayList<Letter> generateLevel()
	{
		//the letter which will be the right answer
		Letter target = new Letter();

		//generate a random letter to be the answer
		target = getRandomLetter();
		
		//shuffle the list containing the remaining un-played letters
		Collections.shuffle(randList);
		
		//add the target to the Array first to signify as the answer
		level.add(target);
		
		//loads into level until there are 6 items in level
		int i = 0;
		while(i < numOfButtons)
		{
			if(Character.compare(target.getChar(), randList.get(i).getChar())!=0)
			{
				level.add(randList.get(i));
			}
			i++;
		}
		
		remainingList.remove(level.get(0));
		
		return level;
	}
	
	
}
