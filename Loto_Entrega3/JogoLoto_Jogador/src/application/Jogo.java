package application;

import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.entities.Jogador;
import model.entities.MonitorLoto;

import java.util.concurrent.TimeUnit;

import gui.components.Bars;
import gui.components.Grid;
import gui.util.Controller;

/**
 *  Jogo do Loto, foi um projeto desenvolvido no ambito da Unidade curricular
 *  em Laboratorios de Programacao.
 *  
 *  Consiste numa simulacao do jogo de loto onde existe um monitor e varios jogadores.
 *  Este projeto representa o monitor.
 *  
 *   @author      Ivan Xavier
 *   @version     1.0
 *   @since       1.0
 */
public class Jogo extends Application{
    private GridPane grid;
    private Label logger = new Label();
    private Jogador jogador = new Jogador();
    
    /**
     *  Começo por limpar os valores do ficheiro JSON, passo para dentro do container Stage
     *  todos os objetos e eventos.
     *  Permite gerir algumas atividades, como reinciar o jogo.
     *   
     *  A interface e separada em tres partes, barra superior, grelha para o cartao e
     *  barra inferior, sao guardados num container Stage, carregados numa janela Root
     *  e associados a uma Scene, alterei o visual da interface com CSS nesta fase.
     *   
     *  A leitura do ficheiro JSON e a associacao do mesmo á interface e feita com Threads.
     *   
     *   
     *	 @param		primaryStage	Container para organizar objetos da interface
     *   
     *   @see	Controller#createDisplayField()
     *   @see	Bars#getTopBar(TextArea)
     *   @see	Grid#createGrid()
     *   @see	Bars#getBottomBar(GridPane, Stage, StringProperty, MonitorLoto)
     *	 @see	Controller#getRoot(FlowPane, GridPane, FlowPane)
     *   
     *   @author      Ivan Xavier
     *   @since       1.0
     */
    @Override
    public void start(Stage primaryStage) {
        logger.setId("logger");

        TextArea displayField = Controller.createDisplayField();
        FlowPane topBar = Bars.getTopBar(displayField);      
        grid = Grid.createGrid();
        FlowPane bottomBar = Bars.getBottomBar(grid, primaryStage, jogador);
        BorderPane root = Controller.getRoot(topBar, grid, bottomBar);
        Scene scene = Controller.getScene(root);
        
        Task<Void> task = new Task<Void>() {
            @Override public Void call() {
            	// Threads Nr
                final int max = 8;
                for (int i=1; i<=max; i++) {
                    if (isCancelled()) {
                       break;
                    }
                    updateProgress(i, max);
                    while(true) {
	                    try{
	                    	TimeUnit.SECONDS.sleep(3);    	
	                    	Controller.setValue(String.valueOf(MonitorLoto.readJSON()));	                    	
	                    }
	                    catch(Exception e){
	                        System.err.println("Erro, Ficheiro não pode ser lido!");
	                        break;
	                    }
                    }
                }
                return null;
            }
        };
        new Thread(task).start();  
        
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}