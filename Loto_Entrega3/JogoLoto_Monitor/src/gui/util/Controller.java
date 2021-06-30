package gui.util;

import java.math.BigInteger;
import java.util.Arrays;

import application.Jogo;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.entities.MonitorLoto;

/**
 *	Classe responsavel pelo controlo da interface e do jogo.
 *  Foram implementados alguns objetos secundarios nesta zona,
 *  como a criacao dos campos editaveis e titulos.
 *  
 *   @author      Ivan Xavier
 *   @version     1.0
 *   @since       1.0
 */
public class Controller {
	static int whiteSpacesLine1 = 0;
	static int whiteSpacesLine2 = 0;
	static int whiteSpacesLine3 = 0;

    /**
     *	Controlador responsavel por reiniciar o jogo
     *
     *   @param		primaryStage	Container para organizar objetos da interface
     *   @param		value			StringProperty associado ao monitor dos numeros.	
     *   @param		monitor			Monitor para sincronizar os dados escolhidos com o JSON
     *   
     *   @see	Controller#changeMessage(String)
     *   @see	Jogo#start(Stage)
     *   
     *   @author      Ivan Xavier
     *   @since       1.0
     */
    public static void restartGame(Stage primaryStage, StringProperty value, MonitorLoto monitor) {
    	Jogo jogo = new Jogo();
    	value.set("");
    	monitor.reset();
    	primaryStage.close();
        Platform.runLater( () -> jogo.start(new Stage()));
    }
    
    /**
     *	Envia a mensagem para o loger e reconfigura o tamanho do mesmo,
     *	para evitar ocupar espaço desnecessario.
     *
     *   @param		string		Texto a enviar para o logger
     *   
     *   @author      Ivan Xavier
     *   @since       1.0
     */
    public static BorderPane createRoot(FlowPane topBar, GridPane grid, FlowPane bottomBar) {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        root.setTop(topBar);
        root.setCenter(grid);  
        root.setBottom(bottomBar);
        return root;
    }
    
    /**
     *	Envia a mensagem para o loger e reconfigura o tamanho do mesmo,
     *	para evitar ocupar espaco desnecessario.
     *
     *   @param		string		Texto a enviar para o logger
     *   	
     *   @see	Jogo#getLogger()
     *   
     *   @author      Ivan Xavier
     *   @since       1.0
     */
    public static void changeMessage(String string) {
        Jogo.getLogger().setMaxHeight(5);
        Jogo.getLogger().setText(string);
        Jogo.getLogger().autosize();
    }
    
