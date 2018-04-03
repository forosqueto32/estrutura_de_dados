package LDE;

import java.util.Iterator;

public class IteradorReverso<Tipo> implements Iterator<Tipo> {
	
	private Celula celula;
	
	public IteradorReverso(Celula celula) {
		this.celula = celula;
	}

	@Override
	public boolean hasNext() {
		
		return (this.celula != null);
	}

	@Override
	public Tipo next() {
		Tipo c = (Tipo) celula.getElemento();
		if(this.hasNext()){
			c = (Tipo)this.celula.getElemento();
			this.celula = this.celula.getAnterior();
		}
		return c;
	}

}
