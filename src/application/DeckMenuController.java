package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DeckMenuController {
	Stage applicationStage;
	
	@FXML 
	private Label titleLabel;
	
	@FXML
	private TextField deckNameTextfield;
	
	@FXML
	private Button addDeckButton;
	
	@FXML
	private ChoiceBox<String> deckListChoiceBox;
	
	@FXML
	private Button editDeckButton;
	
	
	
	@FXML
	void addDeck(ActionEvent addDeck) throws IOException {
		String deckName = deckNameTextfield.getText();
		Deck newDeck = new Deck(deckName);
		String newDeckPath = "/src/Decks/" + newDeck.getDeckName();
		File deckFile = new File(newDeckPath);
		
		System.out.println(deckName + newDeckPath);
		//https://www.w3schools.com/java/java_files_create.asp was helpful
		//deckFile.createNewFile();
		
	}
	
	
	@FXML
	void editDeck(ActionEvent editDeck) {
	
	}
	
	
}