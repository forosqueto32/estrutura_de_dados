package LSE;

public class Celula {//CLASSE PARA REPRESENTAR A CELULA
	
	private Celula proxima;//referência para a próxima célula , que pode ser null caso essa seja a última célula da Lista.
	private Object elemento;// referência para o elemento a qual ela se refere
	
	
	public Celula(Celula proxima, Object elemento) {
		this.proxima = proxima;
		this.elemento = elemento;
	}
	
	public Celula(Object elemento) {
		this.elemento = elemento;
	}
	
	//--Métodos--
	public Celula getProxima() {
		return proxima;
	}
	public void setProxima(Celula proxima) {
		this.proxima = proxima;
	}
	public Object getElemento() {
		return elemento;
	}
}

