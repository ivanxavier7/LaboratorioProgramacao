package gui.components;

import application.Jogo;
import gui.util.Controller;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.entities.MonitorLoto;


/**
 *  Classe responsavel pelas barra superior e inferior da interface
 *  
 *   @author      Ivan Xavier
 *   @version     1.0
 *   @since       1.0
 */
public class Bars {
    /**
     * 	Devolve a barra superior do jogo, permite organizar alguns objetos 
     *	num formato de LinearLayout.
     *	Consituida por um titulo e um monitor que interliga o value com o 
     *	value, para atualizar os valores escolhidos no monitor.
     *   
     *   
     *   @param		value		Valor para ser Binded a Label que guarda os numeros 
     *   @return 	topBar		Barra superior da aplicacao
     *   
     *   @see	Controller#getLabel(String)
     *   @see	Controller#createDisplayField(StringProperty)
     *   
     *   @author      Ivan Xavier
     *   @since       1.0
     */
	public static FlowPane getTopBar(StringProperty value) {
	    Label title = Controller.getLabel("Monitor de Loto");
        FlowPane topBar = new FlowPane();
        title.setId("title");
        topBar.setHgap(20);
        topBar.setVgap(20);
        topBar.getChildren().addAll( title, Controller.createDisplayField(value));
        return topBar;
	}
	
    /**
     * 	Devolve a barra inferior do jogo, permite organizar alguns objetos 
     *	num formato de LinearLayout.
     *	Consituida por um titulo do numero de jogadores, um campo de edicao para digitar o numero de jogadores,
     * 	um botao de confirmacao e um botao para reiniciar o jogo.
     *   
     *   @param 	 grid			Grelha para guardar os botões
     *   @param 	 primaryStage	Container principal
     *   @param		 value			Valor para ser Binded á Label que guarda os numeros 
     *   @param		 monitor		Entidade que lida com a data associada ao Monitor dos numeros, na interface
     *   
     *   @return bottomBar		Barra inferior da aplicacao
     *   
     *   @see	Controller#createDisplayField(StringProperty)
     *   @see	Controller#getLabel(String)
     *   @see	Buttons#playerButton(TextField, MonitorLoto)
     *   @see	Buttons#restartButton(Stage, StringProperty, MonitorLoto)
     *   @see	Jogo#getLogger()
     *   
     *   @author      Ivan Xavier
     *   @since       1.0
     */
	public static FlowPane getBottomBar(GridPane grid, Stage primaryStage, StringProperty value, MonitorLoto monitor) {
		FlowPane bottomBar = new FlowPane() ;
        TextField textField = Controller.createPlayersField();
        bottomBar.setHgap(20);
        bottomBar.setVgap(20);
        bottomBar.getChildren().addAll(
        		Controller.getLabel("Número de Jogadores"),
        		textField,
        		Buttons.playerButton(textField, monitor),
        		Buttons.restartButton(primaryStage, value, monitor),
        		Jogo.getLogger()
        		);
        
        return bottomBar;
        
	}
}
