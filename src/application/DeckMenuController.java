package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import javafx.collections.ObservableList;
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
	private Button refreshDeckButton;
	
	
	
	@FXML
	void addDeck(ActionEvent addDeck) throws IOException {
		String deckName = deckNameTextfield.getText();
		Deck newDeck = new Deck(deckName);
		//https://docs.oracle.com/javase/7/docs/api/java/nio/file/Path.html
		//https://stackoverflow.com/questions/4871051/how-to-get-the-current-working-directory-in-java
		// I am not sure if this works on windows. I might need to replace "/" with a system dependent separator
		Path deckPath = FileSystems.getDefault().getPath("src", "Decks");
		Path dataPath = FileSystems.getDefault().getPath("src", "Data");
		String newDeckPath = deckPath.toString() + "/" + newDeck.getDeckName() + ".txt";
		String newDeckDataPath = dataPath.toString() + "/" + newDeck.getDeckName() + ".data"; 
		
		File deckFile = new File(newDeckPath);
		File deckDataFile = new File(newDeckDataPath);
		//https://www.w3schools.com/java/java_files_create.asp was helpful
		try {
		
		if (deckFile.createNewFile()) {
			System.out.println(deckFile.getAbsolutePath());
		} else {
			System.out.println("Deck with that name already exists");
		}
		} catch (IOException e){
			System.out.println("Error occured when creating Deck");
			e.printStackTrace();
	}
		try {
			
			if (deckDataFile.createNewFile()) {
				System.out.println(deckDataFile.getAbsolutePath());
			} else {
				System.out.println("Deck data file with that name already exists");
			}
			} catch (IOException e){
				System.out.println("Error occured when creating Deck Data file");
				e.printStackTrace();
		}
	}
	
	@FXML
	void refeshDeckOptions(ActionEvent editDeck) {
		Path path = FileSystems.getDefault().getPath("src", "Decks");
		File Decks = new File(path.toString());
		String DeckContents[] = Decks.list();
		deckListChoiceBox.getItems().setAll(DeckContents);

	
	}
}