import java.util.Arrays;

public class Game {
    private int [][] cartao = new int[3][9];
    private int nrEscolhido;

    public void getCartao() {
        generateCartao();
        checkRepeated();
        // Printa card
        System.out.println("Cartão recebido:");
        System.out.println(Arrays.deepToString(cartao)
            .replace("],","\n").replace(",","\t| ")
            .replaceAll("[\\[\\]]", " "));
    }
    public void setCartao(int i, int j, int valor) {
        this.cartao[i][j] = valor;
    }

    public void setNrEscolhido(int nrEscolhido) {
        this.nrEscolhido = nrEscolhido;
    }

    private int[][] generateCartao() {
        for (int i = 0; i < 3; i++) {
            int min = 1;
            for (int j = 0; j < 9; j++) {
                int nrRandom = (int)(Math.random() * 9) + min;
                cartao[i][j] = nrRandom;
                
                if (j == 0) {
                    min += 9;
                }
                else if (j == 7) {
                    min += 12;
                }
                else {
                    min += 10;
                }
            }
        }
        return cartao;
    }

    public void checkRepeated() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                for (int p=0; p<3; p++){
                    if( i != p) {
                        while (cartao[i][j] == cartao[p][j]) {
                            int min = (int) (cartao[i][j] / 10); 
                            cartao[i][j] = (int)(Math.random() * 9) + min * 10;
                        }
                    }
                }           
            }
        }
    }

    public void setCartao(int[][] cartao) {
        this.cartao = cartao;
    }

}
