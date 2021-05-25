package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import gui.util.Bars;
import gui.util.Common;
import gui.util.Grid;

public class Jogo extends Application {
    private GridPane grid;
    private Label logger = new Label();

    @Override
    public void start(Stage primaryStage) {
        logger.setId("logger");
        grid = Grid.createGrid();

        TextArea displayField = Common.createDisplayField();
        FlowPane topBar = Bars.getTopBar(displayField);        
        FlowPane bottomBar = Bars.getBottomBar(grid);
        BorderPane root = Common.getRoot(topBar, grid, bottomBar);
        Scene scene = Common.getScene(root);
        
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}