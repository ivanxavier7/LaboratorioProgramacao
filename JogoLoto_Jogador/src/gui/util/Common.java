package gui.util;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

public class Common {
    private final static StringProperty value = new SimpleStringProperty();
    
    public static TextArea createDisplayField() {
        TextArea displayField = new TextArea();
        displayField.textProperty().bind(Bindings.format("%s", value));
        displayField.setEditable(false);
        displayField.setMaxHeight(5);
        displayField.setMinWidth(586);
        displayField.autosize();
        return displayField;
    }
    
    public static TextField createBetField() {
    	TextField textField = new TextField("");
        textField.setEditable(true);
        textField.setMaxHeight(5);
        textField.autosize();
        Constraints.setTextFieldInteger(textField);
        Constraints.setTextFieldMaxLength(textField, 2);
        return textField;
    }
    
    public static Label getLabel(String string) {
        Label label = new Label();
        label.setText(string);
        label.setMaxWidth(Double.POSITIVE_INFINITY);
        label.setMaxHeight(Double.POSITIVE_INFINITY);
        return label;
    }
    
    public static BorderPane getRoot(FlowPane top, GridPane center, FlowPane bottom) {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        root.setTop(top);
        root.setCenter(center);  
        root.setBottom(bottom);
        return root;
    }
    
    public static Scene getScene(BorderPane root) {
        Scene scene = new Scene(root, 1000, 400);
        scene.getStylesheets().add("application/application.css");
        return scene;
    }
}
