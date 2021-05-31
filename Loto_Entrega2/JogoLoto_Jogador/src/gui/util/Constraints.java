package gui.util;

import javafx.scene.control.TextField;

public class Constraints {
	
    /**
     * Altera um campo edit�vel para apenas aceitar n�meros racionais
     *
     *   @param		textField		Campo edit�vel dos jogadores
     *   
     *   @author      Ivan Xavier
     *   @since       1.0
     */
	public static void setTextFieldInteger(TextField textField) {
		textField.textProperty().addListener((obs, oldValue, newValue) -> {
			if (!newValue.matches("\\d*")) {
				textField.setText(oldValue);
			}
		});
	}

    /**
     * Altera um campo edit�vel para limitar a quantidade de n�meros que
     * o utilizador pode inserir.
     *
     *   @param		textField		Campo edit�vel dos jogadores
     *	 @param		max				Valor inteiro do comprimento m�ximo
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
     * Altera um campo edit�vel para apenas aceitar n�meros racionais e fracion�rios.
     *
     *   @param		textField		Campo edit�vel dos jogadores
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