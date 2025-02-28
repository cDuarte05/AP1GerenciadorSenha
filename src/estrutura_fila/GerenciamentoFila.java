package estrutura_fila;

public class GerenciamentoFila {

	FilaPrioritaria filaPrioritaria;
	FilaComum filaComum;
	FilaSumidos filaSumidos;
	
	public GerenciamentoFila() {
		filaPrioritaria = new FilaPrioritaria();
		filaComum = new FilaComum();
		filaSumidos = new FilaSumidos();
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
	
	
	public void naoAtendiasPrioritaria() {
		Senha senha = filaPrioritaria.fila.pegarNaPosicao(0);
		int tentativas = senha.getTentativas();
		if(tentativas >2) {
			filaSumidos.adicionar(senha);
			filaPrioritaria.remover();
			//remover a senha da fila
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
	
}
