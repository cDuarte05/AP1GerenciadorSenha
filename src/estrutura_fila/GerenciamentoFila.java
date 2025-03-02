package estrutura_fila;

public class GerenciamentoFila {

	FilaPrioritaria filaPrioritaria;
	FilaComum filaComum;
	FilaSumidos filaSumidos;
	FilaAtendidos filaAtendidos;
	
	public GerenciamentoFila() {
		filaPrioritaria = new FilaPrioritaria();
		filaComum = new FilaComum();
		filaSumidos = new FilaSumidos();
		filaAtendidos = new FilaAtendidos();
	}
	
	public boolean filasVazias() {
		return (!(filaComum.estaVazia() && filaPrioritaria.estaVazia())); //retorna true se não estão vazias
	}
		
	public Senha ordemChamada() {
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
	
	public void atendidaPrioritaria(Senha senhaChamada) {
		senhaChamada.setStatus("Atendida");
		filaAtendidos.adicionar(senhaChamada);
		filaPrioritaria.remover();
	}
	
	public void atendidaComum(Senha senhaChamada) {
		senhaChamada.setStatus("Atendida");
		filaAtendidos.adicionar(senhaChamada);
		filaComum.remover();
	}
	
	public void atenderRemover(Senha senhaChamada) { //esse metodo serve para fazer a realocação de senhas 
		if(senhaChamada.getTipo() == "Prioritaria" ) { //confere se é prioritaria e se for, chama o metodo que muda o status, adiciona na fila de atendidos e remove.
			atendidaPrioritaria(senhaChamada);
		}else if(senhaChamada.getTipo() == "Comum" ) {
			atendidaComum(senhaChamada);
		}
	}
	
	public void naoAtendiasPrioritaria() {
		Senha senha = filaPrioritaria.fila.pegarNaPosicao(0);
		int tentativas = senha.getTentativas();
		if(tentativas >3) {
			filaSumidos.adicionar(senha);
			filaPrioritaria.remover();
		}
	}
	
	public void naoAtendiasComum() {
		Senha senha = filaComum.fila.pegarNaPosicao(0);
		int tentativas = senha.getTentativas();
		if(tentativas >2) {
			filaSumidos.adicionar(senha);
			filaComum.remover();
		}
	}
	
	public void removerRealocarSumidos(Senha senhaChamada) { //esse metodo serve para fazer a realocação de senhas 
		senhaChamada.setStatus("Não atendida");
		if(senhaChamada.getTipo() == "Prioritaria" ) { //confere se é prioritaria e se for, chama o metodo que muda o status, adiciona na fila de atendidos e remove.
			naoAtendiasPrioritaria();
		}else if(senhaChamada.getTipo() == "Comum" ) {
			naoAtendiasComum();
		}
	}
	
	public void listarSenhas(FilaPrioritaria filaPrioritaria, FilaComum filaComum) {
		System.out.println("Listando senhas na fila");
		System.out.println("Fila prioritaria: ");
		int tam = filaPrioritaria.tamanho();
		for(int i = 0; i<=tam; i++) {
			System.out.println(filaPrioritaria.fila.pegarNaPosicao(i));
		}
		System.out.println();
		tam = filaComum.tamanho();
		for(int i = 0; i<=tam; i++) {
			System.out.println(filaComum.fila.pegarNaPosicao(i));
		} //falta testar
	}
}
