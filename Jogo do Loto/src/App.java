import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
            Scanner scan = new Scanner(System.in);
            Game jogo = new Game();
            try {
                jogo.generateCartao();
                jogo.getCartao();
                while (true) {
                    try {
                        System.out.println("Introduza o numero sorteado:");
                        int numero = scan.nextInt();
                        if (numero > 0 && numero <= 90) {    
                            jogo.setNrEscolhido(numero);
                        if(jogo.isConclusaoCartao()) {
                            System.out.println("Chamou a funcao! Introduzir aqui a parte de continuar");
                            break;
                        }
                        } else {
                            System.out.println("Ocorreu um erro! Por favor digite de novo: 1");
                        }
                    } catch (Exception e) {
                        System.out.println("Ocorreu um erro! Por favor digite de novo: 2" + e);
                    }                    
                }
            } catch (Exception e) {
                System.out.println("Ocorreu um erro! Por favor digite de novo: 3" + e);
            }
        }
    }

