package gui.components;

import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Controller;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.entities.Jogador;
import model.entities.MonitorLoto;

public class Grid {
    private static Jogador jogador = new Jogador();
    private static Buttons buttonsUtil = new Buttons();
    private static int[][] cartao = new int[3][9];
    private static int[][] cartaoAntigo = new int[3][9];
    private static boolean dragFlag = false;
    private static boolean firstRowComplete = false;
    private static boolean secondRowComplete = false;
    private static boolean thirdRowComplete = false;
    private static boolean firstRowReward = false;
    private static double reward = 0.0;
	static int whiteSpacesLine1 = 0;
	static int whiteSpacesLine2 = 0;
	static int whiteSpacesLine3 = 0;
    
    public static GridPane createGrid() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(10));
        updateGrid(grid);
        jogador.getClone(cartaoAntigo, cartao);
        return grid;
    }
    
    public static GridPane updateGrid(GridPane grid) {
    	cartao = jogador.generateCartao();
    	grid.getChildren().clear();
        for (int c=1; c<=9; c++) {
        	for (int r=1; r<=3; r++) {
        		if(cartao[r-1][c-1]!=99) {
	        		TextField textField = buttonsUtil.createNumberTextField(String.valueOf(cartao[r-1][c-1]));
	        		grid.add(textField, c, r);
        		}
        		else {
        			TextField textField = buttonsUtil.createNumberTextField("");
            		grid.add(textField, c, r);
        		}
        	}
        }
        return grid;
    }
    
    public static GridPane originalGrid(GridPane grid) {
    	jogador.getClone(cartao, cartaoAntigo);
    	grid.getChildren().clear();
        for (int c=1; c<=9; c++) {
        	for (int r=1; r<=3; r++) {
        		if(cartao[r-1][c-1]!=99) {
	        		TextField textField = buttonsUtil.createNumberTextField(String.valueOf(cartao[r-1][c-1]));
	        		Constraints.setTextFieldInteger(textField);
	        		Constraints.setTextFieldMaxLength(textField, 2);
	        		grid.add(textField, c, r);
        		}
        		else {
        			TextField textField = buttonsUtil.createNumberTextField("");
	        		Constraints.setTextFieldInteger(textField);
	        		Constraints.setTextFieldMaxLength(textField, 2);
            		grid.add(textField, c, r);
        		}
        	}
        }
        return grid;
    }
    
    public static Boolean validateGrid(GridPane grid, Label logger) {
		int Line1 = 0;
		int Line2 = 0;
		int Line3 = 0;
		System.out.println("RESET");
		Boolean valid = true;
		Controller.changeMessage(logger,"");
    	for (Node node : grid.getChildren()) {
		    Integer colIndex = GridPane.getColumnIndex(node);
		    Integer rowIndex = GridPane.getRowIndex(node);
    	    if (node instanceof TextField) {
    	    	// Count White Spaces
    	    	if(!((TextField)node).getText().equals("")) {
	    	    	int textFieldNr = Integer.parseInt(((TextField)node).getText());
	    	    	// Column Validation
	    	    	valid = columnValidation(textFieldNr, colIndex, rowIndex, logger, valid);
	    	    } else {
	    	    	// White Space catch
	    	    	switch(rowIndex) {
		    	    	case 1:
		    	    		Line1 ++;
		    	    		break;
		    	    	case 2:
		    	    		Line2 ++;
		    	    		break;
		    	    	case 3:
		    	    		Line3 ++;
		    	    		break;
		    	    	}
	    	    	whiteSpacesLine1 = Line1;	
	    	    	whiteSpacesLine2 = Line2;
	    	    	whiteSpacesLine3 = Line3;
	    	    }
    	    }
    	}
    	if(whiteSpacesLine1 > 4) {
    		System.out.println("Espaços em branco " + whiteSpacesLine1);
    		Controller.changeMessage(logger, String.format("Existe(m) %d espaço(s) em branco a mais na 1ª Linha", whiteSpacesLine1 - 4));
    		valid = false;
    	} else if(whiteSpacesLine1 < 4) {
    		Controller.changeMessage(logger, String.format("Existe(m) %d espaço(s) em branco a menos na 1ª Linha", whiteSpacesLine1 - 4));
    		valid = false;
    	} else if(whiteSpacesLine2 > 4) {
    		Controller.changeMessage(logger, String.format("Existe(m) %d espaço(s) em branco a mais na 2ª Linha", whiteSpacesLine2 - 4));
    		valid = false;
    	} else if(whiteSpacesLine2 < 4) {
    		Controller.changeMessage(logger, String.format("Existe(m) %d espaço(s) em branco a menos na 2ª Linha", whiteSpacesLine2 - 4));
    		valid = false;
    	} else if(whiteSpacesLine3 > 4) {
    		Controller.changeMessage(logger, String.format("Existe(m) %d espaço(s) em branco a mais na 3ª Linha", whiteSpacesLine3 - 4));
    		valid = false;
    	} else if(whiteSpacesLine3 < 4) {
    		Controller.changeMessage(logger, String.format("Existe(m) %d espaço(s) em branco a menos na 3ª Linha", whiteSpacesLine3 - 4));

    		valid = false;
    	} 
	    // Repeated Validation
	    valid = repeatedValidation(grid, logger, valid);
    	return valid;
    }
    
	public static Node getItemByIndex (final int row, final int col, GridPane grid) {
        Node result = null;
        ObservableList<Node> childrens = grid.getChildren();
        for (Node node : childrens) {
            if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
                result = node;
                break;
            }
        }
        return result;
    }
    
    public static Boolean repeatedValidation(GridPane grid, Label logger, Boolean validNr) {
    	for (Node node : grid.getChildren()) {
		    Integer colIndex = GridPane.getColumnIndex(node);
	        String rowNr1 = ((TextField)getItemByIndex(1, colIndex, grid)).getText();
	        String rowNr2 = ((TextField)getItemByIndex(2, colIndex, grid)).getText();
	        String rowNr3 = ((TextField)getItemByIndex(3, colIndex, grid)).getText();
    	    if (node instanceof TextField) {
    	        if(!rowNr1.equals("") && rowNr1.equals(rowNr2) || !rowNr1.equals("") && rowNr1.equals(rowNr3)) {
    	        	Controller.changeMessage(logger, String.format("O número %s está repetido na %d coluna", rowNr1, colIndex));
    	        	validNr = false;
    	        } else if(!rowNr2.equals("") && rowNr2.equals(rowNr3)) {
    	        	Controller.changeMessage(logger, String.format("O número %s está repetido na %d coluna", rowNr2, colIndex));
    	        	validNr = false;
    	        }
    	    }
    	}
    	return validNr;
    }
    
    private static Boolean columnValidation(int textFieldNr, int colIndex, int rowIndex, Label logger, Boolean validNr) {
        switch (colIndex) {
        case 1:
        	if(textFieldNr < 1 || textFieldNr > 9) {
        		Controller.changeMessage(logger, String.format("O valor: %d na %dª coluna e na %dª linha é inválido!", textFieldNr, colIndex, rowIndex));
        		validNr = false;
        	}
          break;
        case 2:
        	if(textFieldNr < 10 || textFieldNr > 19) {
        		Controller.changeMessage(logger, String.format("O valor: %d na %dª coluna e na %dª linha é inválido!", textFieldNr, colIndex, rowIndex));
        		validNr = false;
        	}
          break;
        case 3:
        	if(textFieldNr < 20 || textFieldNr > 29) {
        		Controller.changeMessage(logger, String.format("O valor: %d na %dª coluna e na %dª linha é inválido!", textFieldNr, colIndex, rowIndex));
        		validNr = false;
        	}
          break;
        case 4:
        	if(textFieldNr < 30 || textFieldNr > 39) {
        		Controller.changeMessage(logger, String.format("O valor: %d na %dª coluna e na %dª linha é inválido!", textFieldNr, colIndex, rowIndex));
        		validNr = false;
        	}
          break;
        case 5:
        	if(textFieldNr < 40 || textFieldNr > 49) {
        		Controller.changeMessage(logger, String.format("O valor: %d na %dª coluna e na %dª linha é inválido!", textFieldNr, colIndex, rowIndex));
        		validNr = false;
        	}
          break;
        case 6:
        	if(textFieldNr < 50 || textFieldNr > 59) {
        		Controller.changeMessage(logger, String.format("O valor: %d na %dª coluna e na %dª linha é inválido!", textFieldNr, colIndex, rowIndex));
        		validNr = false;
        	}
          break;
        case 7:
        	if(textFieldNr < 60 || textFieldNr > 69) {
        		Controller.changeMessage(logger, String.format("O valor: %d na %dª coluna e na %dª linha é inválido!", textFieldNr, colIndex, rowIndex));
        		validNr = false;
        	}
          break;
        case 8:
        	if(textFieldNr < 70 || textFieldNr > 79) {
        		Controller.changeMessage(logger, String.format("O valor: %d na %dª coluna e na %dª linha é inválido!", textFieldNr, colIndex, rowIndex));
        		validNr = false;
        	}
          break;
        case 9:
        	if(textFieldNr < 80 || textFieldNr > 90) {
        		Controller.changeMessage(logger, String.format("O valor: %d na %dª coluna e na %dª linha é inválido!", textFieldNr, colIndex, rowIndex));
        		validNr = false;
        	}
          break;
        }
        return validNr;
    }
    
    public static Scene hideBottomBar(GridPane grid, Label logger) {
    	// Remove bottom bar
        TextArea displayField = Controller.createDisplayField();
        FlowPane topBar = Bars.getTopBar(displayField);
        FlowPane bottomBar = Bars.getLogger(logger);  
        BorderPane root = Controller.getRoot(topBar, grid, bottomBar);
        Scene scene = Controller.getScene(root);
        return scene;     
    }
    
    public static Scene showBottomBarAfterClick(Stage primaryStage, GridPane grid) {
    	// Remove bottom bar
        TextArea displayField = Controller.createDisplayField();
        FlowPane topBar = Bars.getTopBar(displayField);        
        FlowPane bottomBar = Bars.getBottomBarAfterClick(grid, primaryStage, jogador);
        BorderPane root = Controller.getRoot(topBar, grid, bottomBar);
        Scene scene = Controller.getScene(root);
        return scene;    
    }
    
    public static Scene showBottomBar(Stage primaryStage, GridPane grid, Jogador jogador) {
    	// Remove bottom bar
        TextArea displayField = Controller.createDisplayField();
        FlowPane topBar = Bars.getTopBar(displayField);        
        FlowPane bottomBar = Bars.getBottomBar(grid, primaryStage, jogador);
        BorderPane root = Controller.getRoot(topBar, grid, bottomBar);
        Scene scene = Controller.getScene(root);
        return scene;
        
    }
    
    // Blocks Click Spam
    public static void lockGrid(Stage primaryStage, GridPane grid, Label logger, Jogador jogador) {
    	Scene scene = showBottomBar(primaryStage, grid, jogador);
        // Change Events
    	if(MonitorLoto.readJSON() == null) {
	    	if(validateGrid(grid, logger)) {
	        	scene = hideBottomBar(grid, logger);
	        	for (Node node : grid.getChildren()) {
	        	    if (node instanceof TextField) {
	        	    	((TextField)node).setEditable(false);
	        	    	((TextField)node).setOnMouseClicked(new EventHandler<MouseEvent>() {
	        	                @Override
	        	                public void handle(MouseEvent e) {
	        	                	int textFieldNumber = 0;
	        	                	if(!((TextField)e.getSource()).getText().equals("")) {
	        	                		textFieldNumber = Integer.parseInt(((TextField)e.getSource()).getText());
	        	                	}
	        	                    if (e.getButton().equals(MouseButton.PRIMARY)) {
	        	                        if (!dragFlag) {
	        	                            if (e.getClickCount() >= 1) {
	        	                            	// Restart game if monitor = ""
	        	                            	if(MonitorLoto.readJSON() == null) {
	        	                            		Scene scene = showBottomBarAfterClick(primaryStage, grid);
	        	                                    primaryStage.setScene(scene);
	        	                                    primaryStage.setResizable(false);
	        	                                    primaryStage.show();
	        	                                    Alerts.showAlert(
	        	                                    		"Jogo Acabou",
	        	                                    		"Este jogo de loto acabou",
	        	                                    		"Se desejar continuar a jogar clique em reiniciar e espere pelo monitor para confirmar o seu cartão",
	        	                                    		AlertType.WARNING);
	        	                            	} else {
		        	                                if(MonitorLoto.checkJSON(textFieldNumber)) {
		        	                                	((TextField)e.getSource()).setText("");;
		        	                                	((TextField)e.getSource()).setStyle(
		        	                                			  "-fx-background-color:"
		        	                                			+ "#b0e0e6,"
		        	                                			+ "linear-gradient(#b0e0e6 0%, #1f2429 20%, #191d22 100%),"
		        	                                			+ "linear-gradient(#20262b, #191d22),"
		        	                                			+ "radial-gradient(center 50% 0%, radius 100%, rgba(100,67,120,0.5), rgba(255,0,255,0));"
		        	                                			+ "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
		        	                                };
		        	                                Integer rowIndex = GridPane.getRowIndex(node);
		        	                                Controller.changeMessage(logger, String.format("Selecionou o número %d", textFieldNumber));
		        	                                checkProgress(grid, logger, rowIndex, primaryStage, jogador); 
	        	                            	}
	        	                            }
	        	                        }
	        	                        dragFlag = false;
	        	                    }
	        	                }
	        	    	});
	        	    }
	        	}
	    	}
    	} else {
    		Controller.changeMessage(logger, "Por favor aguarde pelo final deste turno para confirmar o Cartão!");
    	}
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    private static void checkProgress(GridPane grid, Label logger, int rowIndex, Stage primaryStage, Jogador jogador) {
    	Boolean lineComplete = true;
    	for(int col=1; col<10; col++) {
	        String colNr = ((TextField)getItemByIndex(rowIndex, col, grid)).getText();
	        if(!colNr.equals("")) {
	        	lineComplete = false;
	        }
    	}
    	if(lineComplete) {
    		if(!firstRowReward) {
    			reward = jogador.getAposta() * 2;
    			Controller.changeMessage(logger, String.format("Completou uma linha, ganhou %.2f€", reward));
    			firstRowReward = true;
    		}
        	for(int col=1; col<10; col++) {
    	        ((TextField)getItemByIndex(rowIndex, col, grid)).setStyle(
          			  "-fx-background-color:"
	          			  + "linear-gradient("
	          			  + "from 25% 25% to 100% 100%,"
	          			  + "#5FFFFF,"
	          			  + "#8154F7);");
	        }
        	checkLinesProgress(grid, logger, rowIndex, primaryStage);     	
    	}
    }
    
	public static void checkLinesProgress(GridPane grid, Label logger, int rowIndex, Stage primaryStage) {
    	switch (rowIndex) {
        case 1:
        	firstRowComplete = true;
        	break;
        case 2:
        	secondRowComplete = true;
        	break;
        case 3:
        	thirdRowComplete = true;
        	break;
    	}
    	if(firstRowComplete && secondRowComplete && thirdRowComplete) { 
    		reward = jogador.getAposta() * 10;
    		Controller.changeMessage(logger, "Parabéns pela vitória no jogo anterior");
    		for(int row=1; row<4; row++) {
            	for(int col=1; col<10; col++) {
        	        ((TextField)getItemByIndex(row, col, grid)).setVisible(false);
    	        }
    		}
    		Scene scene = showBottomBarAfterClick(primaryStage, grid);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
            Alerts.showAlert("Ganhou!", "Parabéns ganhou o jogo de loto!", String.format("Por favor diriga-se ao monitor para receber %.2f", reward), AlertType.CONFIRMATION);
    	}
	};
}
