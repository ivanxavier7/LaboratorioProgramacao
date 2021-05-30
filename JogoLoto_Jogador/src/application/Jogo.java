package application;

import javafx.application.Application;
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

public class Jogo extends Application{
    private GridPane grid;
    private Label logger = new Label();
    private Jogador jogador = new Jogador();
    
    @Override
    public void start(Stage primaryStage) {
        logger.setId("logger");
        grid = Grid.createGrid();

        TextArea displayField = Controller.createDisplayField();
        FlowPane topBar = Bars.getTopBar(displayField);        
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