package gui.util;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class Buttons {
    private boolean dragFlag = false;
    private int clickCounter = 0;
    private static Grid gridUtil = new Grid();

    
    public TextField createNumberTextField(String number) {
		TextField textField = createTextField(number);
		Constraints.setTextFieldMaxLength(textField, 2);
		Constraints.setTextFieldInteger(textField);
		//textField.setOnAction(new NumberButtonHandler(number));
        textField.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
            	int textFieldNumber = 0;
            	if(!((TextField)e.getSource()).getText().equals("")) {
            		textFieldNumber = Integer.parseInt(((TextField)e.getSource()).getText());
            	}
                if (e.getButton().equals(MouseButton.PRIMARY)) {
                    if (!dragFlag) {
                        System.out.println(++clickCounter + " " + e.getClickCount());
                        if (e.getClickCount() == 1) {
                            System.out.println("TESTE" + textFieldNumber);
                            
                        } else if (e.getClickCount() > 1) {
                        	System.out.println("DUPLO CLIQUE!" + textFieldNumber);
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
    
    
    public static Button clearButton(GridPane grid) {
	    Button clearButton = Buttons.createButton("Cartão Original");
	    clearButton.setId("resetbutton");
	    clearButton.setOnAction(event -> gridUtil.originalGrid(grid));
	    return clearButton;
    }
    
    public static Button generateCardButton(GridPane grid) {
    	Button playerButton = new Button("Gerar");
        playerButton.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        GridPane.setFillHeight(playerButton, true);
        GridPane.setFillWidth(playerButton, true);
        GridPane.setHgrow(playerButton, Priority.ALWAYS);
        GridPane.setVgrow(playerButton, Priority.ALWAYS);
        playerButton.setOnAction(event -> gridUtil.updateGrid(grid));
        playerButton.setId("generatebutton");
    	return playerButton;
    }
    
    public static Button confirmCardButton(Button cardConfirmButton, GridPane grid, Label logger) {
    	cardConfirmButton.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        GridPane.setFillHeight(cardConfirmButton, true);
        GridPane.setFillWidth(cardConfirmButton, true);
        GridPane.setHgrow(cardConfirmButton, Priority.ALWAYS);
        GridPane.setVgrow(cardConfirmButton, Priority.ALWAYS);
        cardConfirmButton.setOnAction(event -> gridUtil.validateGrid(grid, logger));
        cardConfirmButton.setId("confirmbutton");
    	return cardConfirmButton;
    }
    
    public void gamePlayers(TextField textField, Label logger) {
    	if(!textField.getText().equals("")) {
	    	//monitor.writeJogadoresJSON(Integer.parseInt(textField.getText()));
	    	Controller.changeMessage(logger, String.format("Inseriu %s jogadores", textField.getText()));
    	}
    }
}
