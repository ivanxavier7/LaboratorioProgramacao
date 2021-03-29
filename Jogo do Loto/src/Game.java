import java.util.Arrays;

public class Game {
    private int [][] cartao = new int[3][9];
    private int nrEscolhido;

    public void getCartao() {
        generateCartao();
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
                //System.out.println("arr[" + i + "][" + j + "] = " + cartao[i][j]);
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
    public void setCartao(int[][] cartao) {
        this.cartao = cartao;
    }

}
