package estrutura_fila;

public class ListaLigada {
    private No cabeca;
    private No cauda;
    private int tamanho;

    public ListaLigada(){
        this.cabeca = null;
        this.cauda = null;
        this.tamanho = 0;
    }

    public void adicionarInicio(Senha dado){

        No novoNo = new No(dado);
        if (cabeca == null){
            cabeca = novoNo;
            cauda = novoNo;
        }
        else {
            novoNo.proximo = cabeca;
            cabeca.anterior = novoNo;
            cabeca = novoNo;
        }
        tamanho++;
    }
    public void adicionarFim(Senha dado){
        No novoNo = new No(dado);
        if (cabeca == null){
            cabeca = novoNo;
            cauda = novoNo;
        }
        else{
            cauda.proximo = novoNo;
            novoNo.anterior = cauda;
            cauda = novoNo;
        }
        tamanho++;
    }

    public void adicionarNaPosicao(Senha dado, int posicao){

        if (posicao < 0 || posicao > tamanho){
            throw new IndexOutOfBoundsException("Posicao invalida: " + posicao);
        }
        
        if (posicao == 0){
            adicionarInicio(dado);
            return;
        }
        if (posicao == tamanho){
            adicionarFim(dado);
            return;
        }

        No novoNo = new No(dado);
        No atual = cabeca;
        for (int i=0; i<posicao; i++){
           atual = atual.proximo; 
        }
        novoNo.proximo = atual;
        novoNo.anterior = atual.anterior;
        atual.anterior.proximo = novoNo;
        atual.anterior = novoNo;
        tamanho++;
    }

    public void removerNaPosicao(int posicao){

        if (posicao < 0 || posicao >= tamanho){
            throw new IndexOutOfBoundsException("Posicao invalida: " + posicao);
        }
        if (posicao == 0){
            removerInicio();
            return;
        }
        if (posicao == (tamanho - 1)){
            removerFim();
            return;
        }
        
        No atual = cabeca;
        for(int i=0; i < posicao; i++){
            atual = atual.proximo;
        }

        atual.anterior.proximo = atual.proximo;
        atual.proximo.anterior = atual.anterior;
        tamanho--;

    }


    public void removerInicio(){

        if (cabeca == null){ //vazia
            return;
        }
        if (cabeca == cauda){ //so um elemento
            cabeca = null;
            cauda = null;
        }
        else{  // mais de um elemento
            cabeca = cabeca.proximo;
            cabeca.anterior = null;
        }
        tamanho--;
    }

    public void removerFim(){
        if (cabeca == null){ //vazia
            return;
        }
        if (cabeca == cauda){ //so um elemento
            cabeca = null;
            cauda = null;
        }
        else { // mais de um elemento
            cauda = cauda.anterior;
            cauda.proximo = null;
        }
        tamanho--;
    }

    public boolean contem (Senha dadoBuscado){

        No atual = cabeca;
        while (atual !=null) {
            if (atual.dado == dadoBuscado){
                return true;
            }
            atual = atual.proximo;
        }
        return false;
    }

    public Senha pegarNaPosicao(int posicao){

        if (posicao < 0 || posicao >= tamanho){
            throw new IndexOutOfBoundsException("Posicao invalida: " + posicao);
        }
        No atual = cabeca;
        for(int i=0; i < posicao; i++){
            atual = atual.proximo;
        }
        return atual.dado;
    }

    public int tamanho(){
        return tamanho;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        No atual = cabeca;
        while (atual != null) {
            sb.append(atual.dado);
            if (atual.proximo != null) {
                sb.append(", ");
            }
            atual = atual.proximo;
        }
        sb.append("]");
        return sb.toString();
    } 

}

