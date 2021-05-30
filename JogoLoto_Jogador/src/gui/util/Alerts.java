package gui.util;

import gui.components.Grid;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.entities.Jogador;

public class Alerts {
	
	private static Alert getAlert(String title, String header, String content, AlertType type) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.setResizable(false);
		
		return alert;
	}
	
	public static void showAlert(String title, String header, String content, AlertType type) {
		Alert alert = getAlert(title, header, content, type);
		alert.showAndWait();
	}
	
	public static void showReset(GridPane grid, Stage primaryStage, Jogador jogador) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Current project is modified");
		alert.setContentText("Save?");
		ButtonType okButton = new ButtonType("Cartão Original", ButtonBar.ButtonData.YES);
		ButtonType noButton = new ButtonType("Novo Cartão", ButtonBar.ButtonData.NO);
		alert.getButtonTypes().setAll(okButton, noButton);
		alert.showAndWait().ifPresent(type -> {
			if (type == okButton) { // do something
	    		Scene scene = Grid.showBottomBar(primaryStage, grid, jogador);
	            primaryStage.setScene(scene);
	            primaryStage.setResizable(false);
	            primaryStage.show();
				Grid.originalGrid(grid);

			} else if (type == noButton) { // do something
	    		Scene scene = Grid.showBottomBar(primaryStage, grid, jogador);
	            primaryStage.setScene(scene);
	            primaryStage.setResizable(false);
	            primaryStage.show();
				Grid.updateGrid(grid);
			} 
			});
	}
}