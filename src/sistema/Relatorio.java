package sistema;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.lang.Math;



import estrutura_fila.GerenciamentoFila;
import estrutura_fila.Senha;

public class Relatorio {
	private int totalSenhas = 0;
    private int totalAtendimentos = 0;
    private int tempoEsperaMedioEmSegundos = 0;
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
        if (!(GerenciamentoFila.filaAtendidos.estaVazia() && GerenciamentoFila.filaSumidos.estaVazia())) {
            relatorio = new Relatorio();
            long somaMedia = 0;
            LocalDateTime tempTempoChegada;
            LocalDateTime tempTempoSaida;
            int contador;
            relatorio.totalSenhas = totalComum + totalPreferencial;
            relatorio.totalAtendimentos = GerenciamentoFila.filaAtendidos.tamanho();
            for (contador = 0; contador < GerenciamentoFila.filaAtendidos.tamanho(); contador++) {
                Senha tempSenha;
                tempSenha = GerenciamentoFila.filaAtendidos.fila.pegarNaPosicao(contador);
                tempTempoChegada = tempSenha.getHoraChegada();
                tempTempoSaida = tempSenha.getHoraSaida();
                Duration intervalo = Duration.between(tempTempoChegada, tempTempoSaida);
                somaMedia += intervalo.toSeconds();
            }
            relatorio.tempoEsperaMedioEmSegundos = (int)somaMedia / contador;
            relatorio.totalAusentes = GerenciamentoFila.filaSumidos.tamanho();
        }
        return relatorio;
    }

    public static void imprimirRelatorio (Relatorio r, int totalComum, int totalPreferencial) {
        if (r == null) {
            System.out.println("Não houve nenhuma tentativa de atendimento, logo o relatório está vazio.");
        } else {
            int total = totalComum + totalPreferencial;
            float percentoComum = (float)totalComum / total;
            float percentoPreferencial = (float)totalPreferencial / total;
            System.out.println ("Total de senhas geradas: " + r.totalSenhas 
            + " (~" + Math.round((percentoComum*100)) + "% Comum - ~" + Math.round((percentoPreferencial*100)) + "% Preferencial)");
            System.out.println ("Total de senhas atendidas: " + r.totalAtendimentos);
            System.out.println ("Tempo média de espera (Em Segundos): " + r.tempoEsperaMedioEmSegundos);
            System.out.println ("Total de senhas com ausência: " + r.totalAusentes);
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
            System.out.println ("Hora de criação do Relatório: " + LocalDateTime.now().format(formato));
        }
    }
}

// Número total de Senhas: XX (X% Comum - X% Preferencial)
// Número total de Atendimentos: XX
// Tempo médio de espera: ?
// Quantidade de senhas ausentes:
// Hora de criação do Relatório: 
