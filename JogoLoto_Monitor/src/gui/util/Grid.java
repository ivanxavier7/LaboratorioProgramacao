package gui.util;

import gui.components.Buttons;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Grid {
    private static GridPane grid= new GridPane();;
	
    public static GridPane getGrid() {
		return grid;
	}

	public static void setGrid(GridPane grid) {
		Grid.grid = grid;
	}

	public static GridPane createGrid() {
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(10));
        int buttonNr = 1;
        for (int r=1; r<=10; r++) {
        	for (int c=1; c<=9; c++) {
        		Button button = Buttons.createNumberButton(buttonNr, grid);
        		grid.add(button, r, c);
        		buttonNr ++;
        	}
        }
        return grid;
    }
}
