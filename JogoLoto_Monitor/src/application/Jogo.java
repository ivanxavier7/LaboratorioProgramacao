package application;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import model.entities.MonitorLoto;
import gui.components.Buttons;
import gui.util.Constraints;
import gui.util.Controller;
import gui.util.Grid;

public class Jogo extends Application {

    private final static StringProperty value = new SimpleStringProperty();

	private static MonitorLoto monitor = new MonitorLoto();
    private Label title = Controller.getLabel("Monitor de Loto");
    private static Label logger = new Label();

	@Override
    public void start(Stage primaryStage) {
        GridPane grid = Grid.createGrid();
        logger.setId("logger");
        Controller.changeMessage("Iniciou com sucesso");

        Button clearButton = Buttons.createButton("Reiniciar Jogo");
        clearButton.setId("resetbutton");
        clearButton.setOnAction(event -> Controller.restartGame(primaryStage, value, monitor));

        TextArea displayField = Controller.createDisplayField(value);
        TextField textField = Controller.createPlayersField();
        
        FlowPane topBar = new FlowPane();
        title.setId("title");
        topBar.setHgap(20);
        topBar.setVgap(20);
        topBar.getChildren().addAll( title, displayField);
        
        FlowPane bottomBar = new FlowPane() ;
        bottomBar.setHgap(20);
        bottomBar.setVgap(20);
        bottomBar.getChildren().addAll(
        		Controller.getLabel("Número de Jogadores"),
        		textField,
        		Buttons.playerButton(textField, monitor),
        		clearButton,
        		logger
        		);
        
        
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        root.setTop(topBar);
        root.setCenter(grid);  
        root.setBottom(bottomBar);
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("application/application.css");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
	public static String getValue() {
		return value.get();
	}
	
	public static void setValueMonitor(int newNumber) {
		String oldNumbers = String.valueOf(getValue());
		if(oldNumbers.equals("null")) {
			oldNumbers = "";
		}
		value.set(oldNumbers + "  " + newNumber);
	}
    
	public static Label getLogger() {
		return logger;
	}

    public static void main(String[] args) {
        launch(args);
    }
}