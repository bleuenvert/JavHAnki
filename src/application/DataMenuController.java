package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.FileInputStream;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
	
	
	void refreshDeckList(ActionEvent refresh) {
		
	}
	
	void getData(ActionEvent getData) {
		
	}
}