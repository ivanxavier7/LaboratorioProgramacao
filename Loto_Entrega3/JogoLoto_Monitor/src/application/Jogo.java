package application;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.entities.MonitorLoto;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import gui.components.Bars;
import gui.components.Grid;
import gui.util.Controller;
import gui.util.SocketServer;

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

public class Jogo extends Application {

    private final static StringProperty value = new SimpleStringProperty();
    private final static StringProperty userInfoValue = new SimpleStringProperty();
	private static MonitorLoto monitor = new MonitorLoto();
    private static Label logger = new Label();
    
    /**
     * Devolve a String dos valores escolhidos e guardados.
     *  
     *   @return 	value.get()	Retirada do StringProperty.	
     *   
     *   @author	Ivan Xavier
     *   @since		1.0
     */
	public static String getValue() {
		return value.get();
	}
	
	
    /**
     * Devolve um Label para permitir ao utilizador ver as mensagens de erro e sucesso.
     *  
     *   @return	logger	Campo de texto na interface para mostrar mensagens
     *   
     *   @author	Ivan Xavier
     *   @since		1.0
     */
	public static Label getLogger() {
		return logger;
	}
	
	
    /**
     * Guarda numa StringProperty o formato desejado para imprimir no monitor.
     * Usado para passar para o Label da interface.
     *   
     *   @param		newNumber	Numero escolhido e que vai ser enviado para a interface
     *   
     *   @author	Ivan Xavier
     *   @since		1.0
     */
	public static void setValueMonitor(int newNumber) {
		String oldNumbers = String.valueOf(getValue());
		if(oldNumbers.equals("null")) {
			oldNumbers = "";
		}
		value.set(oldNumbers + "  " + newNumber);
	}  

    /**
     *  Comeco por limpar os valores do ficheiro JSON, passo para dentro do container Stage
     *  todos os objetos e eventos.
     *  Permite gerir algumas atividades, como reinciar o jogo.
     *   
     *  A interface � separada em tres partes, barra superior, grelha para o cartao e
     *  barra inferior, sao guardados num container Stage, carregados numa janela Root
     *  e associados a uma Scene, alterei o visual da interface com CSS nesta fase.
     *   
     *   
     *   
     *	 @param		primaryStage	Container para organizar objetos da interface
     *   
     *   @see	Controller#restartGame(Stage, StringProperty, MonitorLoto)
     *   @see	Bars#getTopBar(StringProperty)
     *   @see	Grid#createGrid()
     *   @see	Bars#getBottomBar(GridPane, Stage, StringProperty, MonitorLoto)
     *	 @see	Controller#createRoot(FlowPane, GridPane, FlowPane)
     *   
     *   @author      Ivan Xavier
     *   @since       1.0
     */
	@Override
    public void start(Stage primaryStage) {
        logger.setId("logger");
        // Clean JSON
        monitor.reset();
        // Prepare top midle and bottom section
        FlowPane topBar = Bars.getTopBar(value);
        GridPane grid = Grid.createGrid();
        Label infoUsers = Controller.getLabel("Monitor de Loto");
        FlowPane bottomBar = Bars.getBottomBar(primaryStage, userInfoValue, value, monitor, infoUsers);
        
        // Load sections to root
        BorderPane root = Controller.createRoot(topBar, grid, bottomBar);
        // Load root to scene
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("application/application.css");
        
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
	                    	TimeUnit.SECONDS.sleep(1);
	                    	userInfoValue.set("Cart�o: " + Arrays.deepToString(MonitorLoto.formatJSONCard()) + "\t"
	                    					+ "Aposta:" + MonitorLoto.formatJSONBet() +   "\t"
	                    					+ "ID:" + MonitorLoto.formatJSONID() + "\t\t"); 
	                    }
	                    catch(Exception e){
	                        System.err.println("Erro, Ficheiro n�o pode ser lido!" + e);
	                        break;
	                    }
                    }
                }
                return null;
            }
        };
        new Thread(task).start();  
        Controller.validateCard(MonitorLoto.formatJSONCard());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}