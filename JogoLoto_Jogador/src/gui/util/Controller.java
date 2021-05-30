package gui.util;

import java.util.concurrent.TimeUnit;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import model.entities.Jogador;

public class Controller {
    private final static StringProperty value = new SimpleStringProperty();
    static TextField playerBet = new TextField();
	
    public static void setValue(String string) {
    	value.set(string);
    }
    
    public static TextArea createDisplayField() {
        TextArea displayField = new TextArea();
        displayField.textProperty().bind(Bindings.format("%s", value));
        displayField.setEditable(false);
        displayField.setMaxHeight(5);
        displayField.setMinWidth(586);
        displayField.autosize();
        return displayField;
    }
    
    public static TextField createBetField() {
    	playerBet.setMaxWidth(150);
    	playerBet.setPromptText("€");
        playerBet.setEditable(true);
        playerBet.setMaxHeight(5);
        playerBet.autosize();
        Constraints.setTextFieldDouble(playerBet);
        Constraints.setTextFieldMaxLength(playerBet, 10);
        return playerBet;
    }
    
    public static void parsePlayerBet(Jogador jogador, Label logger) {
    	double bet = Double.parseDouble(playerBet.getText());
    	jogador.setAposta(bet, logger);
    }
    
    public static Label getLabel(String string) {
        Label label = new Label();
        label.setText(string);
        label.setMaxWidth(Double.POSITIVE_INFINITY);
        label.setMaxHeight(Double.POSITIVE_INFINITY);
        return label;
    }
    
    public static BorderPane getRoot(FlowPane top, GridPane center, FlowPane bottom) {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        root.setTop(top);
        root.setCenter(center);  
        root.setBottom(bottom);
        return root;
    }
    
    public static Scene getScene(BorderPane root) {
        Scene scene = new Scene(root, 1000, 400);
        scene.getStylesheets().add("application/application.css");
        return scene;
    }
    
    public static Task<Void> getTaskDisplay(TextField displayField) {
	    Task<Void> task = new Task<Void>() {
	        @Override public Void call() {
	            final int max = 8;
	            for (int i=1; i<=max; i++) {
	                if (isCancelled()) {
	                   break;
	                }
	                updateProgress(i, max);
	                while(true) {
	                    try{
	                    	TimeUnit.SECONDS.sleep(3);
	                    }
	                    catch(Exception e){
	                        System.err.println("Erro, Ficheiro não pode ser lido!");
	                        break;
	                    }
	                }
	            }
	            return null;
	        }
	    };
        return task;
    }
	
    public static void changeMessage(Label logger, String string) {
        logger.setMaxHeight(5);
        logger.setText(string);
        logger.autosize();
    }
}
