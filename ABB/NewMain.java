/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvore_binaria_busca;

import arvore_binaria_busca.ArvoreBB;
import arvore_binaria_busca.No;
import exercicio_abb.Pessoa;
import exercicio_abb.Pessoa;
import java.util.Collection;
import javax.swing.JOptionPane;

/**
 *
 * @author harri
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArvoreBB<Integer, Pessoa> listaPessoas = new ArvoreBB<>();
        Pessoa pessoa1 = new Pessoa(115,"Harrison");
        Pessoa pessoa2 = new Pessoa(116,"Maria");
        Pessoa pessoa3 = new Pessoa(110,"Roberto");
        Pessoa pessoa4 = new Pessoa(119,"Lucas");
        Pessoa pessoa5 = new Pessoa(105,"Mariana");
        //Pessoa pessoa6 = new Pessoa(104,"Pedro");
        Pessoa pessoa7 = new Pessoa(106,"Bruno");
        Pessoa pessoa8 = new Pessoa(118,"Paula");
        Pessoa pessoa9 = new Pessoa(120,"Anna");
        Pessoa pessoa10 = new Pessoa(111,"Gabriel");
        
        listaPessoas.inserir(pessoa1.getCpf(), pessoa1);
        listaPessoas.inserir(pessoa2.getCpf(), pessoa2);
        listaPessoas.inserir(pessoa3.getCpf(), pessoa3);
        listaPessoas.inserir(pessoa4.getCpf(), pessoa4);
        listaPessoas.inserir(pessoa5.getCpf(), pessoa5);
        //listaPessoas.inserir(pessoa6.getCpf(), pessoa6);
        listaPessoas.inserir(pessoa7.getCpf(), pessoa7);
        listaPessoas.inserir(pessoa8.getCpf(), pessoa8);
        listaPessoas.inserir(pessoa9.getCpf(), pessoa9);
        listaPessoas.inserir(pessoa10.getCpf(), pessoa10);
   
        int opcao = 0;
        while(opcao != 5){
        opcao = Integer.parseInt(JOptionPane.showInputDialog("Informa a opcão.\n 1-Inserir Pessoa.\n 2-Consultar Pessoa.\n 3-Remover Pessoas.\n 4- Exibir Ordenado.\n 5- Sair."));
        switch(opcao){
            case 1:
                Pessoa pessoa = new Pessoa();
                pessoa.cadastrarPessoa();
                listaPessoas.inserir(pessoa.getCpf(), pessoa);
                break;
            case 2:
                int cpf = Integer.parseInt(JOptionPane.showInputDialog("Informe o CPF:"));
                pessoa = listaPessoas.obter(cpf).getValor();
                System.out.println("Nome da Pessoa: "+pessoa.getNome()+"\n CPF: "+pessoa.getCpf());
                break;
            case 3:
                cpf = Integer.parseInt(JOptionPane.showInputDialog("Informe o CPF:"));
                listaPessoas.remover(cpf);
                
                break;
            case 4:
                listaPessoas.getOrdenado();
                System.out.println("Número de Pessoas: "+listaPessoas.tamanho());
                System.out.println("Sucessor de 119: "+listaPessoas.obter(pessoa2.getCpf()).getFilhoDireito().getChave());
                break;
                
            default:
                System.out.println("Valor inválido");
                break;
        }
        }
        
    }
    
}
