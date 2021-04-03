import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
            Scanner scan = new Scanner(System.in);
            Game jogo = new Game();
            try {
                //int[][] cartao = jogo.generateCartao();
                jogo.getCartao();
                while (true) {
                    try {
                        System.out.println("Introduza o numero sorteado:");
                        int numero = scan.nextInt();
                        if (numero > 0 && numero <= 90) {    
                            jogo.setNrEscolhido(numero);
                        if(jogo.isConclusaoCartao()) {                                                                            
                            if(jogo.getCartaoOriginal() == false) {
                                break;
                        };
                        }
                        } else {
                            System.out.println("Ocorreu um erro! Por favor digite de novo: 1");
                        }
                    } catch (Exception e) {
                        System.out.println("Ocorreu um erro! Por favor digite de novo: 2" + e);
                        break;
                    }                    
                }
            } catch (Exception e) {
                System.out.println("Ocorreu um erro! Por favor digite de novo: 3" + e);
            }
        }
    }

