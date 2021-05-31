package gui.util;

import application.Jogo;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.entities.MonitorLoto;

/**
 *	Classe responsavel pelo controlo da interface e do jogo.
 *  Foram implementados alguns objetos secundarios nesta zona,
 *  como a criacao dos campos editaveis e titulos.
 *  
 *   @author      Ivan Xavier
 *   @version     1.0
 *   @since       1.0
 */
public class Controller {
	

    /**
     *	Controlador responsavel por reiniciar o jogo
     *
     *   @param		primaryStage	Container para organizar objetos da interface
     *   @param		value			StringProperty associado ao monitor dos numeros.	
     *   @param		monitor			Monitor para sincronizar os dados escolhidos com o JSON
     *   
     *   @see	Controller#changeMessage(String)
     *   @see	Jogo#start(Stage)
     *   
     *   @author      Ivan Xavier
     *   @since       1.0
     */
    public static void restartGame(Stage primaryStage, StringProperty value, MonitorLoto monitor) {
    	Jogo jogo = new Jogo();
    	value.set("");
    	monitor.reset();
    	primaryStage.close();
        Platform.runLater( () -> jogo.start(new Stage()));
    }
    
    /**
     *	Envia a mensagem para o loger e reconfigura o tamanho do mesmo,
     *	para evitar ocupar espaço desnecessario.
     *
     *   @param		string		Texto a enviar para o logger
     *   
     *   @author      Ivan Xavier
     *   @since       1.0
     */
    public static BorderPane createRoot(FlowPane topBar, GridPane grid, FlowPane bottomBar) {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        root.setTop(topBar);
        root.setCenter(grid);  
        root.setBottom(bottomBar);
        return root;
    }
    
    /**
     *	Envia a mensagem para o loger e reconfigura o tamanho do mesmo,
     *	para evitar ocupar espaco desnecessario.
     *
     *   @param		string		Texto a enviar para o logger
     *   	
     *   @see	Jogo#getLogger()
     *   
     *   @author      Ivan Xavier
     *   @since       1.0
     */
    public static void changeMessage(String string) {
        Jogo.getLogger().setMaxHeight(5);
        Jogo.getLogger().setText(string);
        Jogo.getLogger().autosize();
    }
    
    /**
     *	Recebe um titulo generico.
     *
     *	 @param		string		Texto a enviar para o logger
     *
     *   @return	label		Campo de texto não editavel
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
     *	Recebe um titulo generico.
     *
     *	 @param		value			StringProperty associado ao monitor dos numeros.
     *
     *   @return	displayField	Monitor para receber os numeros, permite a expansao da mesma.
     *      
     *   @author      Ivan Xavier
     *   @since       1.0
     */
    public static TextArea createDisplayField(StringProperty value) {
        TextArea displayField = new TextArea();
        displayField.textProperty().bind(Bindings.format("%s", value));
        displayField.setEditable(false);
        displayField.setMaxHeight(5);
        displayField.setMinWidth(586);
        displayField.autosize();
        return displayField;
    }
    
    /**
     *	Recebe o campo editavel para introduzir o numero de jogadores
     *
     *
     *   @return	textField	Campo de texto para o numero de jogadores
     *   
     *   @see	Constraints#setTextFieldInteger(TextField)
     *   @see	Constraints#setTextFieldMaxLength(TextField, int)
     *      
     *   @author      Ivan Xavier
     *   @since       1.0
     */
    public static TextField createPlayersField() {
    	TextField textField = new TextField();
        textField.setEditable(true);
        textField.setMaxHeight(5);
        textField.autosize();
        Constraints.setTextFieldInteger(textField);
        Constraints.setTextFieldMaxLength(textField, 2);
        return textField;
    }
}
