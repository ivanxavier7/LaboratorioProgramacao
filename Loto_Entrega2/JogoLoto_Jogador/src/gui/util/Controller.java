package gui.util;

import java.util.concurrent.TimeUnit;

import application.Jogo;
import gui.components.Bars;
import gui.components.Grid;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.entities.Jogador;
import model.entities.MonitorLoto;

/**
 *	Classe responsável pelo controlo da interface e do jogo.
 *  Foram implementados alguns objetos secundários nesta zona,
 *  como a criação dos campos editáveis e títulos.
 *  
 *   @author      Ivan Xavier
 *   @version     1.0
 *   @since       1.0
 */
public class Controller {
    private final static StringProperty value = new SimpleStringProperty();
    private static boolean dragFlag = false;
    private static boolean firstRowComplete = false;
    private static boolean secondRowComplete = false;
    private static boolean thirdRowComplete = false;
    private static boolean firstRowReward = false;
    private static boolean gameStarted = false;
    private static double reward = 0.0;
    static TextField playerBet = new TextField();
	
    public static void setValue(String string) {
    	value.set(string);
    }
    
    /**
     * Reintroduz a barra inferior na interface
     * 
     *	 @param		primaryStage	Container para organizar objetos da interface
     *	 @param 	grid			Grelha na interface que contém os botões
     *	 @param		jogador			Entidade que gera o cartão e guarda a sua aposta
     *  
     *   @return	scene			Interface com a barra inferior
     *   
     *   @see	Controller#createDisplayField()
     *   @see	Bars#getTopBar(TextArea)
     *   @see	Bars#getBottomBar(GridPane, Stage, Jogador)
     *   @see	Controller#getRoot(FlowPane, GridPane, FlowPane)
     *   @see	Controller#getScene(BorderPane)
     *   
     *   @author	Ivan Xavier
     *   @since		1.0
     */
    public static Scene showBottomBar(Stage primaryStage, GridPane grid, Jogador jogador) {
        TextArea displayField = Controller.createDisplayField();
        FlowPane topBar = Bars.getTopBar(displayField);        
        FlowPane bottomBar = Bars.getBottomBar(grid, primaryStage, jogador);
        BorderPane root = Controller.getRoot(topBar, grid, bottomBar);
        Scene scene = Controller.getScene(root);
        return scene;
        
    }
	
    /**
     * Esconde a barra inferior, apenas mantém o logger
     * 
     *	 @param		logger			Campo de texto na interface para mostrar mensagens
     *  
     *   @return	scene			Interface sem a barra inferior
     *   
     *   @see	Controller#createDisplayField()
     *   @see	Bars#getTopBar(TextArea)
     *   @see	Bars#getLogger(Label)
     *   @see	Controller#getRoot(FlowPane, GridPane, FlowPane)
     *   @see	Controller#getScene(BorderPane)
     *   
     *   @author	Ivan Xavier
     *   @since		1.0
     */
    public static Scene hideBottomBar(GridPane grid, Label logger) {
        TextArea displayField = Controller.createDisplayField();
        FlowPane topBar = Bars.getTopBar(displayField);
        FlowPane bottomBar = Bars.getLogger(logger);  
        BorderPane root = Controller.getRoot(topBar, grid, bottomBar);
        Scene scene = Controller.getScene(root);
        return scene;     
    }
    
    /**
     * Mostra uma nova barra após a conclusão do jogo,
     * nesta barra é apresentado ao utilizador a opção de sair ou de reiniciar o jogo
     * 
     * 	 @param		primaryStage	Container para organizar objetos da interface
     * 	 @param 	grid			Grelha na interface que contém os botões
     *	 @param		logger			Campo de texto na interface para mostrar mensagens
     *  
     *   @return	scene			Interface com uma barra inferior nova
     *   
     *   @see	Controller#createDisplayField()
     *   @see	Bars#getTopBar(TextArea)
     *   @see	Bars#getBottomBarAfterClick(GridPane, Stage, Jogador)
     *   @see	Controller#getRoot(FlowPane, GridPane, FlowPane)
     *   @see	Controller#getScene(BorderPane)
     *   
     *   @author	Ivan Xavier
     *   @since		1.0
     */
    public static Scene showBottomBarAfterClick(Stage primaryStage, GridPane grid, Jogador jogador) {
        TextArea displayField = Controller.createDisplayField();
        FlowPane topBar = Bars.getTopBar(displayField);        
        FlowPane bottomBar = Bars.getBottomBarAfterClick(grid, primaryStage, jogador);
        BorderPane root = Controller.getRoot(topBar, grid, bottomBar);
        Scene scene = Controller.getScene(root);
        return scene;    
    }
    
