package application;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import model.entities.Jogador;
import model.entities.MonitorLoto;

import gui.util.Constraints;

public class Jogo extends Application {

    private final StringProperty value = new SimpleStringProperty();
    private GridPane grid;
    private Label title = getLabel("Jogador de Loto");
    private Label logger = new Label();
    private Button playerButton = new Button("Gerar");
    private Button cardConfirmButton = new Button("Confirmar Cartão");
    private TextField textField = new TextField();
    private boolean dragFlag = false;
    private int clickCounter = 0;
    private static int[][] cartao = new int[3][9];
    private static int[][] cartaoAntigo = new int[3][9];
    private Jogador jogador = new Jogador();

    @Override
    public void start(Stage primaryStage) {
    	value.set("");
        grid = createGrid();
        logger.setId("logger");
        Button clearButton = createButton("Cartão Original");
        clearButton.setId("resetbutton");
        //clearButton.setOnAction(event -> restartGame(primaryStage));
        clearButton.setOnAction(event -> previousGrid());

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
        		generateCardButton(),
        		clearButton,
        		confirmCardButton(),
        		logger
        		);
        
        
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        root.setTop(topBar);
        root.setCenter(grid);  
        root.setBottom(bottomBar);
        Scene scene = new Scene(root, 800, 400);
        scene.getStylesheets().add("application/application.css");
        
        //TEST ZONE
        //writeGrid();        
        updateGrid();
        //validateGrid();
        
