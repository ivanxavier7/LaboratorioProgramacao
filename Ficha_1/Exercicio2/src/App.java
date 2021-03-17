import java.util.Locale;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        int intensidade;
        int humidade;
        int resistencia;
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		intensidade = sc.nextInt();
		humidade = sc.nextInt();
		resistencia = sc.nextInt();
		
		RefeicaoDeMinhoca comida1 = new RefeicaoDeMinhoca(intensidade, humidade, resistencia);
		
		intensidade = sc.nextInt();
		humidade = sc.nextInt();
		resistencia = sc.nextInt();
		
		RefeicaoDeMinhoca comida2 = new RefeicaoDeMinhoca(intensidade, humidade, resistencia);
		
		sc.close();
		
		if (comida1.getVelocidadeMedia() >= comida2.getVelocidadeMedia()) {
			System.out.println("Alimento 1");
		}
		else {
			System.out.println("Alimento 2");
		}
    }
}
