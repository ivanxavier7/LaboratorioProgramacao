package gui.util;

import java.util.Arrays;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.entities.Jogador;

public class Grid {
    private static Jogador jogador = new Jogador();
    private static Buttons buttonsUtil = new Buttons();
    private static int[][] cartao = new int[3][9];
    private static int[][] cartaoAntigo = new int[3][9];
    
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
    	    	if(!((TextField)node).getText().equals("")) {
	    	    	int textFieldNr = Integer.parseInt(((TextField)node).getText());
	    	    	System.out.println("error " +  textFieldNr);
	    	        switch (colIndex) {
	    	        case 1:
	    	        	if(textFieldNr < 1 || textFieldNr > 9) {
	    	        		Controller.changeMessage(logger, String.format("O valor: %d na %d� coluna e na %d� linha � inv�lido!", textFieldNr, colIndex, rowIndex));
	    	        		valid = false;
	    	        	}
	    	          break;
	    	        case 2:
	    	        	if(textFieldNr < 10 || textFieldNr > 19) {
	    	        		Controller.changeMessage(logger, String.format("O valor: %d na %d� coluna e na %d� linha � inv�lido!", textFieldNr, colIndex, rowIndex));
	    	        		valid = false;
	    	        	}
	    	          break;
	    	        case 3:
	    	        	if(textFieldNr < 20 || textFieldNr > 29) {
	    	        		Controller.changeMessage(logger, String.format("O valor: %d na %d� coluna e na %d� linha � inv�lido!", textFieldNr, colIndex, rowIndex));
	    	        		valid = false;
	    	        	}
	    	          break;
	    	        case 4:
	    	        	if(textFieldNr < 30 || textFieldNr > 39) {
	    	        		Controller.changeMessage(logger, String.format("O valor: %d na %d� coluna e na %d� linha � inv�lido!", textFieldNr, colIndex, rowIndex));
	    	        		valid = false;
	    	        	}
	    	          break;
	    	        case 5:
	    	        	if(textFieldNr < 40 || textFieldNr > 49) {
	    	        		Controller.changeMessage(logger, String.format("O valor: %d na %d� coluna e na %d� linha � inv�lido!", textFieldNr, colIndex, rowIndex));
	    	        		valid = false;
	    	        	}
	    	          break;
	    	        case 6:
	    	        	if(textFieldNr < 50 || textFieldNr > 59) {
	    	        		Controller.changeMessage(logger, String.format("O valor: %d na %d� coluna e na %d� linha � inv�lido!", textFieldNr, colIndex, rowIndex));
	    	        		valid = false;
	    	        	}
	    	          break;
	    	        case 7:
	    	        	if(textFieldNr < 60 || textFieldNr > 69) {
	    	        		Controller.changeMessage(logger, String.format("O valor: %d na %d� coluna e na %d� linha � inv�lido!", textFieldNr, colIndex, rowIndex));
	    	        		valid = false;
	    	        	}
	    	          break;
	    	        case 8:
	    	        	if(textFieldNr < 70 || textFieldNr > 79) {
	    	        		Controller.changeMessage(logger, String.format("O valor: %d na %d� coluna e na %d� linha � inv�lido!", textFieldNr, colIndex, rowIndex));
	    	        		valid = false;
	    	        	}
	    	          break;
	    	        case 9:
	    	        	if(textFieldNr < 80 || textFieldNr > 90) {
	    	        		Controller.changeMessage(logger, String.format("O valor: %d na %d� coluna e na %d� linha � inv�lido!", textFieldNr, colIndex, rowIndex));
	    	        		valid = false;
	    	        	}
	    	          break;
	    	        }
	    	    } else {
	    	    	whiteSpaces ++;
	    	    }
    	    }
    	}
    	if(whiteSpaces - 12 > 0) {
    		Controller.changeMessage(logger, String.format("Existe(m) %d espa�o(s) em branco a menos", whiteSpaces - 12));
    		valid = false;
    	} else if(whiteSpaces - 12 < 0) {
    		Controller.changeMessage(logger, String.format("Existe(m) %d espa�o(s) em branco a mais", 12 - whiteSpaces));
    		valid = false;
    	}
    	return valid;
    }
    
    private void writeGrid(GridPane grid) {
    	System.out.println("\nCart�o recebido:" + jogador.getStringCartao(cartao));
    	for (Node node : grid.getChildren()) {
    	    System.out.println("Id: " + node.getId());
    	    if (node instanceof TextField) {
    	        // clear
    	    	((TextField)node).setText("TESTE");
    	    }
    	}
    }
    
    public static void lockGrid(GridPane grid, Label logger) {
    	if(validateGrid(grid, logger)) {
        	for (Node node : grid.getChildren()) {
        	    if (node instanceof TextField) {
        	    	((TextField)node).setEditable(false);;
        	    }
        	}
        	Controller.changeMessage(logger, "Cart�o confirmado com sucesso!");
    	}

    }
}
