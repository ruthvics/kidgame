/**
* Driver Code of the game
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

import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

public class ApplicationWindow extends Application{
	
	private AudioControl music = new AudioControl();
	
	   /**
	   * Starts Javafx into the home scene and starts playing music
	   * @return Stage
	   */
	@Override
	public void start(Stage primaryStage) {
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("GameHomeScene.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
		primaryStage.show();
		music.playMenuMusic();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
