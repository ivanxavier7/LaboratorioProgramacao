package model.entities;

import java.util.Arrays;
import java.util.Random;

import org.json.JSONArray;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class Jogador {
    private int[] linhas = new int[3];

    public int[][] generateCartao(GridPane grid, TextField textField) {
        int[][] cartao = new int[3][9];
        for (int lin=0; lin<3; lin++) {
            int min = 1;
            if (min == 0) {
                min = min + 1;
            }
            for (int col=0; col<9; col++) {
                int nrRandom = (int) (Math.random() * 9) + min;
                cartao[lin][col] = nrRandom;
                textField.setText(String.valueOf(nrRandom));
                grid.add(textField, col, lin);
                if (col == 0) {
                    min += 9;
                } else if (col == 7) {
                    min += 12;
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

    public String getStringCartao(int[][] cartao) {
        return "\n" +
                Arrays.deepToString(cartao)
                .replace("],", "\n")
                .replace(",", "\t| ")
                .replaceAll("[\\[\\]]", " ")
                .replace(" -", "X ")
                .replace("99", "  ");
    }

    public String getStringMenu(){
        return "\n" 
        + "----------------------------------\n"
        + "------- BEM VINDO AO LOTO! -------\n"
        + "----------------------------------\n\n"
        + "O objetivo do loto é simples, assim que o cartão é concedido ao jogador, \n"
        + "ele terá de completar o número de linhas e colunas da matriz.\n\n"
        + "Os números que aparecerem marcados com um 'X',\n"
        + "são os numeros pertencentes ao cartão que o jogador já obteve. \n\n"
        + "Regras do Jogo:\n"
        + "  - Vence quem conseguir completar o cartão primeiro. \n"
        + "  - Os números admitidos neste loto vão de 1 a 90 inclusive.\n"
        + "  - No final do jogo, o jogador poderá voltar a jogar com o mesmo o cartão.\n"
        + "  - Terá a opção de criar um novo cartão, continuar ou abandonar o jogo. \n\n"
        + "Boa Sorte!";
    }

    public void setNrEscolhido(int nrEscolhido, int[][] cartao) {
        try {
            boolean nrChosen = false;
            boolean nrExist = false;
            int nrEscolhido1 = 0;
            int col = 0;
            if(nrEscolhido > 0 && nrEscolhido < 90){
                nrEscolhido1 = nrEscolhido/10;
            }
            else if(nrEscolhido == 90){
                nrEscolhido1 = 8;
            }
             if (nrEscolhido > 0 && nrEscolhido < 91) {
                for (int lin=0; lin<3; lin++) {
                   col = nrEscolhido1;
                   if(nrEscolhido == 90){
                       col=8;
                       ;
                   }
                   
                    if (nrEscolhido == cartao[lin][col]) {
                        cartao[lin][col] = 0 - cartao[lin][col];
                        nrExist = true;

                    } else if(nrEscolhido == -cartao[lin][col]){
                      nrChosen = true;
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
   
    public void getClone(int[][] cartaoCopia, int[][] cartao) {
        for (int lin = 0; lin<3; lin++) {
            for (int col = 0; col < 9; col++) {
                cartaoCopia[lin][col] = cartao[lin][col];
            }
        }
    } 
    
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
     
    public String restartGame(String resposta) {
        if(resposta.equals("sim") || resposta.equals("Sim")|| resposta.equals("S") || resposta.equals("s")){
            return "sim";
        } else if(resposta.equals("Sair") || resposta.equals("sair")){
            return "sair";}
        else if(resposta.equals("nao") || resposta.equals("Nao")|| resposta.equals("N") || resposta.equals("n")){
            return "nao";}
        else {
            return "";
        }
    }
}
