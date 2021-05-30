package gui.components;

import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Controller;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import model.entities.Jogador;

public class Buttons {
    private boolean dragFlag = false;
   
    public TextField createNumberTextField(String number) {
		TextField textField = createTextField(number);
		Constraints.setTextFieldMaxLength(textField, 2);
		Constraints.setTextFieldInteger(textField);
		// Blocks Click Spam
        textField.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
            	//int textFieldNumber = 0;
            	if(!((TextField)e.getSource()).getText().equals("")) {
            		//textFieldNumber = Integer.parseInt(((TextField)e.getSource()).getText());
            	}
                if (e.getButton().equals(MouseButton.PRIMARY)) {
                    if (!dragFlag) {
                        if (e.getClickCount() >= 1) {
                        	// Click event before lock the card
                        }
                    }
                    dragFlag = false;
                }
            }
        });
		return textField;
     }
    
    private TextField createTextField(String text) {
        TextField textField = new TextField(text);
        textField.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        textField.setEditable(true);
        GridPane.setFillHeight(textField, true);
        GridPane.setFillWidth(textField, true);
        GridPane.setHgrow(textField, Priority.ALWAYS);
        GridPane.setVgrow(textField, Priority.ALWAYS);
        return textField ;
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
    
    public static Button originalButton(GridPane grid) {
	    Button originalButton = createButton("Cartão Original");
	    originalButton.setId("resetbutton");
	    originalButton.setOnAction(event -> Grid.originalGrid(grid));
	    return originalButton;
    }
    
    public static Button exitButton(GridPane grid) {
	    Button exitButton = new Button("Sair");
	    exitButton.setId("resetbutton");
	    exitButton.setOnAction(event -> Platform.exit());
	    exitButton.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        GridPane.setFillHeight(exitButton, true);
        GridPane.setFillWidth(exitButton, true);
        GridPane.setHgrow(exitButton, Priority.ALWAYS);
        GridPane.setVgrow(exitButton, Priority.ALWAYS);
	    return exitButton;
    }
    
    public static Button betButton(GridPane grid, Jogador jogador, Label logger) {
	    Button betButton = new Button("Apostar");
	    betButton.setId("resetbutton");
	    betButton.setOnAction(event -> Controller.parsePlayerBet(jogador, logger));
	    betButton.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        GridPane.setFillHeight(betButton, true);
        GridPane.setFillWidth(betButton, true);
        GridPane.setHgrow(betButton, Priority.ALWAYS);
        GridPane.setVgrow(betButton, Priority.ALWAYS);
	    return betButton;
    }
        
    public static Button generateCardButton(GridPane grid) {
    	Button playerButton = new Button("Gerar");
        playerButton.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        GridPane.setFillHeight(playerButton, true);
        GridPane.setFillWidth(playerButton, true);
        GridPane.setHgrow(playerButton, Priority.ALWAYS);
        GridPane.setVgrow(playerButton, Priority.ALWAYS);
        playerButton.setOnAction(event -> Grid.updateGrid(grid));
        playerButton.setId("generatebutton");
    	return playerButton;
    }
    
    public static Button restartAfterClickButton(GridPane grid, Stage primaryStage, Jogador jogador) {
    	Button playerButton = new Button("Reiniciar");
        playerButton.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        GridPane.setFillHeight(playerButton, true);
        GridPane.setFillWidth(playerButton, true);
        GridPane.setHgrow(playerButton, Priority.ALWAYS);
        GridPane.setVgrow(playerButton, Priority.ALWAYS);
        playerButton.setOnAction(event -> Alerts.showReset(grid, primaryStage, jogador));
        playerButton.setId("generatebutton");
    	return playerButton;
    } 
    
    public static Button confirmCardButton(Button cardConfirmButton, GridPane grid, Label logger, Stage primaryStage, Jogador jogador) {
    	cardConfirmButton.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        GridPane.setFillHeight(cardConfirmButton, true);
        GridPane.setFillWidth(cardConfirmButton, true);
        GridPane.setHgrow(cardConfirmButton, Priority.ALWAYS);
        GridPane.setVgrow(cardConfirmButton, Priority.ALWAYS);
        cardConfirmButton.setOnAction(event -> Grid.lockGrid(primaryStage ,grid, logger, jogador));
        cardConfirmButton.setId("confirmbutton");
    	return cardConfirmButton;
    }

}
