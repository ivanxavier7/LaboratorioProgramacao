package gui.components;

import java.math.BigInteger;
import java.util.Random;

import gui.util.Controller;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.entities.Jogador;

/**
 *  Jogo do Loto, foi um projeto desenvolvido no ambito da Unidade curricular
 *  em Laboratórios de Programação.
 *  
 *  Consiste numa simulacao do jogo de loto onde existe um monitor e vários jogadores.
 *  Este projeto representa o monitor.
 *  
 *   @author      Ivan Xavier
 *   @version     1.0
 *   @since       1.0
 */
public class Bars {
	static BigInteger id = new BigInteger(256, new Random());
    private static Label title = Controller.getLabel("Jogador de Loto");
    private static Label idLabel = Controller.getLabel("ID:" + id);
    private static Label logger = new Label();
    private static Button cardConfirmButton = new Button("Confirmar Cartão");
    
    /**
     * 	Devolve a barra superior do jogo, permite organizar alguns objetos 
     *	num formato de LinearLayout.
     *	Consituida por um título e um monitor que interliga o JSON com o displayField.
     *   
     *   
     *   @param		displayField	Valor para ser Binded á Label que guarda os números 
     *   
     *   @return 	topBar	Barra superior da aplicação
     *     
     *   @author      Ivan Xavier
     *   @since       1.0
     */
    public static FlowPane getTopBar(TextArea displayField) {
        FlowPane topBar = new FlowPane();
        title.setId("title");
        topBar.setHgap(20);
        topBar.setVgap(20);
        topBar.getChildren().addAll( title, displayField);
        return topBar;
    }
    
    /**
     * 	Devolve a barra inferior do jogo, permite organizar alguns objetos 
     *	num formato de LinearLayout.
     *	Consituida por um título, um campo de edição para digitar a aposta,
     * 	um botão de confirmação e um botão para gerar um novo cartão e um 
     *  para confirmar o cartão.
     *   
     *   @param 	 grid			Grelha para guardar os botões
     *   @param 	 primaryStage	Container principal
     *   @param		 jogador		Entidade que gera o cartão e guarda a sua aposta
     *   
     *   @return bottomBar		Barra inferior da aplicação
     *   
     *   @see	Buttons#createButton(String)
     *   @see	Controller#getLabel(String)
     *   @see	Controller#createBetField()
     *   @see	Buttons#betButton(GridPane, Jogador, Label)
     *   @see	Buttons#generateCardButton(GridPane)
     *   @see	Buttons#originalButton(GridPane)
     *   @see	Buttons#confirmCardButton(Button, GridPane, Label, Stage, Jogador)
     *   
     *   @author      Ivan Xavier
     *   @since       1.0
     */
    public static FlowPane getBottomBar(GridPane grid, Stage primaryStage, Jogador jogador) {
        Button createButton = Buttons.createButton("Cartão Original");
        createButton.setId("resetbutton");
        createButton.setOnAction(event -> Grid.originalGrid(grid));
        FlowPane bottomBar = new FlowPane() ;
        bottomBar.setHgap(20);
        bottomBar.setVgap(20);
        bottomBar.getChildren().addAll(
        		Controller.getLabel("Aposta"),
        		Controller.createBetField(),
        		Buttons.betButton(grid, jogador, logger),
        		Buttons.generateCardButton(grid),
        		Buttons.originalButton(grid),
        		Buttons.confirmCardButton(cardConfirmButton, grid, logger, primaryStage, jogador, id),
        		logger,
        		idLabel    		
        		);
        return bottomBar;
    }
    
    /**
     * 	Devolve a barra inferior do jogo após a conclusão do mesmo, permite organizar
     *  alguns objetos num formato de LinearLayout.
     *	Consituida por dois botões, um para reiniciar que chama um alerta e um para sair.
     *   
     *   @param 	 grid			Grelha para guardar os botões
     *   @param 	 primaryStage	Container principal
     *   @param		 jogador		Entidade que gera o cartão e guarda a sua aposta
     *   
     *   @return bottomBar		Barra inferior da aplicação
     *   
     *   @see	Buttons#restartAfterClickButton(GridPane, Stage, Jogador)
     *   @see	Buttons#exitButton(GridPane)
     *   
     *   @author      Ivan Xavier
     *   @since       1.0
     */
    public static FlowPane getBottomBarAfterClick(GridPane grid, Stage primaryStage, Jogador jogador) {
        FlowPane bottomBar = new FlowPane() ;
        bottomBar.setHgap(20);
        bottomBar.setVgap(20);
        bottomBar.getChildren().addAll(
        		Buttons.restartAfterClickButton(grid, primaryStage, jogador),
        		Buttons.exitButton(),
        		idLabel
        		);
        return bottomBar;
    }
    
    /**
     *	Recebe uma barra apenas com o logger, remover os botões e manter os avisos.
     *
     *	 @param		logger		Objeto associado ao logger
     *
     *   @return	bottomBar	Barra sem botões e com o logger
     *      
     *   @author      Ivan Xavier
     *   @since       1.0
     */
    public static FlowPane getLogger(Label logger) {
        FlowPane bottomBar = new FlowPane();
        title.setId("title");	// CSS
        bottomBar.setHgap(20);
        bottomBar.setVgap(20);
        bottomBar.getChildren().addAll(logger);
        return bottomBar;
    }
}
