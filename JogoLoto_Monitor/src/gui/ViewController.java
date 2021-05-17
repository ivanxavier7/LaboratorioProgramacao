package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import gui.util.Alerts;
import gui.util.Constraints;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import model.entities.Person;
import model.entities.Monitor;

public class ViewController implements Initializable {
	public ViewController() {
		
	}


	Monitor monitor = new Monitor();

	@FXML
	private TextField txtNumber1;

	@FXML
	private TextField txtNumber2;
	
	@FXML
	private TextField nrInserido;

	@FXML
	private Label labelResult;
	
	@FXML
	private Label labelGrid1;
	
	@FXML
	private Label labelGrid2;
	
	@FXML
	private Label label1;
	
	@FXML
	private Label label2;
	
	@FXML
	private Label label3;

	@FXML
	private Button btSum;
	
	@FXML
	private Button btInserir;
	
	@FXML
	private Button btColor;
	
	@FXML
	private Button btAll;
	
	private Label[] labelArray;

	public void labelConfig() {
		labelArray = new Label[89];
		labelArray[0] = label1;
		labelArray[1] = label2;
		labelArray[2] = label3;
	}
	
	@FXML
	public void onBtSumAction() {
		try {
			Locale.setDefault(Locale.US);
			double number1 = Double.parseDouble(txtNumber1.getText());
			double number2 = Double.parseDouble(txtNumber2.getText());
			double sum = number1 + number2;
			labelResult.setText(String.format("%.2f", sum));
			Alerts.showAlert("Título do Alerta", "Conteúdo", String.format("%.2f + %.2f = %.2f", number1, number2, sum),
					AlertType.INFORMATION);
		} catch (NumberFormatException e) {
			Alerts.showAlert("Título do Alerta", "Conteúdo", e.getMessage(), AlertType.ERROR);
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// Validacao
		Constraints.setTextFieldInteger(txtNumber1);
		Constraints.setTextFieldInteger(txtNumber2);
		Constraints.setTextFieldInteger(nrInserido);
		Constraints.setTextFieldMaxLength(txtNumber1, 2);
		Constraints.setTextFieldMaxLength(txtNumber2, 2);
		Constraints.setTextFieldMaxLength(nrInserido, 2);
		labelConfig();		
	}
	
	
	@FXML
	public void labelClick1() {
		
		System.out.println("Botao Ja foi escolhido!");
		
		if(label1.getStyle().equals("-fx-border-color:red; -fx-background-color: green;")) {
			System.out.println("Botao Ja foi escolhido!");
		}
		else {
			label1.setStyle("-fx-border-color:red; -fx-background-color: green;");
			label1.setText("Cor Alterada!");
			monitor.nrEscolhido(1);
			monitor.save();
			monitor.readJSON();
		}
	}
	
}
