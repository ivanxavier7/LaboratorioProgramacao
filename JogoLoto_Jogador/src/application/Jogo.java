package application;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.entities.MonitorLoto;

import java.util.concurrent.TimeUnit;

import gui.util.Bars;
import gui.util.Common;
import gui.util.Grid;

public class Jogo extends Application{
    private GridPane grid;
    private Label logger = new Label();
    private MonitorLoto monitor = new MonitorLoto();
    private Common commonUtils = new Common();

    @Override
    public void start(Stage primaryStage) {
        logger.setId("logger");
        grid = Grid.createGrid();

        TextArea displayField = Common.createDisplayField();
        FlowPane topBar = Bars.getTopBar(displayField);        
        FlowPane bottomBar = Bars.getBottomBar(grid);
        BorderPane root = Common.getRoot(topBar, grid, bottomBar);
        Scene scene = Common.getScene(root);
        // Parte a ser reorganizada
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
	                    	commonUtils.setValue(String.valueOf(monitor.readJSON()));	                    	
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