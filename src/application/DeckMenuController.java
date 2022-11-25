package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.FileInputStream;

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
	void addDeck(ActionEvent addDeck) {
		String deckName = deckNameTextfield.getText();
		Deck newDeck = new Deck(deckName);
		
	}
	
	
	@FXML
	void editDeck(ActionEvent editDeck) {
	
	}
	
	
}