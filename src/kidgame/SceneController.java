/**
* Essentially the engine of the game. Controls the scenes, keeps track high scores and coordinates other classes
* Class: CS-170
* Section: 1
* Assignment: Final Project
*
* @author  Ruthvicsai sivakumar
* @version 1.0
* @since   2021-12-8
*/

package kidgame;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SceneController implements Initializable{
	
	public Stage stage;
	private Scene scene;
	private Parent root;
	private Player newPlayer = new Player();
	private GameControl game = new GameControl();
	
	private String name = "none";
	
	private int maxScore = 0;
	
	@FXML private Text scoreTitle = new Text();
	@FXML private Text highScore1 = new Text();
	@FXML private Text highScore2 = new Text();
	@FXML private Text highScore3 = new Text();
	@FXML private Text highScore4 = new Text();
	@FXML private Text highScore5 = new Text();
	
	@FXML private Text playerNameText = new Text();
	@FXML private Text scoreText = new Text();
	
	@FXML private ImageView itemImageView = new ImageView();
	
	@FXML private TextField playerName = new TextField();
	
	@FXML private Button letter1 = new Button();
	@FXML private Button letter2 = new Button();
	@FXML private Button letter3 = new Button();
	@FXML private Button letter4 = new Button();
	@FXML private Button letter5 = new Button();
	@FXML private Button letter6 = new Button();
	private ArrayList<Button> playerChoice = new ArrayList<Button>();
	
	private ArrayList<Letter> level = new ArrayList<Letter>();

	private AudioControl music = new AudioControl();
	
	private PlayerHighScores highScores = new PlayerHighScores();
	
	private ArrayList<Text> highScoresList = new ArrayList<Text>();
	
	/**
	 * Score is calculated as follows: An array filled with 26 6's created. Each position [0, 25], represents the 
	 * Corresponding letter. (Ex: 0 = A, 1  = B, etc) .
	 * Each time the player gets the answer wrong, one is subtracted from it. It can even go into the negatives. 
	 * At the end the value in each 
	 * spot is summed to get the total which is then multiplied by the highest consecutive right answers. 
	 */
	private int[] scoreTracker = new int[26];
	private int scoreMultiplier = 1;
	private int maxMultiplier = 1;
	
	   /**
	   * Switches to the GameScene to play the game. Calls {@link #setLevel()} before
	   * @param event The action event for the button press
	   */
	public void switchToGame(ActionEvent event) {
		setLevel();
		
		try {
			root = FXMLLoader.load(getClass().getResource("GameScene.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	   /**
	   * Switches to the Home screen after playing the game
	   * @param event The action event for the button press
	   */
	public void switchToHome(ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("GameHomeScene.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	   /**
	   * Switches to the dialog to get players name after the game
	   * @param event The action event for the button press
	   */
	public void getName(ActionEvent event)
	{
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		try {
			root = FXMLLoader.load(getClass().getResource("GetPlayerName.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	   /**
	   * After the dialog and player presses Ok button, score is saved and the players score is 
	   * added to the list depending on their score. At the end calls {@link #switchToHome(ActionEvent event)}.
	   * Also helps input validate through error messages. Uses {@link #validateString(String input)} to validate entry.
	   * Did not create a dedicated validator class since this is the only user input which can be validated.
	   * @param event The action event for the button press
	   */
	public void saveScore(ActionEvent event)
	{
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		int score= (int) stage.getUserData();
		String valid;
		
		
		if(playerName.getText() != null)
		{
			valid = validateString(playerName.getText());
			if(valid == null)
			{
				return;
			}
			else
			{
				name = valid;		//get name from textbox and set to Player object and save
				newPlayer.setHighScore(score);
				newPlayer.setPlayerName(name);
				highScores.newPlayerScore(newPlayer);
				highScores.saveToFile();
				switchToHome(event);
			}
		}
		else
		{

			return;
		}
	}
	
	   /**
	   * Makes sure no illegal characters are in the string and prompts an alert to let the user know what mistake they are making.
	   * @param input String to be validated
	   * @return input Returns same inputed string if valid, otherwise returns null
	   */
	public String validateString(String input)
	{
		String out = null;
		Boolean valid = true;
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Name Error!");
		alert.setHeaderText("Please check your name");

		
		for(int i  = 0; i < input.length(); i++)
		{
			if(Character.compare(input.charAt(i), '|')==0)
			{
				valid = false;
				alert.setContentText("Please do not use \"|\" in your name");
			}
			else if(Character.compare(input.charAt(i), '\\') ==0)
			{
				valid = false;
				alert.setContentText("Please do not use \"\\\" in your name");
			}
			else if(Character.compare(input.charAt(i), '*') ==0)
			{
				valid = false;
				alert.setContentText("Please do not use \"*\" in your name");
			}
			else if(input.equals(""))
			{
				valid = false;
				alert.setContentText("Please do not leave empty");
			}
			else if(input.equals(" "))
			{
				valid = false;
				alert.setContentText("Please do not leave empty");
			}
			else if( highScores.searchPlayers(input) >= 0 )
			{
				valid = false;
				alert.setContentText("This name is already taken");
			}
		}
		
		if(valid == true)
		{
			return input;
		}
		else
		{
			alert.showAndWait();
			return null;
		}

	}
	
	   /**
	   * Populates the high score textboxes with the high scores from the files. 
	   * List and scores are received from PlayerHighScores class
	   */
	public void highScoresDisplay()
	{
		//set up array of scores
		scoreTitle.setText(String.format("%-10s %5s", "Name", "score"));
		highScoresList.add(highScore1);
		highScoresList.add(highScore2);
		highScoresList.add(highScore3);
		highScoresList.add(highScore4);
		highScoresList.add(highScore5);
		
		
		//populate array and text
		ArrayList<Player> playerList = highScores.getPlayerList();
		int size = playerList.size();
		if(size < 5)
		{
			int i;
			for(i = 0 ; i < size; i++)
			{
				highScoresList.get(i).setText(String.format("%-10s %5d",playerList.get(i).getPlayerName(),  playerList.get(i).getHighScore()));
			}
			for(int j = i ; j < 5; j++)
			{
				highScoresList.get(j).setText(" ");
			}
		}
		else
		{
			for(int k = 0 ; k < 5; k++)
			{
				highScoresList.get(k).setText(String.format("%-10s %5d",playerList.get(k).getPlayerName(),  playerList.get(k).getHighScore()));
			}
		}
	}
	
	   /**
	   * Sets up the level with the help of GameControl class
	   * @return boolean True if successful, false if fails
	   */
	public boolean setLevel()
	{
		level.clear(); //clear out old letters
		level = game.generateLevel();
		
		//illegal char or when at the end
		if(Character.compare(level.get(0).getChar(), '#') == 0)
		{
			return false;
		}
		
		//set right answer image
		itemImageView.setImage(level.get(0).getImage());
		
		//shuffle the buttons so that there is a random order
		Collections.shuffle(playerChoice);
		
		//populate
		for(int i = 0; i < playerChoice.size(); i++)
		{
			playerChoice.get(i).setText(level.get(i).getChar()+ "");
		}

		return true;
	}
	
	   /**
	   * Called at the end of the game when all letters have been played
	   * @param event The action event for the button press
	   */
	public void endGame(ActionEvent event)
	{
		//calculates the high score
		if(maxMultiplier < scoreMultiplier)
		{
			maxMultiplier = scoreMultiplier;
		}
		int finalScore = 0;
		for(int i = 0; i < 26; i++)
		{
			finalScore = finalScore + scoreTracker[i];
		}
		finalScore = finalScore * maxMultiplier; //every answer gotten right in a row increases the multiplier, a
													//the largest amount of time player got answers in a row is multiplied by total score

		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setUserData(finalScore);
		
		getName(event);
	}
	
	   /**
	   * Called when player presses wrong button. Plays a sound and modify score accordingly
	   */
	private void wrongAnswer()
	{
		music.playWrongSound();
		
		if(maxMultiplier < scoreMultiplier)
		{
			maxMultiplier = scoreMultiplier;
			scoreMultiplier = 1;
		}
		
		
		int currentChar = level.get(0).getChar();
		scoreTracker[currentChar-65]--;
		
	}
	
	   /**
	   * Called when player gets the answer right. A sound is played
	   * @param event The action event for the button press
	   */
	private void rightAnswer(ActionEvent event)
	{
		scoreMultiplier++;
		music.playCorrectSound();
		
		if(!setLevel())
		{
			endGame(event);
		}
	}
	
	   /**
	   * Called at the Corresponding button is pressed in the game. Modifies score accordingly and also calls 
	   * {@link #rightAnswer(ActionEvent event)} or {@link #wrongAnswer()} accordingly
	   * @param event The action event for the button press
	   */
	public void handleLetter1(ActionEvent event)
	{
		char charChosen = ((Button)event.getSource()).getText().charAt(0);
		
		if(Character.compare(charChosen, level.get(0).getChar()) == 0)
		{
			rightAnswer(event);
		}
		else
		{
			wrongAnswer();
		}
	}
	   
	/**
	   * Called at the Corresponding button is pressed in the game. Modifies score accordingly and also calls 
	   * {@link #rightAnswer(ActionEvent event)} or {@link #wrongAnswer()} accordingly
	   * @param event The action event for the button press
	   */
	public void handleLetter2(ActionEvent event)
	{
		char charChosen = ((Button)event.getSource()).getText().charAt(0);
		
		if(Character.compare(charChosen, level.get(0).getChar()) == 0)
		{
			rightAnswer(event);
		}
		else
		{
			wrongAnswer();
		}
	}

	
	
	   /**
	   * Called at the Corresponding button is pressed in the game. Modifies score accordingly and also calls 
	   * {@link #rightAnswer(ActionEvent event)} or {@link #wrongAnswer()} accordingly
	   * @param event The action event for the button press
	   */
	public void handleLetter3(ActionEvent event)
	{
		char charChosen = ((Button)event.getSource()).getText().charAt(0);
		
		if(Character.compare(charChosen, level.get(0).getChar()) == 0)
		{
			rightAnswer(event);
		}
		else
		{
			wrongAnswer();
		}
	}
	
	
	   /**
	   * Called at the Corresponding button is pressed in the game. Modifies score accordingly and also calls 
	   * {@link #rightAnswer(ActionEvent event)} or {@link #wrongAnswer()} accordingly
	   * @param event The action event for the button press
	   */

	
	public void handleLetter4(ActionEvent event)
	{
		char charChosen = ((Button)event.getSource()).getText().charAt(0);
		
		if(Character.compare(charChosen, level.get(0).getChar()) == 0)
		{

			rightAnswer(event);
		}
		else
		{
			wrongAnswer();
		}
	}
	
	   /**
	   * Called at the Corresponding button is pressed in the game. Modifies score accordingly and also calls 
	   * {@link #rightAnswer(ActionEvent event)} or {@link #wrongAnswer()} accordingly
	   * @param event The action event for the button press
	   */
	
	public void handleLetter5(ActionEvent event)
	{
		char charChosen = ((Button)event.getSource()).getText().charAt(0);
		
		if(Character.compare(charChosen, level.get(0).getChar()) == 0)
		{

			rightAnswer(event);
		}
		else
		{
			wrongAnswer();
		}
	}
	
	   /**
	   * Called at the Corresponding button is pressed in the game. Modifies score accordingly and also calls 
	   * {@link #rightAnswer(ActionEvent event)} or {@link #wrongAnswer()} accordingly
	   * @param event The action event for the button press
	   */
	public void handleLetter6(ActionEvent event)
	{
		char charChosen = ((Button)event.getSource()).getText().charAt(0);
		
		if(Character.compare(charChosen, level.get(0).getChar()) == 0)
		{

			rightAnswer(event);
		}
		else
		{
			wrongAnswer();
		}
	}

	
	   /**
	   * Sets up the buttons to the proper FXML items. These are the buttons which will hold the letters.
	   */
	void setLetters()
	{
		playerChoice.add(letter1);
		playerChoice.add(letter2);
		playerChoice.add(letter3);
		playerChoice.add(letter4);
		playerChoice.add(letter5);
		playerChoice.add(letter6);
	}
	
	

	   /**
	    * Sets up the controller by calling: {@link #highScoresDisplay()}, {@link #setLetters()}, {@link #setLevel()}, in that order
	    * Also fills the score tracker array with 6's
	   * @param location 
	   * @param resources
	   */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		highScoresDisplay();
		setLetters();
		setLevel();
		
		scoreText.setText(maxScore + "");
		
		for(int i = 0; i < 26; i++)
		{
			scoreTracker[i] = 6;
		}
	}
}
