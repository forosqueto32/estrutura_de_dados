package LSE;

public class Celula {//CLASSE PARA REPRESENTAR A CELULA
	
	private Celula proxima;//refer�ncia para a pr�xima c�lula , que pode ser null caso essa seja a �ltima c�lula da Lista.
	private Object elemento;// refer�ncia para o elemento a qual ela se refere
	
	
	public Celula(Celula proxima, Object elemento) {
		this.proxima = proxima;
		this.elemento = elemento;
	}
	
	public Celula(Object elemento) {
		this.elemento = elemento;
	}
	
	//--M�todos--
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