    /**
     * Método responsável por trancar o cartão após confirmação,
     * se o jogo ainda não tiver começado o JSON vai estar vazio, caso isso se verifique
     * pede para validar o cartão, em seguida fica á escuta de números por parte do monitor.
     * Também é responsável por passar os eventos associados ao jogo para a grelha,
     * como a conclusão de uma linha ou a vitória.
     * 
     * 	 @param		primaryStage	Container para organizar objetos da interface
     * 	 @param 	grid			Grelha na interface que contém os botões
     *	 @param		logger			Campo de texto na interface para mostrar mensagens
     *	 @param		jogador			Entidade que gera o cartão e guarda a sua aposta
     *
     *	 @see	#showBottomBar(Stage, GridPane, Jogador)
     *	 @see	MonitorLoto#readJogadoresJSON()
     *	 @see	Grid#validateGrid(GridPane, Label)
     *	 @see	#hideBottomBar(GridPane, Label)
     *	 @see	MonitorLoto#readJSON()
     *	 @see	#showBottomBarAfterClick(Stage, GridPane, Jogador)
     *	 @see	Alerts#showAlert(String, String, String, AlertType)
     *	 @see	Controller#changeMessage(Label, String)
     *	 @see	MonitorLoto#checkJSON(int)
     *	 @see	#checkProgress(GridPane, Label, int, Stage, Jogador)
     *   
     *   @author	Ivan Xavier
     *   @since		1.0
     */
    public static void lockGrid(Stage primaryStage, GridPane grid, Label logger, Jogador jogador) {
    	Scene scene = showBottomBar(primaryStage, grid, jogador);
        // Change Events
    	if(MonitorLoto.readJSON() == null) {
	    	if(Grid.validateGrid(grid, logger)) {
	        	scene = hideBottomBar(grid, logger);
	        	for (Node node : grid.getChildren()) {
	        	    if (node instanceof TextField) {
	        	    	((TextField)node).setEditable(false);
	        	    	((TextField)node).setOnMouseClicked(new EventHandler<MouseEvent>() {
	        	                @Override
	        	                public void handle(MouseEvent e) {
	        	                	int textFieldNumber = 0;
	        	                	if(!((TextField)e.getSource()).getText().equals("")) {
	        	                		textFieldNumber = Integer.parseInt(((TextField)e.getSource()).getText());
	        	                	}
	        	                    if (e.getButton().equals(MouseButton.PRIMARY)) {
	        	                        if (!dragFlag) {
	        	                            if (e.getClickCount() >= 1) {
	        	                            	// Restart game if monitor = ""
	        	                            	if(MonitorLoto.readJSON() == null && gameStarted) {
	        	                            		Scene scene = showBottomBarAfterClick(primaryStage, grid, jogador);
	        	                                    primaryStage.setScene(scene);
	        	                                    primaryStage.setResizable(false);
	        	                                    primaryStage.show();
	        	                                    Alerts.showAlert(
	        	                                    		"Jogo Acabou",
	        	                                    		"Este jogo de loto acabou",
	        	                                    		"Se desejar continuar a jogar clique em reiniciar e espere pelo monitor para confirmar o seu cartão",
	        	                                    		AlertType.WARNING);
	        	                            	} else if(MonitorLoto.readJSON() == null && !gameStarted) {
	        	                            		Controller.changeMessage(logger, "Espere pelo monitor para começar o jogo!");
	        	                            	} else {
	        	                            		gameStarted = true;
		        	                                if(MonitorLoto.checkJSON(textFieldNumber)) {
		        	                                	((TextField)e.getSource()).setText("");;
		        	                                	((TextField)e.getSource()).setStyle(
		        	                                			  "-fx-background-color:"
		        	                                			+ "#b0e0e6,"
		        	                                			+ "linear-gradient(#b0e0e6 0%, #1f2429 20%, #191d22 100%),"
		        	                                			+ "linear-gradient(#20262b, #191d22),"
		        	                                			+ "radial-gradient(center 50% 0%, radius 100%, rgba(100,67,120,0.5), rgba(255,0,255,0));"
		        	                                			+ "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
		        	                                };
		        	                                Integer rowIndex = GridPane.getRowIndex(node);
		        	                                Controller.changeMessage(logger, String.format("Selecionou o número %d", textFieldNumber));
		        	                                checkProgress(grid, logger, rowIndex, primaryStage, jogador); 
	        	                            	}
	        	                            }
	        	                        }
	        	                        dragFlag = false;
	        	                    }
	        	                }
	        	    	});
	        	    }
	        	}
	    	}
    	} else {
    		Controller.changeMessage(logger, "Por favor aguarde pelo final deste turno para confirmar o Cartão!");
    	}
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    /**
     * Método responsável por verificar o progresso do jogador fase ás linhas.
     * Caso o jogador complete uma linha deve receber um prémio, esse prémio não
     * se deve repetir após a primeira linha ser concluída.
     * 
     * 
     * 	 @param 	grid			Grelha na interface que contém os botões
     *	 @param		logger			Campo de texto na interface para mostrar mensagens
     *	 @param		rowIndex		Número da linha a verificar o progresso
     *	 @param		primaryStage	Container para organizar objetos da interface
     *	 @param		jogador			Entidade que gera o cartão e guarda a sua aposta
     *
     *	 @see	Jogador#getAposta()
     *	 @see	Controller#changeMessage(Label, String)
     *	 @see	Grid#getItemByIndex(int, int, GridPane)
     *	 @see	#showBottomBarAfterClick(Stage, GridPane, Jogador)
     *	 @see	Alerts#showAlert(String, String, String, AlertType)
     *   
     *   @author	Ivan Xavier
     *   @since		1.0
     */
	public static void checkLinesProgress(GridPane grid, Label logger, int rowIndex, Stage primaryStage, Jogador jogador) {
    	switch (rowIndex) {
        case 1:
        	firstRowComplete = true;
        	break;
        case 2:
        	secondRowComplete = true;
        	break;
        case 3:
        	thirdRowComplete = true;
        	break;
    	}
    	if(firstRowComplete && secondRowComplete && thirdRowComplete) { 
    		reward = jogador.getAposta() * 10;
    		Controller.changeMessage(logger, "Parabéns pela vitória no jogo anterior");
    		for(int row=1; row<4; row++) {
            	for(int col=1; col<10; col++) {
        	        ((TextField)Grid.getItemByIndex(row, col, grid)).setVisible(false);
    	        }
    		}
    		Scene scene = showBottomBarAfterClick(primaryStage, grid, jogador);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
            Alerts.showAlert("Ganhou!", "Parabéns ganhou o jogo de loto!", String.format("Por favor diriga-se ao monitor para receber %.2f", reward), AlertType.CONFIRMATION);
    	}
	};
	
    /**
     * Verifica se um dado campo se encontra na coluna respetiva.
     * 
     * 
     * 	 @param 	textFieldNr		Botão a validar
     *	 @param		colIndex		Número da coluna
     *	 @param		rowIndex		Número da linha
     *	 @param		logger			Campo de texto na interface para mostrar mensagens		
     *  
     *   @return	validNr		Booleano que traduz se a edição é válida
     *   
     *   @see	Controller#changeMessage(Label, String)
     *   
     *   @author	Ivan Xavier
     *   @since		1.0
     */
    public static Boolean columnValidation(int textFieldNr, int colIndex, int rowIndex, Label logger, Boolean validNr) {
        switch (colIndex) {
        case 1:
        	if(textFieldNr < 1 || textFieldNr > 9) {
        		Controller.changeMessage(logger, String.format("O valor: %d na %dª coluna e na %dª linha é inválido!", textFieldNr, colIndex, rowIndex));
        		validNr = false;
        	}
          break;
        case 2:
        	if(textFieldNr < 10 || textFieldNr > 19) {
        		Controller.changeMessage(logger, String.format("O valor: %d na %dª coluna e na %dª linha é inválido!", textFieldNr, colIndex, rowIndex));
        		validNr = false;
        	}
          break;
        case 3:
        	if(textFieldNr < 20 || textFieldNr > 29) {
        		Controller.changeMessage(logger, String.format("O valor: %d na %dª coluna e na %dª linha é inválido!", textFieldNr, colIndex, rowIndex));
        		validNr = false;
        	}
          break;
        case 4:
        	if(textFieldNr < 30 || textFieldNr > 39) {
        		Controller.changeMessage(logger, String.format("O valor: %d na %dª coluna e na %dª linha é inválido!", textFieldNr, colIndex, rowIndex));
        		validNr = false;
        	}
          break;
        case 5:
        	if(textFieldNr < 40 || textFieldNr > 49) {
        		Controller.changeMessage(logger, String.format("O valor: %d na %dª coluna e na %dª linha é inválido!", textFieldNr, colIndex, rowIndex));
        		validNr = false;
        	}
          break;
        case 6:
        	if(textFieldNr < 50 || textFieldNr > 59) {
        		Controller.changeMessage(logger, String.format("O valor: %d na %dª coluna e na %dª linha é inválido!", textFieldNr, colIndex, rowIndex));
        		validNr = false;
        	}
          break;
        case 7:
        	if(textFieldNr < 60 || textFieldNr > 69) {
        		Controller.changeMessage(logger, String.format("O valor: %d na %dª coluna e na %dª linha é inválido!", textFieldNr, colIndex, rowIndex));
        		validNr = false;
        	}
          break;
        case 8:
        	if(textFieldNr < 70 || textFieldNr > 79) {
        		Controller.changeMessage(logger, String.format("O valor: %d na %dª coluna e na %dª linha é inválido!", textFieldNr, colIndex, rowIndex));
        		validNr = false;
        	}
          break;
        case 9:
        	if(textFieldNr < 80 || textFieldNr > 90) {
        		Controller.changeMessage(logger, String.format("O valor: %d na %dª coluna e na %dª linha é inválido!", textFieldNr, colIndex, rowIndex));
        		validNr = false;
        	}
          break;
        }
        return validNr;
    }
    
    
    /**
     * Verifica o progresso, altera as linhas visualmente para o utilizador saber que a linha está concluída.
     * Atribuí o prémio ao jogador caso conclua a linha.
     * 
     * 
     * 	 @param 	grid			Grelha na interface que contém os botões
     *	 @param		logger			Campo de texto na interface para mostrar mensagens
     *	 @param		rowIndex		Número da linha a verificar o progresso
     *	 @param		primaryStage	Container para organizar objetos da interface
     *	 @param		jogador			Entidade que gera o cartão e guarda a sua aposta
     *
     *	 @see	Grid#getItemByIndex(int, int, GridPane)
     *	 @see	Jogador#getAposta()
     *	 @see	Controller#changeMessage(Label, String)
     *	 @see	#checkLinesProgress(GridPane, Label, int, Stage, Jogador)
     *   
     *   @author	Ivan Xavier
     *   @since		1.0
     */
    private static void checkProgress(GridPane grid, Label logger, int rowIndex, Stage primaryStage, Jogador jogador) {
    	Boolean lineComplete = true;
    	for(int col=1; col<10; col++) {
	        String colNr = ((TextField)Grid.getItemByIndex(rowIndex, col, grid)).getText();
	        if(!colNr.equals("")) {
	        	lineComplete = false;
	        }
    	}
    	if(lineComplete) {
    		if(!firstRowReward) {
    			reward = jogador.getAposta() * 2;
    			Controller.changeMessage(logger, String.format("Completou uma linha, ganhou %.2f€", reward));
    			firstRowReward = true;
    		}
        	for(int col=1; col<10; col++) {
    	        ((TextField)Grid.getItemByIndex(rowIndex, col, grid)).setStyle(
          			  "-fx-background-color:"
	          			  + "linear-gradient("
	          			  + "from 25% 25% to 100% 100%,"
	          			  + "#5FFFFF,"
	          			  + "#8154F7);");
	        }
        	checkLinesProgress(grid, logger, rowIndex, primaryStage, jogador);     	
    	}
    }
    
    /**
     *	Criar o ecrã que armazena os números sorteados
     *  Associa o valor carregado pelo JSON a um TextArea, para mostrar ao jogador os números escolhidos
     *
     *
     *   @return	displayField	Monitor para receber os números, permite a expansão da mesma.
     *      
     *   @author      Ivan Xavier
     *   @since       1.0
     */
    public static TextArea createDisplayField() {
        TextArea displayField = new TextArea();
        displayField.textProperty().bind(Bindings.format("%s", value));
        displayField.setEditable(false);
        displayField.setMaxHeight(5);
        displayField.setMinWidth(586);
        displayField.autosize();
        return displayField;
    }
    
    /**
     *	Criar um campo editável para armazenar a aposta
     *
     *   @return	playerBet	Configura o campo editável associado á aposta.
     *   
     *   @see	Constraints#setTextFieldDouble(TextField)
     *   @see	Constraints#setTextFieldMaxLength(TextField, int)
     *      
     *   @author      Ivan Xavier
     *   @since       1.0
     */
    public static TextField createBetField() {
    	playerBet.setMaxWidth(150);
    	playerBet.setPromptText("€");
        playerBet.setEditable(true);
        playerBet.setMaxHeight(5);
        playerBet.autosize();
        Constraints.setTextFieldDouble(playerBet);
        Constraints.setTextFieldMaxLength(playerBet, 10);
        return playerBet;
    }
    
    /**
     *	Passa a aposta recebida para o objeto jogador
     *
     *	 @param		jogador			Entidade que gera o cartão e guarda a sua aposta
     *	 @param		logger			Campo de texto na interface para mostrar mensagens
     *
     *	 @see	Jogador#setAposta(double, Label)
     *      
     *   @author      Ivan Xavier
     *   @since       1.0
     */
    public static void parsePlayerBet(Jogador jogador, Label logger) {
    	double bet = Double.parseDouble(playerBet.getText());
    	jogador.setAposta(bet, logger);
    }
    
    /**
     *	Recebe um título genérico.
     *
     *	 @param		string		Texto a enviar para o logger
     *
     *   @return	label		Campo de texto não editável
     *      
     *   @author      Ivan Xavier
     *   @since       1.0
     */
    public static Label getLabel(String string) {
        Label label = new Label();
        label.setText(string);
        label.setMaxWidth(Double.POSITIVE_INFINITY);
        label.setMaxHeight(Double.POSITIVE_INFINITY);
        return label;
    }
    
    /**
     *	Recebe o a layout para associar á Scene, associamos a barra superior,
     *  a grelha e a barra inferior ao Root. 
     *
     *	 @param		top			Barra superior
     *	 @param		center		Grelha
     *	 @param		bottom		Barra inferior
     *
     *   @return	root		Objetos organizados numa BorderPane layout
     *      
     *   @author      Ivan Xavier
     *   @since       1.0
     */
    public static BorderPane getRoot(FlowPane top, GridPane center, FlowPane bottom) {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        root.setTop(top);
        root.setCenter(center);  
        root.setBottom(bottom);
        return root;
    }
    
    /**
     *	Recebe o contaier com o root dentro e associa CSS ao mesmo
     *
     *	 @param		root		Layout de organização BorderPane
     *
     *   @return	primaryStage	Container para organizar objetos da interface
     *      
     *   @author      Ivan Xavier
     *   @since       1.0
     */
    public static Scene getScene(BorderPane root) {
        Scene scene = new Scene(root, 1000, 400);
        scene.getStylesheets().add("application/application.css");
        return scene;
    }
    
    /**
     *	Threads para lerem o ficheiro JSON e carregarem o monitor para ler os números
     *
     *	 @param		displayField	Monitor para receber os números, permite a expansão da mesma.
     *
     *   @return	task			Devolve uma tarefa
     *      
     *   @author      Ivan Xavier
     *   @since       1.0
     */
    public static Task<Void> getTaskDisplay(TextField displayField) {
	    Task<Void> task = new Task<Void>() {
	        @Override public Void call() {
	            final int max = 8;
	            for (int i=1; i<=max; i++) {
	                if (isCancelled()) {
	                   break;
	                }
	                updateProgress(i, max);
	                while(true) {
	                    try{
	                    	TimeUnit.SECONDS.sleep(3);
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
        return task;
    }
	
    /**
     *	Envia a mensagem para o loger e reconfigura o tamanho do mesmo,
     *	para evitar ocupar espaço desnecessário.
     *
     *   @param		logger		Campo de texto na interface para mostrar mensagens	
     *   @param		string		Texto a enviar para o logger
     *   	
     *   @see	Jogo#getLogger()
     *   
     *   @author      Ivan Xavier
     *   @since       1.0
     */
    public static void changeMessage(Label logger, String string) {
        logger.setMaxHeight(5);
        logger.setText(string);
        logger.autosize();
    }
}
