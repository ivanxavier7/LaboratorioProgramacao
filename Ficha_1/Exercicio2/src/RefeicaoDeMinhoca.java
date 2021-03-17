public class RefeicaoDeMinhoca {
    private int intensidade, humidade, resistencia;
	private double distancia, tempo, velocidadeMedia;

	public RefeicaoDeMinhoca(int intensidade, int humidade, int resistencia) {
		super();
		this.setIntensidade(intensidade);
		this.setHumidade(humidade);
		this.setResistencia(resistencia);
	}

	public void setIntensidade(int intensidade) {
		if (intensidade >= 0 && intensidade <= 1000) {
			this.intensidade = intensidade;
		}
		else {
			System.out.println("Por favor introduza um valor de intensidade válido!");
		}
	}

	public void setHumidade(int humidade) {
		if (humidade >= 0 && humidade <= 100) {
			this.humidade = humidade;
		}
		else {
			System.out.println("Por favor introduza um valor de humidade válido!");
		}
	}

	public void setResistencia(int resistencia) {
		if (resistencia >= 0 && resistencia <= 10) {
			this.resistencia = resistencia;
		}
		else {
			System.out.println("Por favor introduza um valor de resistencia válido!");
		}

	}

	public double getDistancia() {
		distancia = Math.sqrt( (double) intensidade * 100);
		return distancia;
	}
	
	public double getVelocidadeDeslocacao() {
		return (double) humidade / resistencia;
	}

	public double getTempo() {
		tempo = this.getDistancia() / this.getVelocidadeDeslocacao();
		return tempo;
	}
	
	public double getVelocidadeMedia() {
		velocidadeMedia = this.getDistancia() / this.getTempo();
		return velocidadeMedia;
	}
}
