/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvore_binaria_busca;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Stack;

/**
 *
 * @author harri
 */
public class ArvoreBB<K extends Comparable<K>, V> implements IArvoreBB<K, V> {

    private No<K, V> raiz;
    private int tamanho;
    private No<K, V> no_atual;// remover

    public ArvoreBB() {
        tamanho = 0;
    }

    @Override
    public boolean inserir(K chave, V valor) {
        No<K, V> novoNo = new No<>();
        novoNo.setChave(chave);
        novoNo.setValor(valor);

        if (raiz != null) {
            no_atual = raiz;
            boolean no_setado = false;
            while (no_setado == false) {
                int proximo_sentido = chave.compareTo(no_atual.getChave());
                System.out.println("Sentido: " + proximo_sentido);
                if (proximo_sentido < 0 && no_atual.getFilhoEsquerdo() == null) {
                    System.out.println(chave + " Adicionado na esquerda de: " + no_atual.getChave());
                    no_atual.setFilhoEsquerdo(novoNo);
                    novoNo.setPai(no_atual);
                    tamanho++;
                    return true;
                } else if (proximo_sentido == 0) {
                    no_atual.setValor(novoNo.getValor());
                    System.out.println(chave + " Substituido por.");
                    return true;
                } else if (proximo_sentido > 0 && no_atual.getFilhoDireito() == null) {
                    no_atual.setFilhoDireito(novoNo);
                    novoNo.setPai(no_atual);
                    tamanho++;
                    System.out.println(chave + " Adicionado na direita de: " + no_atual.getChave());
                    return true;
                }

                if (proximo_sentido < 0) {
                    no_atual = no_atual.getFilhoEsquerdo();
                } else if (proximo_sentido > 0) {
                    no_atual = no_atual.getFilhoDireito();
                }
            }

        } else {
            raiz = novoNo;
            tamanho++;
            System.out.println("Raiz: " + raiz.getChave());
            return true;
        }//s

        return false;
    }

    @Override
    public boolean remover(K chave) {
        No<K, V> no_remover = this.obter(chave);

        if (no_remover == null) {
            return false;
        } else {
            No<K, V> no_pai = no_remover.getPai();
            if (no_pai == null) {//CASO NÓ RAIZ
                raiz = null;
                tamanho--;
                return true;
            } else {
                int pai_lado_filho_remover = chave.compareTo(no_pai.getChave());//verificar se o filho está na esquerda ou direita
                if (no_remover.isLeaf()) {//CASO NÓ FOLHA
                    if (pai_lado_filho_remover < 0) {
                        no_pai.setFilhoEsquerdo(null);
                        tamanho--;
                        return true;
                    } else {
                        no_pai.setFilhoDireito(null);
                        tamanho--;
                        return true;
                    }
                } else if (no_remover.oneChild() && no_remover.twoChildren() == false) {//CASO UM FILHO
                    if (pai_lado_filho_remover < 0) {//NO_REMOVER ESTÁ NA ESQUERDA DO PAI
                        if (no_remover.getFilhoEsquerdo() != null) {
                            no_pai.setFilhoEsquerdo(no_remover.getFilhoEsquerdo());
                            tamanho--;
                            return true;
                        } else {
                            no_pai.setFilhoEsquerdo(no_remover.getFilhoDireito());
                            tamanho--;
                            return true;
                        }

                    } else {//NO_REMOVER ESTÁ NA DIREITA DO PAI
                        if (no_remover.getFilhoEsquerdo() != null) {
                            no_pai.setFilhoDireito(no_remover.getFilhoEsquerdo());
                            tamanho--;
                            return true;
                        } else {
                            no_pai.setFilhoDireito(no_remover.getFilhoDireito());
                            tamanho--;
                            return true;
                        }

                    }
                } else if (no_remover.twoChildren()) {//CASO DOIS FILHOS
                    boolean encontrado = false;
                    no_atual = no_remover.getFilhoDireito();
                    while (encontrado != true) {
                        if (no_atual.getFilhoEsquerdo() != null) {
                            no_atual = no_atual.getFilhoEsquerdo();
                        } else {
                            //encontrado = true;
                            //sucessor = no_atual;
                            K chaveAux = no_atual.getChave();
                            V valorAux = no_atual.getValor();
                            remover(no_atual.getChave());//ONE CHIELD
                            no_remover.setChave(chaveAux);//no_atual é agora o sucessor
                            no_remover.setValor(valorAux);
                            tamanho--;
                            return true;
                        }
                    }
                }
            }

            return false;
        }
    }

