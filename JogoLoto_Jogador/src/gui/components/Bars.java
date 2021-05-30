package gui.components;

import gui.util.Controller;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.entities.Jogador;

public class Bars {
    private static Label title = Controller.getLabel("Jogador de Loto");
    private static Label logger = new Label();
    private static Button cardConfirmButton = new Button("Confirmar Cartão");
    
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
        		Buttons.confirmCardButton(cardConfirmButton, grid, logger, primaryStage, jogador),
        		logger
        		);
        return bottomBar;
    }
    
    public static FlowPane getBottomBarAfterClick(GridPane grid, Stage primaryStage, Jogador jogador) {
        FlowPane bottomBar = new FlowPane() ;
        bottomBar.setHgap(20);
        bottomBar.setVgap(20);
        bottomBar.getChildren().addAll(
        		Buttons.restartAfterClickButton(grid, primaryStage, jogador),
        		Buttons.exitButton(grid)
        		);
        return bottomBar;
    }
    
    public static FlowPane getTopBar(TextArea displayField) {
        FlowPane topBar = new FlowPane();
        title.setId("title");
        topBar.setHgap(20);
        topBar.setVgap(20);
        topBar.getChildren().addAll( title, displayField);
        return topBar;
    }
    
    public static FlowPane getLogger(Label logger) {
        FlowPane bottomBar = new FlowPane();
        title.setId("title");
        bottomBar.setHgap(20);
        bottomBar.setVgap(20);
        bottomBar.getChildren().addAll(logger);
        return bottomBar;
    }
}
