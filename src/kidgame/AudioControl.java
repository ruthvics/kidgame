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

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

public class AudioControl {
	
    private Media menuMusic;
    private MediaPlayer player;
    private Media correctSound;
    private MediaPlayer correctSoundPlayer;
    private Media wrongSound;
    private MediaPlayer wrongSoundPlayer;
    
	protected String usingSystemProperty = System.getProperty("user.dir");
	protected String fileSep = System.getProperty("file.separator");
	


	   /**
	   * Default constructor to set up all the objects with corresponding path names
	   */
	AudioControl()
	{
		menuMusic = new Media(convertToPath("happy_background_music_for_kids_videos_and_children_games_apps_5275632074389196206.mp3"));
		correctSound = new Media(convertToPath("rightansw.mp3"));
		wrongSound = new Media(convertToPath("wrongsound.mp3"));
		player  = new MediaPlayer(menuMusic);
		//correctSoundPlayer = new MediaPlayer(correctSound);
		//wrongSoundPlayer = new MediaPlayer(wrongSound);
	}
	   /**
	   * Plays the menu music in a loop
	   */
	void playMenuMusic()
	{
		if(!player.getStatus().equals(Status.PLAYING))
		{
			player.setCycleCount(MediaPlayer.INDEFINITE);
			player.play();
		}
		else
		{
			//System.out.println("Already Playing");
		}
	}
	
	   /**
	   * Plays the when correct answer is chosen
	   */
	void playCorrectSound()
	{
		correctSoundPlayer = new MediaPlayer(correctSound);
		correctSoundPlayer.play();
	}
	
	   /**
	   * Plays the when wrong answer is chosen
	   */
	void playWrongSound()
	{
		wrongSoundPlayer = new MediaPlayer(wrongSound);
		wrongSoundPlayer.play();
	}
	
	   /**
	   * Plays the when wrong answer is chosen
	   * @param fileName The name of the file in the source file
	   * @return path A full path name specific to the computer with all valid chars
	   */
	private String convertToPath(String fileName)
	{
		String path = "file:" + fileSep + fileSep + usingSystemProperty + fileSep +  fileName;
		
		path = path.replaceAll("[ ]", "%20");
		return path;
	}

}
