package gui.components;

import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Controller;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import model.entities.Jogador;

/**
 *	Classe respons�vel pelos bot�es da interface
 *	Repons�vel pela configura��o dos bot�es na grelha e dos seus eventos
 *  
 *   @author      Ivan Xavier
 *   @version     1.0
 *   @since       1.0
 */
public class Buttons {
    private boolean dragFlag = false;  
    
    /**
     *	Cria um campo edit�vel usado para gerar a grelha com todos os n�meros de 1 a 90,
     *  Limita a sua edi��o a inteiros com comprimento m�ximo de dois d�gitos ou String vazia
     *
     *   @param		number		N�mero do bot�o
     *   
     *   @return	button		Bot�o com o n�mero pretendido
     *   
     *   @see	Constraints#setTextFieldInteger(TextField)
     *   @see	Constraints#setTextFieldMaxLength(TextField, int)
     *   
     *   @author      Ivan Xavier
     *   @since       1.0
     */
    public TextField createNumberTextField(String number) {
		TextField textField = createTextField(number);
		Constraints.setTextFieldMaxLength(textField, 2);
		Constraints.setTextFieldInteger(textField);
		// Blocks Click Spam
        textField.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
            	//int textFieldNumber = 0;
            	if(!((TextField)e.getSource()).getText().equals("")) {
            		//textFieldNumber = Integer.parseInt(((TextField)e.getSource()).getText());
            	}
                if (e.getButton().equals(MouseButton.PRIMARY)) {
                    if (!dragFlag) {
                        if (e.getClickCount() >= 1) {
                        	// Click event before lock the card
                        }
                    }
                    dragFlag = false;
                }
            }
        });
		return textField;
     }
    /**
     *	Cria um campo edit�vel gen�rico e configura o mesmo em rela��o � grelha
     *
     *   @param		text		Texto contido no bot�o
     *   
     *   @return	textField	Campo edit�vel com o texto pretendido
     *   
     *   
     *   @author      Ivan Xavier
     *   @since       1.0
     */
    private TextField createTextField(String text) {
        TextField textField = new TextField(text);
        textField.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        textField.setEditable(true);
        GridPane.setFillHeight(textField, true);
        GridPane.setFillWidth(textField, true);
        GridPane.setHgrow(textField, Priority.ALWAYS);
        GridPane.setVgrow(textField, Priority.ALWAYS);
        return textField ;
    }
    
    /**
     *	Cria um botao gen�rico e configura o mesmo em rela��o � grelha
     *
     *   @param		text		Texto contido no bot�o
     *   
     *   @return	button		Bot�o com o texto pretendido
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
     *	Cria um botao para voltar ao cart�o original na grelha
     *
     *   @param		grid		Grelha para guardar os bot�es
     *   
     *   @return	button		Bot�o para voltar ao  cart�o original
     *   
     *   @see #createButton(String)
     *   
     *   @author      Ivan Xavier
     *   @since       1.0
     */
    public static Button originalButton(GridPane grid) {
	    Button originalButton = createButton("Cart�o Original");
	    originalButton.setId("resetbutton");
	    originalButton.setOnAction(event -> Grid.originalGrid(grid));
	    return originalButton;
    }
    
    /**
     *	Cria um botao para sair do jogo
     *   
     *   @return	exitButton		Bot�o para sair do jogo
     *   
     *   @see #createButton(String)
     *      
     *   @author      Ivan Xavier
     *   @since       1.0
     */
    public static Button exitButton() {
	    Button exitButton = createButton("Sair");
	    exitButton.setId("resetbutton");
	    exitButton.setOnAction(event -> Platform.exit());
	    return exitButton;
    }
    
    /**
     *	Cria um botao para guardar a aposta inserida no campo edit�vel
     *
     *   @param		grid				Grelha para guardar os bot�es
     *   @param		jogador				Entidade que gera o cart�o e guarda a sua aposta
     *   @param		logger				Campo de texto na interface para mostrar mensagens
     *   
     *   @return	betButton		Bot�o para apostar
     *   
     *   
     *   @author      Ivan Xavier
     *   @since       1.0
     */
    public static Button betButton(GridPane grid, Jogador jogador, Label logger) {
	    Button betButton = createButton("Apostar");
	    betButton.setId("resetbutton");
	    betButton.setOnAction(event -> Controller.parsePlayerBet(jogador, logger));
	    betButton.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
	    return betButton;
    }
    
    /**
     *	Cria um botao para gerar novos cart�es na grelha
     *
     *   @param		grid		Grelha para guardar os bot�es
     *   
     *   @return	button		Bot�o para gerar cart�es
     *   
     *   @see #createButton(String)
     *   
     *   @author      Ivan Xavier
     *   @since       1.0
     */    
    public static Button generateCardButton(GridPane grid) {
    	Button playerButton = createButton("Gerar");
        playerButton.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        playerButton.setOnAction(event -> Grid.updateGrid(grid));
        playerButton.setId("generatebutton");
    	return playerButton;
    }
    
    
    /**
     *	Cria um botao para abrir um alerta de reinicio do jogo,
     *	Nesse alerta existe a op��o de reiniciar o jogo com o cart�o original
     *  ou com um novo cart�o
     *
     *   @param		grid			Grelha para guardar os bot�es
     *   @param		primaryStage	Container para organizar objetos da interface
     *   @param		jogador		Entidade que gera o cart�o e guarda a sua aposta
     *   
     *   @return	playerButton		Bot�o para gerar cart�es
     *   
     *   @see #createButton(String)
     *   
     *   @author      Ivan Xavier
     *   @since       1.0
     */    
    public static Button restartAfterClickButton(GridPane grid, Stage primaryStage, Jogador jogador) {
    	Button playerButton = createButton("Reiniciar");
        playerButton.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        playerButton.setOnAction(event -> Alerts.showReset(grid, primaryStage, jogador));
        playerButton.setId("generatebutton");
    	return playerButton;
    } 
    
    /**
     *	Modifica o bot�o de confirma��o para validar o cart�o e verificar se o jogo j� se encontra a decorrer.
     *
     *	 @param 	cardConfirmButton	Bot�o para confirmar o cart�o
     *   @param		grid				Grelha para guardar os bot�es
     *   @param		logger				Campo de texto na interface para mostrar mensagens
     *   @param		primaryStage		Container para organizar objetos da interface
     *   @param		jogador				Entidade que gera o cart�o e guarda a sua aposta
     *   
     *   @see	Controller#changeMessage(String)
     *   
     *   @author      Ivan Xavier
     *   @since       1.0
     */
    public static Button confirmCardButton(Button cardConfirmButton, GridPane grid, Label logger, Stage primaryStage, Jogador jogador) {
    	cardConfirmButton.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        cardConfirmButton.setOnAction(event -> Controller.lockGrid(primaryStage ,grid, logger, jogador));
        cardConfirmButton.setId("confirmbutton");
    	return cardConfirmButton;
    }

}
