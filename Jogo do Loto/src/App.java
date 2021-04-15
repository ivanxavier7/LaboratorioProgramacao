import java.util.Scanner;
/**
 * Lotto Game developed for the curricular unit in Programming Laboratories
 * <p>
 * Main Application Class
 * @author Ivan Xavier - 92441
 * @author Simão Silva - 102914
 * @version 1.0
 */
public class App {
    /**
     * cartao - array used to store the card received by the game
     * cartaoOriginal - array used to hold the copy of the card received by the game
     */
    private static int[][] cartao = new int[3][9];
    private static int[][] cartaoOriginal = new int[3][9]; 
       
    /**
     * Main application
     * <p>
     * In this class we have the interactions between the player and the game,
     * as well as some handling of errors and exceptions.
     * <p>
     * The card order is made automatically, the player only has to enter numbers and decide
     * whether to play with a new card, keep the same card or leave.
     * @param args
     * @throws Exception simple error message, printed on the console 
     * @since 1.0
     */
    public static void main(String[] args) throws Exception {  

        System.out.println("");
        System.out.println("----------------------------------");
        System.out.println("--------BEM VINDO AO LOTO!--------");
        System.out.println("----------------------------------");
        System.out.println("");

        System.out.println("BREVE RESUMO DO LOTO");
        System.out.println("O objetivo do loto é bem simples, assim que o cartão é concedido ao jogador");
        System.out.println("ele terá de completar o numero de linhas e colunas da matriz. Os números");
        System.out.println("sorteados não serão feitos aqui para permitir que mais pessoas possam jogar.");
        System.out.println("Vence quem conseguir completar o cartão primeiro.");
        System.out.println("Este loto foi construido de maneira a que o jogador precise apenas de");
        System.out.println("introduzir um número valido(1 a 90 inclusivos) para começar a se divertir");
        System.out.println("Boa Sorte!");
        System.out.println("");
        System.out.println("");
        
        try {
            Game jogo = new Game();   
            cartao = jogo.generateCartao();
            jogo.getClone(cartaoOriginal, cartao);
            while (true) {
                try {    
                    Scanner scan = new Scanner(System.in);
                    // Print the card on the console
                    System.out.println("Cartão recebido:\n" + jogo.getStringCartao(cartao)); 
                    // Prompts the user to enter a number
                    System.out.println("Introduza o número sorteado:");
                    int numero = scan.nextInt();   
                    jogo.setNrEscolhido(numero, cartao);
                    // Asks if the card is complete
                    if(jogo.isConclusaoCartao(cartao)) {
                        
// ve aqui sfv
                        try {
                        System.out.println("Parabéns, Ganhou o Jogo!");
                        // Print the original card on the console
                        System.out.println("Cartão Original:\n" + jogo.getStringCartao(cartaoOriginal));
                        // Asks the user if he wants to continue playing
                        System.out.println("Deseja continuar a jogar com o mesmo cartão? S/n ou Sair");
                        String resposta = scan.next();
                        if(resposta.equals("Sair") || resposta.equals("sair")) {
                            System.out.println("Obrigado, volte sempre!");
                            scan.close();
                            break;
                        } 
                        // Restart the game depending on the answer
                        if(resposta.equals("S") || resposta.equals("sim") || resposta.equals("Sim") || resposta.equals("s")){
                            jogo.getClone(cartao, cartaoOriginal);
                        }
                        if(resposta.equals("N") || resposta.equals("nao") || resposta.equals("Nao") || resposta.equals("n")){
                            cartao = jogo.generateCartao();
                            jogo.getClone(cartaoOriginal, cartao);    
                        }
                    } catch (Exception e) {
                        System.out.println("Nao conseguimos compreender a sua decisão, diga nos de novo");
                    }                                                                     
                    }    
                } catch (Exception e) {
                    System.out.println("Ocorreu um erro! Por favor digite um valor válido: ");
                }                    
            } 
            } catch (Exception e) {
                System.out.println("Existe um erro dentro do jogo!");
        }   
    }
}

