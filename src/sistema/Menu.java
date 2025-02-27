package sistema;

import java.util.Scanner;

public class Menu {
	
	Scanner sc = new Scanner(System.in);
	int opc;
	int totalSenhaNormal, totalSenhaPreferencial; //criei duas diferentes pensando que talvez possa ser util saber quantas de cada
	
	public void menu() {
		
		do {
		    System.out.println("\n1. Gerar senha normal");
		    System.out.println("2. Gerar senha preferencial");
		    System.out.println("3. Chamar senha normal");
		    System.out.println("4. Chamar senha preferencial");
		    System.out.println("5. Listar senhas na fila ");
		    System.out.println("6. Gerar relatório");
		    System.out.println("7. Sair do programa");
		    System.out.println("Digite sua escolha: ");
		    opc = sc.nextInt();
		    
		    switch (opc) {
		        case 1:
		            System.out.println("\nSenha normal gerada.");
		            totalSenhaNormal++;
		            break;
		        case 2:
		            System.out.println("\nGerando senha preferencial...");
		            totalSenhaPreferencial++;
		            break;
		        case 3:
		            System.out.println("\nChamando senha normal...");
		            break;
		        case 4:
		            System.out.println("\nChamando senha preferencial...");
		            break;
		        case 5:
		            System.out.println("\nListando senhas na fila...");
		            break;
		        case 6:
		            System.out.println("\nGerando relatório...");
		            break;
		        case 7:
		            System.out.println("\nSaindo do programa...");
		            break;
		        default:
		            System.out.println("\nOpção inválida. Tente novamente.");
		    }
		} while (opc != 7);

	}
}
