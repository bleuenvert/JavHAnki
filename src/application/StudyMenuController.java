package application;

import javafx.fxml.FXML;
import java.io.File;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller for StudyMenuView.fxml. There is a refresh decklist button, a study deck button that opens
 * a new sub-studying cards menu, and 3 methods used for this sub-study menu buttons.
 * @author bleu
 *
 */
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
	
	/**
	 *  Will check the deck folder and place the name of each file in the folder as an option in the 
	 * deck list choice box.
	 * @param refreshDeckList
	 */
	@FXML
	void refreshDeckOptions(ActionEvent refreshDeckList) {
		Path path = FileSystems.getDefault().getPath("src", "Decks");
		File Decks = new File(path.toString());
		String DeckContents[] = Decks.list();
		decklistChoiceBox.getItems().setAll(DeckContents);
	}
	
	/**
	 * checks if answer is currently hidden. If so this button allows the user to see the answer.
	 * if there is no next line in the deck file or if the answer is not currently hidden this button 
	 * does nothing.
	 * @param hiddenAnswer value of whether the answer(back) currently hidden
	 * @param backContent label that displays the back of the card.
	 * @param deckScanner scanner for reading the deck file
	 * @return
	 */
	Boolean seeAnswer(Boolean hiddenAnswer, Label backContent, Scanner deckScanner) {
		if (hiddenAnswer == true && deckScanner.hasNextLine()) {
			backContent.setText(deckScanner.nextLine());
			return false;
		} else {
			return false;
		}
	}
	
	/**
	 * If the answer is not hidden this will reset the backContent to the empty string to prepare for the next
	 * card study. It also adds 1 to the correct answers variable. Then it checks if the deck has a next line,
	 * is so it sets frontContent to the next card value and returns true to the hiddenAnswer variable.
	 * if there is not a next line in the deck file the frontContent is set to a message to convey studying
	 * of the deck is complete and the dataWriter prints the correct and incorrect answers to the data file.
	 * @param hiddenAnswer value of whether the answer is currently hidden
	 * @param frontContent label that displays the front of the card
	 * @param backContent label that displays the back of the card
	 * @param deckScanner scanner for deck file
	 * @param dataWriter print writer for data file
	 * @param correctAnswers number of correct answers in total
	 * @param incorrectAnswers number of incorrect answers in total
	 * @return
	 */
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
	
	/**
	 * If the answer is not hidden this will reset the backContent to the empty string to prepare for the next
	 * card study. It also adds 1 to the incorrect answers variable. Then it checks if the deck has a next line,
	 * is so it sets frontContent to the next card value and returns true to the hiddenAnswer variable.
	 * if there is not a next line in the deck file the frontContent is set to a message to convey studying
	 * of the deck is complete and the dataWriter prints the correct and incorrect answers to the data file.
	 * @param hiddenAnswer value of whether the answer is currently hidden
	 * @param frontContent label that displays the front of the card
	 * @param backContent label that displays the back of the card
	 * @param deckScanner scanner for deck file
	 * @param dataWriter print writer for data file
	 * @param correctAnswers number of correct answers in total
	 * @param incorrectAnswers number of incorrect answers in total
	 * @return
	 */
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
	
	/**
	 * This method creates a sub-study menu for studying on. It creates a scene with a VBox root 
	 * and three HBox nodes(the first two have a label for front/back respectively, a label to display
	 * the content of the card, and some buttons). It scans the deck selected by the user in the choicebox,
	 * in order to display the cards of the relevant deck in the sub-study menu. It scans the data file
	 * and saves the information to the variables timesStudied, correctAnswers, incorrectAnswers. The
	 * seeAnswer/correctAnswer/incorrectAnswer buttons call on their respective methods above in order
	 * to move between thinking/answering states of the user (show front of card only, then show answer
	 * and allow user to select whether they were successful).
	 * @param study button press by user
	 * @throws FileNotFoundException
	 */
	@FXML
	void studyDeck(ActionEvent study) throws FileNotFoundException {
		int timesStudied;
		int correctAnswers;
		int incorrectAnswers;
		
		//set back to true for each new study session
		hiddenAnswer = true;
		
		//https://edencoding.com/stage-controller/
		// used this to find how to get the scene/stage from the controller
		Scene mainScene = rootVBox.getScene();
		Stage mainStage = (Stage) mainScene.getWindow();

		
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
		timesStudied = Integer.parseInt(dataScanner.nextLine()) + 1;
		correctAnswers = Integer.parseInt(dataScanner.nextLine());
		incorrectAnswers = Integer.parseInt(dataScanner.nextLine());
		dataScanner.close();
		PrintWriter dataWriter = new PrintWriter(data);
		dataWriter.println(timesStudied);
		
		//https://www.demo2s.com/java/javafx-space-padding-and-margin.html
		// for element spacing
		VBox studyContainer = new VBox(10);
		
		HBox frontContainer = new HBox(10);
		Label frontLabel = new Label("Front:");
		Label frontContent = new Label(deckScanner.nextLine());
		Button seeAnswerButton = new Button("See Answer");

		frontContainer.getChildren().addAll(frontLabel, frontContent, seeAnswerButton);
		
		HBox backContainer = new HBox(10);
		Label backLabel = new Label("Back:");
		Label backContent = new Label();
		Button correctButton = new Button("Correct");
		Button incorrectButton = new Button("Incorrect");
		backContainer.getChildren().addAll(backLabel, backContent, correctButton, incorrectButton);
		
		studyContainer.getChildren().addAll(frontContainer, backContainer);
		Scene studyScene = new Scene(studyContainer, 500, 100);
		mainStage.setScene(studyScene);

		


		// the "Answer" methods return a boolean relevant the the state of hiddenAnswer.
		seeAnswerButton.setOnAction(nextEvent -> hiddenAnswer = seeAnswer(hiddenAnswer, backContent, deckScanner));
		correctButton.setOnAction(correctEvent -> hiddenAnswer = correctAnswer(hiddenAnswer, frontContent, backContent, deckScanner, dataWriter, correctAnswers, incorrectAnswers));
		incorrectButton.setOnAction(incorrectEvent -> hiddenAnswer = incorrectAnswer(hiddenAnswer, frontContent, backContent, deckScanner, dataWriter, correctAnswers, incorrectAnswers));
		
		
		
	}
}