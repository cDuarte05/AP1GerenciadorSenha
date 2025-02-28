package sistema;

import java.util.Scanner;

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
	Senha senha;
	Scanner sc = new Scanner(System.in);
	int opc;
	int totalSenhaNormal, totalSenhaPreferencial; //criei duas diferentes pensando que talvez possa ser util saber quantas de cada
	
	public Menu() {
		filaPrioritaria = new FilaPrioritaria();
		filaComum = new FilaComum();
		gerenciamento = new GerenciamentoFila();
		filaSumidos = new FilaSumidos();
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
		        	Senha senhaChamada = gerenciamento.ordemChamada();
		        	if(!(senhaChamada == null)) {
		        		char resp;
		        		do {
			        		System.out.println("Chamando senha: " + senha + " Tipo: " + senhaChamada.getTipo()); // ainda não sei como fazer pra atribuir um valor de posição a cada senha e se realmente precisa
			        		System.out.println("A senha foi respondida? (s/n)");
			        		resp = sc.next().toLowerCase().charAt(0); //garantir que seja a primeira e minuscula
			        		if(resp == 's') {
			        			int tentativas = 0;
			        			tentativas++;
			        			senha.setTentativas(tentativas);
			        		}
			        		// ja aqui eu preciso também dar um jeito de remover e colocar numa outra classe nova chamada lista atendidos 
		        		}while(resp == 's' || (senha.getTentativas() <= 3));
		        		System.out.println("Senha não respondida!");
		        		filaSumidos.adicionar(senhaChamada);
		        		//preciso agora dar um jeito de remover a senha chamada estou pensando em usar o .contemItem e verificar o tipo e depois remover e colocar na classe FilaSumidos
		        	}else {
	        		System.out.println("Filas vazias!");
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