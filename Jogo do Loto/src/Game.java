import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private int[][] cartao = new int[3][9];
    private int[][] cartaoOriginal = new int[3][9];
    private int[][] cartaoVelho = new int[3][9];
    private int nrEscolhido;
    private boolean conclusaoCartao;

    public Game() {
        this.cartao = generateCartao();
    }

    public void getCartao() {
        // Print card
        System.out.println("Cartão recebido:");
        //System.out.println(Arrays.deepToString(cartao));
        System.out.println(Arrays.deepToString(cartao)
                .replace("],", "\n")
                .replace(",", "\t| ")
                .replaceAll("[\\[\\]]", " ")
                .replace(" 0", " X")
                .replace("99", "  "));
    }

    public void getCartaoOriginal() {
        Scanner scan = new Scanner(System.in);
        
        // Print card
        System.out.println("Cartão original:");
        //System.out.println(Arrays.deepToString(cartao));
        System.out.println(Arrays.deepToString(cartaoOriginal)
                .replace("],", "\n")
                .replace(",", "\t| ")
                .replaceAll("[\\[\\]]", " ")
                .replace(" 0", " X")
                .replace("99", "  "));
        System.out.println("Deseja continuar a jogar com o mesmo cartão? S/n");
        String resposta = scan.nextLine();
        if (resposta.equals("sim") || resposta.equals("Sim")|| resposta.equals("S") || resposta.equals("s") || resposta.equals("")){
            if(cartao != cartaoVelho){
                cartao = cartaoVelho;
            }
            
            }
            this.cartao = cartaoOriginal;
            if(cartaoOriginal != cartaoVelho){
                cartaoOriginal = cartaoVelho;
            
            getCartao();

        }
        else {
            this.cartao = generateCartao();
            getCartao();
        }
        
    }

    public void setNrEscolhido(int nrEscolhido) {
        try {
            if (nrEscolhido > 0 && nrEscolhido < 91) {
                // Procurar no cartao a correspondencia ao numero escolhido
                this.nrEscolhido = nrEscolhido;
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
                System.out.println("Erro. Digite de novo: ");
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro: " + e);
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
                cartaoOriginal[i][j] = nrRandom;
                cartaoVelho[i][j] = nrRandom;

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
        setWhiteSpaces();
        return cartao;
    }

    private void checkRepeated() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                for (int p = 0; p < 3; p++) {
                    if (i != p) {
                        while (cartao[i][j] == cartao[p][j]) {
                            int coluna = (int) (cartao[i][j] / 10);
                            int x = (int) (Math.random() * 9) + coluna * 10;
                            if (x == 0) {
                                cartao[i][j] = 1;
                                cartaoOriginal[i][j] = 1;
                                cartaoVelho[i][j] = 1;
                            } else {
                                cartao[i][j] = x;
                                cartaoOriginal[i][j] = x;
                                cartaoVelho[i][j] = x;
                            }
                        }
                    }
                }
            }
        }
    }

    private void setWhiteSpaces() {
        int[] posicoes;
        for (int i=0; i<3; i++){
            posicoes = new Random().ints(0, 9).distinct().limit(8).toArray();
            //System.out.println(Arrays.toString(posicoes));
            for (int j=0; j<8; j++) {
                cartao[i][posicoes[j]] = 99;
                cartaoOriginal[i][posicoes[j]] = 99;  
                cartaoVelho[i][posicoes[j]] = 99;  
            }
        }
    }

    public boolean isConclusaoCartao() {
        conclusaoCartao = false;
        int nrEmFalta = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                if (cartao[i][j] != 0 && cartao[i][j] != 99) {
                    nrEmFalta ++;
                }
            }
        }
        if(nrEmFalta == 0) {
            conclusaoCartao = true;
        }
        return conclusaoCartao;
    }

}