    /**
     * Valida o cartão escolhido/editado pelo utilizador.
     * A validação é feita a percorrer todos os elementos ddo cartao,
     * quando contém algum valor, é enviado para a validação de colunas,
     * caso seja um botão em branco, encaminha para a contagem de espaços por linha.
     * 
     * 
     * 	 @param 	cartao[][]		Grelha na interface que contém os botões
     *	 @param		logger		Campo de texto na interface para mostrar mensagens
     *  
     *   @return	valid		Booleano que traduz se o cartão é válido ou não
     *   
     *   @author	Ivan Xavier
     *   @since		1.0
     */
    public static Boolean validateCard(int[][] cartao) {
		int Line1 = 0;
		int Line2 = 0;
		int Line3 = 0;
		Boolean valid = true;
        for (int lin = 0; lin<3; lin++) {
            for (int col = 0; col < 9; col++) {
	    	// Count White Spaces
            if(cartao[lin][col] != 0) {
    	    	int textFieldNr = cartao[lin][col];
    	    	// Column Validation
    	    	valid = columnValidation(textFieldNr, col, lin, valid);
    	    } else {
    	    	// White Space catch
    	    	switch(lin) {
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
        }if(whiteSpacesLine1 != 9 && whiteSpacesLine2 != 9 && whiteSpacesLine3 != 9) {
	    	if(whiteSpacesLine1 > 4) {
	    		Alerts.showAlert("Espaços em Branco!", "Erro na linha!", String.format("Existe(m) %d espaço(s) em branco a mais na 1ª Linha", whiteSpacesLine1 - 4), AlertType.ERROR);
	    		valid = false;
	    	} else if(whiteSpacesLine1 < 4) {
	    		Alerts.showAlert("Espaços em Branco!", "Erro na linha!", String.format("Existe(m) %d espaço(s) em branco a menos na 1ª Linha", whiteSpacesLine1 - 4), AlertType.ERROR);
	    		valid = false;
	    	} else if(whiteSpacesLine2 > 4) {
	    		Alerts.showAlert("Espaços em Branco!", "Erro na linha!", String.format("Existe(m) %d espaço(s) em branco a mais na 2ª Linha", whiteSpacesLine1 - 4), AlertType.ERROR);
	    		valid = false;
	    	} else if(whiteSpacesLine2 < 4) {
	    		Alerts.showAlert("Espaços em Branco!", "Erro na linha!", String.format("Existe(m) %d espaço(s) em branco a menos na 2ª Linha", whiteSpacesLine1 - 4), AlertType.ERROR);
	    		valid = false;
	    	} else if(whiteSpacesLine3 > 4) {
	    		Alerts.showAlert("Espaços em Branco!", "Erro na linha!", String.format("Existe(m) %d espaço(s) em branco a mais na 3ª Linha", whiteSpacesLine1 - 4), AlertType.ERROR);
	    		valid = false;
	    	} else if(whiteSpacesLine3 < 4) {
	    		Alerts.showAlert("Espaços em Branco!", "Erro na linha!", String.format("Existe(m) %d espaço(s) em branco a menos na 3ª Linha", whiteSpacesLine1 - 4), AlertType.ERROR);
	    		valid = false;
	    	}
        }
	    // Repeated Validation
	    valid = repeatedValidation(cartao, valid);
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
        public static Boolean repeatedValidation(int[][] cartao, Boolean validNr) {
        	for (int col=0; col<9; col++) {
    	        String rowNr1 = String.valueOf(cartao[0][col]);
    	        String rowNr2 = String.valueOf(cartao[0][col]);
    	        String rowNr3 = String.valueOf(cartao[0][col]);
    	        if(!rowNr1.equals("0") && rowNr1.equals(rowNr2) || !rowNr1.equals("0") && rowNr1.equals(rowNr3)) {
    	        	Alerts.showAlert("Espaços em Branco!", "Erro na linha!", String.format("O número %s está repetido na %d coluna", rowNr1, col), AlertType.ERROR);
    	        	validNr = false;
    	        } else if(!rowNr2.equals("0") && rowNr2.equals(rowNr3)) {
    	        	Alerts.showAlert("Espaços em Branco!", "Erro na linha!", String.format("O número %s está repetido na %d coluna", rowNr2, col), AlertType.ERROR);
    	        	validNr = false;
    	        }
        	}
        	return validNr;
        }    
        
    /**
     * Verifica se um dado campo se encontra na coluna respetiva.
     * 
     * 
     * 	 @param 	textFieldNr		Botão a validar
     *	 @param		colIndex		Número da coluna
     *	 @param		rowIndex		Número da linha
     *	 @param		logger			Campo de texto na interface para mostrar mensagens		
     *  
     *   @return	validNr		Booleano que traduz se a edição é válida
     *   
     *   @see	Controller#changeMessage(Label, String)
     *   
     *   @author	Ivan Xavier
     *   @since		1.0
     */
    public static Boolean columnValidation(int textFieldNr, int colIndex, int rowIndex, Boolean validNr) {
        switch (textFieldNr) {
        case 0:
        	break;
        case 1:
        	if(textFieldNr < 1 || textFieldNr > 9) {
        		Alerts.showAlert("Erro na coluna!", "Erro na coluna!", String.format("O valor: %d na %dª coluna e na %dª linha é inválido!", textFieldNr, colIndex, rowIndex), AlertType.ERROR);
        		validNr = false;
        	}
          break;
        case 2:
        	if(textFieldNr < 10 || textFieldNr > 19) {
        		Alerts.showAlert("Erro na coluna!", "Erro na coluna!", String.format("O valor: %d na %dª coluna e na %dª linha é inválido!", textFieldNr, colIndex, rowIndex), AlertType.ERROR);
        		validNr = false;
        	}
          break;
        case 3:
        	if(textFieldNr < 20 || textFieldNr > 29) {
        		Alerts.showAlert("Erro na coluna!", "Erro na coluna!", String.format("O valor: %d na %dª coluna e na %dª linha é inválido!", textFieldNr, colIndex, rowIndex), AlertType.ERROR);
        		validNr = false;
        	}
          break;
        case 4:
        	if(textFieldNr < 30 || textFieldNr > 39) {
        		Alerts.showAlert("Erro na coluna!", "Erro na coluna!", String.format("O valor: %d na %dª coluna e na %dª linha é inválido!", textFieldNr, colIndex, rowIndex), AlertType.ERROR);
        		validNr = false;
        	}
          break;
        case 5:
        	if(textFieldNr < 40 || textFieldNr > 49) {
        		Alerts.showAlert("Erro na coluna!", "Erro na coluna!", String.format("O valor: %d na %dª coluna e na %dª linha é inválido!", textFieldNr, colIndex, rowIndex), AlertType.ERROR);
        		validNr = false;
        	}
          break;
        case 6:
        	if(textFieldNr < 50 || textFieldNr > 59) {
        		Alerts.showAlert("Erro na coluna!", "Erro na coluna!", String.format("O valor: %d na %dª coluna e na %dª linha é inválido!", textFieldNr, colIndex, rowIndex), AlertType.ERROR);
        		validNr = false;
        	}
          break;
        case 7:
        	if(textFieldNr < 60 || textFieldNr > 69) {
        		Alerts.showAlert("Erro na coluna!", "Erro na coluna!", String.format("O valor: %d na %dª coluna e na %dª linha é inválido!", textFieldNr, colIndex, rowIndex), AlertType.ERROR);
        		validNr = false;
        	}
          break;
        case 8:
        	if(textFieldNr < 70 || textFieldNr > 79) {
        		Alerts.showAlert("Erro na coluna!", "Erro na coluna!", String.format("O valor: %d na %dª coluna e na %dª linha é inválido!", textFieldNr, colIndex, rowIndex), AlertType.ERROR);
        		validNr = false;
        	}
          break;
        case 9:
        	if(textFieldNr < 80 || textFieldNr > 90) {
        		Alerts.showAlert("Erro na coluna!", "Erro na coluna!", String.format("O valor: %d na %dª coluna e na %dª linha é inválido!", textFieldNr, colIndex, rowIndex), AlertType.ERROR);
        		validNr = false;
        	}
          break;
        }
        return validNr;
    }
    
    /**
     *	Recebe um titulo generico.
     *
     *	 @param		string		Texto a enviar para o logger
     *
     *   @return	label		Campo de texto não editavel
     *      
     *   @author      Ivan Xavier
     *   @since       1.0
     */
	public static Label getLabel(String string) {
        Label label = new Label();
        label.setText(string);
        label.setMaxWidth(Double.POSITIVE_INFINITY);
        label.setMaxHeight(Double.POSITIVE_INFINITY);
        return label;
    }
    
    /**
     *	Recebe os valores clicados pelo monitor
     *
     *	 @param		value			StringProperty associado ao monitor dos numeros.
     *
     *   @return	displayField	Monitor para receber os numeros, permite a expansao da mesma.
     *      
     *   @author      Ivan Xavier
     *   @since       1.0
     */
    public static TextArea createDisplayField(StringProperty value) {
        TextArea displayField = new TextArea();
        displayField.textProperty().bind(Bindings.format("%s", value));
        displayField.setEditable(false);
        displayField.setMaxHeight(5);
        displayField.setMinWidth(586);
        displayField.autosize();
        return displayField;
    }
    
    /**
     *	Recebe as informações acerca dos utilizadores
     *
     *	 @param		value			StringProperty associado ao monitor dos numeros.
     *
     *   @return	displayField	Monitor para receber as informções dos utilizadores.
     *      
     *   @author      Ivan Xavier
     *   @since       1.0
     */
    public static TextArea createUserField(StringProperty userInfoValue) {
        TextArea displayField = new TextArea();
        displayField.textProperty().bind(Bindings.format("%s", userInfoValue));
        displayField.setEditable(false);
        displayField.setMinHeight(5);
        displayField.setMinWidth(586);
        displayField.autosize();
        return displayField;
    }
    
    /**
     *	Recebe o campo editavel para introduzir o numero de jogadores
     *
     *
     *   @return	textField	Campo de texto para o numero de jogadores
     *   
     *   @see	Constraints#setTextFieldInteger(TextField)
     *   @see	Constraints#setTextFieldMaxLength(TextField, int)
     *      
     *   @author      Ivan Xavier
     *   @since       1.0
     */
    public static TextField createPlayersField() {
    	TextField textField = new TextField();
        textField.setEditable(true);
        textField.setMaxHeight(5);
        textField.autosize();
        Constraints.setTextFieldInteger(textField);
        Constraints.setTextFieldMaxLength(textField, 2);
        return textField;
    }
}
