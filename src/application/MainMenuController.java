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
	
	
	@FXML
	void openStudyMenu(ActionEvent openStudyMenuEvent) {
		Scene mainScene = applicationStage.getScene();
			try {
				FXMLLoader loader = new FXMLLoader();
				VBox root = loader.load(new FileInputStream("src/application/StudyMenuView.fxml"));
				Scene scene = new Scene(root,400,400);
				applicationStage.setScene(scene);
				applicationStage.setTitle("Study Menu");
				applicationStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	

