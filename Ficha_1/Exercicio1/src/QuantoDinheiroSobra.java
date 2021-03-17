public class QuantoDinheiroSobra {
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
}
