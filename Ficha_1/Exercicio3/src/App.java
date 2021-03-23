import java.util.Locale;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {
        int avaliacao = 0;
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        DesempenhoDeColaboradores teste = new DesempenhoDeColaboradores();

        DesempenhoDeColaboradores colaboradores = new DesempenhoDeColaboradores();

        while (true) {
            avaliacao = sc.nextInt();
            if(avaliacao == -1) {
                break;
            }
            else if(avaliacao >= 0 && avaliacao <= 100) {
                colaboradores.setAvaliacao(avaliacao);
            }
            else {
                System.out.println("Por favor introduza um valor inteiro entre 0 e 100:");
            }
        }

        sc.close();
        colaboradores.getPercentagens();
    }
}
