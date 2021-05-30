package gui.components;

import application.Jogo;
import gui.util.Constraints;
import gui.util.Controller;
import gui.util.Grid;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import model.entities.MonitorLoto;

public class Buttons {
	private static boolean dragFlag = false;
	private static Button playerButton;


	
    public static Button createNumberButton(int number, GridPane grid) {
        Button button = createButton(Integer.toString(number));
        GridPane.setFillHeight(button, true);
        GridPane.setFillWidth(button, true);
        GridPane.setHgrow(button, Priority.ALWAYS);
        GridPane.setVgrow(button, Priority.ALWAYS);
        button.setOnAction(event -> createNumberButtonEvent(number, grid, button));
        return button ;
    }
    
    public static Button createNumberButtonEvent(int number, GridPane grid, Button button) {
		// Blocks Click Spam
		button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (e.getButton().equals(MouseButton.PRIMARY)) {
                    if (!dragFlag) {
                        if (e.getClickCount() >= 1) {
                            Jogo.setValueMonitor(number);
                            int btNumber = Integer.parseInt(((Button)e.getSource()).getText());
                            grid.getChildren().remove((Button)e.getSource());        
                            // Saves the chosen number in the JSON file
                            MonitorLoto.nrEscolhido(btNumber);
                            Controller.changeMessage(String.format("Selecionou o número %s", String.valueOf(number)));
                        }
                    }
                    dragFlag = false;
                }
            }
        });
		return button;
     }

    public static Button createButton(String text) {
        Button button = new Button(text);
        button.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        GridPane.setFillHeight(button, true);
        GridPane.setFillWidth(button, true);
        GridPane.setHgrow(button, Priority.ALWAYS);
        GridPane.setVgrow(button, Priority.ALWAYS);
        return button ;
    }
    
    public static Button playerButton(TextField textField, MonitorLoto monitor) {
    	playerButton = new Button("Confirmar");
        playerButton.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        GridPane.setFillHeight(playerButton, true);
        GridPane.setFillWidth(playerButton, true);
        GridPane.setHgrow(playerButton, Priority.ALWAYS);
        GridPane.setVgrow(playerButton, Priority.ALWAYS);
        playerButton.setOnAction(event -> gamePlayersButton(textField, monitor));
        playerButton.setId("confirmbutton");
    	return playerButton;
    }
    
    public static void gamePlayersButton(TextField textField, MonitorLoto monitor) {
    	if(!textField.getText().equals("")) {
	    	monitor.writeJogadoresJSON(Integer.parseInt(textField.getText()));
	    	Controller.changeMessage(String.format("Inseriu %s jogadores", textField.getText()));
    	}
    }
}
