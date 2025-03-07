package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import estrutura_fila.GerenciamentoFila;
import estrutura_fila.Senha;

public class TesteGerenciamenteFila {

    
    @Test
    public void testePegarInstancia () {
        GerenciamentoFila gerenciamento = null;
        assertEquals(gerenciamento, null);
        gerenciamento = GerenciamentoFila.pegarInstancia();
        assertNotEquals(gerenciamento, null);
    }

    @Test
    public void testeInicializarFilas() {
        GerenciamentoFila.esvaziarFilas();
        assertEquals(GerenciamentoFila.filaAtendidos, null);
        assertEquals(GerenciamentoFila.filaComum, null);
        assertEquals(GerenciamentoFila.filaPrioritaria, null);
        assertEquals(GerenciamentoFila.filaSumidos, null);
        GerenciamentoFila.inicializarFilas();
        assertNotEquals(GerenciamentoFila.filaAtendidos, null);
        assertNotEquals(GerenciamentoFila.filaComum, null);
        assertNotEquals(GerenciamentoFila.filaPrioritaria, null);
        assertNotEquals(GerenciamentoFila.filaSumidos, null);
    }

    @Test
    public void testeFilasVaziasComFilasVazias () {
        GerenciamentoFila.inicializarFilas();
        assertTrue(GerenciamentoFila.filasVazias());
    }

    @Test
    public void testeFilasVaziasComFilaComumComElementos () {
        Senha senha = new Senha("Tipo","Status",1);
        GerenciamentoFila.inicializarFilas();
        GerenciamentoFila.filaComum.adicionar(senha);
        assertFalse(GerenciamentoFila.filasVazias());
    }

    @Test
    public void testeFilasVaziasComFilaPrioritariaComElementos () {
        Senha senha = new Senha("Tipo","Status",1);
        GerenciamentoFila.inicializarFilas();
        GerenciamentoFila.filaPrioritaria.adicionar(senha);
        assertFalse(GerenciamentoFila.filasVazias());
    }

    @Test
    public void testeFilasVaziasComFilasComElementos () {
        Senha senha = new Senha("Tipo","Status",1);
        GerenciamentoFila.inicializarFilas();
        GerenciamentoFila.filaPrioritaria.adicionar(senha);
        GerenciamentoFila.filaComum.adicionar(senha);
        assertFalse(GerenciamentoFila.filasVazias());
    }

    @Test
    public void testeOrdemChamadaFilasVazias () {
        GerenciamentoFila.inicializarFilas();
        assertEquals(GerenciamentoFila.ordemChamada(), null);
    }

    @Test
    public void testeOrdemChamadaFilaComumComElemento () {
        Senha senha = new Senha("Tipo","Status",1);
        GerenciamentoFila.inicializarFilas();
        GerenciamentoFila.filaComum.adicionar(senha);
        assertEquals(GerenciamentoFila.ordemChamada(), senha);
    }

    @Test
    public void testeOrdemChamadaFilaPrioritariaComElemento () {
        Senha senha = new Senha("Tipo","Status",1);
        GerenciamentoFila.inicializarFilas();
        GerenciamentoFila.filaPrioritaria.adicionar(senha);
        assertEquals(GerenciamentoFila.ordemChamada(), senha);
    }

    @Test
    public void testeOrdemChamadaFilasComElementos () {
        Senha senha = new Senha("Comum","Status",1);
        Senha senha2 = new Senha("Prioritaria","Status",2);
        GerenciamentoFila.inicializarFilas();
        GerenciamentoFila.filaComum.adicionar(senha);
        GerenciamentoFila.filaPrioritaria.adicionar(senha2);
        assertEquals(GerenciamentoFila.ordemChamada(), senha2);
        GerenciamentoFila.atenderRemover(GerenciamentoFila.ordemChamada());
        assertEquals(GerenciamentoFila.ordemChamada(), senha);
    }

    @Test
    public void testeAtenderFilasVazias () {
        Senha senha = new Senha("Comum","Status",1);
        GerenciamentoFila.inicializarFilas();
        assertThrows(IllegalStateException.class, ()->GerenciamentoFila.atenderRemover(senha));
    }

    @Test
    public void testeAtenderFilasComElementos () {
        Senha senha = new Senha("Comum","Status",1);
        Senha senha2 = new Senha("Prioritaria","Status",2);
        GerenciamentoFila.inicializarFilas();
        GerenciamentoFila.filaComum.adicionar(senha);
        GerenciamentoFila.filaPrioritaria.adicionar(senha2);
        assertFalse(GerenciamentoFila.filaPrioritaria.estaVazia());
        assertFalse(GerenciamentoFila.filaComum.estaVazia());
        assertEquals(GerenciamentoFila.ordemChamada(), senha2);
        GerenciamentoFila.atenderRemover(GerenciamentoFila.ordemChamada());
        assertTrue(GerenciamentoFila.filaAtendidos.contem(senha2));
        assertEquals(GerenciamentoFila.ordemChamada(), senha);
        assertTrue(GerenciamentoFila.filaPrioritaria.estaVazia());
        assertFalse(GerenciamentoFila.filaComum.estaVazia());
        GerenciamentoFila.atenderRemover(GerenciamentoFila.ordemChamada());
        assertTrue(GerenciamentoFila.filaAtendidos.contem(senha2));
        assertTrue(GerenciamentoFila.filaAtendidos.contem(senha));
        assertEquals(GerenciamentoFila.filaAtendidos.toString(),"[" + senha2.toString() + ", " + senha.toString() + "]");
        assertTrue(GerenciamentoFila.filaComum.estaVazia());
    }

    @Test
    public void testeRealocarSumidosSenhaNaoPresente () {
        Senha senha = new Senha("Comum","Status",1);
        Senha senha2 = new Senha("Prioritaria","Status",2);
        senha.setTentativas(3);
        senha2.setTentativas(3);
        GerenciamentoFila.inicializarFilas();
        assertThrows(IndexOutOfBoundsException.class, ()-> GerenciamentoFila.removerRealocarSumidos(senha));
        assertThrows(IndexOutOfBoundsException.class, ()-> GerenciamentoFila.removerRealocarSumidos(senha2));
        assertEquals(GerenciamentoFila.filaSumidos.toString(), "[]");
    }

    @Test
    public void testeRealocarSumidosSenhaPresente () {
        Senha senha = new Senha("Comum","Status",1);
        Senha senha2 = new Senha("Prioritaria","Status",2);
        senha.setTentativas(3);
        senha2.setTentativas(3);
        GerenciamentoFila.inicializarFilas();
        GerenciamentoFila.filaComum.adicionar(senha);
        GerenciamentoFila.filaPrioritaria.adicionar(senha2);
        assertTrue(GerenciamentoFila.filaSumidos.estaVazia());
        GerenciamentoFila.removerRealocarSumidos(senha);
        assertTrue(GerenciamentoFila.filaComum.estaVazia());
        assertTrue(GerenciamentoFila.filaSumidos.contem(senha));
        assertFalse(GerenciamentoFila.filaPrioritaria.estaVazia());
        GerenciamentoFila.removerRealocarSumidos(senha2);
        assertTrue(GerenciamentoFila.filaComum.estaVazia());
        assertTrue(GerenciamentoFila.filaPrioritaria.estaVazia());
        assertTrue(GerenciamentoFila.filaSumidos.contem(senha));
        assertTrue(GerenciamentoFila.filaSumidos.contem(senha2));
        assertEquals(GerenciamentoFila.filaSumidos.toString(), "[" + senha.toString() + ", " + senha2.toString() + "]");
    }
}
