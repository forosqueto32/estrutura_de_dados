/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvore_binaria_busca;

import javax.swing.JOptionPane;

/**
 *
 * @author harri
 */
public class No<K extends Comparable<K>, V> {
    private K chave;
    private V valor;
    private No<K, V> pai, filhoEsquerdo, filhoDireito;
    
    public No(){
    }
    
    public K getChave() {
        return chave;
    }

    public V getValor() {
        return valor;
    }

    public No<K, V> getPai() {
        return pai;
    }

    public No<K, V> getFilhoEsquerdo() {
        return filhoEsquerdo;
    }

    public No<K, V> getFilhoDireito() {
        return filhoDireito;
    }

    public void setChave(K chave) {
        this.chave = chave;
    }

    public void setValor(V valor) {
        this.valor = valor;
    }

    public void setPai(No<K, V> pai) {
        this.pai = pai;
    }

    public void setFilhoEsquerdo(No<K, V> filhoEsquerdo) {
        this.filhoEsquerdo = filhoEsquerdo;
    }

    public void setFilhoDireito(No<K, V> filhoDireito) {
        this.filhoDireito = filhoDireito;
    }
    
   public boolean isLeaf(){
        return filhoEsquerdo == null && filhoDireito == null;
    }
   
   public boolean oneChild(){
       return filhoEsquerdo != null || filhoDireito != null;
   }
   
   public boolean twoChildren(){
       return filhoEsquerdo != null && filhoDireito != null;
   }
}
