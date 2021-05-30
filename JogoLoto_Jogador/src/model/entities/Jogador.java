package model.entities;

import java.util.Random;


import gui.util.Controller;
import javafx.scene.control.Label;

public class Jogador {
	private double aposta = 0;
    
    public void setAposta(double aposta, Label logger) {
    	Controller.changeMessage(logger, String.format("Apostou %.2f euros", aposta));
		this.aposta = aposta;
	}
    
	public double getAposta() {
		return aposta;
	}

	public int[][] generateCartao() {
        int[][] cartao = new int[3][9];
        for (int lin=0; lin<3; lin++) {
            int min = 1;
            if (min == 0) {
                min = min + 1;
            }
            for (int col=0; col<9; col++) {
            	if(col == 0) {
	                int nrRandom = (int) (Math.random() * 9) + min;
	                cartao[lin][col] = nrRandom;
                } else if(col> 0 && col<8){
                	int nrRandom = (int) (Math.random() * 10) + min;
                	cartao[lin][col] = nrRandom;
                } else if(col==8) {
                	int nrRandom = (int) (Math.random() * 11) + min;
                	cartao[lin][col] = nrRandom;
                }
                if (col == 0) {
                    min += 9;
                } else if (col == 7) {
                    min += 10;
                } else {
                    min += 10;
                }
            }
        }
        replaceRepeated(cartao);
        setWhiteSpaces(cartao);
        return cartao;
    }
    
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
 
    private void setWhiteSpaces(int [][] cartao) {
        int[] posicoes;
        for (int lin=0; lin<3; lin++){
            posicoes = new Random().ints(0, 9).distinct().limit(4).toArray();
            for (int j=0; j<4; j++) {
                cartao[lin][posicoes[j]] = 99;  
            }
        }
    }

    public void getClone(int[][] cartaoCopia, int[][] cartao) {
        for (int lin = 0; lin<3; lin++) {
            for (int col = 0; col < 9; col++) {
                cartaoCopia[lin][col] = cartao[lin][col];
            }
        }
    } 
     
}
