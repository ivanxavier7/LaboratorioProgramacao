package gui.util;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

public class Bars {
    private Label title = Common.getLabel("Jogador de Loto");
    private Label logger = new Label();
    private Button playerButton = new Button("Gerar");
    private Button cardConfirmButton = new Button("Confirmar Cartão");
    private Grid gridUtil = new Grid();
    
    public FlowPane getBottomBar(GridPane grid) {
    	TextField textField = new TextField();
        Button clearButton = Buttons.createButton("Cartão Original");
        clearButton.setId("resetbutton");
        clearButton.setOnAction(event -> gridUtil.originalGrid(grid));
        FlowPane bottomBar = new FlowPane() ;
        bottomBar.setHgap(20);
        bottomBar.setVgap(20);
        bottomBar.getChildren().addAll(
        		Common.getLabel("Número de Jogadores"),
        		textField,
        		Buttons.generateCardButton(playerButton, grid),
        		Buttons.clearButton(grid),
        		Buttons.confirmCardButton(cardConfirmButton, grid, logger),
        		logger
        		);
        return bottomBar;
    }
    
    public FlowPane getTopBar(TextArea displayField) {
        FlowPane topBar = new FlowPane();
        title.setId("title");
        topBar.setHgap(20);
        topBar.setVgap(20);
        topBar.getChildren().addAll( title, displayField);
        return topBar;
    }
}
