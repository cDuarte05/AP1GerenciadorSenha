package sistema;

import java.util.Scanner;

import estrutura_fila.GerenciamentoFila;
import estrutura_fila.Senha;

public class Menu {
	
	GerenciamentoFila gerenciamento;
	Senha senha;
	Scanner sc = new Scanner(System.in);
	int opc;
	int totalSenhaNormal, totalSenhaPreferencial; //criei duas diferentes pensando que talvez possa ser util saber quantas de cada -> vai ser util, ele pede o percentual de cada

	
	public Menu() {
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
		    try {
				opc = sc.nextInt();				
			} catch (Exception e) {
				System.out.println("Excessão:Linha: 34 Classe: Menu - " + e);
				break;
			}
			
		    
		    switch (opc) {
		        case 1:
		        	totalSenhaNormal++;
		        	senha = new Senha("Comum","Ativa",totalSenhaNormal);
		        	GerenciamentoFila.filaComum.adicionar(senha);
		            System.out.println("\nSenha normal gerada.");
		            break;
		            
		        case 2:
		        	totalSenhaPreferencial++;
		        	senha = new Senha("Prioritaria","Ativa",totalSenhaPreferencial);
		        	GerenciamentoFila.filaPrioritaria.adicionar(senha);
		            System.out.println("\nsenha preferencial gerada.");
		            break;
		            
		        case 3:
		        	Senha senhaChamada = gerenciamento.ordemChamada();
		        	if(!(senhaChamada == null)) {
		        		int tentativas = senhaChamada.getTentativas(); 
		        		char resp;
		        		do {
			        		System.out.println("Chamando senha: " + senhaChamada); 
			        		System.out.println("A senha foi respondida? (s/n)");
			        		resp = sc.next().toLowerCase().charAt(0); //garantir que seja a primeira e minuscula
			        		
			        		if(resp == 'n') {
			        			tentativas++;
			        			senhaChamada.setTentativas(tentativas);
			        		}
			        		
			        		if(resp =='s') {
			        			gerenciamento.atenderRemover(senhaChamada); //esse metodo vai remover da fila e colocar na fila de já atendido
			        			break;
			        		}
			        		
		        		} while (resp == 'n' && tentativas < 3); //esse do while tem algo errado -> a primeira tentativa é 0, segunda 1, terceira 2, ent devia só ser tentativa < 3
		        		
		        		if(tentativas >= 3) {
		        			gerenciamento.removerRealocarSumidos(senhaChamada);
		        		}
		        	} else {
		        		System.out.println("Filas vazias!");
		        	}		        		
		        	break;
		        	
		        case 4:
		            gerenciamento.listarSenhas();
		            break;
		        case 5:
					Relatorio relatorio = Relatorio.pegarInstancia();
					relatorio = Relatorio.gerarRelatorio(totalSenhaNormal, totalSenhaPreferencial);
					Relatorio.imprimirRelatorio(relatorio, totalSenhaNormal, totalSenhaPreferencial);
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