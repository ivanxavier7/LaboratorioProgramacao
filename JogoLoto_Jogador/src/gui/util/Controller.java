package gui.util;

import javafx.scene.control.Label;

public class Controller {
    public static void changeMessage(Label logger, String string) {
        logger.setMaxHeight(5);
        logger.setText(string);
        logger.autosize();
    }
}
