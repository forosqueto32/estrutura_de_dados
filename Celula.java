package LDE;

public class Celula{//CLASSE PARA REPRESENTAR A CELULA
	
	private Celula proxima;//refer�ncia para a pr�xima c�lula , que pode ser null caso essa seja a �ltima c�lula da Lista.
	private Object elemento;// refer�ncia para o elemento a qual ela se refere
	private Celula anterior;
	
	
	public Celula(Object elemento) {
		this.elemento = elemento;
	}
	
	public Celula(Celula proxima, Object elemento) {	
		this.elemento = elemento;
		this.proxima = proxima;
	}
	
	public Celula(Celula anterior, Celula proxima, Object elemento) {	
		this.elemento = elemento;
		this.anterior = anterior;
		this.proxima = proxima;
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
	
	public void setElemento(Object elemento) {
		this.elemento = elemento;
	}
	
	public Celula getAnterior() {
		return anterior;
	}

	public void setAnterior(Celula anterior) {
		this.anterior = anterior;
	}

	

	
}


