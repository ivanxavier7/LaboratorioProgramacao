package gui.util;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.entities.Jogador;
import model.entities.MonitorLoto;

public class Grid {
    private static Jogador jogador = new Jogador();
    private static Buttons buttonsUtil = new Buttons();
    private static int[][] cartao = new int[3][9];
    private static int[][] cartaoAntigo = new int[3][9];
    private static boolean dragFlag = false;
    private static int clickCounter = 0;
    private static boolean firstRowComplete = false;
    private static boolean secondRowComplete = false;
    private static boolean thirdRowComplete = false;
    
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
    	grid.getChildren().clear();;
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
		Boolean valid = true;
		Controller.changeMessage(logger,"");
		int whiteSpaces = 0;
    	for (Node node : grid.getChildren()) {
		    Integer colIndex = GridPane.getColumnIndex(node);
		    Integer rowIndex = GridPane.getRowIndex(node);
    	    if (node instanceof TextField) {
    	    	// Count White Spaces
    	    	if(!((TextField)node).getText().equals("")) {
	    	    	int textFieldNr = Integer.parseInt(((TextField)node).getText());
	    	    	// Column Validation
	    	    	valid = columnValidation(textFieldNr, colIndex, rowIndex, logger);
	    	    } else {
	    	    	whiteSpaces ++;
	    	    }
    	    }
    	}
    	if(whiteSpaces - 12 > 0) {
    		Controller.changeMessage(logger, String.format("Existe(m) %d espaço(s) em branco a menos", whiteSpaces - 12));
    		valid = false;
    	} else if(whiteSpaces - 12 < 0) {
    		Controller.changeMessage(logger, String.format("Existe(m) %d espaço(s) em branco a mais", 12 - whiteSpaces));
    		valid = false;
    	}
	    // Repeated Validation
	    valid = repeatedValidation(grid, logger);
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
    
    public static Boolean repeatedValidation(GridPane grid, Label logger) {
    	Boolean validNr = true;
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
    
    private static Boolean columnValidation(int textFieldNr, int colIndex, int rowIndex, Label logger) {
    	Boolean validNr = true;
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
    // Blocks Click Spam
    public static void lockGrid(GridPane grid, Label logger) {
    	if(validateGrid(grid, logger)) {
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
        	                            System.out.println(++clickCounter + " " + e.getClickCount());
        	                            if (e.getClickCount() == 1) {
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
        	                                checkProgress(grid, logger, rowIndex);
        	                                
        	                                
        	                            } else if (e.getClickCount() > 1) {
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
        	                                checkProgress(grid, logger, rowIndex);
        	                            }
        	                        }
        	                        dragFlag = false;
        	                    }
        	                }
        	    	});
        	    }
        	}
        	Controller.changeMessage(logger, "Cartão confirmado com sucesso!");
    	}
    }
    
    private static void checkProgress(GridPane grid, Label logger, int rowIndex) {
    	Boolean lineComplete = true;
    	for(int col=1; col<10; col++) {
	        String colNr = ((TextField)getItemByIndex(rowIndex, col, grid)).getText();
	        if(!colNr.equals("")) {
	    		System.out.println("Não está completa");
	        	lineComplete = false;
	        }
    	}
    	if(lineComplete) {
    		Controller.changeMessage(logger, "Completou uma linha, ganhou <PREMIO> €");
        	for(int col=1; col<10; col++) {
    	        ((TextField)getItemByIndex(rowIndex, col, grid)).setStyle(
          			  "-fx-background-color:"
	          			  + "linear-gradient("
	          			  + "from 25% 25% to 100% 100%,"
	          			  + "#5FFFFF,"
	          			  + "#8154F7);");
	        }
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
        		Controller.changeMessage(logger, "Parabéns ganhou o jogo!");
        		for(int row=1; row<4; row++) {
                	for(int col=1; col<10; col++) {
            	        ((TextField)getItemByIndex(row, col, grid)).setVisible(false);
        	        }
        		}
        	}
    	}
    }
}
