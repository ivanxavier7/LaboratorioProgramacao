import java.util.Locale;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        String v = sc.nextLine();
        String[] arrayV = v.split(" ");
        for (String x : arrayV) {
            int y = Integer.parseInt(x);
            System.out.println("RESULTADO: " + y);
        }
    }
}
// teste