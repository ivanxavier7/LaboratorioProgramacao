import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        int numero = -1;
        while (numero > 0 && numero <= 90) {

            try {
                Scanner scan = new Scanner(System.in);
                Game jogo = new Game();
                jogo.generateCartao();
                jogo.getCartao();
                numero = scan.nextInt();
                jogo.setNrEscolhido(numero);
                scan.close();

            } catch (Exception e) {
                System.out.println("ocorreu um erro. Digite de novo ");
            }
        }

    }
}