        System.out.println("ASDASDASDASDASDASDASD-------------" + ((TextField)getNodeByRowColumnIndex(1,1,grid)).getText());
        jogador.getClone(cartaoAntigo, cartao);
        
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
    	start(stage);
    }
     
    private TextField createNumberTextField(int number) {
		TextField textField = createTextField(Integer.toString(number));
		//textField.setOnAction(new NumberButtonHandler(number));
        textField.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
            	int textFieldNumber = Integer.parseInt(((TextField)e.getSource()).getText());
                Node source = (Node)e.getSource() ;
                Integer colIndex = GridPane.getColumnIndex(source);
                Integer rowIndex = GridPane.getRowIndex(source);
                if (e.getButton().equals(MouseButton.PRIMARY)) {
                    if (!dragFlag) {
                        System.out.println(++clickCounter + " " + e.getClickCount());
                        if (e.getClickCount() == 1) {
                            System.out.println("TESTE" + textFieldNumber);
                            System.out.println("Coluna - " + colIndex);
                            System.out.println("Linha - " + rowIndex);
                        } else if (e.getClickCount() > 1) {
                        	System.out.println("DUPLO CLIQUE!" + textFieldNumber);
                        	//readGrid();
                        }
                    }
                    dragFlag = false;
                }
            }
        });
		return textField;
     }
    
    private TextField createNumberTextField(String number) {
		TextField textField = createTextField(number);
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
    
    private Boolean validateGrid() {
		Boolean valid = true;
    	for (Node node : grid.getChildren()) {
		    Integer colIndex = GridPane.getColumnIndex(node);
		    Integer rowIndex = GridPane.getRowIndex(node);
    	    if (node instanceof TextField) {
    	    	if(!((TextField)node).getText().equals("")) {
	    	    	int textFieldNr = Integer.parseInt(((TextField)node).getText());
	    	    	System.out.println("AI O CARALHO" +  textFieldNr);
	    	        switch (colIndex) {
	    	        case 1:
	    	        	if(textFieldNr < 1 || textFieldNr > 9) {
	    	        		changeMessage(String.format("O valor: %d na %dª coluna e na %dª linha é inválido!", textFieldNr, colIndex, rowIndex));
	    	        		valid = false;
	    	        	}
	    	          break;
	    	        case 2:
	    	        	if(textFieldNr < 10 || textFieldNr > 19) {
	    	        		changeMessage(String.format("O valor: %d na %dª coluna e na %dª linha é inválido!", textFieldNr, colIndex, rowIndex));
	    	        		valid = false;
	    	        	}
	    	          break;
	    	        case 3:
	    	        	if(textFieldNr < 20 || textFieldNr > 29) {
	    	        		changeMessage(String.format("O valor: %d na %dª coluna e na %dª linha é inválido!", textFieldNr, colIndex, rowIndex));
	    	        		valid = false;
	    	        	}
	    	          break;
	    	        case 4:
	    	        	if(textFieldNr < 30 || textFieldNr > 39) {
	    	        		changeMessage(String.format("O valor: %d na %dª coluna e na %dª linha é inválido!", textFieldNr, colIndex, rowIndex));
	    	        		valid = false;
	    	        	}
	    	          break;
	    	        case 5:
	    	        	if(textFieldNr < 40 || textFieldNr > 49) {
	    	        		changeMessage(String.format("O valor: %d na %dª coluna e na %dª linha é inválido!", textFieldNr, colIndex, rowIndex));
	    	        		valid = false;
	    	        	}
	    	          break;
	    	        case 6:
	    	        	if(textFieldNr < 50 || textFieldNr > 59) {
	    	        		changeMessage(String.format("O valor: %d na %dª coluna e na %dª linha é inválido!", textFieldNr, colIndex, rowIndex));
	    	        		valid = false;
	    	        	}
	    	          break;
	    	        case 7:
	    	        	if(textFieldNr < 60 || textFieldNr > 69) {
	    	        		changeMessage(String.format("O valor: %d na %dª coluna e na %dª linha é inválido!", textFieldNr, colIndex, rowIndex));
	    	        		valid = false;
	    	        	}
	    	          break;
	    	        case 8:
	    	        	if(textFieldNr < 70 || textFieldNr > 79) {
	    	        		changeMessage(String.format("O valor: %d na %dª coluna e na %dª linha é inválido!", textFieldNr, colIndex, rowIndex));
	    	        		valid = false;
	    	        	}
	    	          break;
	    	        case 9:
	    	        	if(textFieldNr < 80 || textFieldNr > 90) {
	    	        		changeMessage(String.format("O valor: %d na %dª coluna e na %dª linha é inválido!", textFieldNr, colIndex, rowIndex));
	    	        		valid = false;
	    	        	}
	    	          break;
	    	      }
	    	    }
    	    }
    	}
    	return valid;
    }
    
    private void writeGrid() {
    	System.out.println("\nCartão recebido:" + jogador.getStringCartao(cartao));
    	for (Node node : grid.getChildren()) {
    	    System.out.println("Id: " + node.getId());
    	    if (node instanceof TextField) {
    	        // clear
    	    	((TextField)node).setText("TESTE");
    	    }
    	}
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
    
    private Button playerButton() {
        playerButton.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        GridPane.setFillHeight(playerButton, true);
        GridPane.setFillWidth(playerButton, true);
        GridPane.setHgrow(playerButton, Priority.ALWAYS);
        GridPane.setVgrow(playerButton, Priority.ALWAYS);
        playerButton.setOnAction(this::gamePlayers);
        playerButton.setId("confirmPlayerbutton");
    	return playerButton;
    }
    
    private Button generateCardButton() {
        playerButton.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        GridPane.setFillHeight(playerButton, true);
        GridPane.setFillWidth(playerButton, true);
        GridPane.setHgrow(playerButton, Priority.ALWAYS);
        GridPane.setVgrow(playerButton, Priority.ALWAYS);
        playerButton.setOnAction(event -> updateGrid());
        playerButton.setId("generatebutton");
    	return playerButton;
    }
    
    private Button confirmCardButton() {
    	cardConfirmButton.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        GridPane.setFillHeight(playerButton, true);
        GridPane.setFillWidth(playerButton, true);
        GridPane.setHgrow(playerButton, Priority.ALWAYS);
        GridPane.setVgrow(playerButton, Priority.ALWAYS);
        cardConfirmButton.setOnAction(event -> validateGrid());
        cardConfirmButton.setId("confirmbutton");
    	return cardConfirmButton;
    }
    
    public void gamePlayers(ActionEvent event) {
    	if(!textField.getText().equals("")) {
	    	//monitor.writeJogadoresJSON(Integer.parseInt(textField.getText()));
	    	changeMessage(String.format("Inseriu %s jogadores", textField.getText()));
    	}
    }

    private GridPane createGrid() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(10));
        int buttonNr = 1;
        for (int c=1; c<=9; c++) {
        	for (int r=1; r<=3; r++) {
        		//Button button = createNumberButton(buttonNr);
        		TextField textField = createNumberTextField(buttonNr);
        		grid.add(textField, c, r);
        		buttonNr ++;
        	}
        }
        return grid;
    }
    
    private GridPane updateGrid() {
    	cartao = jogador.generateCartao();
        for (int c=1; c<=9; c++) {
        	for (int r=1; r<=3; r++) {
        		if(cartao[r-1][c-1]!=99) {
	        		TextField textField = createNumberTextField(cartao[r-1][c-1]);
	        		grid.add(textField, c, r);
        		}
        		else {
        			TextField textField = createNumberTextField("");
            		grid.add(textField, c, r);
        		}
        	}
        }
        return grid;
    }
    
    public Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                result = node;
                System.out.println("TESTE DO CRL" + ((TextField)node).getText());
                break;
            }
        }
        return result;
    }
    
    private GridPane previousGrid() {
    	jogador.getClone(cartao, cartaoAntigo);
        for (int c=1; c<=9; c++) {
        	for (int r=1; r<=3; r++) {
        		String nrCartao = String.valueOf(cartao[r-1][c-1]);
        		if(cartao[r-1][c-1]!=99) {
	        		TextField textField = createNumberTextField(cartao[r-1][c-1]);
	        		Constraints.setTextFieldInteger(textField);
	        		Constraints.setTextFieldMaxLength(textField, 2);
	        		grid.add(textField, c, r);
        		}
        		else {
        			TextField textField = createNumberTextField("");
	        		Constraints.setTextFieldInteger(textField);
	        		Constraints.setTextFieldMaxLength(textField, 2);
            		grid.add(textField, c, r);
        		}
        	}
        }
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