public class Jogador {
    public void setNrEscolhido(int nrEscolhido, int[][] cartao) {
        try {
            boolean nrChosen = false;
            boolean nrExist = false;
            if (nrEscolhido > 0 && nrEscolhido < 91) {
                for (int lin=0; lin<3; lin++) {
                    for (int col=0; col<9; col++) {
                        if (nrEscolhido == cartao[lin][col]) {
                            cartao[lin][col] = 0 - cartao[lin][col];
                            nrExist = true;

                        } else if(nrEscolhido == -cartao[lin][col]){
                            nrChosen = true;
                        }
                    }
                }
                if (nrChosen == true) {  
                    System.out.println("Este número já foi escolhido!");
                } else if (nrExist == false) {  
                    System.out.println("Infelizmente este número não existe no cartão!");
                }                
            } else {
                System.out.println("Erro, Digite um valor de 1 a 90!");
            }
        } catch (Exception e) {
            System.out.println("Erro, Digite um valor válido!");
        }
    }
}
