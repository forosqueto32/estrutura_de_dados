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
public class Main {

    public static void main(String[] args) {
        ArvoreBB<String, Integer> arvore = new ArvoreBB<>();
        arvore.inserir("j", 30);
        arvore.inserir("c", 15);
        arvore.inserir("m", 20);
        //arvore.inserir("Bruno", 19);
        arvore.inserir("a", 19);
        arvore.inserir("f", 15);
        arvore.inserir("e", 20);
        arvore.inserir("g", 45);
        arvore.inserir("l", 45);
        arvore.inserir("q", 45);
        arvore.inserir("o", 45);
       arvore.inserir("t", 45);
        arvore.inserir("s", 45);
        arvore.inserir("v", 45);
        arvore.getOrdenado();
        /*
        System.out.println("Contém " + arvore.contem("d"));
        System.out.println("Tamanho:" + arvore.tamanho());
        System.out.println("Filho esquerdo de d:" + arvore.obter("d").getFilhoEsquerdo().getChave());        
        System.out.println(arvore.remover("a"));
        arvore.remover("h");
        System.out.println("Filho Direito: "+arvore.obter("d").getFilhoDireito().getChave());
        System.out.println(arvore.remover("k"));
        System.out.println("Obter:"+arvore.obter("i").getValor());
        */
        
       
    }

}