    @Override
    public boolean contem(K chave) {
        if (raiz == null) {
            throw new NullPointerException("Arvóre Vazia");
        } else {
            no_atual = raiz;
            int sentido = chave.compareTo(no_atual.getChave());//arrumar
            if (sentido == 0) {
                return true;
            } else {
                while (no_atual != null) {
                    sentido = chave.compareTo(no_atual.getChave());//arrumar
                    if (sentido < 0) {
                        no_atual = no_atual.getFilhoEsquerdo();
                    } else if (sentido > 0) {
                        no_atual = no_atual.getFilhoDireito();
                    } else if (sentido == 0) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public No<K, V> obter(K chave) {
        if (raiz == null) {
            throw new NullPointerException("Arvóre Vazia");
        } else {
            no_atual = raiz;
            int sentido = chave.compareTo(no_atual.getChave());//arrumar
            if (sentido == 0) {
                return no_atual;
            } else {
                while (no_atual != null) {
                    sentido = chave.compareTo(no_atual.getChave());//arrumar
                    if (sentido < 0) {
                        no_atual = no_atual.getFilhoEsquerdo();
                    } else if (sentido > 0) {
                        no_atual = no_atual.getFilhoDireito();
                    } else if (sentido == 0) {
                        return no_atual;
                    }
                }
            }
            return null;
        }

    }

    @Override
    public int tamanho() {
        return this.tamanho;
    }

    @Override
    public boolean vazia() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void limpar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Collection<No<K, V>> getOrdenado() {//MUITOS IFS
        LinkedList<No<K, V>> explorados = new LinkedList<>();
        Stack<No<K, V>> visitados = new Stack<>();
        System.out.println("Método getOrdenado Iniciado.");
        if (this.raiz == null) {
            throw new NullPointerException("Árvore Vázia.");
        } else {
            No<K, V> no_atual = this.raiz;
            visitados.push(no_atual);
            while (!visitados.isEmpty()) {
                if (no_atual.getFilhoEsquerdo() != null) {
                    no_atual = no_atual.getFilhoEsquerdo();
                    visitados.push(no_atual);
                }
                if (no_atual.getFilhoEsquerdo() == null) {
                    explorados.add(no_atual);
                }
                if (no_atual.getFilhoEsquerdo() == null && no_atual.getFilhoDireito() != null) {
                    no_atual = no_atual.getFilhoDireito();
                    visitados.pop();
                    visitados.push(no_atual);
                    continue;//ARRUMAR E NÃO PRECISAR DO CONTINUE
                }
                if (no_atual.getFilhoEsquerdo() == null && no_atual.getFilhoDireito() == null) {
                    visitados.pop();
                    if (!visitados.isEmpty() && visitados.peek().getFilhoDireito() != null) {
                        explorados.add(visitados.peek());
                        no_atual = visitados.peek().getFilhoDireito();
                        visitados.pop();
                        visitados.push(no_atual);

                    } else if (!visitados.isEmpty() && visitados.peek().getFilhoDireito() == null) {//VER SE ESSE ELSE VAI RODAR
                        explorados.add(visitados.peek());
                        visitados.pop();
                        if (!visitados.isEmpty() && visitados.peek().getFilhoDireito() == null) {
                            while (visitados.peek().getFilhoDireito() == null) {
                                explorados.add(visitados.peek());
                                visitados.pop();
                            }

                        }
                        if (!visitados.isEmpty() && visitados.peek().getFilhoDireito() != null) {
                            explorados.add(visitados.peek());
                            no_atual = visitados.peek().getFilhoDireito();
                            visitados.pop();
                            visitados.push(no_atual);
                        }

                    }
                    //no_atual = visitados.peek().getFilhoDireito();
                    //visitados.push(no_atual);
                }
                if (visitados.isEmpty()) {
                    System.out.println("Imprimindo a lista:");
                    for (No<K, V> c : explorados) {
                        System.out.println("Em Ordem: " + c.getChave() + "\n");
                    }
                    return explorados;
                }
            }
        }

        return null;
    }

    public Collection<No<K, V>> getPreOrdenado() {
        System.out.println("Pre Ordenado.");
        LinkedList<No<K, V>> explorados = new LinkedList<>();
        Stack<No<K, V>> visitados = new Stack<>();
        No<K, V> aux = null, aux2 = null, no_atual = this.raiz;

        if (no_atual == null) {
            throw new NullPointerException("Árvore Vázia");
        } else {
            explorados.add(no_atual);
            visitados.push(no_atual);
            while (explorados.size() != this.tamanho) {
                while (no_atual.getFilhoEsquerdo() != null && no_atual.getFilhoEsquerdo() != aux && no_atual.getFilhoEsquerdo() != aux2) {
                    no_atual = no_atual.getFilhoEsquerdo();
                    explorados.add(no_atual);
                    visitados.push(no_atual);
                }
                if (no_atual.getFilhoDireito() != null && no_atual.getFilhoDireito() != aux) {
                    no_atual = no_atual.getFilhoDireito();
                    explorados.add(no_atual);
                    visitados.push(no_atual);
                } else {
                    aux = visitados.pop();
                    no_atual = visitados.peek();
                    aux2 = no_atual.getFilhoEsquerdo();
                }
            }
        }

        return explorados;
    }
    
    
    public Collection<No<K, V>> getPosOrdenado(){
        System.out.println("Pós Ordenado.");
        LinkedList<No<K,V>> explorados = new LinkedList<>();
        Stack<No<K,V>> visitados = new Stack<>();
        No<K,V> aux1 = null, aux2 = null, no_atual = this.raiz;
        
        if(this.raiz == null){
            throw new NullPointerException("Arvore Vazia");
        }else{
            visitados.push(no_atual);
            while(!visitados.isEmpty()){
                if(no_atual.getFilhoEsquerdo() != null){
                    no_atual = no_atual.getFilhoEsquerdo();
                    visitados.push(no_atual);
                    continue;
                }else{
                    if(no_atual.getFilhoDireito() != null){
                        no_atual = no_atual.getFilhoDireito();
                        visitados.push(no_atual);
                        aux1 = no_atual;
                    }else{//caso nó folha
                        explorados.add(no_atual);
                        visitados.pop();
                        if(visitados.peek().getFilhoDireito() != null && visitados.peek().getFilhoDireito() != aux1){
                            no_atual = visitados.peek().getFilhoDireito();
                            aux1 = no_atual;
                            visitados.push(no_atual);//preciso de um Else if para caso seja nulo
                        }else if(visitados.peek().getFilhoDireito() != null && visitados.peek().getFilhoDireito() == aux1){
                                while(!visitados.isEmpty() && visitados.peek().getFilhoDireito() == aux1){
                                no_atual = visitados.peek();
                                aux1 = no_atual;
                                explorados.add(no_atual);
                                visitados.pop();
                                }
                                
                                if(!visitados.isEmpty() && visitados.peek().getFilhoDireito() != null){
                                    //System.out.println(visitados.peek().getChave());
                                    no_atual = visitados.peek().getFilhoDireito();
                                    visitados.push(no_atual);
                                    aux1 = no_atual;
                                }
                            
                        }
                    }
                }
            }
            
        }
        
        return explorados;
    }
    
    
}
