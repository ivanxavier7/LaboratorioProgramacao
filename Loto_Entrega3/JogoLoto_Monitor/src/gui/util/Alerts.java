package gui.util;

import gui.components.Grid;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
/**
 *  Classe responsável por configurar os alertas de sistema
 *  
 *   @author      Ivan Xavier
 *   @version     1.0
 *   @since       1.0
 */
public class Alerts {
	
    /**
     * Gera um alerta simples, pode usar diferentes tipos como informação, erro, aviso.
     *   
     *   @param		title		Título
     *   @param		header		Cabeçalho
     *   @param		content		Conteúdo do alerta
     *   @param		type		Tipo do alerta
     *   
     *   @return	alert		Devolve um alerta
     *   
     *   @author	Ivan Xavier
     *   @since		1.0
     */
	private static Alert getAlert(String title, String header, String content, AlertType type) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.setResizable(false);
		
		return alert;
	}
	
	
    /**
     * Mostra um alerta simples, pode usar diferentes tipos como informação, erro, aviso.
     *   
     *   @param		title		Título
     *   @param		header		Cabeçalho
     *   @param		content		Conteúdo do alerta
     *   @param		type		Tipo do alerta
     *   
     *   @see	#getAlert(String, String, String, AlertType)
     *   
     *   @author	Ivan Xavier
     *   @since		1.0
     */
	public static void showAlert(String title, String header, String content, AlertType type) {
		Alert alert = getAlert(title, header, content, type);
		alert.showAndWait();
	}
	
}