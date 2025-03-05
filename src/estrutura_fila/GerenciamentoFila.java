package estrutura_fila;

public class GerenciamentoFila { //singleton

	public static FilaPrioritaria filaPrioritaria;
	public static FilaComum filaComum;
	public static FilaSumidos filaSumidos;
	public static FilaAtendidos filaAtendidos;

	private static GerenciamentoFila instancia;
	
	private GerenciamentoFila() {
		filaPrioritaria = new FilaPrioritaria();
		filaComum = new FilaComum();
		filaSumidos = new FilaSumidos();
		filaAtendidos = new FilaAtendidos();
	}
	
	public static GerenciamentoFila pegarInstancia() {
		if (instancia == null) {
			instancia = new GerenciamentoFila();
		}
		return instancia; 
	}

	public static void inicializarFilas() {
		if (instancia == null) {
			instancia = new GerenciamentoFila();
		} 
	}

	public static boolean filasVazias() {
		return ((filaComum.estaVazia() && filaPrioritaria.estaVazia())); //retorna true se estão vazias
	}
		
	public static Senha ordemChamada() {
		if (!filasVazias()) {
			if(filaPrioritaria.estaVazia()) {
				return filaComum.fila.pegarNaPosicao(0);
			}else {
				return filaPrioritaria.fila.pegarNaPosicao(0);
			}
		}else {
			System.out.println("Filas vazias");
			return null;
		}
	}
	
	public static void atendidaPrioritaria(Senha senhaChamada) {
		senhaChamada.setStatus("Atendida");
		senhaChamada.setHoraSaida();
		filaPrioritaria.remover();
		filaAtendidos.adicionar(senhaChamada);
	}
	
	public static void atendidaComum(Senha senhaChamada) {
		senhaChamada.setStatus("Atendida");
		senhaChamada.setHoraSaida();
		filaAtendidos.adicionar(senhaChamada);
		filaComum.remover();
	}
	
	public static void atenderRemover(Senha senhaChamada) { //esse metodo serve para fazer a realocação de senhas 
		if(senhaChamada.getTipo() == "Prioritaria" ) { //confere se é prioritaria e se for, chama o metodo que muda o status, adiciona na fila de atendidos e remove.
			atendidaPrioritaria(senhaChamada);
		}else if(senhaChamada.getTipo() == "Comum" ) {
			atendidaComum(senhaChamada);
		}
	}
	
	public static void naoAtendiasPrioritaria() {
		Senha senha = filaPrioritaria.fila.pegarNaPosicao(0);
		int tentativas = senha.getTentativas();
		if(tentativas >= 3) {
			filaSumidos.adicionar(senha);
			filaPrioritaria.remover();
		}
	}
	
	public static void naoAtendiasComum() {
		Senha senha = filaComum.fila.pegarNaPosicao(0);
		int tentativas = senha.getTentativas();
		if(tentativas >= 3) {
			filaSumidos.adicionar(senha);
			filaComum.remover();
		}
	}
	
	public static void removerRealocarSumidos(Senha senhaChamada) { //esse metodo serve para fazer a realocação de senhas 
		senhaChamada.setStatus("Não atendida");
		if(senhaChamada.getTipo() == "Prioritaria" ) { //confere se é prioritaria e se for, chama o metodo que muda o status, adiciona na fila de atendidos e remove.
			naoAtendiasPrioritaria();
		}else if(senhaChamada.getTipo() == "Comum" ) {
			naoAtendiasComum();
		}
	}
	
	public static void listarSenhas() {
		int tam;
		if(!filaPrioritaria.estaVazia()) {
			System.out.println("Listando senhas na fila");
			System.out.println();
			System.out.println("Fila prioritaria: ");
			tam = filaPrioritaria.tamanho();
			for(int i = 0; i<tam; i++) {
				System.out.println(filaPrioritaria.fila.pegarNaPosicao(i));
			}
		}else System.out.println("Fila prioritaria vazia");
		
		if(!filaComum.estaVazia()) {
			System.out.println();
			System.out.println("Fila comum: ");
			tam = filaComum.tamanho();
			for(int i = 0; i<tam; i++) {
				System.out.println(filaComum.fila.pegarNaPosicao(i));
			} //falta testar -> parece que tá tudo funcionando doq você fez, mas tive que arrumar várias coisinhas de lógica
		}else System.out.println("Fila comum vazia");
	}
}
