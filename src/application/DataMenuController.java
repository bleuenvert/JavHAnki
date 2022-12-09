package application;

import javafx.fxml.FXML;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Controller for DataMenuView.fxml. There is a refresh decklist button and a get data from a selected deck button.
 * @author bleu
 *
 */
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
	
	/**
	 * Will check the data folder and place the name of each file in the folder as an option in the 
	 * deck list choice box.
	 * @param refresh button press by user
	 * @throws FileNotFoundException
	 */
	@FXML
	void refreshDeckList(ActionEvent refresh) throws FileNotFoundException {
		Path path = FileSystems.getDefault().getPath("src", "Data");
		File Decks = new File(path.toString());
		String DeckContents[] = Decks.list();
		decklistChoiceBox.getItems().setAll(DeckContents);
		

		
	}
	/**
	 * Takes the selected file name from the deck list choicebox, finds the path of the file, creates
	 * a scanner to read the data file. It scans the data files 3 lines: times studied is the first line,
	 * correct answers is the second line and incorrect answers is the third line. It then computes the 
	 * percentage of correct to incorrect answers and sets the relevant labels to display this information.
	 * @param getData button press by user
	 * @throws FileNotFoundException
	 */
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