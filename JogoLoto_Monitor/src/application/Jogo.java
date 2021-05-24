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

import gui.util.Constraints;

public class Jogo extends Application {

    private final StringProperty value = new SimpleStringProperty();
    private GridPane grid;
    private MonitorLoto monitor = new MonitorLoto();
    private Label title = getLabel("Monitor de Loto");
    private Label logger = new Label();
    private Button playerButton = new Button("Confirmar");
    private TextField textField = new TextField();


    class NumberButtonHandler implements EventHandler<ActionEvent> {
        private final int number ;
        NumberButtonHandler(int number) {
            this.number = number ;
        }
        @Override
        public void handle(ActionEvent event) {
            value.set(value.get() + "    " + number);
            int btNumber = Integer.parseInt(((Button)event.getSource()).getText());
            grid.getChildren().remove((Button)event.getSource());
            
            // Saves the chosen number in the JSON file
            monitor.nrEscolhido(btNumber);
            changeMessage(String.format("Selecionou o número %s", String.valueOf(number)));
        }

    }

    @Override
    public void start(Stage primaryStage) {
    	value.set("");
        grid = createGrid();
        logger.setId("logger");
        int buttonNr = 1;
        for (int r=1; r<=10; r++) {
        	for (int c=1; c<=9; c++) {
        		Button button = createNumberButton(buttonNr);
        		grid.add(button, r, c);
        		buttonNr ++;
        	}
        }

        Button clearButton = createButton("Reiniciar Jogo");
        clearButton.setId("resetbutton");
        clearButton.setOnAction(event -> restartGame(primaryStage));

        TextArea displayField = createDisplayField();
        TextField textField = createPlayersField();
        
        FlowPane topBar = new FlowPane();
        title.setId("title");
        topBar.setHgap(20);
        topBar.setVgap(20);
        topBar.getChildren().addAll( title, displayField);
        
        FlowPane bottomBar = new FlowPane() ;
        bottomBar.setHgap(20);
        bottomBar.setVgap(20);
        bottomBar.getChildren().addAll(
        		getLabel("Número de Jogadores"),
        		textField,
        		playerButton(),
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
    
    private Label getLabel(String string) {
        Label label = new Label();
        label.setText(string);
        label.setMaxWidth(Double.POSITIVE_INFINITY);
        label.setMaxHeight(Double.POSITIVE_INFINITY);
        return label;
    }
    
    private void restartGame(Stage stage) {
    	
    	value.set("");
    	changeMessage("Reiniciou com sucesso");
    	monitor.reset();
    	start(stage);
    }

     private Button createNumberButton(int number) {
        Button button = createButton(Integer.toString(number));
        button.setOnAction(new NumberButtonHandler(number));
        return button ;
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        GridPane.setFillHeight(button, true);
        GridPane.setFillWidth(button, true);
        GridPane.setHgrow(button, Priority.ALWAYS);
        GridPane.setVgrow(button, Priority.ALWAYS);
        return button ;
    }
    
    private Button playerButton() {
        playerButton.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        GridPane.setFillHeight(playerButton, true);
        GridPane.setFillWidth(playerButton, true);
        GridPane.setHgrow(playerButton, Priority.ALWAYS);
        GridPane.setVgrow(playerButton, Priority.ALWAYS);
        playerButton.setOnAction(this::gamePlayers);
        playerButton.setId("confirmbutton");
    	return playerButton;
    }
    
    public void gamePlayers(ActionEvent event) {
    	if(!textField.getText().equals("")) {
	    	monitor.writeJogadoresJSON(Integer.parseInt(textField.getText()));
	    	changeMessage(String.format("Inseriu %s jogadores", textField.getText()));
    	}
    }


    private GridPane createGrid() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(10));
        return grid;
    }

    private TextArea createDisplayField() {
        TextArea displayField = new TextArea();
        displayField.textProperty().bind(Bindings.format("%s", value));
        displayField.setEditable(false);
        displayField.setMaxHeight(5);
        displayField.setMinWidth(586);
        displayField.autosize();
        return displayField;
    }
    
    private TextField createPlayersField() {
        textField.setEditable(true);
        textField.setMaxHeight(5);
        textField.autosize();
        Constraints.setTextFieldInteger(textField);
        Constraints.setTextFieldMaxLength(textField, 2);
        return textField;
    }
    
    private void changeMessage(String string) {
        logger.setMaxHeight(5);
        logger.setText(string);
        logger.autosize();
    }

    public static void main(String[] args) {
        launch(args);
    }
}