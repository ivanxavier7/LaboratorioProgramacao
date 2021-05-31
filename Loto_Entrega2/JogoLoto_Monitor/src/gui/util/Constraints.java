package gui.util;

import javafx.scene.control.TextField;

/**
 *	Classe responsavel por limitar os valores que o utilizador pode introduzir
 *  
 *   @author      Ivan Xavier
 *   @version     1.0
 *   @since       1.0
 */
public class Constraints {
	
    /**
     * Altera um campo editavel para apenas aceitar números racionais
     *
     *   @param		textField		Campo editável dos jogadores
     *   
     *   @author      Ivan Xavier
     *   @since       1.0
     */
	public static void setTextFieldInteger(TextField textField) {
		textField.textProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null && !newValue.matches("\\d*")) {
				textField.setText(oldValue);
			}
		});
	}	
	
    /**
     * Altera um campo editavel para limitar a quantidade de numeros que
     * o utilizador pode inserir.
     *
     *   @param		textField		Campo editavel dos jogadores
     *	 @param		max				Valor inteiro do comprimento máximo
     *   
     *   @author      Ivan Xavier
     *   @since       1.0
     */
	public static void setTextFieldMaxLength(TextField textField, int max) {
		textField.textProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null && newValue.length() > max) {
				textField.setText(oldValue);
			}
		});
	}

    /**
     * Altera um campo editavel para apenas aceitar numeros racionais e fracionarios.
     *
     *   @param		textField		Campo editavel dos jogadores
     *   
     *   @author      Ivan Xavier
     *   @since       1.0
     */
	public static void setTextFieldDouble(TextField textField) {
		textField.textProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null && !newValue.matches("\\d*([\\.]\\d*)?")) {
				textField.setText(oldValue);
			}
		});
	}
}