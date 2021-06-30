package model.entities;

import java.util.Random;


import gui.util.Controller;
import javafx.scene.control.Label;

/**
 *	Classe responsável pelo geração do cartão e pelas apostas do utilizador
 *  
 *   @author      Ivan Xavier
 *   @version     1.0
 *   @since       1.0
 */
public class Jogador {
	public double aposta = 0;

	/**
     *	Associa um valor á aposta do utilizador.
     *	 @param		aposta		Valor fracionário ou inteiro
     *   @param		logger	Campo de texto na interface para mostrar mensagens
     *   
     *   @author      Ivan Xavier
     *   @since       1.0
     */
    public void setAposta(double aposta, Label logger) {
    	Controller.changeMessage(logger, String.format("Apostou %.2f euros", aposta));
		this.aposta = aposta;
	}
    
    /**
     *	Devolve o valor apostado pelo utilizador.
     *
     *	 @return	aposta		Valor fracionário ou inteiro
     *   
     *   @author      Ivan Xavier
     *   @since       1.0
     */
	public double getAposta() {
		return aposta;
	}

    /**
     *	Método responsável por gerar o cartão e guardar o mesmo num array bidimensional.
     *  Os números são gerados aleatóriamente e estão organizados por intervalos e colunas.
     *  Este método chama outros para confirmar se existem repetidos ou espaços em branco.
     *
     *	 @return	cartao		Array[3x][9x]
     *
     *	 @see	#replaceRepeated(int[][])
     *	 @see	#setWhiteSpaces(int[][])
     *   
     *   @author      Ivan Xavier
     *   @since       1.0
     */
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
    
    /**
     *	Verifica se existem números repetidos no cartão gerado e substitui os mesmos
     *  por outro número gerado de forma aleatória.
     *
     *	 @param	cartao		Array[3x][9x]
     *   
     *   @author      Ivan Xavier
     *   @since       1.0
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
     *	Atribui espaços em branco ao cartão de forma aleatória.
     *
     *	 @param	cartao		Array[3x][9x]
     *   
     *   @author      Ivan Xavier
     *   @since       1.0
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
     *	Transfere a informação de um cartão para outro, permitindo a sua cópia.
     *	Método usado para voltar ao cartão original
     *
     *	 @param	cartaoCopia		Array[3x][9x] Cópia do array
     *	 @param	cartao			Array[3x][9x] Array a copiar
     *   
     *   @author      Ivan Xavier
     *   @since       1.0
     */
    public void getClone(int[][] cartaoCopia, int[][] cartao) {
        for (int lin = 0; lin<3; lin++) {
            for (int col = 0; col < 9; col++) {
                cartaoCopia[lin][col] = cartao[lin][col];
            }
        }
    } 
     
}
