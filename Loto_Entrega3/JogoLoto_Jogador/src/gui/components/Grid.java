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
     * Configura a grelha e carrega os botões na mesma.
     *  
     *   @return	grid	Grelha na interface que contém os botões
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
     * Altera o cartão carregado na grelha para um novo cartão.
     * 
     * 	 @param 	grid	Grelha na interface que contém os botões
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
     * Altera o cartão carregado na grelha para o cartão original.
     * 
     * 	 @param 	grid	Grelha na interface que contém os botões
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
     * Valida o cartão escolhido/editado pelo utilizador.
     * A validação é feita a percorrer todos os elementos da grelha,
     * quando contém algum valor, é enviado para a validação de colunas,
     * caso seja um botão em branco, encaminha para a contagem de espaços por linha.
     * 
     * 
     * 	 @param 	grid		Grelha na interface que contém os botões
     *	 @param		logger		Campo de texto na interface para mostrar mensagens
     *  
     *   @return	valid		Booleano que traduz se o cartão é válido ou não
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
     * Verifica se existem números repetidos na coluna onde o número foi inserido.
     * 
     * 
     * 	 @param 	grid		Grelha na interface que contém os botões
     *	 @param		logger		Campo de texto na interface para mostrar mensagens
     *	 @param		validNr		Booleano que passa por várias validações
     *  
     *   @return	validNr		Booleano que traduz se a edição é válida
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
    
    /**
     * Devolve o Node associado a um botão em especifico, pelas suas coordenadas
     * 
     *	 @param		col			Número da coluna
     *	 @param		row			Número da linha
     *	 @param 	grid		Grelha na interface que contém os botões
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
