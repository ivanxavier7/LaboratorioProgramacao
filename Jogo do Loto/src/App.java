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

         
        
        try {
            Game jogo = new Game();   
            jogo.getStringMenu();
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

