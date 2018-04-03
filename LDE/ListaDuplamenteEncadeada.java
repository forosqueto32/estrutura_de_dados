package LDE;

import java.util.Iterator;

public class ListaDuplamenteEncadeada<Tipo> implements Lista<Tipo> {
	
	private Celula primeira;
	private Celula ultima;
	private int totalElementos;
	
	@Override
	public void adiciona(Tipo elemento) {//O método adiciona() insere no fim da Lista 
		if(this.totalElementos == 0){
			this.adicionaNoInicio(elemento);
		}else{
			Celula nova = new Celula(elemento);
			this.ultima.setProxima(nova);
			nova.setAnterior(this.ultima);
			this.ultima = nova;
			this.totalElementos++;
		}
	}
	@Override
	public void adiciona(int posicao, Tipo elemento) {
		 if(posicao == 0){ // No começo.
			    this.adicionaNoInicio(elemento);
			  } else if(posicao == this.totalElementos-1){ // No fim.
			    this.adiciona(elemento);
			  } else {
			    Celula anterior = this.pegaCelula(posicao - 1);
			    Celula nova = new Celula(anterior.getProxima(), elemento);
			    anterior.setProxima(nova);
			    nova.setAnterior(anterior);
			    this.totalElementos++;
			  }
	}
	 
	private boolean posicaoOcupada(int posicao){
		return posicao >= 0 && posicao < this.totalElementos;
	}
	private Celula pegaCelula(int posicao){//vai me retorna o endereço da proxima celula da celula do parametro
		if(!this.posicaoOcupada(posicao)){
			throw new IllegalArgumentException("Posição inexistente");
		}
		
		Celula atual = this.primeira;
		for(int i = 0; i < posicao; i++){
			atual = atual.getProxima();//GET PROXIMA PEGARÁ O ENDEREÇO DA PROXIMA POSICAO DAS CELULAS
		}
		return atual;
		
		
	}
	@Override
	public Tipo obter(int posicao) {
		
		return (Tipo) this.pegaCelula(posicao).getElemento();
	}
	
	
	 
	public void remover(int posicao){
		if(!this.posicaoOcupada(posicao)){
			throw new IllegalArgumentException("Posicao inválida");
		}
		
		if(posicao == 0 ){
			this.removeDoComeco();
		}else if(posicao == this.totalElementos - 1){
			this.removeDoFim();
		}else{
			Celula anterior = this.pegaCelula(posicao-1);
			Celula atual = anterior.getProxima();
			Celula proxima = atual.getProxima();
			
			
			anterior.setProxima(proxima);
			proxima.setAnterior(anterior);
			
			this.totalElementos--;
			
			
		}
	}
	@Override
	public boolean remover(Tipo elemento){
		Celula atual = this.primeira;
		for(int i = 0; i < this.totalElementos;i++){
			if(atual.getElemento().equals(elemento)){
				if(i == 0){
					this.removeDoComeco();
				}
				else if(i == totalElementos-1){
					this.removeDoFim(); 
				}
				else{ this.remover(i);
				};
			}
			atual = atual.getProxima();
		}
		return false;
	}
	@Override
	public int tamanho() {
		return this.totalElementos;
	}
	
	@Override
	public boolean contem(Tipo elemento) {
		Celula atual = this.primeira;
		while(atual != null){
			if(atual.getElemento().equals(elemento)){
				return true;
			}
			atual = atual.getProxima();
		}
		return false;
	}
	@Override
	public void adicionaNoInicio(Tipo elemento) {
		
		if(this.totalElementos == 0){//caso especial da lista vazia	
			Celula nova = new Celula(this.primeira, elemento);
			nova.setAnterior(null);
			this.primeira = nova;
			this.ultima = this.primeira;
		}else{
			Celula nova = new Celula(this.primeira, elemento);
			this.primeira.setAnterior(nova);
			this.primeira = nova;
		}
		this.totalElementos++;
	}
	
	public void removeDoComeco() {
		if (!this.posicaoOcupada(0)) {
		    throw new IllegalArgumentException("Posição não existe");
		  }
		
		this.primeira = this.primeira.getProxima();
		this.totalElementos--;
		
		if (this.totalElementos == 0) {
		    this.ultima = null;
		  }
		
	}
	
	public void removeDoFim() {
			  if (!this.posicaoOcupada(this.totalElementos - 1)) {
			    throw new IllegalArgumentException("Posição não existe");
			  }
			  if (this.totalElementos == 1) {
			    this.removeDoComeco();
			  } else {
				  
			  Celula penultima = this.ultima.getAnterior();
			  penultima.setProxima(null);
			  this.ultima = penultima;
			  this.totalElementos--;
			  }
	}
	
	public void limpar(){//VERIFICAR
		this.primeira = null;
		this.ultima = null;
	}
	
	public String toString(){
		// Verificando se a Lista está vazia
		  if(this.totalElementos == 0){
		    return "[]";
		  }
		  
		  StringBuilder builder = new StringBuilder("[");
		  Celula atual = this.primeira;

		  // Percorrendo até o penúltimo elemento.
		  for (int i = 0; i < this.totalElementos - 1; i++) {
		    builder.append(atual.getElemento());
		    builder.append(", ");//anterior ", "
		    atual = atual.getProxima();
		  }

		  // último elemento
		  builder.append(atual.getElemento());
		  builder.append("]");
		  
		  return builder.toString();
		}
	
	public Iterator<Tipo> iterator(){
		return new Iterador<>(this.primeira);
	}
	
	public Iterator<Tipo> iteratorReverso(){
		return new IteradorReverso<>(this.ultima);
	}
}
