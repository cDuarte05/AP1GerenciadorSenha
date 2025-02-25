package estrutura_fila;

public class GerenciamentoFila {
	
	FilaPrioritaria filaPrioritaria = new FilaPrioritaria();
	FilaComum filaComum = new FilaComum();
	
	public boolean prioritariaVazia() {
		return filaPrioritaria.tamanho() == 0;
	}
	
	
}
