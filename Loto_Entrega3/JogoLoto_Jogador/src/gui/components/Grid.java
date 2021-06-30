package gui.components;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import gui.util.Constraints;
import gui.util.Controller;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.entities.Jogador;
import model.entities.MonitorLoto;

public class Grid {
    private static Jogador jogador = new Jogador();
    private static Buttons buttonsUtil = new Buttons();
    private static int[][] cartao = new int[3][9];
    private static int[][] cartaoAntigo = new int[3][9];

	static int whiteSpacesLine1 = 0;
	static int whiteSpacesLine2 = 0;
	static int whiteSpacesLine3 = 0;
    
    /**
     * Configura a grelha e carrega os bot�es na mesma.
     *  
     *   @return	grid	Grelha na interface que cont�m os bot�es
     *   
     *   @see Jogador#getClone(int[][], int[][])
     *   
     *   @author	Ivan Xavier
     *   @since		1.0
     */
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
    
    /**
     * Altera o cart�o carregado na grelha para um novo cart�o.
     * 
     * 	 @param 	grid	Grelha na interface que cont�m os bot�es
     *  
     *   @return	grid
     *   
     *   @see	Jogador#generateCartao()
     *   @see	Buttons#createNumberTextField(String)
     *   
     *   @author	Ivan Xavier
     *   @since		1.0
     */
    public static GridPane updateGrid(GridPane grid) {
    	cartao = jogador.generateCartao();
    	grid.getChildren().clear();
        for (int col=1; col<=9; col++) {
        	for (int row=1; row<=3; row++) {
        		if(cartao[row-1][col-1]!=99) {
	        		TextField textField = buttonsUtil.createNumberTextField(String.valueOf(cartao[row-1][col-1]));
	        		grid.add(textField, col, row);
        		}
        		else {
        			TextField textField = buttonsUtil.createNumberTextField("");
            		grid.add(textField, col, row);
        		}
        	}
        }
        return grid;
    }
    
    
    /**
     * Altera o cart�o carregado na grelha para o cart�o original.
     * 
     * 	 @param 	grid	Grelha na interface que cont�m os bot�es
     *  
     *   @return	grid
     *   
     *   @see	Jogador#getClone(int[][], int[][])
     *   @see	Buttons#createNumberTextField(String)
     *   @see	Constraints#setTextFieldInteger(TextField)
     *   @see	Constraints#setTextFieldMaxLength(TextField, int)
     *   
     *   @author	Ivan Xavier
     *   @since		1.0
     */
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
    
    /**
     * Valida o cart�o escolhido/editado pelo utilizador.
     * A valida��o � feita a percorrer todos os elementos da grelha,
     * quando cont�m algum valor, � enviado para a valida��o de colunas,
     * caso seja um bot�o em branco, encaminha para a contagem de espa�os por linha.
     * 
     * 
     * 	 @param 	grid		Grelha na interface que cont�m os bot�es
     *	 @param		logger		Campo de texto na interface para mostrar mensagens
     *  
     *   @return	valid		Booleano que traduz se o cart�o � v�lido ou n�o
     *   
     *   @author	Ivan Xavier
     *   @since		1.0
     */
    public static Boolean validateGrid(GridPane grid, Label logger, BigInteger id) {
		int Line1 = 0;
		int Line2 = 0;
		int Line3 = 0;
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
	    	    	valid = Controller.columnValidation(textFieldNr, colIndex, rowIndex, logger, valid);
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
    		System.out.println("Espa�os em branco " + whiteSpacesLine1);
    		Controller.changeMessage(logger, String.format("Existe(m) %d espa�o(s) em branco a mais na 1� Linha", whiteSpacesLine1 - 4));
    		valid = false;
    	} else if(whiteSpacesLine1 < 4) {
    		Controller.changeMessage(logger, String.format("Existe(m) %d espa�o(s) em branco a menos na 1� Linha", whiteSpacesLine1 - 4));
    		valid = false;
    	} else if(whiteSpacesLine2 > 4) {
    		Controller.changeMessage(logger, String.format("Existe(m) %d espa�o(s) em branco a mais na 2� Linha", whiteSpacesLine2 - 4));
    		valid = false;
    	} else if(whiteSpacesLine2 < 4) {
    		Controller.changeMessage(logger, String.format("Existe(m) %d espa�o(s) em branco a menos na 2� Linha", whiteSpacesLine2 - 4));
    		valid = false;
    	} else if(whiteSpacesLine3 > 4) {
    		Controller.changeMessage(logger, String.format("Existe(m) %d espa�o(s) em branco a mais na 3� Linha", whiteSpacesLine3 - 4));
    		valid = false;
    	} else if(whiteSpacesLine3 < 4) {
    		Controller.changeMessage(logger, String.format("Existe(m) %d espa�o(s) em branco a menos na 3� Linha", whiteSpacesLine3 - 4));

    		valid = false;
    	} 
	    // Repeated Validation
	    valid = repeatedValidation(grid, logger, valid);
	    if(valid) {
		    // Writing in JSON file to sent do monitor
	    	
		    MonitorLoto.writeInfoJSON(MonitorLoto.readJSONInfo() 
		    		+ ", " + Arrays.deepToString(cartao).replace("99", "0")
		    		+ ", " + id
		    		+ ";");
		    
	    }
    	return valid;
    }
    
    
    /**
     * Verifica se existem n�meros repetidos na coluna onde o n�mero foi inserido.
     * 
     * 
     * 	 @param 	grid		Grelha na interface que cont�m os bot�es
     *	 @param		logger		Campo de texto na interface para mostrar mensagens
     *	 @param		validNr		Booleano que passa por v�rias valida��es
     *  
     *   @return	validNr		Booleano que traduz se a edi��o � v�lida
     *   
     *   @author	Ivan Xavier
     *   @since		1.0
     */
    public static Boolean repeatedValidation(GridPane grid, Label logger, Boolean validNr) {
    	for (Node node : grid.getChildren()) {
		    Integer colIndex = GridPane.getColumnIndex(node);
	        String rowNr1 = ((TextField)getItemByIndex(1, colIndex, grid)).getText();
	        String rowNr2 = ((TextField)getItemByIndex(2, colIndex, grid)).getText();
	        String rowNr3 = ((TextField)getItemByIndex(3, colIndex, grid)).getText();
    	    if (node instanceof TextField) {
    	        if(!rowNr1.equals("") && rowNr1.equals(rowNr2) || !rowNr1.equals("") && rowNr1.equals(rowNr3)) {
    	        	Controller.changeMessage(logger, String.format("O n�mero %s est� repetido na %d coluna", rowNr1, colIndex));
    	        	validNr = false;
    	        } else if(!rowNr2.equals("") && rowNr2.equals(rowNr3)) {
    	        	Controller.changeMessage(logger, String.format("O n�mero %s est� repetido na %d coluna", rowNr2, colIndex));
    	        	validNr = false;
    	        }
    	    }
    	}
    	return validNr;
    }
    
    /**
     * Devolve o Node associado a um bot�o em especifico, pelas suas coordenadas
     * 
     *	 @param		col			N�mero da coluna
     *	 @param		row			N�mero da linha
     *	 @param 	grid		Grelha na interface que cont�m os bot�es
     *  
     *   @return	result		Node da grelha especificado pelas coordenadas
     *   
     *   @author	Ivan Xavier
     *   @since		1.0
     */
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
}
