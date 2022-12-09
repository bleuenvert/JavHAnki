package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DataMenuController {
	Stage applicationStage;
	
	@FXML 
	private Label timesStudiedLabel;
	
	@FXML 
	private Label successLabel;
	
	@FXML
	private Button refreshButton;
	
	@FXML
	private Button getDataButton;
	
	@FXML
	private ChoiceBox<String> decklistChoiceBox;
	
	@FXML
	void refreshDeckList(ActionEvent refresh) throws FileNotFoundException {
		Path path = FileSystems.getDefault().getPath("src", "Data");
		File Decks = new File(path.toString());
		String DeckContents[] = Decks.list();
		decklistChoiceBox.getItems().setAll(DeckContents);
		

		
	}
	
	@FXML
	void getData(ActionEvent getData) throws FileNotFoundException {
		String deckToData = decklistChoiceBox.getValue();
		Path path = FileSystems.getDefault().getPath("src", "Data");
		String deckpath = path.toString() + '/' + deckToData;
		File deck = new File(deckpath);
		Scanner scanner = new Scanner(deck);
		timesStudiedLabel.setText("Times Deck Was Studied: " + scanner.nextLine());
		int correctAnswers = Integer.parseInt(scanner.nextLine());
		int incorrectAnswers = Integer.parseInt(scanner.nextLine());
		double percentage = (correctAnswers * 100.0 / (correctAnswers + incorrectAnswers));
		successLabel.setText("Success Percentage: " + percentage) ;
		scanner.close();
		
		
	}
}