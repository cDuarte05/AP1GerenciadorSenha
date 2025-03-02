package sistema;

import java.util.Scanner;

import estrutura_fila.FilaAtendidos;
import estrutura_fila.FilaComum;
import estrutura_fila.FilaPrioritaria;
import estrutura_fila.FilaSumidos;
import estrutura_fila.GerenciamentoFila;
import estrutura_fila.Senha;

public class Menu {
	
	GerenciamentoFila gerenciamento;
	FilaPrioritaria filaPrioritaria;
	FilaComum filaComum;
	FilaSumidos filaSumidos;
	FilaAtendidos filaAtendidos;
	Senha senha;
	Scanner sc = new Scanner(System.in);
	int opc;
	int totalSenhaNormal, totalSenhaPreferencial; //criei duas diferentes pensando que talvez possa ser util saber quantas de cada

	
	public Menu() {
		filaPrioritaria = new FilaPrioritaria();
		filaComum = new FilaComum();
		gerenciamento = new GerenciamentoFila();
		filaSumidos = new FilaSumidos();
		filaAtendidos = new FilaAtendidos();
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
		        	totalSenhaNormal++;
		        	senha = new Senha("Comum","Ativa",totalSenhaNormal);
		        	filaComum.adicionar(senha);
		            System.out.println("\nSenha normal gerada.");
		            break;
		        case 2:
		        	totalSenhaPreferencial++;
		        	senha = new Senha("Prioritaria","Ativa",totalSenhaPreferencial);
		        	filaPrioritaria.adicionar(senha);
		            System.out.println("\nsenha preferencial gerada.");
		            break;
		        case 3:
		        	Senha senhaChamada = gerenciamento.ordemChamada(filaPrioritaria,filaComum);
		        	if(!(senhaChamada == null)) {
		        		int tentativas = senhaChamada.getTentativas(); 
		        		char resp;
		        		do {
			        		System.out.println("Chamando senha: " + senha); 
			        		System.out.println("A senha foi respondida? (s/n)");
			        		resp = sc.next().toLowerCase().charAt(0); //garantir que seja a primeira e minuscula
			        		
			        		if(resp == 'n') {
			        			tentativas++;
			        			senha.setTentativas(tentativas);
			        		}
			        		
			        		if(resp =='s') {
			        			gerenciamento.atenderRemover(senhaChamada); //esse metodo vai remover da fila e colocar na fila de já atendido
			        		}
			        		
		        		}while (tentativas < 4); //esse do while tem algo errado
		        		
		        		gerenciamento.removerRealocarSumidos(senhaChamada);
		        		
		        	}else {
		        		System.out.println("Filas vazias!");
		        	}		        		
		        	break;
		        	
		        case 4:
		            gerenciamento.listarSenhas(filaPrioritaria, filaComum);
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