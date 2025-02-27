package sistema;

import java.util.Scanner;

public class Main {
	
	public static void main (String[] args) {
		
		Menu menu = new Menu();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Bem vindo ao nosso sistem de gerenciamento de senha");
		menu.menu();
	}
}
