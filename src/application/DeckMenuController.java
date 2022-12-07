package application;

import javafx.fxml.FXML;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Path;


import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
	private Button refreshButton;
	
	@FXML
	private Label cardFrontLabel;
	
	@FXML
	private Label cardBackLabel;
	
	@FXML
	private TextField cardFrontTextField;
	
	@FXML
	private TextField cardBackTextField;
	
	@FXML
	private Button addCardButton;
	
	
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
	void refeshDeckOptions(ActionEvent refreshDeckList) {
		Path path = FileSystems.getDefault().getPath("src", "Decks");
		File Decks = new File(path.toString());
		String DeckContents[] = Decks.list();
		deckListChoiceBox.getItems().setAll(DeckContents);

	
	}
	
	@FXML
	void addCard(ActionEvent addCard) throws IOException{
		Path path = FileSystems.getDefault().getPath("src", "Decks");
		String front = cardFrontTextField.getText();
		String back = cardBackTextField.getText();
		String deck = deckListChoiceBox.getValue();
		String pathToDeck = path.toString() + '/' + deck;
		File deckFile = new File(pathToDeck);
		
		//https://stackoverflow.com/questions/24982744/printwriter-to-append-data-if-file-exist
		// showed syntax for creating a printwriter that appends files
		//https://stackoverflow.com/questions/8210616/printwriter-append-method-not-appending
		//used also
		try{
			if (deckFile.exists()) {
				PrintWriter pwriter = new PrintWriter(new FileWriter((pathToDeck), true));
				pwriter.append(front); pwriter.append("\n");
				pwriter.append(back); pwriter.append("\n");
				pwriter.close();
			} else {
				System.out.println("deck does not exist");
			}


		} catch (IOException e){
			System.out.println("Error");
		}
		
		
	}
	
}