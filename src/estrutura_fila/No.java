package estrutura_fila;

public class No {

    Senha dado;
    No proximo;
    No anterior;

    public No (Senha dado){
        this.dado = dado;
        this.proximo = null;
        this.anterior = null;
    }
}