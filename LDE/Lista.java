/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LDE;

/**
 *
 * @author 11645
 */
public interface Lista<Tipo> extends Iterable<Tipo>{
    
	
	
	public void adiciona(Tipo elemento);
		
	
    public void adiciona(int indice, Tipo elemento);
    public void adicionaNoInicio(Tipo elemento);
    
    public Tipo obter(int indice);
    
    public void remover(int indice);
    public boolean remover(Tipo valor);
    
    public void limpar();
    public int tamanho();
    
    public boolean contem(Tipo valor);
}
