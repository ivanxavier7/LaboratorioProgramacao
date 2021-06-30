package gui.util;

import gui.components.Grid;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.entities.Jogador;
/**
 *  Classe respons�vel por configurar os alertas de sistema
 *  
 *   @author      Ivan Xavier
 *   @version     1.0
 *   @since       1.0
 */
public class Alerts {
	
    /**
     * Gera um alerta simples, pode usar diferentes tipos como informa��o, erro, aviso.
     *   
     *   @param		title		T�tulo
     *   @param		header		Cabe�alho
     *   @param		content		Conte�do do alerta
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
     * Mostra um alerta simples, pode usar diferentes tipos como informa��o, erro, aviso.
     *   
     *   @param		title		T�tulo
     *   @param		header		Cabe�alho
     *   @param		content		Conte�do do alerta
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
	
    /**
     * Alerta ap�s pedido para reiniciar o sistema, quando o utilizador ou o monitor acabam o jogo,
     * aparece um novo menu no final, com a op��o para reiniciar ou sair.
     * Quando o utilizador pede para reiniciar, este alerta � chamado com a op��o de usar o cart�o original
     * ou um cart�o novo.
     *   
     *   @param		grid			Grelha para guardar os bot�es
     *   @param		primaryStage	Container para organizar objetos da interface
     *   @param		jogador			Jogador, respons�vel por gerar o cart�o e guardar a sua aposta
     *   
     *   @return	alert		Devolve um alerta
     *   
     *   @see	Controller#showBottomBar(Stage, GridPane, Jogador)
     *   @see	Grid#originalGrid(GridPane)
     *   @see	Grid#updateGrid(GridPane)
     *   
     *   @author	Ivan Xavier
     *   @since		1.0
     */
	public static void showReset(GridPane grid, Stage primaryStage, Jogador jogador) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Reiniciar Jogo");
		alert.setContentText("Por favor escolha o cart�o com qual deseja reiniciar o jogo");
		ButtonType okButton = new ButtonType("Cart�o Original", ButtonBar.ButtonData.YES);
		ButtonType noButton = new ButtonType("Novo Cart�o", ButtonBar.ButtonData.NO);
		alert.getButtonTypes().setAll(okButton, noButton);
		alert.showAndWait().ifPresent(type -> {
				if (type == okButton) {
		    		Scene scene = Controller.showBottomBar(primaryStage, grid, jogador);
		            primaryStage.setScene(scene);
		            primaryStage.setResizable(false);
		            primaryStage.show();
					Grid.originalGrid(grid);
	
				} else if (type == noButton) {
		    		Scene scene = Controller.showBottomBar(primaryStage, grid, jogador);
		            primaryStage.setScene(scene);
		            primaryStage.setResizable(false);
		            primaryStage.show();
					Grid.updateGrid(grid);
				} 
			});
	}
}