package estrutura_fila;

public class GerenciamentoFila {

	FilaPrioritaria filaPrioritaria;
	FilaComum filaComum;
	
	public GerenciamentoFila() {
		filaPrioritaria = new FilaPrioritaria();
		filaComum = new FilaComum();
	}
	
	public boolean prioritariaVazia() {
		return filaPrioritaria.tamanho() == 0;
	}
	
	public void naoAtendiasPrioritaria() {
		Senha senha = filaPrioritaria.fila.pegarNaPosicao(0);
		int tentativas = senha.getTentativas();
		if(tentativas >2) {
			//realocar a senha para a filaSUmidos
			//remover a senha da fila
		}
	}
	
	public void naoAtendiasComum() {
		Senha senha = filaComum.fila.pegarNaPosicao(0);
		int tentativas = senha.getTentativas();
		if(tentativas >2) {
			//realocar a senha para a filaSUmidos
			//remover a senha da fila
		}
	}
}
