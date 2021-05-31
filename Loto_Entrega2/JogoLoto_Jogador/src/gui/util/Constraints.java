package gui.util;

import javafx.scene.control.TextField;

public class Constraints {
	
    /**
     * Altera um campo editável para apenas aceitar números racionais
     *
     *   @param		textField		Campo editável dos jogadores
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
     * Altera um campo editável para limitar a quantidade de números que
     * o utilizador pode inserir.
     *
     *   @param		textField		Campo editável dos jogadores
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
     * Altera um campo editável para apenas aceitar números racionais e fracionários.
     *
     *   @param		textField		Campo editável dos jogadores
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