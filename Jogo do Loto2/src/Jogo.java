public class Jogo {
    private int[] linhas = new int[3];
    public boolean isConclusaoCartao(int[][] cartao) {
        int nrEmFalta = 0;
        for (int lin = 0; lin<3; lin++) {
            int nrEmFaltaNaLinha = 0;
            for (int col = 0; col < 9; col++) {
                if (cartao[lin][col] >= 0 && cartao[lin][col] != 99) {
                    nrEmFalta ++;
                    nrEmFaltaNaLinha ++;
                }
            }
            if(nrEmFaltaNaLinha == 0) {                
                if(linhas[lin] == 0) {                   
                    System.out.println("\nA linha " + (lin + 1) + " foi concluída!\n");
                    linhas[lin] = 1;
                }    
            }     
        }
        if(nrEmFalta == 0) {
            return true;
        } return false;
    }
}
