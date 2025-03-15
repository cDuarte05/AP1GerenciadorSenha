package estrutura_fila;

public class GerenciamentoFila { //singleton

	public static FilaPrioritaria filaPrioritaria = null;
	public static FilaComum filaComum = null;
	public static FilaSumidos filaSumidos = null;
	public static FilaAtendidos filaAtendidos = null;

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

	public static void inicializarFilas () {
		instancia = new GerenciamentoFila();
	}

	public static void esvaziarFilas () {
		filaAtendidos = null;
		filaComum = null;
		filaPrioritaria = null;
		filaSumidos = null;
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
		filaAtendidos.adicionar(senhaChamada);
		filaPrioritaria.remover();
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
	
	public static void naoAtendidasPrioritaria() {
		Senha senha = filaPrioritaria.fila.pegarNaPosicao(0);
		int tentativas = senha.getTentativas();
		if(tentativas >= 3) {
			filaSumidos.adicionar(senha);
			filaPrioritaria.remover();
		}
	}
	
	public static void naoAtendidasComum() {
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
			naoAtendidasPrioritaria();
		}else if(senhaChamada.getTipo() == "Comum" ) {
			naoAtendidasComum();
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
			} 
		}else System.out.println("Fila comum vazia");
	}
}
