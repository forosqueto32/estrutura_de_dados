/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvore_binaria_busca;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
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
        return this.tamanho == 0;
    }

    @Override
    public void limpar() {
        this.raiz = null;
        this.tamanho = 0;
    }

    @Override
    public No<K, V> menorNo() {
        if (this.raiz == null) {
            throw new NullPointerException("Arvore vazia");
        } else {
            No<K, V> no_atual = this.raiz;
            while (this.no_atual.getFilhoEsquerdo() != null) {
                no_atual = no_atual.getFilhoEsquerdo();
            }
        }
        return no_atual;
    }

    @Override
    public No<K, V> maiorNo() {
        if (this.raiz == null) {
            throw new NullPointerException("Arvore vazia");
        } else {
            No<K, V> no_atual = this.raiz;
            while (this.no_atual.getFilhoDireito() != null) {
                no_atual = no_atual.getFilhoEsquerdo();
            }
        }
        return no_atual;
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

    public Collection<No<K, V>> getPosOrdenado() {

        LinkedList<No<K, V>> visited = new LinkedList<>();
        Stack<No<K, V>> stack = new Stack<>();
        No<K, V> aux = null, aux2 = null, current;

        if (this.tamanho() == 0) {
            throw new NullPointerException("Empty Tree");
        } else {
            current = this.raiz;
            stack.push(current);
            while (visited.size() != this.tamanho) {
                while (current.getFilhoEsquerdo() != null && current.getFilhoEsquerdo() != aux
                        && current.getFilhoEsquerdo() != aux2) {
                    current = current.getFilhoEsquerdo();
                    stack.push(current);
                }
                if (current.getFilhoDireito() != null && current.getFilhoDireito() != aux) {
                    current = current.getFilhoDireito();
                    stack.push(current);
                } else if (current.getFilhoEsquerdo() == null || current.getFilhoEsquerdo() == aux2) {
                    aux = stack.pop();
                    visited.add(aux);
                    if (this.raiz.equals(aux)) {
                        break;
                    } else {
                        current = stack.peek();
                        aux2 = current.getFilhoEsquerdo();
                    }
                }
            }
        }
        return visited;
    }

    public Collection<No<K, V>> preOrdenadoRecursivo() {
        LinkedList<No<K, V>> lista = new LinkedList<>();
        preOrdenadoRecursivo(raiz, lista);
        return lista;
    }

    private void preOrdenadoRecursivo(No<K, V> no, LinkedList<No<K, V>> lista) {
        if (no != null) {
            lista.add(no);
            preOrdenadoRecursivo(no.getFilhoEsquerdo(), lista);
            preOrdenadoRecursivo(no.getFilhoDireito(), lista);
        }
    }

    public Collection<No<K, V>> emOrdemRecursivo() {
        LinkedList<No<K, V>> lista = new LinkedList<>();
        inorderRecursive(raiz, lista);
        return lista;
    }

    private void inorderRecursive(No<K, V> node, LinkedList<No<K, V>> lista) {
        if (node != null) {
            inorderRecursive(node.getFilhoEsquerdo(), lista);
            lista.add(node);
            inorderRecursive(node.getFilhoDireito(), lista);
        }
    }

    public Collection<No<K, V>> posOrdemRecursivo() {
        LinkedList<No<K, V>> list = new LinkedList<>();
        posOrdemRecursivo(raiz, list);
        return list;
    }

    private void posOrdemRecursivo(No<K, V> no, LinkedList<No<K, V>> lista) {
        if (no != null) {
            posOrdemRecursivo(no.getFilhoEsquerdo(), lista);
            posOrdemRecursivo(no.getFilhoDireito(), lista);
            lista.add(no);
        }
    }

    public Collection<No<K, V>> emLargura() {
        System.out.print("\nImpressÃ£o em Largura:\n");
        No<K, V> no_atual = raiz;
        Queue<No<K, V>> fila = new LinkedList<>();
        LinkedList<No<K, V>> elementos = new LinkedList<>();
        fila.add(raiz);
        while (!fila.isEmpty()) {
            if (no_atual.getFilhoEsquerdo() != null) {
                fila.add(no_atual.getFilhoEsquerdo());
            }
            if (no_atual.getFilhoDireito() != null) {
                fila.add(no_atual.getFilhoDireito());
            }
            elementos.add(fila.poll());
            System.out.print(elementos.getLast().getChave() + " ");
            no_atual = fila.peek();

        }
        return elementos;

    }
}
