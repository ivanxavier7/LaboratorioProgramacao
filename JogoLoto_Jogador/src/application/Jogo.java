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
    private Grid gridUtil = new Grid();
    private Bars barsUtil = new Bars();

    @Override
    public void start(Stage primaryStage) {
        logger.setId("logger");
        grid = gridUtil.createGrid();

        TextArea displayField = Common.createDisplayField();
        FlowPane topBar = barsUtil.getTopBar(displayField);        
        FlowPane bottomBar = barsUtil.getBottomBar(grid);
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