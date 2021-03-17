public class DesempenhoDeColaboradores {
    private int[] avaliacoes = {0, 0, 0, 0, 0, 0};
    private String[] percentagens = {"", "", "", "", "", ""};    
    
    public void setAvaliacao(int avaliacao) {
        try {
            if (avaliacao >= 0 && avaliacao < 30) {
                this.avaliacoes[0] ++;
            }
            else if (avaliacao >= 30 && avaliacao < 50) {
                this.avaliacoes[1] ++;
            }
            else if (avaliacao >= 50 && avaliacao < 75) {
                this.avaliacoes[2] ++;
            }
            else if (avaliacao >= 75 && avaliacao < 85) {
                this.avaliacoes[3] ++;
            }
            else if (avaliacao >= 85 && avaliacao < 95) {
                this.avaliacoes[4] ++;
            }
            else if (avaliacao >= 95 && avaliacao < 100) {
                this.avaliacoes[5] ++;
            }
            else {
                System.out.println("Por favor introduza um valor inteiro de 0 a 100:");
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public void getPercentagens() {
        int totalAvaliacoes = 0;
        for (int i=0; i<=5; i++) {
            totalAvaliacoes += avaliacoes[i];
        }
        if (totalAvaliacoes != 0) {
            System.out.println(totalAvaliacoes);
            for (int i=0; i<=5; i++) {
                double percentagem = (double) this.avaliacoes[i] / totalAvaliacoes  * 1000;
                percentagem = Math.round(percentagem) / 10.0;
                this.percentagens[i] = String.valueOf(percentagem) + "%";
                System.out.println(this.percentagens[i]);
            }
        }
        else {
            System.out.println("Por favor introduza uma avaliação antes de sair!");
        }
    }

}
