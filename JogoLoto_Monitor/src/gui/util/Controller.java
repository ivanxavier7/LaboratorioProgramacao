package gui.util;

import application.Jogo;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.entities.MonitorLoto;

public class Controller {
    public static void changeMessage(String string) {
        Jogo.getLogger().setMaxHeight(5);
        Jogo.getLogger().setText(string);
        Jogo.getLogger().autosize();
    }
    
	public static Label getLabel(String string) {
        Label label = new Label();
        label.setText(string);
        label.setMaxWidth(Double.POSITIVE_INFINITY);
        label.setMaxHeight(Double.POSITIVE_INFINITY);
        return label;
    }
    
    public static void restartGame(Stage primaryStage, StringProperty value, MonitorLoto monitor) {
    	Jogo jogo = new Jogo();
    	value.set("");
    	Controller.changeMessage("Reiniciou com sucesso");
    	monitor.reset();
    	primaryStage.close();
        Platform.runLater( () -> jogo.start(new Stage()));
    }
    


    public static TextArea createDisplayField(StringProperty value) {
        TextArea displayField = new TextArea();
        displayField.textProperty().bind(Bindings.format("%s", value));
        displayField.setEditable(false);
        displayField.setMaxHeight(5);
        displayField.setMinWidth(586);
        displayField.autosize();
        return displayField;
    }
    
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
