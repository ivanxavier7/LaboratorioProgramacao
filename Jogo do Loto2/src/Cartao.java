import java.util.Arrays;
import java.util.Random;

public class Cartao {
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
                                cartao[lin2][col] = 1;
                            } else {
                                cartao[lin2][col] = novoNr;
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
     * @since 1.3
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
        return "\n" +
                Arrays.deepToString(cartao)
                .replace("],", "\n")
                .replace(",", "\t| ")
                .replaceAll("[\\[\\]]", " ")
                .replace(" -", "X ")
                .replace("99", "  ");
    }

    /**
     * Returns the menu to be printed on the console.
     * <p>
     * This method show a little resume of the game.
     * @return a string with the game menu
     * @since 1.3
     */
    public String getStringMenu(){
        return "\n" 
        + "----------------------------------\n"
        + "------- BEM VINDO AO LOTO! -------\n"
        + "----------------------------------\n\n"
        + "O objetivo do loto é simples, assim que o cartão é concedido ao jogador, \n"
        + "ele terá de completar o número de linhas e colunas da matriz.\n\n"
        + "Os números que aparecerem marcados com um 'X',\n"
        + "são os numeros pertencentes ao cartão que o jogador já obteve. \n\n"
        + "Regras do Jogo:\n"
        + "  - Vence quem conseguir completar o cartão primeiro. \n"
        + "  - Os números admitidos neste loto vão de 1 a 90 inclusive.\n"
        + "  - No final do jogo, o jogador poderá voltar a jogar com o mesmo o cartão.\n"
        + "  - Terá a opção de criar um novo cartão, continuar ou abandonar o jogo. \n\n"
        + "Boa Sorte!";
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
}
