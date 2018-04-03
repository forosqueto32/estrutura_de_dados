/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvore_binaria_busca;
/**
 *
 * @author harri
 */
public interface IArvoreBB<K, V> {
    
    public boolean inserir(K chave, V valor);
    public boolean remover(K chave);
    public boolean contem(K chave);
    public int tamanho();
    public boolean vazia();
    public void limpar();
}
