package gui.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Grid {
    private static GridPane grid= new GridPane();;
	
    /**
     * Devolve a grelha com os botoes inseridos
     *  
     *   @return	grid	
     *   
     *   @author	Ivan Xavier
     *   @since		1.0
     */
    public static GridPane getGrid() {
		return grid;
	}

    /**
     * Configura a grelha e carrega os botoes na mesma.
     *  
     *   @return	grid	Grelha na interface que contem os botoes com os numeros de 1 a 90
     *   
     *   @author	Ivan Xavier
     *   @since		1.0
     */
	public static GridPane createGrid() {
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(10));
        int buttonNr = 1;
        for (int row=1; row<=10; row++) {
        	for (int col=1; col<=9; col++) {
        		Button button = Buttons.createNumberButton(buttonNr, grid);
        		grid.add(button, row, col);
        		buttonNr ++;
        	}
        }
        return grid;
    }
}
