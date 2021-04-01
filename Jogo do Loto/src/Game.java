import java.util.Arrays;

public class Game {
    private int[][] cartao = new int[3][9];
    private int nrEscolhido;

    public void getCartao() {
        // Printa card
        System.out.println("Cartão recebido:");
        System.out.println(Arrays.deepToString(cartao).replace("],", "\n").replace(",", "\t| ")
                .replaceAll("[\\[\\]]", " ").replace(" 0", " X"));
        System.out.println("Introduza o numero sorteado:");

    }

    public void setCartao(int i, int j, int valor) {
        this.cartao[i][j] = valor;
    }

    public void setNrEscolhido(int nrEscolhido) {
        this.nrEscolhido = nrEscolhido;

        try {
            if (this.nrEscolhido > 0 && this.nrEscolhido < 91) {
                // procurar no cartao a correspondencia ao numero escolhido
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (this.nrEscolhido == cartao[i][j]) {
                            // trocar valor igual por 0
                            this.cartao[i][j] = 0;
                            getCartao();
                        }
                    }
                }

            } else {
                System.out.println("Erro. Digite de novo:");
            }
        } catch (Exception e) {
            System.out.println("ocorreu um erro:" + e);
        }
    }

    public int[][] generateCartao() {
        for (int i = 0; i < 3; i++) {
            int min = 1;
            if (min == 0) {
                min = min + 1;
            }

            for (int j = 0; j < 9; j++) {
                int nrRandom = (int) (Math.random() * 9) + min;
                cartao[i][j] = nrRandom;

                if (j == 0) {
                    min += 9;
                } else if (j == 7) {
                    min += 12;
                } else {
                    min += 10;
                }
            }
        }
        checkRepeated();
        return cartao;
    }

    public void checkRepeated() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                for (int p = 0; p < 3; p++) {
                    if (i != p) {
                        while (cartao[i][j] == cartao[p][j]) {

                            int coluna = (int) (cartao[i][j] / 10);

                            int x = (int) (Math.random() * 9) + coluna * 10;
                            if (x == 0) {
                                cartao[i][j] = 1;
                            } else {
                                cartao[i][j] = x;
                            }
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
