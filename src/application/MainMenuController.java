package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.FileInputStream;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Is the controller for MainMenuView.fxml. There are three buttons that move to sub-menus.
 * @author bleu
 *
 */
public class MainMenuController {
	Stage applicationStage;
	
	@FXML
	private TitledPane mainMenuTitledPane;
	
	@FXML
	private Button deckManagementButton;
	
	@FXML
	private Button viewDataButton;
	
	@FXML
	private Button studyMenuButton;
	
	/**
	 * This method opens StudyMenuView.fxml and sets scene/stage variables (ie. window title/size).
	 * 
	 * @param openStudyMenuEvent Occurs when the user presses the study menu button.
	 */
	@FXML
	void openStudyMenu(ActionEvent openStudyMenuEvent) {
			try {
				//Referenced https://docs.oracle.com/javase/8/javafx/api/javafx/stage/Stage.html to learn
				//to create a stage.
				applicationStage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				VBox studyRoot = loader.load(new FileInputStream("src/application/StudyMenuView.fxml"));
				Scene scene = new Scene(studyRoot,300,75);
				applicationStage.setScene(scene);
				applicationStage.setTitle("Study Menu");
				applicationStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	
	/**
	 * This method opens DeckMenuView.fxml and sets scene/stage variables (ie. window title/size).
	 * 
	 * @param openDeckMenuEvent Occurs when the user presses the deck menu button.
	 */
	@FXML
	void openDeckMenu(ActionEvent openDeckMenuEvent) {
			try {
				applicationStage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				VBox deckRoot = loader.load(new FileInputStream("src/application/DeckMenuView.fxml"));
				Scene scene = new Scene(deckRoot,330,300);
				applicationStage.setScene(scene);
				applicationStage.setTitle("Deck Management Menu");
				applicationStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	
	/**
	 * This method opens DataMenuView.fxml and sets scene/stage variables (ie. window title/size).
	 * 
	 * @param openDataMenuEvent Occurs when the user presses the data menu button.
	 */
	@FXML
	void openDataMenu(ActionEvent openDataMenuEvent) {
			try {
				applicationStage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				VBox dataRoot = loader.load(new FileInputStream("src/application/DataMenuView.fxml"));
				Scene scene = new Scene(dataRoot,225,250);
				applicationStage.setScene(scene);
				applicationStage.setTitle("Data Menu");
				applicationStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	

