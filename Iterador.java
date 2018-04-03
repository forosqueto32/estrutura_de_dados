package LDE;

import java.util.Iterator;

public class Iterador<Tipo> implements Iterator<Tipo> {
	private Celula celula;

	public Iterador(Celula celula){
		this.celula = celula;
	}
	
	@Override
	public boolean hasNext() {
		
		return (this.celula != null);
	}

	@Override
	public Tipo next() {
		Tipo c = (Tipo) this.celula.getElemento();
		if(this.hasNext()){
			c = (Tipo)celula.getElemento();
			this.celula = this.celula.getProxima();
		}
		return c;
	}

}
