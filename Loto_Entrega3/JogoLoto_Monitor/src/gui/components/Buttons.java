package gui.components;

import application.Jogo;
import gui.util.Controller;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import model.entities.MonitorLoto;


/**
 *	Classe responsavel pelos botoes da interface
 *	Reponsavel pela configuracao dos botoes na grelha e dos seus eventos
 *  
 *   @author      Ivan Xavier
 *   @version     1.0
 *   @since       1.0
 */
public class Buttons {
	private static boolean dragFlag = false;
	private static Button playerButton;

    /**
     *	Cria o botao usado para gerar a grelha com todos os numeros de 1 a 90
     *
     *   @param		number		Numero do botao
     *   @param		grid		Grelha para guardar os botoes
     *   
     *   @return	button		Botao com o numero pretendido
     *   
     *   @see	#createNumberButtonEvent(int, GridPane, Button)
     *   
     *   @author      Ivan Xavier
     *   @since       1.0
     */
    public static Button createNumberButton(int number, GridPane grid) {
        Button button = createButton(Integer.toString(number));
        button.setOnAction(event -> createNumberButtonEvent(number, grid, button));
        return button ;
    }
    
    /**
     *	Cria o evento para o botao com um certo numero.
     *	Envia o valor contigo no botao para o monitor e para o JSON,
     *	remove o botao apos o registo para evitar repeticoes.
     *
     *	O uso de getClickCount() evisa o Spam de cliques no botoes,
     *	caso o mesmo carrege varias vezes antes da animacao desaparecer,
     *	bloqueia repeticoes.
     *
     *   @param		number		Numero do botao
     *   @param		grid		Grelha para guardar os botoes
     *   @param		button		Botao a associar ao evento
     *   
     *   @return	button		Botao com o evento
     *   
     *   @see	#createNumberButtonEvent(int, GridPane, Button)
     *   
     *   @author      Ivan Xavier
     *   @since       1.0
     */
    public static Button createNumberButtonEvent(int number, GridPane grid, Button button) {
		// Blocks Click Spam
		button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (e.getButton().equals(MouseButton.PRIMARY)) {
                    if (!dragFlag) {
                        if (e.getClickCount() >= 1) {
                            Jogo.setValueMonitor(number);
                            int btNumber = Integer.parseInt(((Button)e.getSource()).getText());
                            grid.getChildren().remove((Button)e.getSource());        
                            // Saves the chosen number in the JSON file
                            MonitorLoto.nrEscolhido(btNumber);
                            Controller.changeMessage(String.format("Selecionou o número %s", String.valueOf(number)));
                        }
                    }
                    dragFlag = false;
                }
            }
        });
		return button;
     }

    /**
     *	Cria um botao generico e configura o mesmo em relacao a grelha
     *
     *   @param		text		Texto contido no botao
     *   
     *   @return	button		Botao com o texto pretendido
     *   
     *   
     *   @author      Ivan Xavier
     *   @since       1.0
     */
    public static Button createButton(String text) {
        Button button = new Button(text);
        button.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        GridPane.setFillHeight(button, true);
        GridPane.setFillWidth(button, true);
        GridPane.setHgrow(button, Priority.ALWAYS);
        GridPane.setVgrow(button, Priority.ALWAYS);
        return button ;
    }
    
    /**
     *	Modifica o botao generico para um botao que reinicia o jogo.
     *
     *   @param		text			Texto contido no botao
     *   @param		primaryStage	Container para organizar objetos da interface
     *   @param		value			StringProperty associado ao monitor dos numeros.	
     *   @param		monitor			Monitor para sincronizar os dados escolhidos com o JSON
     *   
     *   @see	Controller#restartGame(Stage, StringProperty, MonitorLoto)
     *   
     *   @author      Ivan Xavier
     *   @since       1.0
     */
    public static Button restartButton(Stage primaryStage, StringProperty value, MonitorLoto monitor) {
        Button restartButton = Buttons.createButton("Reiniciar Jogo");
        restartButton.setId("resetbutton");
        restartButton.setOnAction(event -> Controller.restartGame(primaryStage, value, monitor));
        return restartButton;
    }
    
    /**
     *	Modifica o botao generico para um botao que envia introduzido pelo utilizador,
     *  respetivo ao numero de jogadores.
     *
     *   @param		textField		Campo editavel dos jogadores
     *   @param		monitor			Monitor para sincronizar os dados escolhidos com o JSON
     *   
     *   @see	#gamePlayersButton(TextField, MonitorLoto)
     *   
     *   @author      Ivan Xavier
     *   @since       1.0
     */
    public static Button playerButton(TextField textField, MonitorLoto monitor) {
    	playerButton = createButton("Confirmar");
        playerButton.setOnAction(event -> gamePlayersButton(textField, monitor));
        playerButton.setId("confirmbutton");
    	return playerButton;
    }
    
    /**
     *	Modifica o botao para introduzir jogadores de forma a enviar para o JSON
     *  os dados inseridos e atualizar a mensagem para o utilizador pelo logger.
     *
     *   @param		textField		Campo editavel dos jogadores
     *   @param		monitor			Monitor para sincronizar os dados escolhidos com o JSON
     *   
     *   @see	Controller#changeMessage(String)
     *   
     *   @author      Ivan Xavier
     *   @since       1.0
     */
    public static void gamePlayersButton(TextField textField, MonitorLoto monitor) {
    	if(!textField.getText().equals("")) {
	    	monitor.writeJogadoresJSON(Integer.parseInt(textField.getText()));
	    	Controller.changeMessage(String.format("Inseriu %s jogadores", textField.getText()));
    	}
    }  
    
    /**
     *	Valida os lados recebidos pelo jogador, da aposta, cartao e id.
     *
     *   @param		textField		Campo editavel dos jogadores
     *   @param		monitor			Monitor para sincronizar os dados escolhidos com o JSON
     *   
     *   @see	#gamePlayersButton(TextField, MonitorLoto)
     *   
     *   @author      Ivan Xavier
     *   @since       1.0
     */
    public static Button playerValidate(TextField textField, MonitorLoto monitor) {
    	playerButton = createButton("Confirmar");
        playerButton.setOnAction(event -> gamePlayersButton(textField, monitor));
        playerButton.setId("confirmbutton");
    	return playerButton;
    }
}
