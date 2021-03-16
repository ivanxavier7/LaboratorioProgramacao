package teste1;

import java.util.Locale;
import java.util.Scanner;

public class Exercicio1 {
	private double carteira;
	private double preco;
	private double precoFruta;
	private double quantidadeFruta;
	
	public void setCarteira(double carteira) {
		this.carteira = carteira;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public void setPrecoFruta(double precoFruta) {
		this.precoFruta = precoFruta;
	}
	public void setQuantidadeFruta(double quantidadeFruta) {
		this.quantidadeFruta = quantidadeFruta;
	}
	public double getTotal() {
		return carteira - preco - precoFruta * quantidadeFruta;
	}

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		Exercicio1 objeto= new Exercicio1();
		objeto.setCarteira(sc.nextDouble());
		objeto.setPreco(sc.nextDouble());
		objeto.setPrecoFruta(sc.nextDouble());
		objeto.setQuantidadeFruta(sc.nextDouble());
		System.out.printf("Valor a pagar: %.2f", objeto.getTotal());
	}

}
