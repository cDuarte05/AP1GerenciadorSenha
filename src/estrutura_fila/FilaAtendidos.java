package estrutura_fila;

public class FilaAtendidos {
	    ListaLigada fila;

	    public FilaAtendidos(){
	        this.fila = new ListaLigada();
	    }
	    
	    public void adicionar (Senha dado){
	        fila.adicionarFim(dado);
	    }

	    public void remover(){

	        if (fila.tamanho() == 0){
	            throw new IllegalStateException("Fila vazia");
	        }
	        fila.removerInicio();
	    }

	    public int tamanho(){
	        return fila.tamanho();
	    }

	    public boolean estaVazia(){
	        return (fila.tamanho() == 0);
	    }
	    
	    public boolean contem (Senha dadoBuscado){
	        return fila.contem(dadoBuscado);
	    }

	    @Override
	    public String toString(){
	        return fila.toString();
	    }
}


