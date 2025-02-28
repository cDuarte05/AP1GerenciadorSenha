package sistema;

import java.util.Scanner;

import estrutura_fila.FilaComum;
import estrutura_fila.FilaPrioritaria;
import estrutura_fila.GerenciamentoFila;
import estrutura_fila.Senha;

public class Menu {
	
	GerenciamentoFila gerenciamento;
	FilaPrioritaria filaPrioritaria;
	FilaComum filaComum;
	Senha senha;
	Scanner sc = new Scanner(System.in);
	int opc;
	int totalSenhaNormal, totalSenhaPreferencial; //criei duas diferentes pensando que talvez possa ser util saber quantas de cada
	
	public Menu() {
		filaPrioritaria = new FilaPrioritaria();
		filaComum = new FilaComum();
		gerenciamento = new GerenciamentoFila();
	}
	
	public void menu() {
		
		do {
		    System.out.println("\n1. Gerar senha normal");
		    System.out.println("2. Gerar senha preferencial");
		    System.out.println("3. Chamar senha");
		    System.out.println("4. Listar senhas na fila ");
		    System.out.println("5. Gerar relatório");
		    System.out.println("6. Sair do programa");
		    System.out.println("Digite sua escolha: ");
		    opc = sc.nextInt();
		    
		    switch (opc) {
		        case 1:
		        	senha = new Senha("Comum","Ativa");
		        	filaComum.adicionar(senha);
		            System.out.println("\nSenha normal gerada.");
		            totalSenhaNormal++;
		            break;
		        case 2:
		        	senha = new Senha("Prioritaria","Ativa");
		        	filaPrioritaria.adicionar(senha);
		            System.out.println("\nsenha preferencial gerada.");
		            totalSenhaPreferencial++;
		            break;
		        case 3:
		        	Senha senhaChamada = gerenciamento.ordemChamada();
		        	if(senhaChamada == null) {
		        		System.out.println("Filas vazias!");
		        	}else {
		        		System.out.println("Chamando senha + "); // ainda não sei como fazer pra atribuir um valor de posição a cada senha e se realmente precisa
		        	}
		            break;
		        case 4:
		            System.out.println("\nListando senhas na fila...");
		            break;
		        case 5:
		            System.out.println("\nGerando relatório...");
		            break;
		        case 6:
		            System.out.println("\nSaindo do programa...");
		            break;
		        default:
		            System.out.println("\nOpção inválida. Tente novamente.");
		    }
		} while (opc != 6);

	}
}