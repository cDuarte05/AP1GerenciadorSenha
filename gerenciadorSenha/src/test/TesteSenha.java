package test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import estrutura_fila.Senha;

public class TesteSenha {

    private Senha senhaTeste;

    @BeforeEach
    public void criarObjetoSenha() {
        senhaTeste = new Senha("tipo", "status",1);
    }

    @Test
    public void testeGetTipo () {
        assertEquals("tipo", senhaTeste.getTipo());
        assertNotEquals("tipoIncorreto", senhaTeste.getTipo());
    }

    @Test
    public void testeSetTipo () {
        assertEquals("tipo", senhaTeste.getTipo());
        senhaTeste.setTipo("novoTipo");
        assertNotEquals("tipo", senhaTeste.getTipo());
        assertEquals("novoTipo", senhaTeste.getTipo());
    }

    @Test
    public void testeGetTentativas () { // ao instanciar uma senha, o número de tentativas é por padrão zero
        assertEquals(0, senhaTeste.getTentativas());
        assertNotEquals(1, senhaTeste.getTentativas());
    }

    @Test
    public void testeSetTentativas () {
        assertEquals(0, senhaTeste.getTentativas());
        senhaTeste.setTentativas(5);
        assertNotEquals(0, senhaTeste.getTentativas());
        assertEquals(5, senhaTeste.getTentativas());
    }

    @Test
    public void testeGetStatus () {
        assertEquals("status", senhaTeste.getStatus());
        assertNotEquals("statusIncorreto", senhaTeste.getStatus());
    }

    @Test
    public void testeSetStatus () {
        assertEquals("status", senhaTeste.getStatus());
        senhaTeste.setStatus("novoStatus");
        assertNotEquals("status", senhaTeste.getStatus());
        assertEquals("novoStatus", senhaTeste.getStatus());
    }

    @Test
    public void testeGetHoraChegada () {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");    
        assertEquals(formato.format(LocalDateTime.now()), formato.format(senhaTeste.getHoraChegada()));
        // Usando um formato que só segue até segundos (e não milisegundos ou menor) devido a diferenças negligênciaiveis de tempo.
    }

    @Test
    public void testeSetGetHoraSaida () {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");  
        senhaTeste.setHoraSaida();
        assertEquals(formato.format(LocalDateTime.now()), formato.format(senhaTeste.getHoraSaida()));
        // Usando um formato que só segue até segundos (e não milisegundos ou menor) devido a diferenças negligênciaiveis de tempo.
    }

    @Test
    public void testeGetSenha () {
        assertEquals(1, senhaTeste.getNumeroSenha());
        assertNotEquals(5, senhaTeste.getNumeroSenha());
    }

    @Test
    public void testeToString () { //"Senha [" + numeroSenha + "] - " + tipo
        assertEquals("Senha [1] - tipo", senhaTeste.toString());
        senhaTeste.setTipo("novoTipo");
        assertNotEquals("Senha [1] - tipo", senhaTeste.toString());
        assertEquals("Senha [1] - novoTipo", senhaTeste.toString());
    }
}
