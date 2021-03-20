import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;


public class App {

    public static void findCombinations(int[] A, int out, int i, int k)
    {
        if (k == 0)
        {
            System.out.println(out);
            return;
        }
        
        for (int j = i; j < A.length; j++)
        {
            findCombinations(A, out + A[j], j + 1, k - 1);
        }
    }
    public static void main(String[] args) throws Exception {
        int nrPromocoes = 4;
        int inventario = 9;
        int[] quantidadeCompra = {};
        int[] valorCompra = {};


        String exemplo1 = "4 7";
        String[] arrayStrings = exemplo1.split(" ");
        quantidadeCompra = Arrays.copyOf(quantidadeCompra, quantidadeCompra.length + 1);
        quantidadeCompra[quantidadeCompra.length - 1] = Integer.parseInt(arrayStrings[0]);

        valorCompra = Arrays.copyOf(valorCompra, valorCompra.length + 1);
        valorCompra[valorCompra.length - 1] = Integer.parseInt(arrayStrings[1]);

        String exemplo2 = "5 5";
        arrayStrings = exemplo2.split(" ");
        quantidadeCompra = Arrays.copyOf(quantidadeCompra, quantidadeCompra.length + 1);
        quantidadeCompra[quantidadeCompra.length - 1] = Integer.parseInt(arrayStrings[0]);

        valorCompra = Arrays.copyOf(valorCompra, valorCompra.length + 1);
        valorCompra[valorCompra.length - 1] = Integer.parseInt(arrayStrings[1]);

        String exemplo3 = "3 4";
        arrayStrings = exemplo3.split(" ");
        quantidadeCompra = Arrays.copyOf(quantidadeCompra, quantidadeCompra.length + 1);
        quantidadeCompra[quantidadeCompra.length - 1] = Integer.parseInt(arrayStrings[0]);

        valorCompra = Arrays.copyOf(valorCompra, valorCompra.length + 1);
        valorCompra[valorCompra.length - 1] = Integer.parseInt(arrayStrings[1]);

        String exemplo4 = "2 3";
        arrayStrings = exemplo4.split(" ");
        quantidadeCompra = Arrays.copyOf(quantidadeCompra, quantidadeCompra.length + 1);
        quantidadeCompra[quantidadeCompra.length - 1] = Integer.parseInt(arrayStrings[0]);
        System.out.println(Arrays.toString(quantidadeCompra));

        valorCompra = Arrays.copyOf(valorCompra, valorCompra.length + 1);
        valorCompra[valorCompra.length - 1] = Integer.parseInt(arrayStrings[1]);
        System.out.println(Arrays.toString(valorCompra));
        System.out.println("EXEMPLO DE TESTE:1");
        findCombinations(quantidadeCompra,0 , 0, 3);
        System.out.println("EXEMPLO DE TESTE:2");
        findCombinations(valorCompra,0 , 0, 3);


        for (String x : arrayStrings) {
            int y = Integer.parseInt(x);
            System.out.println("RESULTADO: " + y);
        }

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        String v =  sc.nextLine();
        String[] arrayV = v.split(" ");
        for (String x : arrayV) {
            int y = Integer.parseInt(x);
            System.out.println("RESULTADO: " + y);
        }
        sc.close();
    }
}
