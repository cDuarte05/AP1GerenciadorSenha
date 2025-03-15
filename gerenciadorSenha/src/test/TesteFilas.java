package test;

import estrutura_fila.FilaAtendidos;
import estrutura_fila.FilaComum;
import estrutura_fila.FilaPrioritaria;
import estrutura_fila.FilaSumidos;
import estrutura_fila.Senha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TesteFilas {

	private FilaAtendidos filaAtendidos;
	private FilaComum filaComum;
	private FilaPrioritaria filaPrioritaria;
	private FilaSumidos filaSumidos;
	
	@BeforeEach
	public void iniciarFilas () {
		this.filaAtendidos = new FilaAtendidos();
		this.filaComum = new FilaComum();
		this.filaPrioritaria = new FilaPrioritaria();
		this.filaSumidos = new FilaSumidos();
	}

	@Test
	public void testeAdiconarFilaVazia () {
		Senha senha = new Senha("Tipo","Status",1);
		filaAtendidos.adicionar(senha);
		filaComum.adicionar(senha);
		filaPrioritaria.adicionar(senha);
		filaSumidos.adicionar(senha);
		
		assertTrue(filaAtendidos.contem(senha));
		assertTrue(filaComum.contem(senha));
		assertTrue(filaPrioritaria.contem(senha));
		assertTrue(filaSumidos.contem(senha));
	}

	@Test
	public void testeAdiconarFilaComElementos () {
		Senha senha = new Senha("Tipo","Status",1);
		Senha senha2 = new Senha("Tipo","Status",2);
		filaAtendidos.adicionar(senha);
		filaComum.adicionar(senha);
		filaPrioritaria.adicionar(senha);
		filaSumidos.adicionar(senha);
		
		assertTrue(filaAtendidos.contem(senha));
		assertTrue(filaComum.contem(senha));
		assertTrue(filaPrioritaria.contem(senha));
		assertTrue(filaSumidos.contem(senha));
		
		filaAtendidos.adicionar(senha2);
		filaComum.adicionar(senha2);
		filaPrioritaria.adicionar(senha2);
		filaSumidos.adicionar(senha2);

		assertEquals(filaAtendidos.toString(),"[" + senha.toString() + ", " + senha2.toString() + "]");
		assertEquals(filaComum.toString(),"[" + senha.toString() + ", " + senha2.toString() + "]");
		assertEquals(filaPrioritaria.toString(),"[" + senha.toString() + ", " + senha2.toString() + "]");
		assertEquals(filaSumidos.toString(),"[" + senha.toString() + ", " + senha2.toString() + "]");
	}

	@Test
	public void removerFilaVazia () {
		assertThrows(IllegalStateException.class, ()->filaAtendidos.remover());
		assertThrows(IllegalStateException.class, ()->filaComum.remover());
		assertThrows(IllegalStateException.class, ()->filaPrioritaria.remover());
		assertThrows(IllegalStateException.class, ()->filaSumidos.remover());
	}

	@Test
	public void removerFilaComElementos () {
		Senha senha = new Senha("Tipo","Status",1);
		Senha senha2 = new Senha("Tipo","Status",2);
		filaAtendidos.adicionar(senha);
		filaComum.adicionar(senha);
		filaPrioritaria.adicionar(senha);
		filaSumidos.adicionar(senha);
		filaAtendidos.adicionar(senha2);
		filaComum.adicionar(senha2);
		filaPrioritaria.adicionar(senha2);
		filaSumidos.adicionar(senha2);
		assertEquals(filaAtendidos.toString(),"[" + senha.toString() + ", " + senha2.toString() + "]");
		assertEquals(filaComum.toString(),"[" + senha.toString() + ", " + senha2.toString() + "]");
		assertEquals(filaPrioritaria.toString(),"[" + senha.toString() + ", " + senha2.toString() + "]");
		assertEquals(filaSumidos.toString(),"[" + senha.toString() + ", " + senha2.toString() + "]");
		filaAtendidos.remover();
		filaComum.remover();
		filaPrioritaria.remover();
		filaSumidos.remover();
		assertEquals(filaAtendidos.toString(),"[" + senha2.toString() + "]");
		assertEquals(filaComum.toString(),"[" + senha2.toString() + "]");
		assertEquals(filaPrioritaria.toString(),"[" + senha2.toString() + "]");
		assertEquals(filaSumidos.toString(),"[" + senha2.toString() + "]");
	}

	@Test
	public void testeTamanhoFilaVazia() {
		assertEquals(0, filaAtendidos.tamanho());
		assertEquals(0, filaComum.tamanho());
		assertEquals(0, filaPrioritaria.tamanho());
		assertEquals(0, filaSumidos.tamanho());
	}

	@Test
	public void testeTamanhoFilaComElementos() {
		Senha senha = new Senha("Tipo","Status",1);
		Senha senha2 = new Senha("Tipo","Status",2);
		filaAtendidos.adicionar(senha);
		filaComum.adicionar(senha);
		filaPrioritaria.adicionar(senha);
		filaSumidos.adicionar(senha);
		filaAtendidos.adicionar(senha2);
		filaComum.adicionar(senha2);
		filaPrioritaria.adicionar(senha2);
		filaSumidos.adicionar(senha2);
		assertEquals(2, filaAtendidos.tamanho());
		assertEquals(2, filaComum.tamanho());
		assertEquals(2, filaPrioritaria.tamanho());
		assertEquals(2, filaSumidos.tamanho());
	}

	@Test
	public void testeEstaVaziaSemElementos() {
		assertTrue(filaAtendidos.estaVazia());
		assertTrue(filaComum.estaVazia());
		assertTrue(filaPrioritaria.estaVazia());
		assertTrue(filaSumidos.estaVazia());
	}

	@Test
	public void testeEstaVaziaComElementos() {
		Senha senha = new Senha("Tipo","Status",1);
		filaAtendidos.adicionar(senha);
		filaComum.adicionar(senha);
		filaPrioritaria.adicionar(senha);
		filaSumidos.adicionar(senha);
		assertFalse(filaAtendidos.estaVazia());
		assertFalse(filaComum.estaVazia());
		assertFalse(filaPrioritaria.estaVazia());
		assertFalse(filaSumidos.estaVazia());
	}

	@Test
	public void testeContemSemElementoBuscado() {
		Senha senha = new Senha("Tipo","Status",1);
		Senha senha2 = new Senha("Tipo","Status",2);
		filaAtendidos.adicionar(senha);
		filaComum.adicionar(senha);
		filaPrioritaria.adicionar(senha);
		filaSumidos.adicionar(senha);
		assertFalse(filaAtendidos.contem(senha2));
		assertFalse(filaComum.contem(senha2));
		assertFalse(filaPrioritaria.contem(senha2));
		assertFalse(filaSumidos.contem(senha2));
	}

	@Test
	public void testeContemComElementoBuscado() {
		Senha senha = new Senha("Tipo","Status",1);
		filaAtendidos.adicionar(senha);
		filaComum.adicionar(senha);
		filaPrioritaria.adicionar(senha);
		filaSumidos.adicionar(senha);
		assertTrue(filaAtendidos.contem(senha));
		assertTrue(filaComum.contem(senha));
		assertTrue(filaPrioritaria.contem(senha));
		assertTrue(filaSumidos.contem(senha));
	}
}
