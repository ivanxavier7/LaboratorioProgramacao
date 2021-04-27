public class Sorteio {
    private int[] painel;
    public void setPainel(int[] painel) {
        this.painel = painel;
    }

    public int getNrSorteado() {
        return nrSorteado;
    }

    public void setNrSorteado(int nrSorteado) {
        this.nrSorteado = nrSorteado;
    }

    private int nrSorteado;
    
    public int[] getPainel(){
        return painel;
    }
}
