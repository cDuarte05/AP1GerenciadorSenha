package sistema;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.lang.Math;

import estrutura_fila.GerenciamentoFila;
import estrutura_fila.Senha;

public class Relatorio { //singleton
	private int totalSenhas = 0;
    private int totalAtendimentos = 0;
    private float tempoEsperaMedioEmSegundos = 0;
    private int totalAusentes = 0;

    private Relatorio () {} // Essa classe segue o padrão de projeto "Singleton" (Só se pode ter um único objeto dessa classe), se tu n entender, recomendo checar o material de LP3 do Ledel
    static Relatorio instancia;

    public static Relatorio pegarInstancia () {
        if (instancia == null) {
            instancia = new Relatorio();
        } 
        return instancia;
    }

    public static Relatorio gerarRelatorio(int totalComum, int totalPreferencial) {
        Relatorio relatorio = null;
        if (!(GerenciamentoFila.filaAtendidos.estaVazia() && GerenciamentoFila.filaSumidos.estaVazia())) { //checa se o usuário atendou ou no mínimo tentou atender pelo menos uma pessoa
            relatorio = new Relatorio();
            long somaMedia = 0;
            LocalDateTime tempTempoChegada;
            LocalDateTime tempTempoSaida;
            int contador;
            relatorio.totalSenhas = totalComum + totalPreferencial; // soma total de senhas
            relatorio.totalAtendimentos = GerenciamentoFila.filaAtendidos.tamanho(); // número total de atendimentos
            for (contador = 0; contador < GerenciamentoFila.filaAtendidos.tamanho(); contador++) { // calculo de tempo médio para atendimento
                Senha tempSenha;
                tempSenha = GerenciamentoFila.filaAtendidos.fila.pegarNaPosicao(contador);
                tempTempoChegada = tempSenha.getHoraChegada();
                tempTempoSaida = tempSenha.getHoraSaida();
                Duration intervalo = Duration.between(tempTempoChegada, tempTempoSaida);
                somaMedia += intervalo.toSeconds();
            }
            relatorio.tempoEsperaMedioEmSegundos = (float)(somaMedia / (contador+1)); // parte final do calculo de tempo médio
            relatorio.totalAusentes = GerenciamentoFila.filaSumidos.tamanho(); // número total de pessoas que não compareceram ao chamado da sua senha três vezes
        }
        return relatorio;
    }

    public static void imprimirRelatorio (Relatorio r, int totalComum, int totalPreferencial) {
        if (r == null) {
            System.out.println("Não houve nenhum atendimento, ou tentative de, na data de hoje."); // a função que gera relatório retorna null caso o usuário não tenha atendido ou tentato atender ninguém
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss"); 
            System.out.println ("Hora e data de criação do Relatório: " + LocalDateTime.now().format(formato)); // data de geração do relatório 
        } else {
            int total = totalComum + totalPreferencial;
            float percentoComum = (float)totalComum / total; // cálculo percentual de pessoas com senhas comuns
            float percentoPreferencial = (float)totalPreferencial / total; // cálculo percentual de pessoas com senhas preferenciais
            System.out.println ("Total de senhas geradas: " + r.totalSenhas 
            + " (~" + Math.round((percentoComum*100)) + "% Comum - ~" + Math.round((percentoPreferencial*100)) + "% Preferencial)");
            System.out.println ("Total de senhas atendidas: " + r.totalAtendimentos);
            System.out.println ("Tempo média de espera (Em Segundos): " + Math.round(r.tempoEsperaMedioEmSegundos));
            System.out.println ("Total de senhas com ausência: " + r.totalAusentes);
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss"); 
            System.out.println ("Hora de criação do Relatório: " + LocalDateTime.now().format(formato)); // data de geração do relatório 
        }
    }
}
// Formato do Relatório ->
// Número total de Senhas: X (X% Comum - X% Preferencial)
// Número total de Atendimentos: X
// Tempo médio de espera: X segundos
// Quantidade de senhas ausentes: X
// Hora de criação do Relatório: dd/mm/aaaa - hh:mm:ss
