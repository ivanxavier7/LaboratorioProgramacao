import java.util.Arrays;
import java.util.Random;

/**
 * Lotto Game developed for the curricular unit in Programming Laboratories
 * Game Class
 * @author Ivan Xavier - 92441
 * @author Simão Silva - 102914
 * @version 1.0
 */

public class Game {

    /**
     * Generates the lotto card
     * <p>
     * The numbers generated at random are organized in intervals and columns,
     * the first column can have numbers between 1 and 9, inclusive,
     * the second column can have numbers between 10 and 19, inclusive,
     * and so on until the last column that can have numbers between 80 and 90 inclusive.
     * <p>
     * Calls the methods responsible for replacing the repeated numbers
     * and placing blank spaces on the card, in order to create the card
     * @return int[3x][9x] lotto game card
     * @since 1.0
     */
    public int[][] generateCartao() {
        int[][] cartao = new int[3][9];
        for (int lin=0; lin<3; lin++) {
            int min = 1;
            if (min == 0) {
                min = min + 1;
            }
            for (int col=0; col<9; col++) {
                int nrRandom = (int) (Math.random() * 9) + min;
                cartao[lin][col] = nrRandom;
                if (col == 0) {
                    min += 9;
                } else if (col == 7) {
                    min += 12;
                } else {
                    min += 10;
                }
            }
        }
        replaceRepeated(cartao);
        setWhiteSpaces(cartao);
        return cartao;
    }
    
    /**
     * Replaces repeated numbers
     * <p>
     * Go through all the elements of the card and does a search in the column looking for similar numbers,
     * if it finds a repeated number, divides that number by 10 and asks for the integer to extract the number from the column,
     * then increases a random value that will be assigned to the repeated position.
     * <p>
     * If we extract the column from the value 90 and increment a random value, we will pass the range, hence the use of the condition in column 9
     * @param cartao int[3x][9x] where you want to change the repeated numbers 
     * @since 1.0
     */
    private void replaceRepeated(int [][] cartao) {
        for (int lin=0; lin<3; lin++) {
            for (int col=0; col<9; col++) {
                for (int lin2=0; lin2<3; lin2++) {
                    if (lin != lin2) {
                        while (cartao[lin][col] == cartao[lin2][col]) {
                            int coluna = (int) (cartao[lin][col] / 10);
                            if (coluna == 9) {
                                coluna = 8;
                            } 
                            int novoNr = (int) (Math.random() * 9) + coluna * 10;
                            if (novoNr == 0) {
                                cartao[lin][col] = 1;
                            } else {
                                cartao[lin][col] = novoNr;
                            }
                        }
                    }
                }
            }
        }
    }
 
    /**
     * Assign white space
     * <p>
     * This method generates 4 random integers relative to the position to assign the blank space,
     * then assigns the value of 99 to those positions, when we use the substitution method to show the card on the console,
     * all values ​​with 99 are replaced by “ “, We can use this value because it is not within the range.
     * @param cartao int[3x][9x] card where you want to assign the blanks spaces
     * @since 1.0
     */
    private void setWhiteSpaces(int [][] cartao) {
        int[] posicoes;
        for (int lin=0; lin<3; lin++){
            posicoes = new Random().ints(0, 9).distinct().limit(4).toArray();
            for (int j=0; j<4; j++) {
                cartao[lin][posicoes[j]] = 99;  
            }
        }
    }

    /**
     * Returns the card object to be printed on the console.
     * <p>
     * This method converts the two-dimensional array of three rows and nine columns,
     * into a string with the desired format using the substitution method.
     * @param cartao int[3x][9x] two-dimensional array to store values
     * @return String with the card format
     * @since 1.0
     */
    public String getStringCartao(int[][] cartao) {
        return Arrays.deepToString(cartao)
                .replace("],", "\n")
                .replace(",", "\t| ")
                .replaceAll("[\\[\\]]", " ")
                .replace(" -", "X ")
                .replace("99", "  ");
    }

    /**
     * Validates and assigned to the chosen number
     * <p>
     * Validates the entry of the chosen number and check if the number exists on the card,
     * if it exists it is converted to its negative, when we replace the card to present on the console,
     * we change the symbol " -" to an "X " in order to mark the choice.
     * @param nrEscolhido int chosen by the user to be validated and assigned
     * @param cartao int[3x][9x] card to be marked
     * @since 1.0
     */
    public void setNrEscolhido(int nrEscolhido, int[][] cartao) {
        try {
            boolean nrExist = false;
            if (nrEscolhido > 0 && nrEscolhido < 91) {
                for (int lin=0; lin<3; lin++) {
                    for (int col=0; col<9; col++) {
                        if (nrEscolhido == cartao[lin][col]) {
                            cartao[lin][col] = 0 - cartao[lin][col];
                            nrExist = true;
                        }
                    }
                }
                if (nrExist == false) {  
                    System.out.println("Infelizmente esse numero não aparece no cartão ou ja foi usado");
                }
            } else {
                System.out.println("Erro, Digite um valor de 1 a 90!");
            }
        } catch (Exception e) {
            System.out.println("Erro, Digite um valor válido!");
        }
    }
   
    /**
     * Transfer information from one array to another
     * <p>
     * This method transfers information between two cards
     * @param cartao int[3x][9x] copy card
     * @param cartaoCopia int[3x][9x] original card
     * @since 1.0
     */
    public void getClone(int[][] cartaoCopia, int[][] cartao) {
        for (int lin = 0; lin<3; lin++) {
            for (int col = 0; col < 9; col++) {
                cartaoCopia[lin][col] = cartao[lin][col];
            }
        }
    } 
    
    /**
     * Checks whether the card is complete
     * <p>
     * This method goes through all the elements of the list and checks if there is no number besides the negatives,
     * which represent the user's choice and the elements with 99, which represent the blanks, if any, increment in the counter.
     * <p>
     * The card will only be completed when there are no missing numbers
     * @param cartao int[3x][9x] card where you want to check if it is complete
     * @return boolean translates the verification result to truth or lie
     * @since 1.0
     */
    public boolean isConclusaoCartao(int[][] cartao) {
        int nrEmFalta = 0;
        for (int lin = 0; lin<3; lin++) {
            for (int col = 0; col < 9; col++) {
                if (cartao[lin][col] >= 0 && cartao[lin][col] != 99) {
                    nrEmFalta ++;
                }
            }
        }
        if(nrEmFalta == 0) {
            return true;
        } return false;
    }
     
    /** 
     * Convert the answer to a Boolean
     * <p>
     * The data entered by the user can be summed up in a positive or negative answer,
     * this method allows this conversion.
     * @param resposta a string to interpret the positive answer
     * @return Boolean translation of the answer to positive or negative
     * @since 1.0
     */
    public Boolean restartGame(String resposta) {
        if (resposta.equals("sim") || resposta.equals("Sim")|| resposta.equals("S") || resposta.equals("s") || resposta.equals("")){
            return true;
        } return false;
    }
}
