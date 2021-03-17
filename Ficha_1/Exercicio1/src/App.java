import java.util.Locale;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		QuantoDinheiroSobra objeto= new QuantoDinheiroSobra();
		objeto.setCarteira(sc.nextDouble());
		objeto.setPreco(sc.nextDouble());
		objeto.setPrecoFruta(sc.nextDouble());
		objeto.setQuantidadeFruta(sc.nextDouble());
        sc.close();
		System.out.printf("Valor a pagar: %.2f", objeto.getTotal());
    }
}
