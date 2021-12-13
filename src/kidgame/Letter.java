/**
* Stores the letter and the respective image to be displayed
* Class: CS-170
* Section: 1
* Assignment: FInal Project
*
* @author  Ruthvicsai sivakumar
* @version 1.0
* @since   2021-12-8
*/

package kidgame;

import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.scene.image.Image;

public class Letter {
	protected char letter;
	protected Image item;
	//Path path = Paths.get("file.txt");
	protected String usingSystemProperty = System.getProperty("user.dir");
	protected String fileSep = System.getProperty("file.separator");
	
	   /**
	   * Constructor with all values
	   * @param mLetter The specific letter
	   * @param imagePath The path to the image as a string
	   */
	Letter(char mLetter, String imagePath)
	{
		letter = mLetter;

		item = new Image(convertToPath(imagePath));
	}
	
	   /**
	   * Constructor with only letter, sets image to an error image automatically
	   * @param mLetter The specific letter
	   */
	Letter(char mLetter)
	{
		letter = mLetter;
		item = new Image("file:///Users/ruthvicsaisivakumar/ohlone-CS170A- labs/kidgame/warning_exclamation.png");
	}
	
	   /**
	   * Default Constructor, sets letter to a designated error char, Image to a error image
	   */
	Letter()
	{
		letter = '#';
		item = new Image("file:///Users/ruthvicsaisivakumar/ohlone-CS170A- labs/kidgame/warning_exclamation.png");
	}
	
	   /**
	   * getter for the specific letter(character)
	   * @return letter 
	   */
	public char getChar()
	{
		return letter;
	}
	
	   /**
	   * getter for the specific image
	   * @return item 
	   */
	public Image getImage()
	{
		return item;
	}
	
	private String convertToPath(String fileName)
	{
		return "file:" + fileSep + fileSep + usingSystemProperty + fileSep +  fileName;
	}
}
