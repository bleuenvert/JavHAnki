package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

public class StudyMenuController {
	//https://stackoverflow.com/questions/43194089/local-variable-defined-in-an-enclosing-scope-must-be-final-or-effectively-final
	// used to help solve an issue of scope. hiddenAnswer is no longer a local variable to avoid the enclosing scope issue I had before.
	Boolean hiddenAnswer = true;
	
	@FXML
	private VBox rootVBox;
	@FXML 
	private Label titleLabel;
	
	@FXML 
	private ChoiceBox<String> decklistChoiceBox;
	
	@FXML 
	private Button refreshButton;
	
	@FXML
	private Button studyButton;
	
	@FXML
	void refreshDeckOptions(ActionEvent refreshDeckList) {
		Path path = FileSystems.getDefault().getPath("src", "Decks");
		File Decks = new File(path.toString());
		String DeckContents[] = Decks.list();
		decklistChoiceBox.getItems().setAll(DeckContents);
	}
	
	
	Boolean seeAnswer(Boolean hiddenAnswer, Label backContent, Scanner deckScanner) {
		if (hiddenAnswer == true && deckScanner.hasNextLine()) {
			backContent.setText(deckScanner.nextLine());
			return false;
		} else {
			return false;
		}
	}
	
	Boolean correctAnswer(Boolean hiddenAnswer, Label frontContent, Label backContent, Scanner deckScanner, PrintWriter dataWriter, int correctAnswers, int incorrectAnswers) {
		if (hiddenAnswer == false) {
			backContent.setText("");
			correctAnswers ++;
			if(deckScanner.hasNextLine()) {
			frontContent.setText(deckScanner.nextLine());
			return true;
			} else {
				frontContent.setText("You have completed your study of this deck :)");
				dataWriter.println(correctAnswers);
				dataWriter.println(incorrectAnswers);
				dataWriter.close();
				return false;
			}
				
		} else {
			return true;
		}
	}
	
	Boolean incorrectAnswer(Boolean hiddenAnswer, Label frontContent, Label backLabel, Scanner deckScanner, PrintWriter dataWriter, int correctAnswers, int incorrectAnswers) {
		if (hiddenAnswer == false) {
			backLabel.setText("");
			incorrectAnswers ++;
			if(deckScanner.hasNextLine()) {
			frontContent.setText(deckScanner.nextLine());
			return true;
			} else {
				frontContent.setText("You have completed your study of this deck :)");
				dataWriter.println(correctAnswers);
				dataWriter.println(incorrectAnswers);
				dataWriter.close();
				return false;
			}
		} else {
			return true;
		}
	}
	
	
	@FXML
	void studyDeck(ActionEvent study) throws FileNotFoundException {
		int timesStudied;
		int correctAnswers;
		int incorrectAnswers;
		
		//https://edencoding.com/stage-controller/
		// used this to find how to get the scene/stage from the controller
		Scene mainScene = rootVBox.getScene();
		Stage mainStage = (Stage) mainScene.getWindow();
		hiddenAnswer = true;
		
		String deckToStudy = decklistChoiceBox.getValue();
		Path path = FileSystems.getDefault().getPath("src", "Decks");
		String deckpath = path.toString() + '/' + deckToStudy;
		File deck = new File(deckpath);
		Scanner deckScanner = new Scanner(deck);
		
		Path path2 = FileSystems.getDefault().getPath("src", "Data");
		//https://docs.oracle.com/javase/7/docs/api/java/lang/String.html#substring(int)
		// for string manipulation reference
		deckToStudy = deckToStudy.substring(0, deckToStudy.length() - 3) + "data";
		System.out.println(deckToStudy);
		String dataPath = path2.toString() + '/' + deckToStudy;
		File data = new File(dataPath);
		Scanner dataScanner = new Scanner(data);

		
		//https://www.geeksforgeeks.org/how-to-convert-a-string-to-an-int-in-java/
		//reviewed how to change string to an int
		timesStudied = Integer.parseInt(dataScanner.nextLine());
		correctAnswers = Integer.parseInt(dataScanner.nextLine());
		incorrectAnswers = Integer.parseInt(dataScanner.nextLine());
		PrintWriter dataWriter = new PrintWriter(data);
		dataWriter.println(timesStudied);
		
		VBox studyContainer = new VBox();
		
		HBox frontContainer = new HBox();
		Label frontLabel = new Label("Front:");
		Label frontContent = new Label(deckScanner.nextLine());
		Button seeAnswerButton = new Button("See Answer");

		frontContainer.getChildren().addAll(frontLabel, frontContent, seeAnswerButton);
		
		HBox backContainer = new HBox();
		Label backLabel = new Label("Back:");
		Label backContent = new Label();
		Button correctButton = new Button("Correct");
		Button incorrectButton = new Button("Incorrect");
		backContainer.getChildren().addAll(backLabel, backContent, correctButton, incorrectButton);
		
		studyContainer.getChildren().addAll(frontContainer, backContainer);
		Scene studyScene = new Scene(studyContainer, 400, 450);
		mainStage.setScene(studyScene);

		


		
		seeAnswerButton.setOnAction(nextEvent -> hiddenAnswer = seeAnswer(hiddenAnswer, backContent, deckScanner));
		

		correctButton.setOnAction(correctEvent -> hiddenAnswer = correctAnswer(hiddenAnswer, frontContent, backContent, deckScanner, dataWriter, correctAnswers, incorrectAnswers));
		incorrectButton.setOnAction(incorrectEvent -> hiddenAnswer = incorrectAnswer(hiddenAnswer, frontContent, backContent, deckScanner, dataWriter, correctAnswers, incorrectAnswers));
		
		
		
	}
}