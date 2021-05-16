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

public class ViewController implements Initializable {

	@FXML
	private TextField txtNumber1;

	@FXML
	private TextField txtNumber2;

	@FXML
	private Label labelResult;
	
	@FXML
	private Label labelGrid1;
	
	@FXML
	private Label labelGrid2;

	@FXML
	private Button btSum;
	
	@FXML
	private Button btColor;
	
	@FXML
	private Button btAll;

	@FXML
	private ComboBox<Person> comboBoxPerson;

	@FXML
	private ObservableList<Person> obsList;

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
		// Zona responsavel pela validacao
		Constraints.setTextFieldInteger(txtNumber1);
		Constraints.setTextFieldInteger(txtNumber2);
		Constraints.setTextFieldMaxLength(txtNumber1, 2);
		Constraints.setTextFieldMaxLength(txtNumber2, 2);
		// Carregar lista do objeto pessoa na comboBox
		List<Person> list = new ArrayList<>();
		list.add(new Person(1, "Ivan", "ivanxavier@ua.pt"));
		list.add(new Person(2, "Simao", "simao@ua.pt"));
		list.add(new Person(3, "Teste", "teste@ua.pt"));
		obsList = FXCollections.observableArrayList(list);
		comboBoxPerson.setItems(obsList);

		Callback<ListView<Person>, ListCell<Person>> factory = lv -> new ListCell<Person>() {
			@Override
			protected void updateItem(Person item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? "" : item.getName());
			}
		};
		comboBoxPerson.setCellFactory(factory);
		comboBoxPerson.setButtonCell(factory.call(null));
	}
	
	
	@FXML
	public void onComboBoxPersonAction() {
		Person person = comboBoxPerson.getSelectionModel().getSelectedItem();
		System.out.println(person);
		
	}
	
	@FXML
	public void onBtAllAction() {
		for(Person person : comboBoxPerson.getItems()) {
			System.out.println(person);
		}
	}
	
	@FXML
	public void onBtColorAction() {
		labelGrid1.setStyle("-fx-border-color:red; -fx-background-color: grey;");
		labelGrid1.setText("Cor Alterada!");
		labelGrid2.setStyle("-fx-border-color:red; -fx-background-color: green;");
		labelGrid2.setText("Cor Alterada!");
	}
}
